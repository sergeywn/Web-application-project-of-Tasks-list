<%--
  Created by IntelliJ IDEA.
  User: SergeyVolkov
  Date: 11.01.2025
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Просмотр задания/мероприятия для удаления</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .header {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #ddd;
        }

        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .action-button {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            transition: 0.3s;
            margin-right: 10px;
        }

        .action-button:hover {
            background-color: #45a049;
        }

        /* Дополнительный стиль для формы */
        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #dddddd;
            border-radius: 5px;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<!-- Шапка сайта -->
<div class="header">
    <h1>Просмотр задания/мероприятия выбранного для удаления</h1>
</div>

<!-- Блок с информацией о задании -->
<form action="/deleteTask" method="post">
    <c:forEach var="task" items="${requestScope.Task}">
    <div style="max-width: 600px; margin: 20px auto; padding: 20px; background-color: #ffffff; border: 1px solid #dddddd; border-radius: 5px;">
        <!-- Скрытое поле для передачи id задачи -->
        <input type="hidden" name="taskId" value="${task.getId()}"/>
        <!-- Доступные поля -->
        <p><strong>Наименование:</strong> ${task.getTaskName()}</p>
        <p><strong>Категория:</strong> ${task.getCategoryName()}</p>
        <p><strong>Важность:</strong> ${task.getUrgencyLevel()}</p>
        <p><strong>Дата:</strong> ${task.getDate()}</p>
        <p><strong>Время:</strong> ${task.getTime()}</p>
    </div>

    <!-- Кнопки действий -->
        <a href="/allTasks" class="action-button">Вернуться к списку заданий</a>
        <input type="submit" class="action-button" value="Удалить задание/мероприятие">

</form>
</c:forEach>

</body>
</html>
