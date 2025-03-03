<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.models.Processor" %>
<%
    Processor processor = (Processor) request.getAttribute("processor");
%>
<html>
<head>
    <title>Редактировать процессор</title>
</head>
<body>
    <h1>Редактировать процессор</h1>
    <form action="updateProcessor" method="post">
        <input type="hidden" name="id" value="<%= processor.getId() %>">
        Количество потоков: <input type="text" name="threadsCount" value="<%= processor.getThreadsCount() %>"><br>
        Частота: <input type="text" name="clockFrequency" value="<%= processor.getClockFrequency() %>"><br>
        Макс. частота: <input type="text" name="maxFrequency" value="<%= processor.getMaxFrequency() %>"><br>
        Количество ядер: <input type="text" name="cpuCount" value="<%= processor.getCpuCount() %>"><br>
        <input type="submit" value="Обновить">
    </form>
</body>
</html>