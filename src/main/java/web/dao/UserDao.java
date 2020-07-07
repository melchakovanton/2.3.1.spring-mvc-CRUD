package web.dao;

import java.sql.SQLException;
import java.util.List;
import web.models.User;

public interface UserDao {

    boolean addUser(String login, String password, String email) throws SQLException;

    User getUserById(long id) throws SQLException;

    long getUserIdByLogin(String login) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    boolean updateUser(String newLogin, String password, String email, long id)
            throws SQLException;

    boolean deleteUser(long id) throws SQLException;

    boolean checkLogin(String login) throws SQLException;
}
