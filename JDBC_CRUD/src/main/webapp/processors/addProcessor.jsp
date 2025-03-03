<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить процессор</title>
</head>
<body>
    <h1>Добавить новый процессор</h1>
    <form action="addProcessor" method="post">
        Количество потоков: <input type="text" name="threadsCount"><br>
        Частота: <input type="text" name="clockFrequency"><br>
        Макс. частота: <input type="text" name="maxFrequency"><br>
        Количество ядер: <input type="text" name="cpuCount"><br>
        <input type="submit" value="Добавить">
    </form>
</body>
</html>