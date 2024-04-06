package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUsersTable() throws SQLException
//            throws RuntimeException, SQLException
            ;

    void dropUsersTable() throws SQLException
//            throws SQLException
            ;

    void saveUser(String name, String lastName, byte age) throws SQLException
//            throws SQLException
            ;

    void removeUserById(long id) throws SQLException
//            throws SQLException
            ;

    List<User> getAllUsers() throws SQLException;

    void cleanUsersTable() throws SQLException
//            throws SQLException
            ;
}
