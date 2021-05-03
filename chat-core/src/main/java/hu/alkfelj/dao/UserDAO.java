package hu.alkfelj.dao;
import java.util.List;
import hu.alkfelj.model.User;


public interface UserDAO {
    List<User> findAll();
    void delete(User users);
    User login(String username, String password);
    void AddUser(User user);
    User getUserById(int id);
}
