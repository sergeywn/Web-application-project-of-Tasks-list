package dao;

import dto.TaskFilter;
import entity.CategoriesNameEntity;
import entity.TaskEntity;
import entity.UrgencyLevelsEntity;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class TasksListDao implements Dao<Long, TaskEntity> {

    private static final TasksListDao INSTANCE = new TasksListDao();

    private static final String ADD_TASKS_SQL = """
            INSERT INTO family_app.public.tasks_list (task_name, category_name, urgency_level, date,time)
            VALUES                 ((?),(?),(?),(?),(?))
            """;

    private static final String UPDATE_TASKS_SQL = """
            UPDATE family_app.public.tasks_list t
               SET task_name = (?),
                   category_name = (?),
                   urgency_level = (?),
                   date = (?),
                   time = (?)
             WHERE id = (?);
            """;

    private static final String DELETE_TASKS_SQL =
            """
                    DELETE FROM family_app.public.tasks_list t
                    WHERE t.id = (?);
                    """;

    private static final String FIND_ALL_TASKS_SQL =
            """
                    SELECT t.id, t.task_name, t.category_name, t.urgency_level, t.date, t.time
                      FROM family_app.public.tasks_list t
                     ORDER BY t.date, t.time
                    """;

    public static final String FIND_BY_TASKNAME_TASKS_SQL = """
            SELECT id, task_name, category_name, urgency_level, date, time
              FROM family_app.public.tasks_list
             WHERE task_name = (?)
            """;

    private static final String FIND_BY_ID_TASKS_SQL =
            """
                    SELECT id, task_name, category_name, urgency_level, date, time
                      FROM family_app.public.tasks_list t
                     WHERE id = (?)
                     ORDER BY date, time;
                    """;

    private static final String FIND_BY_DATE_TASKS_SQL =
            """
                    SELECT t.id, t.task_name, t.category_name, t.urgency_level, t.date, t.time
                      FROM family_app.public.tasks_list t
                     WHERE date = (?)
                     ORDER BY t.date, t.time;
                    """;

    private TasksListDao() {
    }

    public List<TaskEntity> add(List<TaskEntity> tasksList) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(ADD_TASKS_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            int categoryName;
            int categoriesLevel;
            int count = 0;

            for (TaskEntity task : tasksList) {
                connection.setAutoCommit(false);

                categoryName = switch (task.getCategoryName()) {
                    case AUTO -> 2;
                    case HOUSE -> 3;
                    case CHILD -> 4;
                    case WIFE -> 5;
                    case HUSBAND -> 6;
                    case SCHOOL -> 7;
                    case FAMILY -> 8;
                    default -> 1;
                };

                categoriesLevel = switch (task.getUrgencyLevel()) {
                    case HIGH_URGENCY -> 1;
                    case MEDIUM_URGENCY -> 2;
                    default -> 3;
                };

                if (task.getTime() == null) {
                    task.setTime(LocalTime.of(9, 0, 0));
                }

                prepareStatement.setString(1, String.valueOf(task.getTaskName()));
                prepareStatement.setInt(2, categoryName);
                prepareStatement.setInt(3, categoriesLevel);
                prepareStatement.setDate(4, Date.valueOf(task.getDate()));
                prepareStatement.setTime(5, Time.valueOf(task.getTime()));

                var executeUpdate = prepareStatement.executeUpdate();
                connection.commit();
                System.out.println("Успешно добавлено " + executeUpdate);

                var generatedKeys = prepareStatement.getGeneratedKeys();
                while (generatedKeys.next()) {
                    task.setId(generatedKeys.getLong("id"));
                    System.out.println("new Id: " + task.getId());
                }
                count = count + executeUpdate;
            }
            System.out.println("Всего было " + tasksList.size() + " задач/и. Добавлено было " + count);
            return tasksList;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public boolean update(List<TaskEntity> tasksList) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(UPDATE_TASKS_SQL)) {

            int categoryName;
            int categoriesLevel;
            int count = 0;

            for (TaskEntity task : tasksList) {
                connection.setAutoCommit(false);

                categoryName = switch (task.getCategoryName()) {
                    case AUTO -> 2;
                    case HOUSE -> 3;
                    case CHILD -> 4;
                    case WIFE -> 5;
                    case HUSBAND -> 6;
                    case SCHOOL -> 7;
                    case FAMILY -> 8;
                    default -> 1;
                };

                categoriesLevel = switch (task.getUrgencyLevel()) {
                    case HIGH_URGENCY -> 1;
                    case MEDIUM_URGENCY -> 2;
                    default -> 3;
                };

                if (task.getTime() == null) {
                    task.setTime(LocalTime.of(9, 0, 0));
                }

                prepareStatement.setString(1, String.valueOf(task.getTaskName()));
                prepareStatement.setInt(2, categoryName);
                prepareStatement.setInt(3, categoriesLevel);
                prepareStatement.setDate(4, Date.valueOf(task.getDate()));
                prepareStatement.setTime(5, Time.valueOf(task.getTime()));
                prepareStatement.setLong(6, task.getId());

                var executeUpdate = prepareStatement.executeUpdate();
                connection.commit();
                if (executeUpdate != 0) {
                    System.out.println("Попытка редактирования задачи с id " + task.getId() + " - " + "успешно");
                } else {
                    System.out.println("Попытка редактирования задачи с id " + task.getId() + " - " + "ошибка");
                }

                count = count + executeUpdate;
            }
            System.out.println("Всего для редактирования было " + tasksList.size() + " задач/и. Отредактировано " + count);
            return tasksList.size() == count;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public boolean delete(List<Long> listId) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(DELETE_TASKS_SQL)) {
            int count = 0;
            for (Long id : listId) {
                connection.setAutoCommit(false);
                prepareStatement.setLong(1, id);
                var executeUpdate = prepareStatement.executeUpdate();
                if (executeUpdate != 0) {
                    System.out.println("Попытка удаления задачи с id " + id + " - " + "успешно");
                } else {
                    System.out.println("Попытка удаления задачи с id " + id + " - " + "ошибка");
                }
                connection.commit();
                count = count + executeUpdate;
            }
            System.out.println("На удаление было " + listId.size() + " задачи, удалено было " + count);
            return listId.size() == count;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<TaskEntity> findAll() {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(FIND_ALL_TASKS_SQL)) {

            ArrayList<TaskEntity> tasksList = new ArrayList<>();

            connection.setAutoCommit(false);
            var resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                TaskEntity taskResult = new TaskEntity();
                taskResult.setId(resultSet.getLong("id"));
                taskResult.setTaskName(resultSet.getString("task_name"));
                taskResult.setCategoryName(switch (resultSet.getString("category_name")) {
                    case "2" -> CategoriesNameEntity.AUTO;
                    case "3" -> CategoriesNameEntity.HOUSE;
                    case "4" -> CategoriesNameEntity.CHILD;
                    case "5" -> CategoriesNameEntity.WIFE;
                    case "6" -> CategoriesNameEntity.HUSBAND;
                    case "7" -> CategoriesNameEntity.SCHOOL;
                    case "8" -> CategoriesNameEntity.FAMILY;
                    default -> CategoriesNameEntity.UNKNOWN;
                });
                taskResult.setUrgencyLevel(switch (resultSet.getString("urgency_level")) {
                    case "1" -> UrgencyLevelsEntity.HIGH_URGENCY;
                    case "2" -> UrgencyLevelsEntity.MEDIUM_URGENCY;
                    default -> UrgencyLevelsEntity.LOW_URGENCY;
                });
                taskResult.setDate(resultSet.getDate("date").toLocalDate());
                taskResult.setTime(resultSet.getTime("time").toLocalTime());
                tasksList.add(taskResult);
            }
            connection.commit();
            return tasksList;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<TaskEntity> findAll(TaskFilter taskFilter) {
        List<TaskEntity> tasksList = new ArrayList<>();
        List<Object> parametersFilter = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();

        if (taskFilter.taskName() != null) {
            whereSql.add("task_name LIKE ?");
            parametersFilter.add("%" + taskFilter.taskName() + "%");
        }
        if (taskFilter.categoryName() != null) {

            int categoryName = switch (taskFilter.categoryName()) {
                case AUTO -> 2;
                case HOUSE -> 3;
                case CHILD -> 4;
                case WIFE -> 5;
                case HUSBAND -> 6;
                case SCHOOL -> 7;
                case FAMILY -> 8;
                default -> 1;
            };

            whereSql.add("category_name = ?");
            parametersFilter.add(categoryName);
        }
        if (taskFilter.urgencyLevel() != null) {

            int categoriesLevel = switch (taskFilter.urgencyLevel()) {
                case HIGH_URGENCY -> 1;
                case MEDIUM_URGENCY -> 2;
                default -> 3;
            };

            whereSql.add("urgency_level = ?");
            parametersFilter.add(categoriesLevel);
        }
        if (taskFilter.date() != null) {
            whereSql.add("date = ?");
            parametersFilter.add(taskFilter.date());
        }

        parametersFilter.add(taskFilter.limit());
        parametersFilter.add(taskFilter.offset());

        var where = whereSql.stream()
                .collect(joining(" AND ", " WHERE ", " ORDER BY date, time LIMIT ? OFFSET ? "));

        if (taskFilter.taskName() == null && taskFilter.categoryName() == null && taskFilter.urgencyLevel() == null && taskFilter.date() == null) {
            where = " ORDER BY date, time LIMIT ? OFFSET ? ";
        }

        var sql =
                """
                        SELECT t.id, t.task_name, t.category_name, t.urgency_level, t.date, t.time
                          FROM family_app.public.tasks_list t
                         """ + where;

        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parametersFilter.size(); i++) {
                prepareStatement.setObject(i + 1, parametersFilter.get(i));
            }
            connection.setAutoCommit(false);
            var resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                TaskEntity taskResult = new TaskEntity();
                taskResult.setId(resultSet.getLong("id"));
                taskResult.setTaskName(resultSet.getString("task_name"));
                taskResult.setCategoryName(switch (resultSet.getString("category_name")) {
                    case "2" -> CategoriesNameEntity.AUTO;
                    case "3" -> CategoriesNameEntity.HOUSE;
                    case "4" -> CategoriesNameEntity.CHILD;
                    case "5" -> CategoriesNameEntity.WIFE;
                    case "6" -> CategoriesNameEntity.HUSBAND;
                    case "7" -> CategoriesNameEntity.SCHOOL;
                    case "8" -> CategoriesNameEntity.FAMILY;
                    default -> CategoriesNameEntity.UNKNOWN;
                });
                taskResult.setUrgencyLevel(switch (resultSet.getString("urgency_level")) {
                    case "1" -> UrgencyLevelsEntity.HIGH_URGENCY;
                    case "2" -> UrgencyLevelsEntity.MEDIUM_URGENCY;
                    default -> UrgencyLevelsEntity.LOW_URGENCY;
                });
                taskResult.setDate(resultSet.getDate("date").toLocalDate());
                taskResult.setTime(resultSet.getTime("time").toLocalTime());
                tasksList.add(taskResult);
            }
            connection.commit();
            return tasksList;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<TaskEntity> findByTaskName(List<String> listTaskName) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(FIND_BY_TASKNAME_TASKS_SQL)) {

            ArrayList<TaskEntity> tasksList = new ArrayList<>();

            for (String taskName : listTaskName) {
                connection.setAutoCommit(false);
                prepareStatement.setString(1, taskName);
                var resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    TaskEntity taskResult = new TaskEntity();
                    taskResult.setId(resultSet.getLong("id"));
                    taskResult.setTaskName(resultSet.getString("task_name"));
                    taskResult.setCategoryName(switch (resultSet.getString("category_name")) {
                        case "2" -> CategoriesNameEntity.AUTO;
                        case "3" -> CategoriesNameEntity.HOUSE;
                        case "4" -> CategoriesNameEntity.CHILD;
                        case "5" -> CategoriesNameEntity.WIFE;
                        case "6" -> CategoriesNameEntity.HUSBAND;
                        case "7" -> CategoriesNameEntity.SCHOOL;
                        case "8" -> CategoriesNameEntity.FAMILY;
                        default -> CategoriesNameEntity.UNKNOWN;
                    });
                    taskResult.setUrgencyLevel(switch (resultSet.getString("urgency_level")) {
                        case "1" -> UrgencyLevelsEntity.HIGH_URGENCY;
                        case "2" -> UrgencyLevelsEntity.MEDIUM_URGENCY;
                        default -> UrgencyLevelsEntity.LOW_URGENCY;
                    });
                    taskResult.setDate(resultSet.getDate("date").toLocalDate());
                    taskResult.setTime(resultSet.getTime("time").toLocalTime());
                    tasksList.add(taskResult);
                }
                connection.commit();
            }
            return tasksList;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<TaskEntity> findById(List<Long> listId) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(FIND_BY_ID_TASKS_SQL)) {

            ArrayList<TaskEntity> tasksList = new ArrayList<>();

            for (Long id : listId) {
                prepareStatement.setLong(1, id);
                var resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    TaskEntity taskResult = new TaskEntity();
                    taskResult.setId(resultSet.getLong("id"));
                    taskResult.setTaskName(resultSet.getString("task_name"));
                    taskResult.setCategoryName(switch (resultSet.getString("category_name")) {
                        case "2" -> CategoriesNameEntity.AUTO;
                        case "3" -> CategoriesNameEntity.HOUSE;
                        case "4" -> CategoriesNameEntity.CHILD;
                        case "5" -> CategoriesNameEntity.WIFE;
                        case "6" -> CategoriesNameEntity.HUSBAND;
                        case "7" -> CategoriesNameEntity.SCHOOL;
                        case "8" -> CategoriesNameEntity.FAMILY;
                        default -> CategoriesNameEntity.UNKNOWN;
                    });
                    taskResult.setUrgencyLevel(switch (resultSet.getString("urgency_level")) {
                        case "1" -> UrgencyLevelsEntity.HIGH_URGENCY;
                        case "2" -> UrgencyLevelsEntity.MEDIUM_URGENCY;
                        default -> UrgencyLevelsEntity.LOW_URGENCY;
                    });
                    taskResult.setDate(resultSet.getDate("date").toLocalDate());
                    taskResult.setTime(resultSet.getTime("time").toLocalTime());
                    tasksList.add(taskResult);
                }
            }
            return tasksList;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<TaskEntity> findByDate(LocalDate localDate) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(FIND_BY_DATE_TASKS_SQL)) {

            ArrayList<TaskEntity> tasksList = new ArrayList<>();

            connection.setAutoCommit(false);
            prepareStatement.setDate(1, Date.valueOf(localDate));
            var resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                TaskEntity taskResult = new TaskEntity();
                taskResult.setId(resultSet.getLong("id"));
                taskResult.setTaskName(resultSet.getString("task_name"));
                taskResult.setCategoryName(switch (resultSet.getString("category_name")) {
                    case "2" -> CategoriesNameEntity.AUTO;
                    case "3" -> CategoriesNameEntity.HOUSE;
                    case "4" -> CategoriesNameEntity.CHILD;
                    case "5" -> CategoriesNameEntity.WIFE;
                    case "6" -> CategoriesNameEntity.HUSBAND;
                    case "7" -> CategoriesNameEntity.SCHOOL;
                    case "8" -> CategoriesNameEntity.FAMILY;
                    default -> CategoriesNameEntity.UNKNOWN;
                });
                taskResult.setUrgencyLevel(switch (resultSet.getString("urgency_level")) {
                    case "1" -> UrgencyLevelsEntity.HIGH_URGENCY;
                    case "2" -> UrgencyLevelsEntity.MEDIUM_URGENCY;
                    default -> UrgencyLevelsEntity.LOW_URGENCY;
                });
                taskResult.setDate(resultSet.getDate("date").toLocalDate());
                taskResult.setTime(resultSet.getTime("time").toLocalTime());
                tasksList.add(taskResult);
            }
            connection.commit();
            return tasksList;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public static TasksListDao getInstance() {
        return INSTANCE;
    }

}
