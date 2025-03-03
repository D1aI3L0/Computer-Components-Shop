<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.models.Motherboard" %>
<%
    Motherboard motherboard = (Motherboard) request.getAttribute("motherboard");
%>
<html>
<head>
    <title>Редактировать материнскую плату</title>
</head>
<body>
    <h1>Редактировать материнскую плату</h1>
    <form action="updateMotherboard" method="post">
        <input type="hidden" name="id" value="<%= motherboard.getId() %>">
        Слоты памяти: <input type="text" name="memorySlots" value="<%= motherboard.getMemorySlots() %>"><br>
        Чипсет: <input type="text" name="chipset" value="<%= motherboard.getChipset() %>"><br>
        Форм-фактор: <input type="text" name="formFactor" value="<%= motherboard.getFormFactor() %>"><br>
        <input type="submit" value="Обновить">
    </form>
</body>
</html>