<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.models.GraphicCard" %>
<%
    GraphicCard graphicCard = (GraphicCard) request.getAttribute("graphicCard");
%>
<html>
<head>
    <title>Редактировать видеокарту</title>
</head>
<body>
    <h1>Редактировать видеокарту</h1>
    <form action="updateGraphicCard" method="post">
        <input type="hidden" name="id" value="<%= graphicCard.getId() %>">
        Количество GPU: <input type="text" name="gpuCount" value="<%= graphicCard.getGpuCount() %>"><br>
        Частота GPU: <input type="text" name="gpuFrequency" value="<%= graphicCard.getGpuFrequency() %>"><br>
        Объем памяти: <input type="text" name="memoryCount" value="<%= graphicCard.getMemoryCount() %>"><br>
        Частота памяти: <input type="text" name="memoryFrequency" value="<%= graphicCard.getMemoryFrequency() %>"><br>
        <input type="submit" value="Обновить">
    </form>
</body>
</html>