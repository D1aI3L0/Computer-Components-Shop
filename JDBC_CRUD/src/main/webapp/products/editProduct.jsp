<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.models.Product" %>
<%
    Product product = (Product) request.getAttribute("product");
%>
<html>
<head>
    <title>Редактировать продукт</title>
</head>
<body>
    <h1>Редактировать продукт</h1>
    <form action="updateProduct" method="post">
        <input type="hidden" name="id" value="<%= product.getId() %>">
        Название: <input type="text" name="name" value="<%= product.getName() %>"><br>
        Производитель: <input type="text" name="manufacturer" value="<%= product.getManufacturer() %>"><br>
        Тип: <input type="text" name="type" value="<%= product.getType() %>"><br>
        Цена: <input type="text" name="price" value="<%= product.getPrice() %>"><br>
        ID процессора: <input type="text" name="processorId" value="<%= product.getProcessorId() %>"><br>
        ID материнской платы: <input type="text" name="motherboardId" value="<%= product.getMotherboardId() %>"><br>
        ID видеокарты: <input type="text" name="graphicCardId" value="<%= product.getGraphicCardId() %>"><br>
        <input type="submit" value="Обновить">
    </form>
</body>
</html>