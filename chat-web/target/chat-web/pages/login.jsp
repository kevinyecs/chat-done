<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<div class="container">
    <p>Login</p>
    <form action="../login" method="post">
        <div class="form-group">
            <label for="name">Nickname</label>
            <input name="name" type="text" class="form-control" id="name" placeholder="Nickname" required>
        </div>
        <div class="form-group">
            <label for="pw">Password</label>
            <input name="pw" type="password" class="form-control" id="pw" placeholder="Password" required>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
        <a href="./register.jsp">Register</a>
    </form>

</div>

</body>
</html>
