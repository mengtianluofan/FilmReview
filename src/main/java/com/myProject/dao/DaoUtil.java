package com.myProject.dao;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/15 18:33
 */
public class DaoUtil {
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driver = null;

    public static Connection getConnection() throws SQLException {
        try {
            Properties properties = new Properties();

            properties.load(DaoUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");

            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
