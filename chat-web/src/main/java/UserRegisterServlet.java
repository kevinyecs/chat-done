import hu.alkfelj.dao.UserDAO;
import hu.alkfelj.dao.UserDAOimpl;
import hu.alkfelj.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class UserRegisterServlet extends HttpServlet {

    UserDAO dao = UserDAOimpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("pw"));
        user.setSex(req.getParameter("sex"));
        user.setAge(req.getParameter("age"));
        user.setIntrest(req.getParameter("intrest"));

        dao.AddUser(user);

        resp.sendRedirect("pages/login.jsp");
    }
}
