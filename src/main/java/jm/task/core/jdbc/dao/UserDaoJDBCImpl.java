package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class UserDaoJDBCImpl implements UserDao {



    public UserDaoJDBCImpl() {

    }

    //create
    public void createUsersTable() throws RuntimeException, SQLException {
        //создание таблицы

        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "lastName VARCHAR(255)," +
                "age INT)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() throws SQLException {
        //полностью удаляет таблицу
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        //создание пользователя в таблице create

        String sql = "INSERT INTO users ( name, lastName, age) VALUES (?,?,?)";

        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) throws SQLException {
        //удаление пользователя по ай ди delete

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        // получение списка пользователей  read

        List<User> usersList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                User user1 = new User();
                user1.setId(resultSet.getLong(1));
                user1.setName(resultSet.getString("name"));
                user1.setLastName(resultSet.getString("lastName"));
                user1.setAge(resultSet.getByte("age"));

                usersList.add(user1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }  return usersList;
    }

    public void cleanUsersTable() throws SQLException {
        //очищает таблицу от данных
        String sql = "DELETE FROM users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


