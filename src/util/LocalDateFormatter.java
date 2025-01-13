package util;

import lombok.experimental.UtilityClass;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@UtilityClass
public class LocalDateFormatter {

    public static final String PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public LocalDate format(String date) {
        return LocalDate.parse(date,FORMATTER);
    }

    public boolean isValid(String date) {
        try {
            return Optional.ofNullable(date).map(LocalDateFormatter::format).isPresent();
        } catch (DateTimeException exception) {
            return false;
        }
    }

}
