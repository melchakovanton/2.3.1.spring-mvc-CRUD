<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Update User</title>
</head>
<body>
<h2>Update User:</h2>
<form action="/users/update" method="POST">
<%--    <label for="id">ID</label><br>--%>
<%--    <input id="id" name="id" type="text" value="${id}"><br>--%>
    <label for="login">Login</label><br>
    <input id="login" name="login" type="text" value="${user.getLogin()}"><br>
    <label for="password">Password</label><br>
    <input id="password" name="password" type="password" value="${user.getPassword()}"><br>
    <label for="email">Email</label><br>
    <input id="email" name="email" type="email" value="${user.getEmail()}"><br><br>
    <input type="hidden" name="id" value="${user.getId()}">
    <input type="submit" value="Update">
</form>
</body>
</html>
