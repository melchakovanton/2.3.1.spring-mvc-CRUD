<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3>Users</h3>
<br/>
<a href="<c:url value="/users"/>" target="_blank">Users list</a>
<br/>
<a href="<c:url value="/users/add"/>" target="_blank">Add User</a>
<br/>
<a href="<c:url value="/users/delete"/>" target="_blank">Delete User</a>
<br/>
<a href="<c:url value="/users/update"/>" target="_blank">Update User</a>
<br/>
</body>
</html>
