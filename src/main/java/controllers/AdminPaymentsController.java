package controllers;

import commonlyUsedStrings.ErrorMessage;
import commonlyUsedStrings.PageLocation;
import dtos.SecureUser;
import exceptionHandling.exceptions.NotAuthorisedException;
import exceptionHandling.validators.AuthorisationValidator;
import models.Payment;
import org.apache.log4j.Logger;
import pagination.PaymentsPagination;
import services.PaymentService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 26.07.2019
 * @project publishing
 */
public class AdminPaymentsController implements GetMethodController {
    private static final PaymentService paymentService = PaymentService.getPaymentService();
    private static final Logger logger = Logger.getLogger(AdminPaymentsController.class);

    /**
     * @param req
     * @return
     */
    public String doGet(HttpServletRequest req) throws SQLException, NotAuthorisedException {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        if (AuthorisationValidator.adminAuthorised(user)) {
            List<Payment> payments = paymentService.getAll();
            Collections.reverse(payments);
            payments = PaymentsPagination.getPagination()
                    .getElements(req, payments);
            req.setAttribute("payments", payments);
            return PageLocation.ADMIN_PAYMENTS;
        }
        logger.error(ErrorMessage.NOT_AUTHORISED);
        return null;
    }
}
