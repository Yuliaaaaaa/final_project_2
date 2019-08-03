package services;

import facade.UserFacade;
import models.Payment;
import org.apache.log4j.Logger;
import repositories.PaymentDetailsRepository;
import repositories.PaymentRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuliia Shcherbakova ON 26.07.2019
 * @project publishing
 */
public class PaymentService extends Service<Payment> {
    private static final PaymentService paymentService = new PaymentService();
    private static final Logger logger = Logger.getLogger(PaymentService.class);

    /**
     *
     */
    private PaymentService() {
        super(new PaymentRepository());
    }

    /**
     * @return
     */
    public static PaymentService getPaymentService() {
        return paymentService;
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<Payment> getAll() throws SQLException {
        PaymentDetailsService paymentDetailsService = PaymentDetailsService.getPaymentDetailsService();
        UserFacade userFacade = UserFacade.getUserFacade();
        return super.getAll()
                .stream()
                .peek(payment -> {
                    int paymentId = payment.getPaymentId();
                    try {
                        payment.setPaymentDetails(paymentDetailsService
                                .getAllByPaymentId(paymentId));
                        int userId = payment.getUserId();
                        payment.setUser(userFacade.
                                getOneById(userId));
                    } catch (SQLException e) {
                        logger.error("Could not get payment delails for payment with id " + paymentId);
                    }
                }).collect(Collectors.toList());
    }
}
