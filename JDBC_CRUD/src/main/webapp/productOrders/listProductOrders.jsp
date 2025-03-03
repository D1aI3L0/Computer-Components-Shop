<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.models.ProductOrder" %>
<html>
<head>
    <title>Связи продуктов и заказов</title>
</head>
<body>
    <h1>Список связей продуктов и заказов</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>ID продукта</th>
            <th>ID заказа</th>
            <th>Действия</th>
        </tr>
        <%
            List<ProductOrder> productOrders = (List<ProductOrder>) request.getAttribute("productOrders");
            if(productOrders != null)
            for (ProductOrder productOrder : productOrders) {
        %>
        <tr>
            <td><%= productOrder.getId() %></td>
            <td><%= productOrder.getProductId() %></td>
            <td><%= productOrder.getOrderId() %></td>
            <td>
                <a href="productOrders/editProductOrder?id=<%= productOrder.getId() %>">Редактировать</a>
                <a href="productOrders/deleteProductOrder?id=<%= productOrder.getId() %>">Удалить</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="productOrders/addProductOrder.jsp">Добавить новую связь</a>
    <a href="productOrders/back">Назад</a>
</body>
</html>