package com.hgsoft.test;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceFactory {
	private static ComboPooledDataSource c3p0 = null;
	private static ApplicationContext context = null;
	static {
		init();
	}

	private static final void init() {
		context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		c3p0 = context.getBean("dataSource", ComboPooledDataSource.class);
	}

	public static DataSource getDataSource() {
		if (c3p0 == null) {
			init();
		}
		return c3p0;
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(c3p0);
		OperationDAO operation = new OperationDAO();
		// List<String> tables = operation.getTableNames("FTP");
		// System.out.println(tables);

		final String database = "FTP";
		final String tableName = "dbo.TB_SystemLog";
		// 备份数据表
		// String[] result = operation.backupTables(database,
		// new String[] { tableName });
		// System.out.println(result);

		// 恢复数据表
		String[] result = operation.restoreFiles(database,
				new String[] { tableName });
		System.out.println(result);
		System.exit(0);
	}
}
