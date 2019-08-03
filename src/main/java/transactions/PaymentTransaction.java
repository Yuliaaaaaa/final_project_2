package transactions;

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
import java.time.Instant;
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
            subscription.setPaid(true);
            long now = new Date().getTime();
            subscription.setOrderDate(new Timestamp(now));
            subscriptionRepository.add(subscription, connection);
            payment.setPaymentDate(new Timestamp(now));
            paymentRepository.add(payment, connection);
            connection.commit();
            PaymentDetail paymentDetail = new PaymentDetail();
            paymentDetail.setSubscriptionId(subscriptionRepository.getLast(connection).getSubscriptionId());
            paymentDetail.setPaymentId(paymentRepository.getLast(connection).getPaymentId());
            paymentDetailsRepository.add(paymentDetail, connection);
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
            payment.setPaymentDate(new Timestamp(now));
            paymentRepository.add(payment, connection);
            connection.commit();
            int paymentId = paymentRepository.getLast(connection).getPaymentId();
            subscriptions.stream()
                    .forEach(subscription -> {
                        subscription.setPaid(true);
                        subscription.setOrderDate(new Timestamp(now));
                        try {
                        subscriptionRepository.update(subscription, connection);
                            PaymentDetail paymentDetail = new PaymentDetail();
                            paymentDetail.setSubscriptionId(subscription.getSubscriptionId());
                            paymentDetail.setPaymentId(paymentId);
                            paymentDetailsRepository.add(paymentDetail, connection);
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

}
