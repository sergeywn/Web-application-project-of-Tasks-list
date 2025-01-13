<%--
  Created by IntelliJ IDEA.
  User: SergeyVolkov
  Date: 07.01.2025
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <c:if test="${not empty sessionScope.user}">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">Выйти</button>
        </form>
    </c:if>
</div>
