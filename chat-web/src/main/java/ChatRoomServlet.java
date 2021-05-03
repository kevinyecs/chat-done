import hu.alkfelj.dao.ChatRoomDAO;
import hu.alkfelj.dao.ChatRoomDAOimpl;
import hu.alkfelj.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/chat")
public class ChatRoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int currentChatRoom = Integer.parseInt(request.getParameter("roomId"));
        User currentUser = (User) request.getSession().getAttribute("currentUser");

        request.getSession().setAttribute("currentUser", currentUser);
        request.getSession().setAttribute("roomId", currentChatRoom);
        request.getRequestDispatcher("pages/chat.jsp").forward(request,resp);
    }


}
