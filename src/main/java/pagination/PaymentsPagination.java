package pagination;

import models.Payment;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class PaymentsPagination implements Pagination<Payment> {
    private static final PaymentsPagination pagination = new PaymentsPagination();

    private PaymentsPagination() {
    }

    public static PaymentsPagination getPagination() {
        return pagination;
    }
}
