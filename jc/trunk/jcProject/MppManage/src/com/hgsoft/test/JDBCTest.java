package com.hgsoft.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
	/* 获取数据库连接的函数 */
	public static Connection getConnection() {
		Connection con = null; // 创建用于连接数据库的Connection对象
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// 加载Mysql数据驱动

			con = DriverManager
					.getConnection(
							"jdbc:sqlserver://192.168.1.37:1433;databaseName=sysframe",
							"sa", "HGAdmin888");// 创建数据连接

		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return con; // 返回所建立的数据库连接
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				connection = null;

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		Connection conn = getConnection();
		
		System.out.println(conn);
		
		closeConnection(conn);

		/*String sql = "SELECT 308 AS OrgID,acc.id,acc.name,convert(decimal(14,2),acc.deposit) as deposit,convert(decimal(14,2),acc.balance) as balance,"
				+ " convert(varchar(10),acc.duration,120) as duration,"
				+ " convert(varchar(10),acc.accountDate,120) as accountDate,"
				+ " acc.remark,convert(decimal(14,2),acc.credit) as credit,convert(decimal(14,2),acc.lastCredit) as lastCredit,acc.state,acc.type,"
				+ " acc.[user],acc.password,sa.name as creation,"
				+ " convert(varchar,acc.createTime,120) as createTime,"
				+ " lsa.name as lastModifier,convert(varchar,acc.lastModifyTime,120) as lastModifyTime,"
				+ " a.name as area,c.name as customPoint,"
				+ " convert(varchar(10),getdate(),120) as BusinessDate,1 as BusinessType"
				+ " from TB_Account acc"
				+ " left join TB_Area a on acc.area = a.Id"
				+ " left join TB_CustomPoint c on acc.customPoint = c.Id"
				+ " left join sys_admin sa on acc.creation = sa.id"
				+ " left join sys_admin lsa on acc.lastModifier = lsa.id"
				+ " where  acc.organization=5 and  acc.createTime >= '2013-10-01 00:00:00' and  acc.createTime<= '2013-10-31 23:59:59'";

		if (conn != null) {
			Statement statement = null;
			try {
				statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					BigDecimal deposit = (BigDecimal)rs.getObject(4);
					BigDecimal balance = (BigDecimal)rs.getObject(5);
					System.out.println("deposit: " + deposit + ",balance: "
							+ balance);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
						statement = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
				
				closeConnection(conn);
			}

		}*/
	}
}
