<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Статистика</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/api/admin/statistics" method="GET">
    <p><b>Активных сессий:</b>${sessions}</p>
    <p><b>Всего пользователей:</b>${users}</p>
    <p><b>Всего сообщений:</b>${messages}</p>
</form>
</body>
</html>