package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {


    private DatabaseConnection() {}

    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            Properties properties = new Properties();
            properties.load(DatabaseConnection.class.getClassLoader().getResourceAsStream(".dbconfig.properties"));

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при подключении к базе данных", e);
        }
        return connection;
    }
}