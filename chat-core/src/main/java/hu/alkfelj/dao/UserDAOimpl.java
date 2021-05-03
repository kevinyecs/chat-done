package hu.alkfelj.dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import hu.alkfelj.config.UrlConfiguration;
import hu.alkfelj.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimpl implements UserDAO {
    private static UserDAOimpl instance;
    private static final String CONNECT_URL = UrlConfiguration.getValue("db.url");

    private static final String SELECT_ALL_USERS = "SELECT * FROM USER";
    private static final String DELETE_USER = "DELETE FROM USER WHERE id = ?";


    public static UserDAOimpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            instance = new UserDAOimpl();
        }
        return instance;
    }

    public UserDAOimpl() {
    }



    @Override
    public List<User> findAll() {

        List<User> result = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection c = DriverManager.getConnection(CONNECT_URL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName((rs.getString("name")));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getString("age"));
                user.setSex(rs.getString("sex"));
                user.setIntrest(rs.getString("intrest"));

                result.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public void delete(User user) {

        try(Connection c = DriverManager.getConnection(CONNECT_URL);
            PreparedStatement stmt = c.prepareStatement(DELETE_USER);
        ) {
            stmt.setInt(1,user.getId());
            stmt.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User login(String username, String password) {
        try (Connection conn = DriverManager.getConnection(CONNECT_URL);
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM USER WHERE name = ?")
        ) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                String dbPass = rs.getString("password");
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), dbPass);
                if(result.verified){
                    User user = new User();
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setId(rs.getInt("id"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void AddUser(User user) {
        try (Connection conn = DriverManager.getConnection(CONNECT_URL);
             PreparedStatement pst = conn.prepareStatement("INSERT INTO USER (name,email,password,age,sex,intrest) VALUES (?,?,?,?,?,?)")
        ) {

            String newPwd = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(newPwd);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4,user.getAge());
            pst.setString(5,user.getSex());
            pst.setString(6,user.getIntrest());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public User getUserById(int id) {
        try (Connection conn = DriverManager.getConnection(CONNECT_URL);
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM USER WHERE id = ?")
        ) {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setPassword(rs.getString(4));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
