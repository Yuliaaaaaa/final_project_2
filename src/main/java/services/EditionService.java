package services;

import models.Edition;
import repositories.EditionRepository;

/**
 * @author Yuliia Shcherbakova ON 25.07.2019
 * @project publishing
 */
public class EditionService extends Service<Edition> {
    private static final EditionService editionService = new EditionService();

    private EditionService() {
        super(new EditionRepository());
    }

    public static EditionService getEditionService() {
        return editionService;
    }
}
