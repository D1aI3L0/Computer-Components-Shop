<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.models.GraphicCard" %>
<html>
<head>
    <title>Видеокарты</title>
</head>
<body>
    <h1>Список видеокарт</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Количество GPU</th>
            <th>Частота GPU</th>
            <th>Объем памяти</th>
            <th>Частота памяти</th>
            <th>Действия</th>
        </tr>
        <%
            List<GraphicCard> graphicCards = (List<GraphicCard>) request.getAttribute("graphicCards");
            if(graphicCards != null)
            for (GraphicCard graphicCard : graphicCards) {
        %>
        <tr>
            <td><%= graphicCard.getId() %></td>
            <td><%= graphicCard.getGpuCount() %></td>
            <td><%= graphicCard.getGpuFrequency() %></td>
            <td><%= graphicCard.getMemoryCount() %></td>
            <td><%= graphicCard.getMemoryFrequency() %></td>
            <td>
                <a href="graphicCards/editGraphicCard?id=<%= graphicCard.getId() %>">Редактировать</a>
                <a href="graphicCards/deleteGraphicCard?id=<%= graphicCard.getId() %>">Удалить</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="graphicCards/addGraphicCard.jsp">Добавить новую видеокарту</a>
    <a href="graphicCards/back">Назад</a>
</body>
</html>