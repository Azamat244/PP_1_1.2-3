package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    UserDao userDaoHibernate = new UserDaoHibernateImpl();


    public void createUsersTable() throws SQLException {
    userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoHibernate.saveUser(name,lastName,age);
        logger.info("User с именем — "+ name + " добавлен в базу данны");
    }

    public void removeUserById(long id) throws SQLException {
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        userDaoHibernate.cleanUsersTable();
    }


}
