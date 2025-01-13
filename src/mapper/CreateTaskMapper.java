package mapper;

import dto.CreateTaskDto;
import dto.UpdateTaskDto;
import entity.CategoriesNameEntity;
import entity.TaskEntity;
import entity.UrgencyLevelsEntity;
import util.LocalDateFormatter;
import util.LocalTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class CreateTaskMapper implements Mapper<CreateTaskDto, List<TaskEntity>>{

    public static final CreateTaskMapper INSTANCE = new CreateTaskMapper();

    @Override
    public List<TaskEntity> mapFrom(CreateTaskDto object) {

        String categoriesNameEntity = switch (object.getCategoryName()) {
            case "АВТО" -> String.valueOf(CategoriesNameEntity.AUTO);
            case "ДОМ" -> String.valueOf(CategoriesNameEntity.HOUSE);
            case "ДЕТИ" -> String.valueOf(CategoriesNameEntity.CHILD);
            case "ЖЕНА" -> String.valueOf(CategoriesNameEntity.WIFE);
            case "МУЖ" -> String.valueOf(CategoriesNameEntity.HUSBAND);
            case "ШКОЛА" -> String.valueOf(CategoriesNameEntity.SCHOOL);
            case "СЕМЬЯ" -> String.valueOf(CategoriesNameEntity.FAMILY);
            default -> String.valueOf(CategoriesNameEntity.UNKNOWN);
        };

        String urgencyLevelsEntity = switch (object.getUrgencyLevel()) {
            case "ВЫСОКАЯ" -> String.valueOf(UrgencyLevelsEntity.HIGH_URGENCY);
            case "СРЕДНЯЯ" -> String.valueOf(UrgencyLevelsEntity.MEDIUM_URGENCY);
            default -> String.valueOf(UrgencyLevelsEntity.LOW_URGENCY);
        };

        List<TaskEntity> taskEntityList = new ArrayList<>();
        TaskEntity taskEntity;
        taskEntity = TaskEntity.builder()
                .taskName(object.getTaskName())
                .categoryName(CategoriesNameEntity.valueOf(categoriesNameEntity))
                .urgencyLevel(UrgencyLevelsEntity.valueOf(urgencyLevelsEntity))
                .date(LocalDateFormatter.format(object.getDate()))
                .time(LocalTimeFormatter.format(object.getTime()))
                .build();
        taskEntityList.add(taskEntity);
        return taskEntityList;
    }

    public static CreateTaskMapper getInstance() {
        return INSTANCE;
    }

}
