package com.hgsoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hgsoft.cxf.common.PropertiesUtil;

public class DBUtil {

	private static final String DBDRIVER = PropertiesUtil.getProperty("DBDRIVER");// 驱动类类名

	//private static final String DBNAME = "huNanStation";// 数据库名

	private static final String DBURL = PropertiesUtil.getProperty("DBURL");// 连接URL

	private static final String DBUSER = PropertiesUtil.getProperty("DBUSER");// 数据库用户名

	private static final String DBPASSWORD = PropertiesUtil.getProperty("DBPASSWORD");// 数据库密码
	
	private static final String LOCALURL = PropertiesUtil.getProperty("LOCALURL");// 本地连接URL

	private static final String LOCALUSER = PropertiesUtil.getProperty("LOCALUSER");// 本地数据库用户名

	private static final String LOCALPASSWORD = PropertiesUtil.getProperty("LOCALPASSWORD");// 本地数据库密码

	private static Connection conn = null;

	private static PreparedStatement ps = null;

	private static ResultSet rs = null;

	//获取数据库连接
	public static Connection getConnection() {

		try {

			Class.forName(DBDRIVER);// 注册驱动

			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);// 获得连接对象

		} catch (ClassNotFoundException e) {// 捕获驱动类无法找到异常

			e.printStackTrace();

		} catch (SQLException e) {// 捕获SQL异常

			e.printStackTrace();
		}
		return conn;

	}
	
	//获取本地数据库连接
		public static Connection getLocalConnection() {

			try {

				Class.forName(DBDRIVER);// 注册驱动

				conn = DriverManager.getConnection(LOCALURL, LOCALUSER, LOCALPASSWORD);// 获得连接对象

			} catch (ClassNotFoundException e) {// 捕获驱动类无法找到异常

				e.printStackTrace();

			} catch (SQLException e) {// 捕获SQL异常

				e.printStackTrace();
			}
			return conn;

		}
		
	//获取数据库连接
	public static Connection getConnection(String dbdriver,String dburl,String dbuser,String dbpassword) {

		try {

			Class.forName(dbdriver);// 注册驱动

			conn = DriverManager.getConnection(dburl, dbuser, dbpassword);// 获得连接对象

		} catch (ClassNotFoundException e) {// 捕获驱动类无法找到异常

			e.printStackTrace();

		} catch (SQLException e) {// 捕获SQL异常

			e.printStackTrace();
		}
		return conn;

	}
	
	public static void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	// 查询数据

	public static List<Object[]> executeQuery(String sql,Connection conn) throws Exception {

		try {

			//conn = getConnection();

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			return convertList(rs);

		} catch (SQLException sqle) {

			throw new SQLException("select data Exception: " + sqle.getMessage());

		} catch (Exception e) {

			throw new Exception("System error: " + e.getMessage());

		}
		finally {

			try {

				if (ps != null) {

					ps.close();

				}

			} catch (Exception e) {

				throw new Exception("ps close exception: " + e.getMessage());

			}

			try {

				if (conn != null) {

					conn.close();

				}

			} catch (Exception e) {

				throw new Exception("conn close exception: " + e.getMessage());

			}

		}


	}

	// 更新

	public static int executeUpdate(String sql,Connection conn) throws Exception {

		int num = 0;// 计数

		try {

			//conn = getConnection();

			ps = conn.prepareStatement(sql);

			num = ps.executeUpdate();

		} catch (SQLException sqle) {

			throw new SQLException("insert data Exception: " + sqle.getMessage());

		} finally {

			try {

				if (ps != null) {

					ps.close();

				}

			} catch (Exception e) {

				throw new Exception("ps close exception: " + e.getMessage());

			}

			try {

				if (conn != null) {

					conn.close();

				}

			} catch (Exception e) {

				throw new Exception("conn close exception: " + e.getMessage());

			}

		}

		return num;

	}
	
	private static List<Object []> convertList(ResultSet rs) throws SQLException {
        List<Object []> list = new ArrayList<Object []>();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        while (rs.next()) {
        	Object [] obj = new Object [columnCount];
            for (int i = 1; i <= columnCount; i++) {
            	obj[i-1]=rs.getObject(i);
            }

            list.add(obj);
        }
        return list;
	}

	
}
