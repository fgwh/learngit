package com.hgsoft.main.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hgsoft.util.Pager;

@Service("dataBaseCollectService")
public class DataBaseCollectService {

	public Connection getConn(String driver,String url,String user,String password) throws Exception{
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch(Exception ex){
			throw ex;
		}
		return conn;
	}
	
	/**
	public List<Object[]> queryBySQL(String driver,String url,String user,String password,String sql,Pager pager) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object[]> list  = null;
		try{
			conn = getConn(driver,url,user,password);
			sql = sql.trim(); //去除空格
			//查询总记录数
			String sqlStr = "select count(*) from ("+sql+") tb";
			pstmt = conn.prepareStatement(sqlStr);
			rs = pstmt.executeQuery();
			int totalSize = 0;
			if(rs.next()){
				totalSize = rs.getInt(1);
			}
			pager.setTotalSize(totalSize);
			int pageIndex = (pager.getCurrentPage()-1)*pager.getPageSize();
			int pageCount = pager.getCurrentPage() * pager.getPageSize();
			sqlStr = "select top "+pageCount+" * from ("+sql+") tb";
			pstmt = conn.prepareStatement(sqlStr);
			rs = pstmt.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount(); 
			list = new ArrayList<Object[]>();
			Object[] obj = new Object[columnCount];
			for(int k = 1;k <= columnCount;k++){
				obj[k-1] = md.getColumnName(k);
			}
			list.add(obj);
			int i = 0;
			while(rs.next()){
				if(i >= pageIndex){
					obj = new Object[columnCount];
					for(int j = 1;j <= columnCount;j++){
						obj[j-1] = rs.getObject(j);
					}
					list.add(obj);
				}
				i++;
			}
		}catch(Exception ex){
			throw ex;
		}finally{
			close(conn,pstmt,rs);
		}
		return list;
	}**/
	
	public List<Object[]> queryBySQL(String driver,String url,String user,String password,String sql,Pager pager) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object[]> list  = null;
		try{
			conn = getConn(driver,url,user,password);
			sql = sql.trim(); //去除空格
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount(); 
			list = new ArrayList<Object[]>();
			Object[] obj = new Object[columnCount];
			for(int k = 1;k <= columnCount;k++){
				String columnName = md.getColumnName(k);
				if(columnName == null || columnName.trim().equals("")){
					obj[k-1] = "COLUMN";
				}else{
					obj[k-1] = columnName.toUpperCase();
				}
			}
			list.add(obj);
			int totalSize = 0;
			List<Object[]> objList = new ArrayList<Object[]>();
			while(rs.next()){
				obj = new Object[columnCount];
				for(int j = 1;j <= columnCount;j++){
					obj[j-1] = rs.getObject(j);
				}
				objList.add(obj);
				totalSize++;
			}
			pager.setTotalSize(totalSize);
			if(totalSize > 0){
				int pageIndex = (pager.getCurrentPage()-1)*pager.getPageSize();
				int pageCount = pager.getCurrentPage() * pager.getPageSize();
				if(pageCount > totalSize){
					pageCount = totalSize;
				}
				for(int i=pageIndex;i<pageCount;i++){
					Object[] object = objList.get(i);
					list.add(object);
				}
			}

		}catch(Exception ex){
			throw ex;
		}finally{
			close(conn,pstmt,rs);
		}
		return list;
	}
	
	/**
	 * 关闭连接
	 * @throws Exception 
	 */
	public void close(Connection conn,PreparedStatement pstmt,ResultSet rs) throws Exception{
		try {
			  if (conn != null && !conn.isClosed()){
				  conn.close();
			  }
			  if (pstmt != null){
				  pstmt.close();
			  }
			  if (rs != null){
				  rs.close();
			  }
			 } catch (Exception e) {
			   throw e;
			}
	}
}
