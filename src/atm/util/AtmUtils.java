package atm.util;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AtmUtils {
    private AtmUtils() {

    }

    public static boolean isValidYearMonth(String value) {
        try {
            YearMonth expiryMonth = YearMonth.parse(value,
                    DateTimeFormatter.ofPattern("yyyy-MM"));
            return expiryMonth.isAfter(YearMonth.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
