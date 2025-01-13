package service;

import dao.TasksListDao;
import dto.CreateTaskDto;
import dto.ReadTaskDto;
import dto.TaskFilter;
import dto.UpdateTaskDto;
import exception.ValidationException;
import mapper.CreateTaskMapper;
import mapper.UpdateTaskMapper;
import validator.CreateTaskValidator;
import validator.UpdateTaskValidator;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;

public class TaskService {

    private final UpdateTaskValidator updateTaskValidator = UpdateTaskValidator.getInstance();
    private final CreateTaskValidator createTaskValidator = CreateTaskValidator.getInstance();
    private final TasksListDao tasksListDao = TasksListDao.getInstance();
    private final CreateTaskMapper createTaskMapper = CreateTaskMapper.getInstance();
    private final UpdateTaskMapper updateTaskMapper = UpdateTaskMapper.getInstance();
    private static final TaskService INSTANCE = new TaskService();


    private TaskService() {
    }

    public List<ReadTaskDto> findAll(TaskFilter taskFilter) {

        String categoryName = tasksListDao.findAll().stream().map(taskEntity -> switch (taskEntity.getCategoryName()) {
            case AUTO -> "АВТО";
            case HOUSE -> "ДОМ";
            case CHILD -> "ДЕТИ";
            case WIFE -> "ЖЕНА";
            case HUSBAND -> "МУЖ";
            case SCHOOL -> "ШКОЛА";
            case FAMILY -> "СЕМЬЯ";
            default -> "РАЗНОЕ";
        }).toString();

        String urgencyLevel = tasksListDao.findAll().stream().map(taskEntity -> switch (taskEntity.getUrgencyLevel()) {
            case HIGH_URGENCY -> "ВЫСОКАЯ";
            case MEDIUM_URGENCY -> "СРЕДНЯЯ";
            default -> "НИЗКАЯ";
        }).toString();

        return tasksListDao.findAll(taskFilter).stream()
                .map(taskEntity -> new ReadTaskDto(
                        taskEntity.getId(), taskEntity.getTaskName(),
                        switch (taskEntity.getCategoryName()) {
                            case AUTO -> "АВТО";
                            case HOUSE -> "ДОМ";
                            case CHILD -> "ДЕТИ";
                            case WIFE -> "ЖЕНА";
                            case HUSBAND -> "МУЖ";
                            case SCHOOL -> "ШКОЛА";
                            case FAMILY -> "СЕМЬЯ";
                            default -> "РАЗНОЕ";
                        },
                        switch (taskEntity.getUrgencyLevel()) {
                            case HIGH_URGENCY -> "ВЫСОКЙ ПРИОРИТЕТ";
                            case MEDIUM_URGENCY -> "СРЕДНИЙ ПРИОРИТЕТ";
                            default -> "НИЗКИЙ ПРИОРИТЕТ";
                        },
                        taskEntity.getDate(),
                        taskEntity.getTime()
                )).collect(toList());
    }

    public List<ReadTaskDto>findById(Long taskId) {
        List<Long> listTaskId = new ArrayList<>();
        listTaskId.add(taskId);
        return tasksListDao.findById(listTaskId).stream()
                .map(taskEntity -> new ReadTaskDto(
                        taskEntity.getId(),
                        taskEntity.getTaskName(),
                        switch (taskEntity.getCategoryName()) {
                            case AUTO -> "АВТО";
                            case HOUSE -> "ДОМ";
                            case CHILD -> "ДЕТИ";
                            case WIFE -> "ЖЕНА";
                            case HUSBAND -> "МУЖ";
                            case SCHOOL -> "ШКОЛА";
                            case FAMILY -> "СЕМЬЯ";
                            default -> "РАЗНОЕ";
                        },
                        switch (taskEntity.getUrgencyLevel()) {
                            case HIGH_URGENCY -> "ВЫСОКЙ ПРИОРИТЕТ";
                            case MEDIUM_URGENCY -> "СРЕДНИЙ ПРИОРИТЕТ";
                            default -> "НИЗКИЙ ПРИОРИТЕТ";
                        },
                        taskEntity.getDate(),
                        taskEntity.getTime()
                ))
                .collect(toList());
    }

    public void addTask(CreateTaskDto createTaskDto) {
        // validation -> map -> taskListDao.add -> return String

        var validationResult = createTaskValidator.isValid(createTaskDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var taskEntity = createTaskMapper.mapFrom(createTaskDto);
        tasksListDao.add(taskEntity);

        System.out.println("Задача/мероприятие успешно добавлено");
    }

    public void updateTask(UpdateTaskDto updateTaskDto) {
        // validation -> map -> taskListDao.add -> return String

        var validationResult = updateTaskValidator.isValid(updateTaskDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var taskEntity = updateTaskMapper.mapFrom(updateTaskDto);
        tasksListDao.update(taskEntity);

        System.out.println("Задача/мероприятие успешно отредактирована");
    }

    public void deleteTask(Long taskId) {
        List<Long> listTaskId = new ArrayList<>();
        listTaskId.add(taskId);
        tasksListDao.delete(listTaskId);
        System.out.println("Задача/мероприятие успешно удалена");
    }

    public static TaskService getInstance() {
        return INSTANCE;
    }
}
