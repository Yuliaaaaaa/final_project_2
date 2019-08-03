package controllers;

import commonlyUsedStrings.PageLocation;
import models.Edition;
import dtos.SecureUser;
import models.Payment;
import models.PaymentDetail;
import models.Subscription;
import services.EditionService;
import services.SubscriptionService;
import transactionServices.PaymentTransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuliia Shcherbakova ON 01.08.2019
 * @project publishing
 */
public class SubscribeController implements GetMethodController, PostMethodController {
    private static final EditionService editionService = EditionService.getEditionService();
    private static final SubscriptionService subscriptionService = SubscriptionService.getSubscriptionService();
    private static final PaymentTransactionService paymentTransactionService = PaymentTransactionService.getPaymentTransactionService();


    /**
     * @param req
     * @return
     * @throws SQLException
     */
    public String doGet(HttpServletRequest req) throws SQLException {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        if (user != null){
            List<Edition> unsubscribedEditions = editionService.getAllUnsubscribedEditions(user.getUserId());
            int startIndex = (int) req.getAttribute("startIndex");
            if (startIndex == 0)
                req.setAttribute("start", true);
            if(unsubscribedEditions.size() - startIndex <= 10)
                req.setAttribute("end", true);
            unsubscribedEditions = unsubscribedEditions.stream().skip(startIndex).limit(10).collect(Collectors.toList());
            req.setAttribute("editions", unsubscribedEditions);
            return PageLocation.SUBSCRIPTION_PAGE;
        }
        else return PageLocation.PAGE_NOT_FOUND;
    }


    /**
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        int issues = Integer.parseInt(req.getParameter("issues"));
        double sum = Double.parseDouble(req.getParameter("sum"));
        boolean pay = Boolean.parseBoolean(req.getParameter("pay"));
        Subscription subscription = new Subscription();
        subscription.setEditionId(id);
        subscription.setIssuesQuantity(issues);
        subscription.setUserId(user.getUserId());
        if(pay){
            Payment payment = new Payment();
            payment.setPaymentSum(sum);
            payment.setUserId(user.getUserId());
            paymentTransactionService.payImmediately(subscription, payment);
            req.setAttribute("paid", true);
        }
        boolean cart = Boolean.parseBoolean(req.getParameter("cart"));
        if(cart){
            subscription.setPaid(false);
            subscriptionService.add(subscription);
            req.setAttribute("addedToCart", true);
        }
        return doGet(req);
    }
}
