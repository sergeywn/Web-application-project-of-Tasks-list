package dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ReadTaskDto {

    private final Long id;
    private final String taskName;
    private final String categoryName;
    private final String urgencyLevel;
    private final LocalDate date;
    private final LocalTime time;

    public ReadTaskDto(Long id, String taskName, String categoryName, String urgencyLevel, LocalDate date, LocalTime time) {
        this.id = id;
        this.taskName = taskName;
        this.categoryName = categoryName;
        this.urgencyLevel = urgencyLevel;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadTaskDto that = (ReadTaskDto) o;
        return Objects.equals(id, that.id) && Objects.equals(taskName, that.taskName) && Objects.equals(categoryName, that.categoryName) && Objects.equals(urgencyLevel, that.urgencyLevel) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName, categoryName, urgencyLevel, date, time);
    }

    @Override
    public String toString() {
        return "ReadTaskDto{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", urgencyLevel='" + urgencyLevel + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
