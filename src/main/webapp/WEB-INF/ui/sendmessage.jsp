<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Отправить сообщение</title>
</head>
<body>
<b><a href="${pageContext.request.contextPath}/ui/user/chats">Входящие</a></b>
·
<b>Отправить</b>
<form action="${pageContext.request.contextPath}/api/message" method="POST">
    <p><b>Получатель:</b></p>
    <input  type="text"  id="recipient"  name="recipient"  required  size="16" />
    <p><b>Сообщение:</b></p>
    <input  type="text"  id="text"  name="text"  required  size="16" />

    <p><input type="submit" value="Отправить" /></p>
    <p><span style='color: red; font-size: 12px;'><b>${error}</p>
</form>

</body>
</html>