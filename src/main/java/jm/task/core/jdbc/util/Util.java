package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class Util {
    // реализуйте настройку соеденения с БД

    private static SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(Util.class.getName());
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/root");
                settings.put(Environment.USER, "2443254");
                settings.put(Environment.PASS, "2443254");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");
                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                logger.info("Соединение с БД установлено");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}



//    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
//    public static final String DB_URL = "jdbc:mysql://localhost:3306/root";
//    public static final String DB_USERNAME = "2443254";
//    public static final String DB_PASSWORD = "2443254";
//
//    public static Connection getConnection(){
//        Connection connection = null;
//        try {
//            Class.forName(DB_DRIVER);
//            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
//            System.out.println("Соединение с БД установлено");
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Соединение с БД не установлено");
//        }
//        return connection;
//    }

