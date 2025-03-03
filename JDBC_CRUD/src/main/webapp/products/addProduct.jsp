<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить продукт</title>
</head>
<body>
    <h1>Добавить новый продукт</h1>
    <form action="addProduct" method="post">
        Название: <input type="text" name="name"><br>
        Производитель: <input type="text" name="manufacturer"><br>
        Тип: <input type="text" name="type"><br>
        Цена: <input type="text" name="price"><br>
        ID процессора: <input type="text" name="processorId"><br>
        ID материнской платы: <input type="text" name="motherboardId"><br>
        ID видеокарты: <input type="text" name="graphicCardId"><br>
        <input type="submit" value="Добавить">
    </form>
</body>
</html>