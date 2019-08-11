package factories;

import enums.Periodicity;

/**
 * @author Yuliia Shcherbakova ON 19.07.2019
 * @project publishing
 */
public class PeriodicityFactory {

    private PeriodicityFactory() {
    }

    public static Periodicity getPeriodicity(String periodicity) {
        switch (periodicity) {
            case "daily":
                return Periodicity.DAILY;
            case "weekly":
                return Periodicity.WEEKLY;
            case "monthly":
                return Periodicity.MONTHLY;
            default:
                return null;
        }
    }
}
