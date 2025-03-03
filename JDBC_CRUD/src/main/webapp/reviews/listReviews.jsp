<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.models.Review" %>
<html>
<head>
    <title>Отзывы</title>
</head>
<body>
    <h1>Список отзывов</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>ID клиента</th>
            <th>ID продукта</th>
            <th>Отзыв</th>
            <th>Действия</th>
        </tr>
        <%
            List<Review> reviews = (List<Review>) request.getAttribute("reviews");
            if(reviews != null)
            for (Review review : reviews) {
        %>
        <tr>
            <td><%= review.getId() %></td>
            <td><%= review.getClientId() %></td>
            <td><%= review.getProductId() %></td>
            <td><%= review.getReview() %></td>
            <td>
                <a href="reviews/editReview?id=<%= review.getId() %>">Редактировать</a>
                <a href="reviews/deleteReview?id=<%= review.getId() %>">Удалить</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="reviews/addReview.jsp">Добавить новый отзыв</a>
    <a href="reviews/back">Назад</a>
</body>
</html>