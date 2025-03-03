<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.models.Client" %>
<%
    Client client = (Client) request.getAttribute("client");
%>
<html>
<head>
    <title>Редактировать клиента</title>
</head>
<body>
    <h1>Редактировать клиента</h1>
    <form action="updateClient" method="post">
        <input type="hidden" name="id" value="<%= client.getId() %>">
        Имя: <input type="text" name="name" value="<%= client.getName() %>"><br>
        Фамилия: <input type="text" name="surname" value="<%= client.getSurname() %>"><br>
        Отчество: <input type="text" name="patronymic" value="<%= client.getPatronymic() %>"><br>
        Телефон: <input type="text" name="phoneNumber" value="<%= client.getPhoneNumber() %>"><br>
        Email: <input type="text" name="emailAddress" value="<%= client.getEmailAddress() %>"><br>
        <input type="submit" value="Обновить">
    </form>
</body>
</html>