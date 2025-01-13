package validator;

import dao.TasksListDao;
import dto.CategoriesNameDto;
import dto.CreateTaskDto;
import dto.UpdateTaskDto;
import dto.UrgencyLevelsDto;
import entity.CategoriesNameEntity;
import entity.TaskEntity;
import entity.UrgencyLevelsEntity;
import mapper.CreateTaskMapper;
import util.LocalDateFormatter;
import util.LocalTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateTaskValidator implements Validator<CreateTaskDto> {

    public static final CreateTaskValidator INSTANCE = new CreateTaskValidator();
    private final CreateTaskMapper createTaskMapper = CreateTaskMapper.getInstance();
    private final TasksListDao tasksListDao = TasksListDao.getInstance();


    @Override
    public ValidationResult isValid(CreateTaskDto object) {

        Error errorNameTask = new Error("invalid.NameTask","Не корректное поле - Наименование. Создайте задачу с наименованием, которого нет в списке");
        Error errorDate = new Error("invalid.Date","Не корректное поле - Дата");
        Error errorTime = new Error("invalid.Time","Не корректное поле - Время");
        Error errorCategories = new Error("invalid.CategoriesName","Не корректное или не заполненное поле - Категория");
        Error errorUrgencyLevel = new Error("invalid.UrgencyLevel","Не корректное или не заполненное поле - Важность");

        var validationResult = new ValidationResult();

        List<String> listTaskName = new ArrayList<>();
        listTaskName.add(object.getTaskName());
        List<TaskEntity> taskEntityList = TasksListDao.getInstance().findByTaskName(listTaskName);

        if (!taskEntityList.isEmpty()) {
            validationResult.add(errorNameTask);
        }
        if (!LocalDateFormatter.isValid(object.getDate())) {
            validationResult.add(errorDate);
        }
        if (!LocalTimeFormatter.isValid(object.getTime())) {
            validationResult.add(errorTime);
        }
        if (object.getCategoryName() == null || CategoriesNameDto.valueOf(object.getCategoryName()) == null) {
            validationResult.add(errorCategories);
        }
        if (object.getUrgencyLevel() == null || UrgencyLevelsDto.valueOf(object.getUrgencyLevel()) == null) {
            validationResult.add(errorUrgencyLevel);
        }
        return validationResult;
    }

    public ValidationResult isValid(UpdateTaskDto object) {

        Error errorNameTask = new Error("invalid.NameTask","Не корректное поле - Наименование. Создайте задачу с наименованием, которого нет в списке");
        Error errorDate = new Error("invalid.Date","Не корректное поле - Дата");
        Error errorTime = new Error("invalid.Time","Не корректное поле - Время");
        Error errorCategories = new Error("invalid.CategoriesName","Не корректное или не заполненное поле - Категория");
        Error errorUrgencyLevel = new Error("invalid.UrgencyLevel","Не корректное или не заполненное поле - Важность");

        var validationResult = new ValidationResult();

        List<String> listTaskName = new ArrayList<>();
        listTaskName.add(object.getTaskName());
        List<TaskEntity> taskEntityList = TasksListDao.getInstance().findByTaskName(listTaskName);

        if (!taskEntityList.isEmpty()) {
            validationResult.add(errorNameTask);
        }
        if (!LocalDateFormatter.isValid(object.getDate())) {
            validationResult.add(errorDate);
        }
        if (!LocalTimeFormatter.isValid(object.getTime())) {
            validationResult.add(errorTime);
        }
        if (object.getCategoryName() == null || CategoriesNameDto.valueOf(object.getCategoryName()) == null) {
            validationResult.add(errorCategories);
        }
        if (object.getUrgencyLevel() == null || UrgencyLevelsDto.valueOf(object.getUrgencyLevel()) == null) {
            validationResult.add(errorUrgencyLevel);
        }
        return validationResult;
    }

    public static CreateTaskValidator getInstance() {
        return INSTANCE;
    }
}
