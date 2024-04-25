package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {



    public UserDaoJDBCImpl() {

    }

    //create
    public void createUsersTable() throws RuntimeException, SQLException {
        //создание таблицы
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "lastName VARCHAR(255)," +
                "age INT)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public void dropUsersTable() throws SQLException {
        //полностью удаляет таблицу
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS users";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        //создание пользователя в таблице create
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO users ( name, lastName, age) VALUES (?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    public void removeUserById(long id) throws SQLException {
        //удаление пользователя по ай ди delete
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM users WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            } if (connection != null) {
                connection.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        // получение списка пользователей  read
        Connection connection = Util.getConnection();
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

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
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Обработка исключения
                }
            } if (connection != null) {
                connection.close();
            }
        } return usersList;
    }

    public void cleanUsersTable() throws SQLException {
        //очищает таблицу от данных
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM users";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}


