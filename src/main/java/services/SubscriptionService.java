package services;

import models.Subscription;
import org.apache.log4j.Logger;
import repositories.SubscriptionRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuliia Shcherbakova ON 03.08.2019
 * @project publishing
 */
public class SubscriptionService extends Service<Subscription> {
    private static final SubscriptionService subscriptionService = new SubscriptionService();
    private static final Logger logger = Logger.getLogger(SubscriptionService.class);
    private EditionService editionService = EditionService.getEditionService();

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
        Subscription subscription = super.getOneById(id);
        setEdition(subscription);
        return subscription;
    }

    private void setEdition(Subscription subscription) throws SQLException {
        subscription.setEdition(editionService.getOneById(subscription.getEditionId()));
    }

    public List<Subscription> getAllUnpaidForUser(int userId) throws SQLException {
        List<Subscription> subscriptions = ((SubscriptionRepository) repository)
                .getAllUnpaidForUser(userId);
        return subscriptions.stream()
                .peek(subscription -> {
                    try {
                        setEdition(subscription);
                    } catch (SQLException e) {
                        logger.error("Could not get edition for subscription " + subscription.getSubscriptionId());
                    }
                })
                .collect(Collectors.toList());
    }
}
