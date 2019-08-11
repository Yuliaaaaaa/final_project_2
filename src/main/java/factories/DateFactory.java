package factories;

import enums.Periodicity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Yuliia Shcherbakova ON 07.08.2019
 * @project publishing
 */
public class DateFactory {

    private DateFactory() {
    }

    /**
     * @param startDate
     * @param periodicity
     * @param quantity
     * @return
     */
    public static Timestamp getExpiredDate(Timestamp startDate, Periodicity periodicity, int quantity) {
        LocalDateTime dateTime = startDate.toLocalDateTime();
        switch (periodicity) {
            case DAILY: {
                dateTime = dateTime.plusDays(quantity);
                break;
            }
            case WEEKLY: {
                dateTime = dateTime.plusWeeks(quantity);
                break;
            }
            case MONTHLY: {
                dateTime = dateTime.plusMonths(quantity);
                break;
            }
        }
        return Timestamp.valueOf(dateTime);
    }

}
