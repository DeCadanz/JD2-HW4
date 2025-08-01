<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Вход</title>
</head>
<body>
<b>Вход</b>
<b> · </b>
<b><a href="${pageContext.request.contextPath}/ui/signUp">Регистрация</a></b>
<form action="${pageContext.request.contextPath}/api/login" method="POST">
    <p><b>Логин:</b></p>
    <input  type="text"  id="login"  name="login"  required  size="20" />
    <p><b>Пароль:</b></p>
    <input  type="password"  id="password"  name="password"  required  size="20" />

    <p><input type="submit" value="Войти" /></p>
    
    <p><span style='color: red; font-size: 12px;'><b>${error}</p>
</form>

</body>
</html>