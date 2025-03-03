<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.models.Product" %>
<html>
<head>
    <title>Продукты</title>
</head>
<body>
    <h1>Список продуктов</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Производитель</th>
            <th>Тип</th>
            <th>Цена</th>
            <th>Действия</th>
        </tr>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if(products != null)
            for (Product product : products) {
        %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getManufacturer() %></td>
            <td><%= product.getType() %></td>
            <td><%= product.getPrice() %></td>
            <td>
                <a href="products/editProduct?id=<%= product.getId() %>">Редактировать</a>
                <a href="products/deleteProduct?id=<%= product.getId() %>">Удалить</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="products/addProduct.jsp">Добавить новый продукт</a>
    <a href="products/back">Назад</a>
</body>
</html>