<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>All Users</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 15px;
        }
        th {
            text-align: left;
        }
        p    {color: red;}
    </style>

</head>
<body>
<table style="width:100%">
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Password</th>
        <th>Email</th>
        <th>Change</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.getId()}</td>
            <td>${item.getLogin()}</td>
            <td>${item.getPassword()}</td>
            <td>${item.getEmail()}</td>
            <td><form action="/users/update" method="GET"><input type="hidden" name="id" value="${item.getId()}"><input type="submit" value="Change"></form></td>
            <td><form action="/users/delete" method="POST"><input type="hidden" name="id" value="${item.getId()}"><input type="submit" value="Delete"></form></td>
        </tr>
    </c:forEach>
</table>

<br><br>
<h2>Add a New User:</h2>
<form action="/users/add" method="POST">
    <label for="login">Login</label><br>
    <input id="login" name="login" type="text"><br>
    <label for="password">Password</label><br>
    <input id="password" name="password" type="password"><br>
    <label for="email">Email</label><br>
    <input id="email" name="email" type="email"><br><br>
    <input type="submit" value="Add">
</form>
</body>
</html>
