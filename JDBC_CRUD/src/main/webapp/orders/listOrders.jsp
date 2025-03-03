<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.models.Order" %>
<html>
<head>
    <title>Заказы</title>
</head>
<body>
    <h1>Список заказов</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Общая стоимость</th>
            <th>Дата заказа</th>
            <th>Статус</th>
            <th>Метод оплаты</th>
            <th>ID клиента</th>
            <th>Действия</th>
        </tr>
        <%
            List<Order> orders = (List<Order>) request.getAttribute("orders");
            if(orders != null)
            for (Order order : orders) {
        %>
        <tr>
            <td><%= order.getId() %></td>
            <td><%= order.getTotalPrice() %></td>
            <td><%= order.getOrderDate() %></td>
            <td><%= order.getStatus() %></td>
            <td><%= order.getPaymentMethod() %></td>
            <td><%= order.getClientId() %></td>
            <td>
                <a href="orders/editOrder?id=<%= order.getId() %>">Редактировать</a>
                <a href="orders/deleteOrder?id=<%= order.getId() %>">Удалить</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="orders/addOrder.jsp">Добавить новый заказ</a>
    <a href="orders/back">Назад</a>
</body>
</html>