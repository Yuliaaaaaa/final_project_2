package factories;

import enums.Periodicity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Yuliia Shcherbakova ON 08.08.2019
 * @project publishing
 */
public class QuantityFactory {

    private QuantityFactory() {
    }

    /**
     * @param startDate
     * @param quantity
     * @param periodicity
     * @return
     */
    public static int getRemainingQuantity(Timestamp startDate, int quantity, Periodicity periodicity) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        long time = now.getTime() - startDate.getTime();
        switch (periodicity) {
            case DAILY: {
                return quantity - (int) (time / (1000 * 60 * 60 * 24));
            }
            case WEEKLY: {
                return quantity - (int) (time / (1000 * 60 * 60 * 24 * 7));
            }
            case MONTHLY: {
                long daysTime = 1000 * 60 * 60 * 24;
                return quantity - (int) (time / (daysTime * 30));
            }
        }
        return 0;
    }


}
