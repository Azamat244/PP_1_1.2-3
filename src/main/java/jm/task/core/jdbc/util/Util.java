package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/root";
    public static final String DB_USERNAME = "2443254";
    public static final String DB_PASSWORD = "2443254";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            System.out.println("Соединение с БД установлено");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
        return connection;
    }
}
