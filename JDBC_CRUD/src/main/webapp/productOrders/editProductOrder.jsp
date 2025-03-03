<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.models.ProductOrder" %>
<%
    ProductOrder productOrder = (ProductOrder) request.getAttribute("productOrder");
%>
<html>
<head>
    <title>Редактировать связь продукта и заказа</title>
</head>
<body>
    <h1>Редактировать связь продукта и заказа</h1>
    <form action="updateProductOrder" method="post">
        <input type="hidden" name="id" value="<%= productOrder.getId() %>">
        ID продукта: <input type="text" name="productId" value="<%= productOrder.getProductId() %>"><br>
        ID заказа: <input type="text" name="orderId" value="<%= productOrder.getOrderId() %>"><br>
        <input type="submit" value="Обновить">
    </form>
</body>
</html>