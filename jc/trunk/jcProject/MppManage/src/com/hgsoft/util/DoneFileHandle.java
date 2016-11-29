package com.hgsoft.util;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * 检查银行提供的done文件是否存在
 * @author hcx
 *
 */
public class DoneFileHandle implements JobExecutionDecider {
	private final Log logger = LogFactory.getLog(DoneFileHandle.class);
	
	/**
	 * 下载文件夹目录
	 */
	private String dpath;

	public String getDpath() {
		return dpath;
	}

	public void setDpath(String dpath) {
		this.dpath = dpath;
	}

	private final boolean handleDoneFile() {
		String wholePath = "";
		boolean result = false;

		if (!dpath.endsWith("/")) {
			wholePath = dpath + "/";
		}

		// 检索数据库最后一次发送的ok文件的时间
		// 取出本次对应的done文件
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =  null;
		Statement statement =null;
		try {
			conn = ApplicationContextUtils.getConnection();
			conn.setAutoCommit(false);
			String countSql = "select count(1) from TB_SendFiles_CMB";
			ps = conn.prepareStatement(countSql);
			//首次执行
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				int count = rs.getInt(1);
				//首次发送 不存在done文件
				if(count == 0)
				{
					logger.info("首次发送文件，不需要检查done文件。");
					result = true;
				}
				else
				{
					StringBuffer sb = new StringBuffer("select id,dirName,okName,rarName,doneName,rarTotalSize,fileTime from TB_SendFiles_CMB where status=1 order by createTime desc");
					String sql = sb.toString();
					ps = conn.prepareStatement(sql);
					
					rs = ps.executeQuery();
					
					if(rs.next())
					{
						String doneName = rs.getString(5);
						wholePath += doneName;//done文件的完整路径
						
						File doneFile = new File(wholePath);
						if(doneFile.exists())
						{
							logger.info("已存在"+wholePath);
							int id = rs.getInt(1);
							sql = "update TB_SendFiles_CMB set status = 2 where id="+id;
							statement = conn.createStatement();
							statement.executeUpdate(sql);
							
							conn.commit();
							
							result = true;
						}
						else
						{
							logger.info("不存在"+wholePath);
							result = false;
						}
					}
				}
			}
			
		} catch (Exception e) {
			if(statement != null)
			{
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			e.printStackTrace();
		}finally
		{
			if(statement != null)
			{
				try {
					statement.close();
					statement = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null)
			{
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			}
			if(conn != null)
			{
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ApplicationContextUtils.closeConnection(conn);
			}
		}
		
		logger.info("done文件下载目录： " + wholePath);

		return result;
	}

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution,
			StepExecution stepExecution) {
		// 路由结果
		if (handleDoneFile()) {
			logger.info("done文件存在，本次任务往下执行。 " );
			return FlowExecutionStatus.COMPLETED;
		} else {
			logger.info("done文件不存在，本次任务结束。 " );
			return FlowExecutionStatus.STOPPED;
		}

	}
}
