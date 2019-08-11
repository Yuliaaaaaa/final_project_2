package pagination;

import models.Edition;

/**
 * @author Yuliia Shcherbakova ON 11.08.2019
 * @project publishing
 */
public class EditionsPagination implements Pagination<Edition> {
    private static final EditionsPagination pagination = new EditionsPagination();

    private EditionsPagination() {
    }

    public static EditionsPagination getPagination() {
        return pagination;
    }
}
