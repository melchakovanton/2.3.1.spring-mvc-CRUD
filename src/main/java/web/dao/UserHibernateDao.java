package web.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.models.User;

@Repository
public class UserHibernateDao implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public boolean addUser(String login, String password, String email) {
        if (!checkLogin(login)) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            boolean result = (Long) session.save("User", new User(login, password, email)) != 0;
            transaction.commit();
            session.close();
            return result;
        } else {
            return false;
        }
    }

    @Override
    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        User result = (User) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public long getUserIdByLogin(String login) {
        long result;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        Object tempResult = criteria.add(Restrictions.eq("login", login)).uniqueResult();
        if (tempResult != null) {
            result = ((User) tempResult).getId();
        } else {
            result = -1;
        }
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        List<User> result = new ArrayList<User>(criteria.list());
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public boolean updateUser(String login, String password, String email, long id) {
        if (checkLogin(getUserById(id).getLogin())) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            User changedUser = (User) criteria.add(Restrictions.eq("id", id)).uniqueResult();
            session.evict(changedUser);
            changedUser.setLogin(login);
            changedUser.setPassword(password);
            changedUser.setEmail(email);
            boolean result = session.merge(changedUser) != null;
            transaction.commit();
            session.close();
            return result;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUser(long id) {
        if (checkLogin(getUserById(id).getLogin())) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            User deletedUser = (User) criteria.add(Restrictions.eq("id", id)).uniqueResult();
            session.delete(deletedUser);
            transaction.commit();
            session.close();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        boolean result = criteria.add(Restrictions.eq("login", login)).uniqueResult() != null;
        transaction.commit();
        session.close();
        return result;
    }
}
