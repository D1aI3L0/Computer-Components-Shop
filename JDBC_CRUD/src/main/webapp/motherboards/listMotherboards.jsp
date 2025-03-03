<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.models.Motherboard" %>
<html>
<head>
    <title>Материнские платы</title>
</head>
<body>
    <h1>Список материнских плат</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Слоты памяти</th>
            <th>Чипсет</th>
            <th>Форм-фактор</th>
            <th>Действия</th>
        </tr>
        <%
            List<Motherboard> motherboards = (List<Motherboard>) request.getAttribute("motherboards");
            if(motherboards != null)
            for (Motherboard motherboard : motherboards) {
        %>
        <tr>
            <td><%= motherboard.getId() %></td>
            <td><%= motherboard.getMemorySlots() %></td>
            <td><%= motherboard.getChipset() %></td>
            <td><%= motherboard.getFormFactor() %></td>
            <td>
                <a href="motherboards/editMotherboard?id=<%= motherboard.getId() %>">Редактировать</a>
                <a href="motherboards/deleteMotherboard?id=<%= motherboard.getId() %>">Удалить</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="motherboards/addMotherboard.jsp">Добавить новую материнскую плату</a>
    <a href="motherboards/back">Назад</a>
</body>
</html>