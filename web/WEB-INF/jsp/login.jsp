<%--
  Created by IntelliJ IDEA.
  User: SergeyVolkov
  Date: 04.01.2025
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Семейная программа (Авторизация)</title>
    <style>
        {
            margin: 0
        ;
            padding: 0
        ;
            box-sizing: border-box
        ;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 300px;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            background-color: #007bff;
            color: white;
            padding: 14px 20px;
            margin-top: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Вход</h1>

    <c:if test="${param.error != null}">
        <span style="color: red;">Неверный логин или пароль</span>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="accountNameId">
            <input type="text" name="accountName" id="accountNameId" value="${param.accountName}" placeholder="Логин" required>
        </label>
        <label for="passwordId">
            <input type="password" name="password" id="passwordId" placeholder="Пароль" required>
        </label>
        <button type="submit">Войти</button>
    </form>
</div>
</body>
</html>
