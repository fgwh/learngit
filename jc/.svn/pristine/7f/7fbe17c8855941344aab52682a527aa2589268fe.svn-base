package com.hgsoft.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hgsoft.component.model.DataHolder;
import com.hgsoft.parse.ConsumptionEntry;
import com.hgsoft.parse.ConsumptionParseToSqlService;

public class ConsumptionParseServiceTest extends TestCase {
	public void testConsumptionParse() {
		
		Thread.currentThread().getContextClassLoader().getResource("ofile_template.xml");
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("ofile_template.xml").getFile().substring(1));
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("ofile").getFile().substring(1));
		String dataPath = Thread.currentThread().getContextClassLoader().getResource("ofile").getFile().substring(1);//"F:/workspaces10/MppManage/src/com/hgsoft/test/ofile";
		String templatePath = Thread.currentThread().getContextClassLoader().getResource("ofile_template.xml").getFile().substring(1);//"F:/workspaces10/MppManage/res/ofile_template.xml";
		ConsumptionParseToSqlService consumptionParseService = new ConsumptionParseToSqlService(
				templatePath);
		LinkedList<ConsumptionEntry> consumptionEntrys = consumptionParseService
				.parse(dataPath, null);
		for(ConsumptionEntry consumptionEntry : consumptionEntrys)
		{
			System.out.println(consumptionEntry);
		}
		List<String> filesPath = new ArrayList<String>();
		filesPath.add("11");
		filesPath.add("22");
		Object[] filesPaths = null;
		filesPaths = filesPath.toArray();
		
		System.out.println("----------------");
		for (Object string : filesPaths) {
			System.out.println(string);
		}
		System.out.println("----------------");
		
		String[] filesPathStr = null;
		filesPathStr = filesPath.toArray(new String[0]);
		
		System.out.println("----------------");
		for (String string : filesPathStr) {
			System.out.println(string);
		}
		System.out.println("----------------");
		
		try {
			new SimpleDateFormat("yyyyMMdd'T'HHmmss").parse("20061212T111111");
			System.out.println(new SimpleDateFormat("yyyyMMdd'T'HHmmss").parse("20061212T111111"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testPre() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext4Test.xml");
		
		DataSource dataSource = (DataSource)ctx.getBean("dataSource");
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into tb_test(ddd) values(?)");
			ps.setTimestamp(1, null);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	public void testGenerateParkFile() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext4Test.xml");
		
		DataSource dataSource = (DataSource)ctx.getBean("dataSource");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BufferedWriter bw = null;
		String encoding = "GBK";
		final Character DELIMITER = '\t';  //数据分隔符
		
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement("select detailNo,icCode,icType,entry,enTime,export,exTime,expType,payAmt,tollBala,trSeq,termCode,termSeq,tac,ownerCode,stopPlaceCode,squadDate from TB_Park_Consumption_Detail");
			rs = ps.executeQuery();
			List<Object[]> fileSource = new LinkedList<Object[]>();
			
			List<Object[]> objectsList = new LinkedList<Object[]>();
			Object[] objects = null;
			int i = 0;
			while(rs.next()) {
				++i;
				objects = new Object[] {i,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)
						,rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)
						,rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15)
						,rs.getString(16),rs.getString(17)};
				objectsList.add(objects);
			}
			
			fileSource.add(new Object[] {1, "1111.txt", "323206", "010000", "2014-06-09 09:04:27", "2"});
			fileSource.add(new Object[] {"1001", i, "i"});
			fileSource.addAll(objectsList);
			ps = conn.prepareStatement("select squadDate,squadDate,ownerCode,CONVERT(varchar(100),SUM(CAST(payAmt as decimal(30,2)))) as gross,icType,squadDate from TB_Park_Consumption_Detail group by squadDate,icType,ownerCode");
			//ps = conn.prepareStatement("select startTime,endTime,ownerCode,CONVERT(varchar(100),SUM(CAST(gross as decimal(30,2)))),icType,squadDate from TB_Park_Consumption_Collect group by startTime,endTime,squadDate,icType,ownerCode");
			rs = ps.executeQuery();
			objectsList = new LinkedList<Object[]>();
			i = 0;
			while(rs.next()) {
				++i;
				objects = new Object[] {i,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)
						,rs.getString(6)};
				objectsList.add(objects);
			}
			
			fileSource.add(new Object[] {"1002", i, "i"});
			fileSource.addAll(objectsList);
			
			File file = new File("E:/front/111.txt");
			if (file.exists()) {
			    file.delete();
			}
			file.createNewFile();

			
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));

			for (Iterator<Object[]> iterator = fileSource.iterator(); iterator.hasNext();) {
				Object[] beans = iterator.next();
				StringBuffer line = new StringBuffer();
				for (int j = 0; j < beans.length; j++) {
					line.append(beans[j]==null?"":beans[j]);
                 	line.append(DELIMITER);
				}
				bw.write(line.substring(0, line.length() - 1) + "\n");
			}
            bw.flush();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if (null != bw) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	public void testFtp() {
		String ftpWorkingDir = "/ICBC/UploadFiles";
		String regexStr = "^("+ftpWorkingDir+ ")/" + "\\w+(\\.txt)$";
		boolean isMatched = Pattern.matches(regexStr,"/ICBC/UploadFiles/aa123.txt");
		System.out.println(isMatched);
	}
}
