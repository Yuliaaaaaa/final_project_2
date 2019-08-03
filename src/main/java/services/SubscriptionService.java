package services;

import models.Subscription;
import org.apache.log4j.Logger;
import repositories.SubscriptionRepository;

import java.sql.SQLException;

/**
 * @author Yuliia Shcherbakova ON 03.08.2019
 * @project publishing
 */
public class SubscriptionService extends Service<Subscription> {
    private static final SubscriptionService subscriptionService = new SubscriptionService();
    private static final Logger logger = Logger.getLogger(SubscriptionService.class);

    /**
     *
     */
    private SubscriptionService() {
        super(new SubscriptionRepository());
    }

    /**
     * @return
     */
    public static SubscriptionService getSubscriptionService() {
        return subscriptionService;
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Subscription getOneById(int id) throws SQLException {
        EditionService editionService = EditionService.getEditionService();
        Subscription subscription = super.getOneById(id);
        subscription.setEdition(editionService.getOneById(subscription.getEditionId()));
        return subscription;
    }
}
