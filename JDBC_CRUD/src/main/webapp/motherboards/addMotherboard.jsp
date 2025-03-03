<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить материнскую плату</title>
</head>
<body>
    <h1>Добавить новую материнскую плату</h1>
    <form action="addMotherboard" method="post">
        Слоты памяти: <input type="text" name="memorySlots"><br>
        Чипсет: <input type="text" name="chipset"><br>
        Форм-фактор: <input type="text" name="formFactor"><br>
        <input type="submit" value="Добавить">
    </form>
</body>
</html>