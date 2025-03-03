<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.models.Order" %>
<%
    Order order = (Order) request.getAttribute("order");
%>
<html>
<head>
    <title>Редактировать заказ</title>
</head>
<body>
    <h1>Редактировать заказ</h1>
    <form action="updateOrder" method="post">
        <input type="hidden" name="id" value="<%= order.getId() %>">
        Общая стоимость: <input type="text" name="totalPrice" value="<%= order.getTotalPrice() %>"><br>
        Дата заказа: <input type="text" name="orderDate" value="<%= order.getOrderDate() %>"><br>
        Статус: <input type="text" name="status" value="<%= order.getStatus() %>"><br>
        Метод оплаты: <input type="text" name="paymentMethod" value="<%= order.getPaymentMethod() %>"><br>
        ID клиента: <input type="text" name="clientId" value="<%= order.getClientId() %>"><br>
        <input type="submit" value="Обновить">
    </form>
</body>
</html>