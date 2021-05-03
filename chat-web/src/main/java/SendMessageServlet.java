import hu.alkfelj.dao.MessageDAO;
import hu.alkfelj.dao.MessageDAOimpl;
import hu.alkfelj.model.User;
import hu.alkfelj.model.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/send-message")
public class SendMessageServlet extends HttpServlet {

    MessageDAO uzenetdao = MessageDAOimpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        User currentUser =(User) req.getSession().getAttribute("currentUser");
        int currentChatRoom = (int) req.getSession().getAttribute("roomId");
        System.out.println(currentChatRoom);

        String content = req.getParameter("content");
        LocalDateTime mikor = LocalDateTime.now();

        Message uzenet = new Message();
        uzenet.setContent(content);
        uzenet.setSendTime(mikor);
        uzenet.setRoom_id(currentChatRoom);
        uzenet.setUserid(currentUser.getId());

        uzenetdao.addUzenet(uzenet);

        resp.sendRedirect("pages/chat.jsp?roomId="+currentChatRoom);
    }

}
