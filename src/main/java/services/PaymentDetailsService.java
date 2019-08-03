package services;

import models.PaymentDetail;
import org.apache.log4j.Logger;
import repositories.PaymentDetailsRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 03.08.2019
 */
public class PaymentDetailsService extends Service<PaymentDetail> {
    private static final PaymentDetailsService paymentDetailsService =  new PaymentDetailsService();
    private static final Logger logger = Logger.getLogger(PaymentDetailsService.class);

    /**
     *
     */
    private PaymentDetailsService() {
        super(new PaymentDetailsRepository());
    }

    /**
     * @return
     */
    public static PaymentDetailsService getPaymentDetailsService() {
        return paymentDetailsService;
    }

    /**
     * @param paymentId
     * @return
     * @throws SQLException
     */
    public List<PaymentDetail> getAllByPaymentId(int paymentId) throws SQLException {
        SubscriptionService subscriptionService = SubscriptionService.getSubscriptionService();
        List<PaymentDetail> paymentDetails = ((PaymentDetailsRepository) repository)
                .getAllByPaymentId(paymentId);
        paymentDetails.stream()
                .peek(paymentDetail -> {
                    try {
                        paymentDetail.setSubscription(subscriptionService
                                .getOneById(paymentDetail.getSubscriptionId()));
                    } catch (SQLException e) {
                        logger.error("Could not get subscription for payment detail " + paymentDetail.getDetailsId());
                    }
                });
        return paymentDetails;
    }
}
