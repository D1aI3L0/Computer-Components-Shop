<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.models.Processor" %>
<html>
<head>
    <title>Процессоры</title>
</head>
<body>
    <h1>Список процессоров</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Количество потоков</th>
            <th>Частота</th>
            <th>Макс. частота</th>
            <th>Количество ядер</th>
            <th>Действия</th>
        </tr>
        <%
            List<Processor> processors = (List<Processor>) request.getAttribute("processors");
            if(processors != null)
            for (Processor processor : processors) {
        %>
        <tr>
            <td><%= processor.getId() %></td>
            <td><%= processor.getThreadsCount() %></td>
            <td><%= processor.getClockFrequency() %></td>
            <td><%= processor.getMaxFrequency() %></td>
            <td><%= processor.getCpuCount() %></td>
            <td>
                <a href="processors/editProcessor?id=<%= processor.getId() %>">Редактировать</a>
                <a href="processors/deleteProcessor?id=<%= processor.getId() %>">Удалить</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="processors/addProcessor.jsp">Добавить новый процессор</a>
    <a href="processors/back">Назад</a>
</body>
</html>