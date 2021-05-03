<%@ page import="hu.alkfelj.model.User" %>
<%@ page import="hu.alkfelj.model.ChatRoom" %>
<%@ page import="hu.alkfelj.dao.UserDAO" %>
<%@ page import="hu.alkfelj.dao.UserDAOimpl" %>
<%@ page import="hu.alkfelj.dao.MessageDAO" %>
<%@ page import="hu.alkfelj.dao.MessageDAOimpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
    <script src="https://cdn.quilljs.com/1.3.6/quill.min.js"></script>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Chat Room</title>
</head>
<body>
<div class="overflow-auto h-50 border border-secondary">
<%
    int roomId = Integer.parseInt(request.getParameter("roomId"));
    User user = (User) session.getAttribute("currentUser");
    session.setAttribute("roomId", roomId);

    UserDAO dao = new UserDAOimpl();
    MessageDAO msg = new MessageDAOimpl();
    String users;

    for(int i = 0; i< msg.findAll().size(); i++) {
        if(msg.findAll().get(i).getRoom_id() == roomId) {
            if(dao.getUserById(msg.findAll().get(i).getUserid()) != null)   users = dao.getUserById(msg.findAll().get(i).getUserid()).getName();
            else users = "Deleted User";
            out.println(" <h4>" + msg.findAll().get(i).getSendTime() +"     "+  users + ":</h4> " + msg.findAll().get(i).getContent());
        }
    }

%>
</div>
<div class="container">
    <form action="../send-message" method="post">
        <div class="form-group">
            <div id="editor-container" style=" height: 25%"></div>
            <input name="content" type="hidden" class="form-control" id="content" placeholder="Type your message here..." value="">
        </div>
        <button type="submit" class="btn btn-primary">Send</button>
    </form>

</div>

<div id="output-html"></div>
<script>
    var quill = new Quill('#editor-container', {
        modules: {
            toolbar: [
                ['bold', 'italic', 'underline','strike'],
                ['image'],
            ]
        },
        placeholder: 'Type your message here..',
        theme: 'snow'
    });

    quill.on('text-change', function(delta, source) {
        updateHtmlOutput()
    })
    $('#btn-convert').on('click', () => { updateHtmlOutput() })
    function getQuillHtml() { return quill.root.innerHTML; }
    function updateHighlight() { hljs.highlightBlock( document.querySelector('#output-html') ) }
    function updateHtmlOutput()
    {
        let html = getQuillHtml();
        console.log ( html );
        document.getElementById('output-html').innerText = html;
        document.getElementById("content").value = html;
        updateHighlight();

    }
    updateHtmlOutput()
</script>

</body>
</html>
