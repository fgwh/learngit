package com.hgsoft.job;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.sql.DataSource;

import org.quartz.Job;
import org.quartz.JobDetail;
//import org.springframework.batch.core.configuration.JobLocator;
//import org.springframework.batch.core.launch.JobLauncher;

import com.hgsoft.main.service.JobTaskService;
import com.hgsoft.main.service.SettlementService;

/**
 * @author huang_cx
 * @date 2013-05-15
 * @version 1.0
 * @Description 任务基础类
 */
public abstract class BaseJob implements Job {
	/** 通过在外部注入所需对象 **/
	protected DataSource dataSource;
	protected JobTaskService jobTaskService;
//	protected JobLocator jobLocator;
//	protected JobLauncher jobLauncher;
	
	protected SettlementService settlementService;

	// protected DataSource dataSourceE;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// public void setDataSourceE(DataSource dataSourceE) {
	// this.dataSourceE = dataSourceE;
	// }

	public void setJobTaskService(JobTaskService jobTaskService) {
		this.jobTaskService = jobTaskService;
	}

	/*public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}*/
	
	public void setSettlementService(SettlementService settlementService) {
		this.settlementService = settlementService;
	}

	/**
	 * 记录日志
	 * 
	 * @param statement
	 * @param detail
	 *            任务明细对象
	 * @param content
	 *            日志内容
	 */
	public abstract void log(Statement statement, JobDetail detail,
			String Content) throws SQLException;

	public static int doubleToInt(double number) {
		DecimalFormat df1 = new DecimalFormat("###0");
		String parValue = df1.format(number);
		int result = Integer.valueOf(parValue);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(434/100);
		System.out.println(doubleToInt(434.4*100));
		System.out.println(doubleToInt(434.1*100));
		System.out.println(doubleToInt(434.9*100));
		System.out.println(doubleToInt(434.04*100));
		System.out.println(doubleToInt(434.01*100));
		System.out.println(doubleToInt(434.09*100));
		System.out.println(doubleToInt(434.00*100));
	}
}
