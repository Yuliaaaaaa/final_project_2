package controllers;

import commonlyUsedStrings.PageLocation;
import dtos.SecureUser;
import models.Payment;
import models.Subscription;
import org.apache.log4j.Logger;
import services.SubscriptionService;
import transactionServices.PaymentTransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuliia Shcherbakova ON 03.08.2019
 */
public class CartController implements GetMethodController, PostMethodController {
    private static final SubscriptionService subscriptionService =
            SubscriptionService.getSubscriptionService();
    private static final PaymentTransactionService paymentTransactionService =
            PaymentTransactionService.getPaymentTransactionService();
    private static final Logger logger = Logger.getLogger(CartController.class);

    /**
     * @param req
     * @return
     * @throws SQLException
     */
    public String doGet(HttpServletRequest req) throws SQLException {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        if (user == null)
            return PageLocation.NOT_AUTHORISED;
        List<Subscription> subscriptionList = subscriptionService.getAllUnpaidForUser(user.getUserId());
        req.setAttribute("subscriptions", subscriptionList);
        return PageLocation.CART_PAGE;
    }


    /**
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String[] selectedSubscriptions = req.getParameterValues("tick");
        if (selectedSubscriptions == null){
            req.setAttribute("notSelected", true);
            return doGet(req);
        }
        boolean pay = Boolean.parseBoolean(req.getParameter("pay"));
        boolean delete = Boolean.parseBoolean(req.getParameter("delete"));
        if (delete) {
            deleteFromCart(req, selectedSubscriptions);
        } else if (pay) {
            payForSubscriptions(req, selectedSubscriptions);
        }
        return doGet(req);
    }

    public void payForSubscriptions(HttpServletRequest req, String[] selectedSubscriptions) throws SQLException {
        double sum = Double.parseDouble(req.getParameter("inputSum"));
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        Payment payment = new Payment();
        payment.setPaymentSum(sum);
        payment.setUserId(user.getUserId());
        List<Subscription> subscriptions = Arrays.stream(selectedSubscriptions).map(idx -> {
            int id = Integer.parseInt(idx);
            try {
                return subscriptionService.getOneById(id);
            } catch (SQLException e) {
                logger.error("Could not get subscription " + id);
                return null;
            }
        }).collect(Collectors.toList());
        paymentTransactionService.payFromCart(subscriptions, payment);
        req.setAttribute("paid", true);
    }

    public void deleteFromCart(HttpServletRequest req, String[] selectedSubscriptions) {
        Arrays.stream(selectedSubscriptions).peek(idx -> {
            int id = Integer.parseInt(idx);
            try {
                subscriptionService.delete(id);
            } catch (SQLException e) {
                logger.error("Could not delete subscription " + id);
            }
        }).collect(Collectors.toList());
        req.setAttribute("deleted", true);
    }
}
