<%--
  Created by IntelliJ IDEA.
  User: SergeyVolkov
  Date: 09.01.2025
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Результат добавления задачи</title>
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

        /* Стили для кнопки */
        .tasks-button {
            display: inline-block;
            padding: 6px 12px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border: none;
            cursor: pointer;
            transition: 0.3s;
            position: absolute;
            top: 120px; /* Поднять кнопку выше */
            right: 20px; /* Разместить справа */
        }

        .tasks-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<!-- Шапка сайта -->
<div class="header">
    <h1>Задача/мероприятие успешно добавлено</h1>
</div>

<!-- Кнопка "К заданиям" -->
<form action="/allTasks" method="get">
    <button type="submit" class="tasks-button">К заданиям</button>
</form>
</body>
</html>
