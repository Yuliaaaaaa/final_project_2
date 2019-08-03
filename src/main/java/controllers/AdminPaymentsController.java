package controllers;

import commonlyUsedStrings.PageLocation;
import models.Payment;
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
        Object admin = req.getSession().getAttribute("admin");
        if (admin == null)
            return PageLocation.NOT_AUTHORISED;
        List<Payment> payments = paymentService.getAll();
        int startIndex = (int) req.getAttribute("startIndex");
        if (startIndex == 0)
            req.setAttribute("start", true);
        if(payments.size() - startIndex <= 10)
            req.setAttribute("end", true);
        payments = payments.stream().skip(startIndex).limit(10).collect(Collectors.toList());
        req.setAttribute("payments", payments);
        return PageLocation.ADMIN_PAYMENTS;
    }
}
