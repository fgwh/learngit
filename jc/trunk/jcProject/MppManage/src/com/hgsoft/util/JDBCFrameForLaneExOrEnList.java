package com.hgsoft.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 郭志强
 * @time 2016/9/7 17:39
 */
public class JDBCFrameForLaneExOrEnList {

    private Connection        connection;
    private PreparedStatement pstmt;
    private ResultSet         resultSet;

    public JDBCFrameForLaneExOrEnList(Connection connection) {
        this.connection = connection;
    }

    /**
     * 查询多条记录
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> list  = new ArrayList<Map<String, Object>>();
        int                       index = 1;

        pstmt = connection.prepareStatement(sql);

        if ((params != null) &&!params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        resultSet = pstmt.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int               cols_len = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();

            for (int i = 0; i < cols_len; i++) {
                String cols_name  = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);

                if (cols_value == null) {
                    cols_value = "";
                }

                map.put(cols_name, cols_value);
            }

            list.add(map);
        }

        return list;
    }

    /**
     * 通过反射机制查询多条记录
     *
     * @param sql
     * @param params
     * @param cls
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> findMoreRefResult(String sql, List<Object> params, Class<T> cls) throws Exception {
        List<T> list  = new ArrayList<T>();
        int     index = 1;

        pstmt = connection.prepareStatement(sql);

        if ((params != null) &&!params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        resultSet = pstmt.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int               cols_len = metaData.getColumnCount();

        while (resultSet.next()) {

            // 通过反射机制创建一个实例
            T resultObject = cls.newInstance();

            for (int i = 0; i < cols_len; i++) {
                String cols_name  = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);

                if (cols_value == null) {
                    cols_value = "";
                }

                Field field = cls.getDeclaredField(cols_name);

                field.setAccessible(true);    // 打开javabean的访问权限
                field.set(resultObject, cols_value);
            }

            list.add(resultObject);
        }

        return list;
    }

    /**
     * 通过反射机制查询单条记录
     *
     * @param sql
     * @param params
     * @param cls
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T findSimpleRefResult(String sql, List<Object> params, Class<T> cls) throws Exception {
        T   resultObject = null;
        int index        = 1;

        pstmt = connection.prepareStatement(sql);

        if ((params != null) &&!params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        resultSet = pstmt.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int               cols_len = metaData.getColumnCount();

        while (resultSet.next()) {

            // 通过反射机制创建一个实例
            resultObject = cls.newInstance();

            for (int i = 0; i < cols_len; i++) {
                String cols_name  = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);

                if (cols_value == null) {
                    cols_value = "";
                }

                Field field = cls.getDeclaredField(cols_name);

                field.setAccessible(true);    // 打开javabean的访问权限
                field.set(resultObject, cols_value);
            }
        }

        return resultObject;
    }

    /**
     * 查询单条记录
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public Map<String, Object> findSimpleResult(String sql, List<Object> params) throws SQLException {
        Map<String, Object> map   = new HashMap<String, Object>();
        int                 index = 1;

        pstmt = connection.prepareStatement(sql);

        if ((params != null) &&!params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        resultSet = pstmt.executeQuery();    // 返回查询结果

        ResultSetMetaData metaData = resultSet.getMetaData();
        int               col_len  = metaData.getColumnCount();

        while (resultSet.next()) {
            for (int i = 0; i < col_len; i++) {
                String cols_name  = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);

                if (cols_value == null) {
                    cols_value = "";
                }

                map.put(cols_name, cols_value);
            }
        }

        return map;
    }

    /**
     * 释放数据库连接
     */
    public void releaseConn() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 获得数据库的连接
     *
     * @param sql
     * @param params
     * @return
     *
     * @throws SQLException
     */

//  public Connection getConnection(){
//      try {
//          connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//      } catch (SQLException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      }
//      return connection;
//  }

    /**
     * 增加、删除、改
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public boolean updateByPreparedStatement(String sql, List<Object> params) throws SQLException {
        boolean flag   = false;
        int     result = -1;

        pstmt = connection.prepareStatement(sql);

        int index = 1;

        if ((params != null) &&!params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        result = pstmt.executeUpdate();
        flag   = (result > 0)
                 ? true
                 : false;

        return flag;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Method description
     *
     *
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static void release(Connection connection,Statement statement,ResultSet resultSet) {

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


//~ Formatted by Jindent --- http://www.jindent.com
