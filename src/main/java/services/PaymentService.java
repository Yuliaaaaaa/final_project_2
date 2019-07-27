package services;

import models.Payment;
import repositories.PaymentRepository;
import repositories.Repository;

/**
 * @author Yuliia Shcherbakova ON 26.07.2019
 * @project publishing
 */
public class PaymentService extends Service<Payment> {
    private static final PaymentService paymentService = new PaymentService();

    private PaymentService() {
        super(new PaymentRepository());
    }

    public static PaymentService getPaymentService() {
        return paymentService;
    }
}
