<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Почтальон Пэт</title>
</head>
<body>
<table border = '0' cellpadding = '5' width = '180'>
    <tr><td align="center">Добро пожаловать в систему сообщений</td></tr>
    <tr><td align="center"><b>Почтальон Пэт</b></td></tr>
    <tr><td align="center">Вы можете</td></tr>
    <tr><td align="center"><b><a href="${pageContext.request.contextPath}/ui/signIn">Войти</a></b></td></tr>
    <tr><td align="center">или</td></tr>
    <tr><td align="center"><b><a href="${pageContext.request.contextPath}/ui/signUp">Зарегистрироваться</a></b></td></tr>
    </table>

<form>

</form>

</body>
</html>