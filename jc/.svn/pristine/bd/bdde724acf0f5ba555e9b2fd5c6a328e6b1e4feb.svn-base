package com.hgsoft.util;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/9/9.
 */

public class LocalDBUtils {
    private static String className;
    private static String jdbcPath;
    private static String user;
    private static String password;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("driver");
        jdbcPath = resourceBundle.getString("jdbc.jdbcUrl");
        user = resourceBundle.getString("jdbc.username");
        password =resourceBundle.getString("jdbc.password");
        className = resourceBundle.getString("jdbc.driverClass");
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }

    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(jdbcPath, user, password);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            } finally {
                resultSet = null;
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            } finally {
                statement = null;
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            } finally {
                connection = null;
            }
        }

    }
}
