package com.hgsoft.job;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;

import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.security.operLog.annotation.Description;
import com.hgsoft.security.operLog.service.OperLogService;


public class OperLogJob extends JobLog implements Job{
	
	private final static int outMonth = -3;
	private final static int outDay = -15;
	private  OperLogService operLogService=(OperLogService)ContextLoader.getCurrentWebApplicationContext().getBean("operLogService");;

	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {
		deleteOutTimeLog();
	}

	/*
	 * 删除超过半个月的操作日志
	 */
	@Description(details="自动删除日志")
	public void deleteOutTimeLog(){
		QrtzLog qrtzLog = getQrtzLogByClassName(this.getClass().getName());
		qrtzLog.setCreatetime(new Date());
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DATE, OperLogJob.outDay); 
		try {
			operLogService.deleteByOutTime(cal.getTime());
			qrtzLog.setDescription("自动删除"+  DateFormatUtils.format(cal.getTime(), "yyyy-MM-dd")+"之前的数据成功");
	        qrtzLog.setJobStatus(JobLog.JOB_STATUS_SUCC);
		} catch (Exception e) { 
			e.printStackTrace();
			qrtzLog.setDescription("自动删除"+DateFormatUtils.format(cal.getTime(), "yyyy-MM-dd")+"之前的数据失败");
	        qrtzLog.setJobStatus(JobLog.JOB_STATUS_FAIL);
		}
		saveQrtzLog(qrtzLog);//记日志
	}
}
