<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.models.Client" %>
<html>
<head>
    <title>Клиенты</title>
</head>
<body>
    <h1>Список клиентов</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Отчество</th>
            <th>Телефон</th>
            <th>Email</th>
            <th>Действия</th>
        </tr>
        <%
            List<Client> clients = (List<Client>) request.getAttribute("clients");
            if (clients != null)
            for (Client client : clients) {
        %>
        <tr>
            <td><%= client.getId() %></td>
            <td><%= client.getName() %></td>
            <td><%= client.getSurname() %></td>
            <td><%= client.getPatronymic() %></td>
            <td><%= client.getPhoneNumber() %></td>
            <td><%= client.getEmailAddress() %></td>
            <td>
                <a href="clients/editClient?id=<%= client.getId() %>">Редактировать</a>
                <a href="clients/deleteClient?id=<%= client.getId() %>">Удалить</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="clients/addClient.jsp">Добавить нового клиента</a>
    <a href="clients/back">Назад</a>
</body>
</html>