package controllers;

import commonlyUsedStrings.PageLocation;
import dtos.SecureUser;
import models.Payment;
import pagination.PaymentsPagination;
import services.PaymentService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
    public String doGet(HttpServletRequest req) throws SQLException {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        if (user == null || !user.isAdmin())
            return PageLocation.NOT_AUTHORISED;
        List<Payment> payments = paymentService.getAll();
        payments = PaymentsPagination.getPagination()
                .getElements(req, payments);
        req.setAttribute("payments", payments);
        return PageLocation.ADMIN_PAYMENTS;
    }
}
