<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h3>User list</h3>

<form action="users" method="post">
    <p>
        <select name="user">
            <option disabled>Choose user</option>
            <option value="1">User</option>
            <option value="2">Admin</option>
        </select>
    </p>
    <p><input type="submit" value="Login"></p>
</form>
</body>
</html>
