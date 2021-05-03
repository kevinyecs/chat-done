<%@ page import="hu.alkfelj.model.User" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="hu.alkfelj.config.UrlConfiguration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
    <script src="https://cdn.quilljs.com/1.3.6/quill.min.js"></script>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>

<body>
<div class="row">
<div class="col-md-4 col-md-offset-5 font-weight-bold">Search user:</div>
<div class="col-md-4 col-md-offset-5">
    <form action="" method="get">
        <input type="text" class="form-control" name="q" placeholder="Search by name">
    </form>
</div>
</div>

<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Intrest</th>
    </tr>
    <tbody>
    <%
        User user = (User) session.getAttribute("currentUser");
        String host = UrlConfiguration.getValue("db.url");
        Connection conn;
        Statement statement;
        ResultSet rs;

        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(host);

        statement = conn.createStatement();
        String query = request.getParameter("q");
        String data;
        if(query!=null){
            data = "select * from USER where name like '%"+query+"%' or intrest like '%"+query+"%'";
        }else{
            data = "SELECT * FROM USER ORDER BY id desc";
        }
        rs = statement.executeQuery(data);
        while(rs.next()){

    %>
    <tr>
        <td><%=rs.getString("id")%></td>
        <td><%=rs.getString("name")%></td>
        <td><%=rs.getString("email")%></td>
        <td><%=rs.getString("intrest")%></td>


    </tr>
<%}%>

</tbody>
    </thead>

</table>

<div class="row">
    <div class="col-md-4 col-md-offset-5 font-weight-bold">Search room by Name or Category:</div>
    <div class="col-md-4 col-md-offset-5">
        <form action="" method="get">
            <input type="text" class="form-control" name="q2" placeholder="search">
        </form>
    </div>
</div>

<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Neve</th>
        <th>Szabály</th>
        <th>Kategória</th>
        <th>Belépés</th>
    </tr>
    <tbody>
    <%
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(host);

        statement = conn.createStatement();
        query = request.getParameter("q2");
        if(query!=null){
            data = "select * from CHATROOM where name like '%"+query+"%' or category like '%"+query+"%'";
        }else{
            data = "SELECT * FROM CHATROOM ORDER BY id desc";
        }
        rs = statement.executeQuery(data);
        while(rs.next()){

    %>
    <tr>
        <td><%=rs.getString("id")%></td>
        <td><%=rs.getString("name")%></td>
        <td><%=rs.getString("rule")%></td>
        <td><%=rs.getString("category")%></td>
        <td><a href="chat.jsp?roomId=<%=rs.getString("id")%>"><i class="fas fa-edit">Belépek</i></a></td>
    </tr>
    <%}%>

    </tbody>
    </thead>

</table>

</body>
</html>
