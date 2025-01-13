package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
public class UpdateTaskDto {

    private final String id;
    private final String taskName;
    private final String categoryName;
    private final String urgencyLevel;
    private final String date;
    private final String time;

}
