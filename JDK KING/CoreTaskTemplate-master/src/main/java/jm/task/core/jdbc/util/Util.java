package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    private final static String DB_NAME = "root";
    private final static String DB_password = "2468013579ZAQ";

    public static Connection getConnection() {
        Connection conn = null;
    try{
        conn = DriverManager.getConnection(DB_URL, DB_NAME , DB_password);
    } catch (SQLException e) {
        e.printStackTrace();

    }

        return conn;
    }
}


