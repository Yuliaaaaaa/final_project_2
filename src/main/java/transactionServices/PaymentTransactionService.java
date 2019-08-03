package transactionServices;


import models.Payment;
import models.Subscription;
import transactions.PaymentTransaction;

import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 03.08.2019
 * @project publishing
 */
public class PaymentTransactionService {
    private static final PaymentTransactionService paymentTransactionService = new PaymentTransactionService();
    private static final PaymentTransaction paymentTransaction = new PaymentTransaction();

    /**
     *
     */
    private PaymentTransactionService() {
    }

    /**
     * @return
     */
    public static PaymentTransactionService getPaymentTransactionService() {
        return paymentTransactionService;
    }

    /**
     * @param subscription
     * @param payment
     * @throws SQLException
     */
    public void payImmediately(Subscription subscription, Payment payment) throws SQLException {
        paymentTransaction.payImmediately(subscription, payment);
    }
}
