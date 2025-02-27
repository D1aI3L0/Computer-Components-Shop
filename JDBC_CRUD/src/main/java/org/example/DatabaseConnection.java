package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    static Properties props;

    public static void Init() throws IOException {
        props = new Properties();
        try {
            InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream(".dbconfig.properties");
            props.load(input);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.username"), props.getProperty("db.password"));
    }
}