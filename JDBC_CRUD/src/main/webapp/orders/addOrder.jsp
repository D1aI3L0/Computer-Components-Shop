<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить заказ</title>
</head>
<body>
    <h1>Добавить новый заказ</h1>
    <form action="addOrder" method="post">
        Общая стоимость: <input type="text" name="totalPrice"><br>
        Дата заказа: <input type="text" name="orderDate"><br>
        Статус: <input type="text" name="status"><br>
        Метод оплаты: <input type="text" name="paymentMethod"><br>
        ID клиента: <input type="text" name="clientId"><br>
        <input type="submit" value="Добавить">
    </form>
</body>
</html>