package services;

import models.Edition;
import daos.repositories.EditionRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Yuliia Shcherbakova ON 25.07.2019
 * @project publishing
 */
public class EditionService extends Service<Edition> {
    private static final EditionService editionService = new EditionService();

    /**
     *
     */
    private EditionService() {
        super(new EditionRepository());
    }

    /**
     * @return
     */
    public static EditionService getEditionService() {
        return editionService;
    }

    /**
     * @param userId
     * @return
     */
    public List<Edition> getAllUnsubscribedEditions(int userId) throws SQLException {
        return ((EditionRepository)repository).getAllUnsubscribedEditions(userId);
    }
}
