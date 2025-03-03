<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.models.Review" %>
<%
    Review review = (Review) request.getAttribute("review");
%>
<html>
<head>
    <title>Редактировать отзыв</title>
</head>
<body>
    <h1>Редактировать отзыв</h1>
    <form action="updateReview" method="post">
        <input type="hidden" name="id" value="<%= review.getId() %>">
        ID клиента: <input type="text" name="clientId" value="<%= review.getClientId() %>"><br>
        ID продукта: <input type="text" name="productId" value="<%= review.getProductId() %>"><br>
        Отзыв: <input type="text" name="review" value="<%= review.getReview() %>"><br>
        <input type="submit" value="Обновить">
    </form>
</body>
</html>