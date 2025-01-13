<%--
  Created by IntelliJ IDEA.
  User: SergeyVolkov
  Date: 10.01.2025
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создание нового задания</title>
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

        button {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            transition: 0.3s;
        }

        button:hover {
            background-color: #45a049;
        }

        .add-button {
            float: right;
            margin-right: 15px;
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

        input, select {
            width: 100%;
            padding: 6px 12px;
            margin-bottom: 10px;
            border: 1px solid #cccccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            transition: 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<!-- Шапка сайта -->
<div class="header">
    <h1>Редактирование задания/мероприятия</h1>
</div>
<!-- Форма для редактирования задания -->
<form action="/editTask" method="post">
    <c:forEach var="task" items="${requestScope.Task}">
        <!-- Скрытое поле для передачи id задачи -->
        <input type="hidden" name="taskId" value="${task.getId()}"/>

        <label for="name">Наименование:</label>
        <input type="text" id="name" name="nameTask"
               value="${task.getTaskName()}"
               placeholder="Введите наименование" required>

        <label for="category">Категория:</label>
        <select id="category" name="categoryName" required>
            <c:forEach var="categories" items="${requestScope.Categories}">
                <option value="" disabled selected hidden>Выберите категорию</option>
                <option value="${categories}">${categories}</option>
            </c:forEach>
        </select>


        <label for="urgency">Важность:</label>
        <select id="urgency" name="urgencyLevel" required>
            <c:forEach var="urgencyLevel" items="${requestScope.UrgencyLevel}">
                <option value="" disabled selected hidden>Выберите важность</option>
                <option value="${urgencyLevel}">${urgencyLevel}</option>
            </c:forEach>
        </select>

        <label for="date">Дата:</label>
        <input type="date" id="date" name="date"
               value="${task.getDate()}" required>

        <label for="time">Время: (не обязательно)</label>
        <input type="time" id="time" name="time"
               value="${task.getTime()}">

    </c:forEach>

    <input type="submit" value="Редактировать задание/мероприятие">
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message} <br></span>
            </c:forEach>
        </div>
    </c:if>
</form>

</body>
</html>
