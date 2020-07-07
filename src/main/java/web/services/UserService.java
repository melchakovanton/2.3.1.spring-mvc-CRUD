package web.services;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.models.User;

@Service
public class UserService {

    private static UserService instance;

    @Autowired
    private UserDao dao;

    @Transactional
    public void addUser(User user)
            throws SQLException {
        dao.addUser(user.getLogin(), user.getPassword(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public User getUserById(long id) throws SQLException {
        return dao.getUserById(id);
    }

    @Transactional(readOnly = true)
    public long getUserIdByLogin(String login) throws SQLException {
        return dao.getUserIdByLogin(login);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() throws SQLException {
        return dao.getAllUsers();
    }

    @Transactional
    public void updateUser(String login, String password, String email, long id)
            throws SQLException {
        dao.updateUser(login, password, email, id);
    }

    @Transactional
    public boolean deleteUser(long id) throws SQLException {
        return dao.deleteUser(id);
    }
}
