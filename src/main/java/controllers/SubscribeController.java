package controllers;

import commonlyUsedStrings.PageLocation;
import factories.PeriodicityFactory;
import models.Edition;
import dtos.SecureUser;
import models.Payment;
import models.PaymentDetail;
import models.Subscription;
import pagination.EditionsPagination;
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
        if (user == null)
            return PageLocation.NOT_AUTHORISED;
        List<Edition> unsubscribedEditions = editionService.getAllUnsubscribedEditions(user.getUserId());
        unsubscribedEditions = EditionsPagination.getPagination()
                .getElements(req, unsubscribedEditions);
        req.setAttribute("editions", unsubscribedEditions);
        return PageLocation.SUBSCRIPTION_PAGE;
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
        if (user == null)
            return PageLocation.NOT_AUTHORISED;
        String issues = req.getParameter("issues");
        if (issues.isEmpty()) {
            req.setAttribute("notSelected", true);
            return doGet(req);
        }
        int userId = user.getUserId();
        int editionId = Integer.parseInt(req.getParameter("editionId"));
        int issuesQuantity = Integer.parseInt(issues);
        double sum = Double.parseDouble(req.getParameter("sum"));
        boolean pay = Boolean.parseBoolean(req.getParameter("pay"));
        String periodicity = req.getParameter("periodicity");
        Subscription subscription = Subscription.getSubscription(userId, editionId, issuesQuantity, periodicity);
        if (pay) {
            pay(userId, subscription, sum);
            req.setAttribute("paid", true);
        }
        boolean cart = Boolean.parseBoolean(req.getParameter("cart"));
        if (cart) {
            addToCart(subscription);
            req.setAttribute("addedToCart", true);
        }
        return doGet(req);
    }

    public static void addToCart(Subscription subscription) throws SQLException {
        subscription.setPaid(false);
        subscriptionService.add(subscription);
    }

    public static void pay(int userId, Subscription subscription, double sum) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentSum(sum);
        payment.setUserId(userId);
        paymentTransactionService.payImmediately(subscription, payment);
    }

}
