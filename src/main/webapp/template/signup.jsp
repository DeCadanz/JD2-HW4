<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>
<body>
<b><a href="${pageContext.request.contextPath}/ui/signIn">Вход</a></b>
·
<b>Регистрация</b>
<form action="${pageContext.request.contextPath}/api/user" method="POST">
    <p><b>Логин:</b></p>
    <input  type="text"  id="login"  name="login"  required  size="16" />
     <p><b>Пароль:</b></p>
    <input  type="password"  id="password"  name="password"  required  size="16" />
     <p><b>ФИО:</b></p>
    <input  type="text"  id="fullname"  name="fullname"  required  size="16" placeholder="Пупкин А.А."/>
     <p><b>Дата рождения:</b></p>
    <input  type="text"  id="birth"  name="birth"  required  size="16" placeholder="1990-01-27" pattern="^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$"/>
    
    <p><input type="submit" value="Зарегистрироваться" /></p>
    <p><span style='color: red; font-size: 12px;'><b>${error}</p>
</form>

</body>
</html>