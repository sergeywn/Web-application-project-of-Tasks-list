<%--
  Created by IntelliJ IDEA.
  User: SergeyVolkov
  Date: 07.01.2025
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Семейная программа</title>
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
    </style>
</head>
<body>

<!-- Шапка сайта -->
<div class="header">
    <h1>Список дел / мероприятий</h1>
</div>

<!-- Кнопки управления -->
<%@include file="header.jsp"%>

<!-- Таблица данных -->

<table>
    <thead>
    <tr>
        <th>Наименование</th>
        <th>Категория</th>
        <th>Важность</th>
        <th>Дата</th>
        <th>Время</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ticket" items="${requestScope.allTasks}">
        <tr>
            <td>${ticket.getTaskName()}</td>
            <td>${ticket.getCategoryName()}</td>
            <td>${ticket.getUrgencyLevel()}</td>
            <td>${ticket.getDate()}</td>
            <td>${ticket.getTime()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
