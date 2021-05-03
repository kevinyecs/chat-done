import at.favre.lib.crypto.bcrypt.BCrypt;
import hu.alkfelj.dao.UserDAO;
import hu.alkfelj.dao.UserDAOimpl;
import hu.alkfelj.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        UserDAO userDAO = UserDAOimpl.getInstance();

        String username = request.getParameter("name");
        String password = request.getParameter("pw");

        User user = userDAO.login(username, password);

        if( user == null){
            response.sendRedirect("pages/login.jsp");
            return;
        }

        request.getSession().setAttribute("currentUser", user);
        response.sendRedirect("pages/landing.jsp");


    }
}
