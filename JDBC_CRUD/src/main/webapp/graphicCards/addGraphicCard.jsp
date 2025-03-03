<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить видеокарту</title>
</head>
<body>
    <h1>Добавить новую видеокарту</h1>
    <form action="addGraphicCard" method="post">
        Количество GPU: <input type="text" name="gpuCount"><br>
        Частота GPU: <input type="text" name="gpuFrequency"><br>
        Объем памяти: <input type="text" name="memoryCount"><br>
        Частота памяти: <input type="text" name="memoryFrequency"><br>
        <input type="submit" value="Добавить">
    </form>
</body>
</html>