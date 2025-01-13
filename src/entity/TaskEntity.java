package entity;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public class TaskEntity {

    private Long id;
    private String taskName;
    private CategoriesNameEntity categoryName;
    private UrgencyLevelsEntity urgencyLevel;
    private LocalDate date;
    private LocalTime time;

    public  TaskEntity() {}

    public TaskEntity(Long id, String taskName, CategoriesNameEntity categoryName, UrgencyLevelsEntity urgencyLevel, LocalDate date, LocalTime time) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public CategoriesNameEntity getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoriesNameEntity categoryName) {
        this.categoryName = categoryName;
    }

    public UrgencyLevelsEntity getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(UrgencyLevelsEntity urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", categoryName=" + categoryName +
                ", urgencyLevel=" + urgencyLevel +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

}
