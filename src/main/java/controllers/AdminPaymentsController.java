package controllers;

import commonlyUsedStrings.PageLocation;
import services.PaymentService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuliia Shcherbakova ON 26.07.2019
 * @project publishing
 */
public class AdminPaymentsController implements GetMethodController {
    private static final PaymentService paymentService = PaymentService.getPaymentService();

    /**
     * @param req
     * @return
     */
    public String doGet(HttpServletRequest req) {
        return PageLocation.ADMIN_PAYMENTS;
    }
}
