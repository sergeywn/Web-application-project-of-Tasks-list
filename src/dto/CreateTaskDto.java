package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
public class CreateTaskDto {

    String taskName;
    String categoryName;
    String urgencyLevel;
    String date;
    String time;
}
