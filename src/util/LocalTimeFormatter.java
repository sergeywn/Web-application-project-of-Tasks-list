package util;

import lombok.experimental.UtilityClass;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@UtilityClass
public class LocalTimeFormatter {

    public static final String PATTERN = "HH:mm";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public LocalTime format(String time) {
        if (time.equals("")) {
            return null;
        }
        return LocalTime.parse(time, FORMATTER);
    }

    public boolean isValid(String time) {
        try {
            if (time.equals("")) {
                return true;
            }
            return Optional.ofNullable(time).map(LocalTimeFormatter::format).isPresent();
            
        } catch (DateTimeException exception) {
            return false;
        }
    }

}
