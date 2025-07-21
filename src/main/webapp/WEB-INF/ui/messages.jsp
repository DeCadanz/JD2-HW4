<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Входящие</title>
</head>
<body>
<b>Входящие</b>
·
<b><a href="${pageContext.request.contextPath}/ui/user/message">Отправить</a></b>
<form action="${pageContext.request.contextPath}/api/message" method="GET">
    <p><b>Получатель: </b>${user}</p>
    
<table border = '0' cellpadding = '5' width = '400'>
    <tr><td><b>Отправитель</b></td><td><b>Сообщение</b></td></tr>
		<c:forEach items="${mList}" var="message">
        <tr><td width = '100'>${message.sender}</td><td>${message.text}</td></tr>
		</c:forEach>
    </table>
</form>

</body>
</html>