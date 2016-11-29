package com.hgsoft.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private static DataSource dataSource;
	private final static Class<ApplicationContextUtils> clazz = ApplicationContextUtils.class;
	private final static String TASKNAME = "Spring上下文";
	private final static Log logger = LogFactory
			.getLog(ApplicationContextUtils.class);
	private final static SimpleDateFormat dateTimeSdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextUtils.applicationContext = applicationContext;

		ApplicationContextUtils.dataSource = ApplicationContextUtils.applicationContext
				.getBean("dataSource", DataSource.class);
	}

	/**
	 * 获取Spring上下文对象
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取数据源
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			writeLog(e, logger, clazz, TASKNAME);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				writeLog(e, logger, clazz, TASKNAME);
				throw new RuntimeException(e);
			}

		}
	}

	/**
	 * 返回DirUtils对象
	 * @return
	 */
	public static DirUtils getDirUtils() {
		if (applicationContext != null) {
			return applicationContext.getBean("dirUtils", DirUtils.class);
		}
		return null;
	}

	/**
	 * 写出错误日志
	 * 
	 * @param e
	 * @param logger
	 * @param taskName
	 */
	@SuppressWarnings("rawtypes")
	public final static void writeLog(Exception e, Log logger, Class clazz,
			String taskName) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			final String str = sw.toString();
			// logger.error("["+clazz.getName()+","+dateTimeSdf.format(new
			// Date())+"]: "+str);
			logger.error("[" + taskName + ":" + clazz.getName() + ","
					+ dateTimeSdf.format(new Date()) + "]: " + str);
		} finally {
			try {
				if (sw != null) {
					sw.close();
					sw = null;
				}
				if (pw != null) {
					pw.close();
					pw = null;
				}
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
	}

}
