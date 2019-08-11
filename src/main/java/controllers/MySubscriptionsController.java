package controllers;

import commonlyUsedStrings.ErrorMessage;
import commonlyUsedStrings.PageLocation;
import dtos.SecureUser;
import enums.Periodicity;
import exceptionHandling.exceptions.NotAuthorisedException;
import exceptionHandling.validators.AuthorisationValidator;
import factories.PeriodicityFactory;
import factories.QuantityFactory;
import models.Subscription;
import org.apache.log4j.Logger;
import services.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 08.08.2019
 * @project publishing
 */
public class MySubscriptionsController implements GetMethodController, PostMethodController {
    private static final SubscriptionService subscriptionService = SubscriptionService.
            getSubscriptionService();
    private static final Logger logger = Logger.getLogger(MySubscriptionsController.class);

    public String doGet(HttpServletRequest req) throws SQLException {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        try {
            if (AuthorisationValidator.userAuthorised(user)) {
                List<Subscription> subscriptions = subscriptionService.getAllPaidForUser(user.getUserId());
                subscriptions.stream()
                        .forEach(subscription -> {
                            Timestamp startDate = subscription.getStartDate();
                            int issuesQuantity = subscription.getIssuesQuantity();
                            Periodicity periodicity = PeriodicityFactory
                                    .getPeriodicity(subscription.getEdition().getPeriodicity());
                            int remainingQuantity = QuantityFactory
                                    .getRemainingQuantity(startDate, issuesQuantity, periodicity);
                            subscription.setIssuesQuantity(remainingQuantity);
                        });
                req.setAttribute("subscriptions", subscriptions);
                return PageLocation.MY_SUBSCRIPTIONS_PAGE;
            }
        } catch (NotAuthorisedException e) {
            return PageLocation.NOT_AUTHORISED;
        }
        logger.error(ErrorMessage.NOT_AUTHORISED);
        return null;

    }

    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        SecureUser user = (SecureUser) req.getSession().getAttribute("user");
        if (user == null)
            return PageLocation.NOT_AUTHORISED;
        String issues = req.getParameter("issues");
        if (issues.isEmpty()){
            req.setAttribute("notSelected", true);
            return doGet(req);
        }
        int userId = user.getUserId();
        int editionId = Integer.parseInt(req.getParameter("editionId"));
        int issuesQuantity = Integer.parseInt(issues);
        double sum = Double.parseDouble(req.getParameter("sum"));
        boolean pay = Boolean.parseBoolean(req.getParameter("pay"));
        String periodicity = req.getParameter("periodicity");
        Timestamp startDate = Timestamp.valueOf(req.getParameter("startDate"));
        Subscription subscription = Subscription.getSubscription(userId, editionId, issuesQuantity, periodicity);
        subscription.setStartDate(startDate);
        if (pay) {
            SubscribeController.pay(userId, subscription, sum);
            req.setAttribute("paid", true);
        }
        boolean cart = Boolean.parseBoolean(req.getParameter("cart"));
        if (cart) {
            SubscribeController.addToCart(subscription);
            req.setAttribute("addedToCart", true);
        }
        return doGet(req);
    }
}
