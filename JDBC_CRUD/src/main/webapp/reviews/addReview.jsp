<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить отзыв</title>
</head>
<body>
    <h1>Добавить новый отзыв</h1>
    <form action="addReview" method="post">
        ID клиента: <input type="text" name="clientId"><br>
        ID продукта: <input type="text" name="productId"><br>
        Отзыв: <input type="text" name="review"><br>
        <input type="submit" value="Добавить">
    </form>
</body>
</html>