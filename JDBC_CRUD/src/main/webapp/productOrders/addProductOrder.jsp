<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить связь продукта и заказа</title>
</head>
<body>
    <h1>Добавить новую связь продукта и заказа</h1>
    <form action="addProductOrder" method="post">
        ID продукта: <input type="text" name="productId"><br>
        ID заказа: <input type="text" name="orderId"><br>
        <input type="submit" value="Добавить">
    </form>
</body>
</html>