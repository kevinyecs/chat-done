
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"></head>
<body>
<div class="container">
    <p>Register</p>
    <form action="../registration" method="post">
        <div class="form-group">
            <label for="email">Email address</label>
            <input name="email" type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email" required>
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="name">Nickname</label>
            <input name="name" type="text" class="form-control" id="name" placeholder="Nickname" required>
        </div>
        <div class="form-group">
            <label for="age">Age</label>
            <input name="age" type="text" class="form-control" id="age" placeholder="Age" required>
        </div>
        <div class="form-group">
            <label for="sex">Sex</label>
            <input name="sex" type="text" class="form-control" id="sex" placeholder="Man/Women" required>
        </div>
        <div class="form-group">
            <label for="intrest">Intrest</label>
            <input name="intrest" type="text" class="form-control" id="intrest" placeholder="I love to hike and drinking wine.." required>
        </div>
        <div class="form-group">
            <label for="pw">Password</label>
            <input name="pw" type="password" class="form-control" id="pw" placeholder="Password" required>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="./login.jsp">Login</a>
    </form>
</div>

</body>
</html>

