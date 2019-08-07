package transactions;

import enums.Periodicity;
import factories.DateFactory;
import factories.PeriodicityFactory;
import jdbc.ConnectionPool;
import models.Payment;
import models.PaymentDetail;
import models.Subscription;
import org.apache.log4j.Logger;
import repositories.PaymentDetailsRepository;
import repositories.PaymentRepository;
import repositories.SubscriptionRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 03.08.2019
 * @project publishing
 */
public class PaymentTransaction {

    private static final SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
    private static final PaymentRepository paymentRepository = new PaymentRepository();
    private static final PaymentDetailsRepository paymentDetailsRepository = new PaymentDetailsRepository();

    private static final Logger logger = Logger.getLogger(PaymentTransaction.class);

    /**
     * @param subscription
     * @param payment
     * @throws SQLException
     */
    public void payImmediately(Subscription subscription, Payment payment) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        try {
            logger.info("Immediate pay transaction started!");
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setSavepoint();
            long now = new Date().getTime();
            Timestamp startDate = new Timestamp(now);
            subscriptionPreparing(startDate, subscription);
            subscriptionRepository.add(subscription, connection);
            payment.setPaymentDate(startDate);
            paymentRepository.add(payment, connection);
            connection.commit();
            int paymentId = paymentRepository.getLast(connection).getPaymentId();
            int subscriptionId = subscriptionRepository.getLast(connection).getSubscriptionId();
            addPaymentDetail(connection, paymentId, subscriptionId);
            connection.commit();
            logger.info("Immediate pay transaction success!");
        } catch (SQLException e) {
            connection.rollback();
            connection.setAutoCommit(true);
            logger.error("Immediate pay transaction fail!");
            throw e;
        }
        connection.close();
    }

    public void payFromCart(List<Subscription> subscriptions, Payment payment) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        try {
            logger.info("Pay transaction started!");
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setSavepoint();
            long now = new Date().getTime();
            Timestamp startDate = new Timestamp(now);
            payment.setPaymentDate(startDate);
            paymentRepository.add(payment, connection);
            connection.commit();
            int paymentId = paymentRepository.getLast(connection).getPaymentId();
            subscriptions.stream()
                    .forEach(subscription -> {
                        subscriptionPreparing(startDate, subscription);
                        try {
                        subscriptionRepository.update(subscription, connection);
                            int subscriptionId = subscription.getSubscriptionId();
                            addPaymentDetail(connection, paymentId, subscriptionId);
                        } catch (SQLException e) {
                            try {
                                connection.rollback();
                                connection.setAutoCommit(true);
                            } catch (SQLException ex) {
                                logger.error("Rolling back failed!");
                            }
                            logger.error("Payment Detail was not added");
                        }
                    });
            connection.commit();
            logger.info("Pay transaction success!");
        } catch (SQLException e) {
            connection.rollback();
            connection.setAutoCommit(true);
            logger.error("Pay transaction fail!");
            throw e;
        }
        connection.close();
    }

    /**
     * @param connection
     * @param paymentId
     * @param subscriptionId
     * @throws SQLException
     */
    private void addPaymentDetail(Connection connection, int paymentId, int subscriptionId) throws SQLException {
        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setSubscriptionId(subscriptionId);
        paymentDetail.setPaymentId(paymentId);
        paymentDetailsRepository.add(paymentDetail, connection);
    }

    /**
     * @param startDate
     * @param subscription
     */
    private void subscriptionPreparing(Timestamp startDate, Subscription subscription) {
        subscription.setPaid(true);
        subscription.setStartDate(startDate);
        int issuesQuantity = subscription.getIssuesQuantity();
        Periodicity periodicity = PeriodicityFactory.
                getPeriodicity(subscription.getEdition().getPeriodicity());
        subscription.setExpireDate(DateFactory
                .getExpiredDate(startDate, periodicity, issuesQuantity));
    }

}
