package com.hgsoft.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class OperationDAO {
	// 数据库的备份路径为编译后的classes目录

	// 如果是WEB程序，就在是WEB工程发布路径的/WEB-INF/classes

	private static final String DB_ROOT = OperationDAO.class.getClassLoader()
			.getResource("").getPath().substring(1);

	private static final String DB_BACKUP_SUFFIX = ".DB";
	private static final String TABLE_BACKUP_SUFFIX = ".TB";
	private static final String ENTER = "\n";
	private static final String TAB = "\t";

	public static String getDB_ROOT() {
		return DB_ROOT;
	}

	/**
	 * 
	 * 获得除了master外所有分类数据库的名字
	 * 
	 * @return
	 */

	public List<String> getDatabases() {

		List<String> list = new ArrayList<String>();

		Connection con = null;
		try {
			con = DataSourceFactory.getDataSource().getConnection();
			DatabaseMetaData md = con.getMetaData();
			ResultSet rs = md.getCatalogs();

			while (rs.next()) {

				String catalog = rs.getString(1);

				if (!"master".equals(catalog)) {

					list.add(catalog);

				}

			}

		} catch (SQLException e) {

			list.add("无法连接到默认指定数据库！");

			e.printStackTrace();

		}

		try {

			if (con != null && !con.isClosed()) {

				con.close();

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return list;

	}

	/**
	 * 
	 * 获得特定数据库中所有表的完整名字（带所属用户）
	 * 
	 * @param catalog
	 * 
	 * @return
	 */

	public List<String> getTableNames(String catalog) {

		List<String> list = new ArrayList<String>();

		Connection con = null;

		try {

			con = DataSourceFactory.getDataSource().getConnection();

			// 获得数据库元数据信息

			DatabaseMetaData md = con.getMetaData();

			ResultSet rs = md.getTables(catalog, null, null,

			new String[] { "table" });

			while (rs.next()) {

				String ownerSchema = rs.getString(2);

				String tableName = rs.getString(3);

				if (!tableName.equals("dtproperties")) {

					list.add(ownerSchema + "." + tableName);

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {

					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}

	/**
	 * 
	 * @param catalog
	 * 
	 * @return 表格对应的数据文件
	 */

	public String[] getDataFilePath(String catalog) {

		File dir = new File(DB_ROOT, catalog);

		String[] list = dir.list(new FilenameFilter() {

			public boolean accept(File dir, String name) {

				if (name.endsWith(TABLE_BACKUP_SUFFIX)) {

					return true;

				}

				return false;

			}

		});

		return list;

	}

	/**
	 * 
	 * 将二维表数据记录转化为JAVA中的二维字符串数组
	 * 
	 * 
	 * 
	 * @param catalog
	 * 
	 * @param tableName
	 * 
	 * @return data
	 */

	public String[][] getDataByTableName(String catalog, String tableName) {

		String[][] data = new String[0][0];

		// 取得数据库联接

		Connection con = null;

		try {
			con = DataSourceFactory.getDataSource().getConnection();

			String countSql = "select count(*) from " + catalog + "."
					+ tableName;

			String sql = "select * from " + catalog + "." + tableName;

			Statement stmt = con.createStatement();

			// 取得数据表的记录数

			ResultSet rs = stmt.executeQuery(countSql);

			int rowCount = 0;

			if (rs.next()) {

				rowCount = rs.getInt(1);

			}

			// 取得数据表的记录

			rs = stmt.executeQuery(sql);

			// 取得数据表的列对象

			ResultSetMetaData resultSetMetaData = rs.getMetaData();

			// 取得列的总数

			int colCount = resultSetMetaData.getColumnCount();

			// 根据数据表的行与列总数创建数组

			data = new String[rowCount][colCount];

			// 将数据记录存放在数组

			int row = 0;

			while (rs.next()) {
				for (int col = 0; col < colCount; col++) {
					data[row][col] = rs.getString(col + 1);
				}
				row++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {

					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return data;

	}

	/**
	 * 
	 * 将传入的表的记录信息备份到指定的目录下的文件中（自定义格式）
	 * 
	 * 
	 * 
	 * @param catalog
	 * 
	 *            数据库名
	 * 
	 * @param tbNames
	 * 
	 *            需要备份的数据表名
	 * 
	 * @return results 操作结果信息
	 */

	public String[] backupTables(String catalog, String[] tbNames) {
		if (null == tbNames) {
			return new String[] { "传入的数据表名称为空!" };
		}
		String[] results = new String[tbNames.length];
		String[][] data = null;
		int len = tbNames.length;

		for (int i = 0; i < len; i++) {

			String tableName = tbNames[i];

			// 创建文件类

			File catRoot = new File(DB_ROOT + catalog);

			if (!catRoot.exists()) {
				catRoot.mkdirs();
			}

			File file = new File(catRoot, tableName + TABLE_BACKUP_SUFFIX);

			// 创建文件写出类
			FileWriter writer = null;
			try {
				writer = new FileWriter(file);

				// 取得数据表的数据
				data = getDataByTableName(catalog, tableName);
				int length = data.length;
				for (int row = 0; row < length; row++) {

					for (int col = 0; col < data[0].length; col++) {
						// 如果字段值为空值，转换为null字符串，如果字段值的长度为度，加入一个空格
						if (data[row][col] == null) {
							data[row][col] = "null";
						} else if (data[row][col].length() == 0) {
							data[row][col] = " ";
						}
						// 行分隔
						if (col == data[0].length - 1) {
							// \n是换行符
							writer.write(data[row][col] + ENTER);
						}
						// 列分隔
						else {
							// \t是水平制表符
							writer.write(data[row][col] + TAB);
						}
					}
				}

				results[i] = "成功备份" + tableName + "表到" + file.getName() + "文件！";

				// 关闭文件写出类
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return results;

	}

	/**
	 * 
	 * 与上相反，将传入表对应的文件上的数据（如果存在）恢复到表中
	 * 
	 * 
	 * 
	 * @param catalog
	 * 
	 *            数据库名
	 * 
	 * @param tbNames
	 * 
	 *            需要恢复的数据表名
	 * 
	 * @return results 操作结果信息
	 */

	public String[] restoreFiles(String catalog, String[] fNames) {
		int length = fNames.length;

		String[] results = new String[length];

		// 创建数组

		String[][] data = null;
		List<String> tableNames = getTableNames(catalog);
		String path = DB_ROOT + "//" + catalog;
		BufferedReader bufferedReader = null;

		for (int i = 0; i < length; i++) {

			// String tableName = fNames[i].substring(fNames[i]
			// .lastIndexOf(".")+1, fNames[i].length());
			String tableName = fNames[i];

			if (!tableNames.contains(tableName)) {

				results[i] = tableName + "数据表在" + catalog

				+ "数据库中不存在，不可以进行恢复操作！";
				continue;
			}

			String fullName = fNames[i] + TABLE_BACKUP_SUFFIX;
			File inFile = new File(path, fullName);

			// 创建集合类
			List<String> list = new ArrayList<String>();
			try {
				bufferedReader = new BufferedReader(new FileReader(inFile));
				while (bufferedReader.ready()) {
					// 读入一行内容
					list.add(bufferedReader.readLine());
				}
				bufferedReader.close();

				if (list.size() > 0) {

					// 取得行总数
					int rowLength = list.size();

					String tempStr = (String) list.get(0);

					StringTokenizer stringToken = new StringTokenizer(tempStr,
							TAB);

					// 取得列总数
					int colLength = stringToken.countTokens();

					// 根据行和列的总数创建内容数组
					data = new String[rowLength][colLength];

					for (int row = 0; row < rowLength; row++) {
						stringToken = new StringTokenizer(
								(String) list.get(row), TAB);

						for (int col = 0; col < colLength; col++) {
							tempStr = stringToken.nextToken();

							// 取代\n字符串

							tempStr.replace(ENTER, " ");
							tempStr = tempStr.trim();

							// 向数组写入内容
							data[row][col] = tempStr;
						}

					}

					// 将数组数据写入数据表
					int result = setDataByTableName(catalog, tableName, data);
					if (result == 1) {
						results[i] = "成功恢复" + fNames[i] + "文件到" + tableName
								+ "表！";

					} else if (result == 0) {
						results[i] = "恢复" + fNames[i] + "文件到" + tableName
								+ "表失败！";

					}

				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return results;

	}

	/**
	 * 
	 * 将JAVA中的二维数组中的数据插入到表中
	 * 
	 * 
	 * 
	 * @param catalog
	 * 
	 * @param tableName
	 * 
	 * @param data
	 * 
	 * @return 操作结果信息
	 */

	public int setDataByTableName(String catalog, String tableName,
			String[][] data) {
		int result = 0;
		// 声明三条SQL语句
		String deleteSql = "delete from " + catalog + "." + tableName;
		String selectSql = "select * from " + catalog + "." + tableName;
		String insertSql = "insert into " + catalog + "." + tableName

		+ " values(";
		// 取得数据库联接
		Connection con = null;
		try {
			con = DataSourceFactory.getDataSource().getConnection();

			// 开始事务
			con.setAutoCommit(false);

			// 创建不带参数的SQL语句执行类
			Statement stmt = con.createStatement();

			// 删除数据表的记录
			stmt.executeUpdate(deleteSql);

			// 取得数据表的记录
			ResultSet rs = stmt.executeQuery(selectSql);

			// 取得数据表的列对象
			ResultSetMetaData resultSetMetaData = rs.getMetaData();

			// 取得列的总数
			int colCount = resultSetMetaData.getColumnCount();

			// 是否含有自增长字段
			boolean isAutoIncrement = false;

			// 自增长的字段索引（从零开始）
			int identityNum = -1;

			for (int col = 0; col < colCount; col++) {
				if (resultSetMetaData.isAutoIncrement(col + 1)) {
					isAutoIncrement = true;
					identityNum = col;
					break;
				}

			}

			// 根据是否自增长动态构建insert语句

			if (isAutoIncrement) {
				for (int col = 0; col < colCount; col++) {
					if (col == identityNum) {
						continue;
					}

					if (col == colCount - 1) {
						insertSql += "?" + ")";
					} else {
						insertSql += "?" + ",";
					}

				}

			} else {

				for (int col = 0; col < colCount; col++) {
					if (col == colCount - 1) {
						insertSql += "?" + ")";
					} else {
						insertSql += "?" + ",";
					}

				}

			}

			// 创建带参数的SQL语句执行类

			PreparedStatement pstmt = con.prepareStatement(insertSql);

			// 创建日期转换类

			DateFormat dateLongFormat = DateFormat.getDateTimeInstance();
			DateFormat dateShortFormat = DateFormat.getDateInstance();

			// 声明java.sql类包的时间变量

			Timestamp timeStamp = null;

			// 将数组写入数据表

			for (int row = 0; row < data.length; row++) {

				int index = 0;

				for (int col = 0; col < colCount; col++) {

					if (col == identityNum) {

						continue;

					}

					// 设置字符串参数

					String columnTypeName = resultSetMetaData

					.getColumnTypeName(col + 1);

					// 要支持其它数据类型可以添加

					if (columnTypeName.equals("varchar")

					| columnTypeName.equals("longvarchar")) {

						pstmt.setString(index + 1, data[row][col]);

					}

					// 设置bit类型参数

					else if (columnTypeName.equals("bit")) {

						pstmt.setInt(index + 1, Integer

						.parseInt(data[row][col]));

					}

					// 设置int类型参数

					else if (columnTypeName.equals("int")) {

						if (data[row][col].equals("null")) {

							pstmt.setString(index + 1, null);

						} else {

							pstmt.setInt(index + 1, Integer

							.parseInt(data[row][col]));

						}

					}

					// 设置float类型参数

					else if (columnTypeName.equals("float")

					| columnTypeName.equals("decimal")) {

						pstmt.setDouble(index + 1, Double

						.parseDouble(data[row][col]));

					}

					// 设置timestamp类型参数

					else if (columnTypeName.equals("timestamp")

					| columnTypeName.equals("datetime")) {

						if (data[row][col].equals("null")) {

							timeStamp = null;

						} else if (data[row][col].length() > 10) {

							timeStamp = new Timestamp(dateLongFormat.parse(

							data[row][col]).getTime());

						} else {

							timeStamp = new Timestamp(dateShortFormat.parse(

							data[row][col]).getTime());

						}

						pstmt.setTimestamp(index + 1, timeStamp);

					} else if (columnTypeName.equals("image")) {

						if (data[row][col] == null

						|| data[row][col].length() <= 4) {

							pstmt.setBytes(index + 1, null);

						} else {

							pstmt

							.setBytes(index + 1, data[row][col]

							.getBytes());

						}

					} else {

						System.out.println("其它数据类型");

					}

					index++;

				}

				// 执行插入操作

				pstmt.execute();

			}

			// 如果自增长，重新设置ID从1开始自增

			if (isAutoIncrement) {

				stmt.execute("DBCC  CHECKIDENT  ('" + catalog + "." + tableName

				+ "',  RESEED,0)");

			}

			// 提交事条

			con.commit();

			result = 1;

		} catch (Exception ex) {

			try {

				// 撤消事务

				con.rollback();

			} catch (Exception ex1) {

				ex1.printStackTrace();

			}

			data = new String[0][0];

			ex.printStackTrace();

		} finally {
			try {
				if (con != null && !con.isClosed()) {

					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	/**
	 * 
	 * 备份整个数据库到指定目录文件上
	 * 
	 * 
	 * 
	 * @param catalog
	 * 
	 * @return
	 */

	public String[] backupDatabase(String catalog) {

		String[] res = new String[1];

		res[0] = "备份" + catalog + "数据库成功！";

		Connection con = null;

		try {

			con = DataSourceFactory.getDataSource().getConnection();

			// 创建文件类

			File catRoot = new File(DB_ROOT + catalog);

			if (!catRoot.exists()) {

				catRoot.mkdirs();

			}

			Statement st = con.createStatement();

			st.execute("backup database " + catalog + " to disk='" + catRoot

			+ File.separator + catalog + DB_BACKUP_SUFFIX + "'");

		} catch (SQLException e) {

			e.printStackTrace();

			res[0] = "备份" + catalog + "数据库失败！";

		}

		try {

			con.close();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return res;

	}

	/**
	 * 
	 * 从指定目录文件恢复备份数据到数据库中，注意，当有多个用户进程时恢复会失败！
	 * 
	 * 
	 * 
	 * @param catalog
	 * 
	 * @return
	 */

	public String[] restoreDatabase(String catalog) {

		String[] res = new String[1];

		res[0] = "恢复" + catalog + "数据库成功！";

		Connection con = null;

		try {

			con = DataSourceFactory.getDataSource().getConnection();

			Statement st = con.createStatement();

			st.execute("use master");

			st.execute("Alter Database " + catalog

			+ " Set Offline with Rollback immediate");

			st.execute("restore database " + catalog + " from disk='" + DB_ROOT

			+ catalog + File.separator + catalog + DB_BACKUP_SUFFIX

			+ "'");

			st.execute("Alter Database " + catalog

			+ " Set OnLine With rollback Immediate");

		} catch (SQLException e) {

			e.printStackTrace();

			res[0] = "恢复" + catalog + "数据库失败！";

		} finally {
			try {
				if (con != null && !con.isClosed()) {

					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;

	}

	/**
	 * 
	 * 分离数据库
	 * 
	 * 
	 * 
	 * @param catalog
	 * 
	 * @return
	 */

	public String[] detachDatabase(String catalog) {

		String[] res = new String[1];

		res[0] = "分离" + catalog + "数据库成功！";

		Connection con = null;

		try {

			con = DataSourceFactory.getDataSource().getConnection();

			Statement st = con.createStatement();

			st.execute("EXEC sp_detach_db '" + catalog + "', 'true'");

		} catch (SQLException e) {

			e.printStackTrace();

			res[0] = "分离" + catalog + "数据库失败！";

		} finally {
			try {
				if (con != null && !con.isClosed()) {

					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;

	}

	/**
	 * 
	 * 附加数据库（只是测试，大家可以参考实现！）
	 * 
	 * 
	 * 
	 * @param catalog
	 * 
	 * @return
	 */

	public String[] attachDatabase(String catalog, String dFile, String lFile) {

		System.out.println(">>>>>>>>>>" + dFile + " " + lFile);

		String[] res = new String[1];

		res[0] = "附加" + catalog + "数据库成功！";

		Connection con = null;

		try {

			con = DataSourceFactory.getDataSource().getConnection();

			Statement st = con.createStatement();

			st

			.execute("EXEC sp_attach_db @dbname = N'"

					+ catalog

					+ "', @filename1 = N'd://Program Files//Microsoft SQL Server//MSSQL//Data//java28.mdf', @filename2 = N'd://Program Files//Microsoft SQL Server//MSSQL//Data//java28_log.ldf'");

		} catch (SQLException e) {

			e.printStackTrace();

			res[0] = "附加" + catalog + "数据库失败！";

		} finally {
			try {
				if (con != null && !con.isClosed()) {

					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;

	}

	public static void main(String[] args) {

		OperationDAO dao = new OperationDAO();

		// List<String> tbNames = dao.getTableNames();

		// for (int i = 0; i < tbNames.size(); i++) {

		// System.out.println(tbNames.get(i));

		// }

		// String[][] datas = dao.getDataByTableName("java28","book");

		// for (int i = 0; i < datas.length; i++) {

		// for (int j = 0; j < datas[i].length; j++) {

		// System.out.print(datas[i][j]+" ");

		// }

		// System.out.println();

		// }

		System.out.println("数据库备份路径为" + DB_ROOT + "，后缀为" + DB_BACKUP_SUFFIX

		+ "!");

		String[] result = dao.backupDatabase("java28");

		System.out.println(result[0]);

	}

}
