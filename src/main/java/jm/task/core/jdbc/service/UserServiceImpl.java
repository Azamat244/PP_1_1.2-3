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


    public void createUsersTable()  {
            userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable()  {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age)  {
        userDaoHibernate.saveUser(name,lastName,age);
        logger.info("User с именем — "+ name + " добавлен в базу данны");
    }

    public void removeUserById(long id)  {
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers()  {
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable()  {
        userDaoHibernate.cleanUsersTable();
    }


}
