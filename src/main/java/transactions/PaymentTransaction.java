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
            Instant now = Instant.now();
            subscription.setOrderDate(Timestamp.from(now));
            subscriptionRepository.add(subscription, connection);
            payment.setPaymentDate(Timestamp.from(now));
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
        //connection.close();
    }

}
