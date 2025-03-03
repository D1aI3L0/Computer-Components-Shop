<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить клиента</title>
</head>
<body>
    <h1>Добавить нового клиента</h1>
    <form action="addClient" method="post">
        Имя: <input type="text" name="name"><br>
        Фамилия: <input type="text" name="surname"><br>
        Отчество: <input type="text" name="patronymic"><br>
        Телефон: <input type="text" name="phoneNumber"><br>
        Email: <input type="text" name="emailAddress" required><br>
        <input type="submit" value="Добавить">
    </form>
</body>
</html>