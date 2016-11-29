package com.hgsoft.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.main.jcManange.service.GrayListService;

public class AutoDeleteGrayList extends JobLog implements Job{
	
	private static Logger logger = Logger.getLogger(AutoDeleteGrayList.class);
	
	private static WebApplicationContext applicationContext = ContextLoader
            .getCurrentWebApplicationContext();
			
	
	public void execute(JobExecutionContext jobExecutionContext)
	            throws JobExecutionException {
		
		QrtzLog qrtzLog = getQrtzLogByClassName(this.getClass().getName());
		qrtzLog.setCreatetime(new Date());
		 
		GrayListService  grayListService = (GrayListService)applicationContext.getBean("grayListService");
		 
		try{
		    grayListService.deleteByAuto();
		    logger.info( "自动删除超过6个月未更新的路段灰名单数据成功。");
            qrtzLog.setDescription("自动删除超过6个月未更新的路段灰名单数据成功");
            qrtzLog.setJobStatus(JobLog.JOB_STATUS_SUCC);
		}catch(Exception e){
			e.printStackTrace();
			logger.info( "自动删除超过6个月未更新的路段灰名单数据失败。");
            qrtzLog.setDescription("自动删除超过6个月未更新的路段灰名单数据失败");
            qrtzLog.setJobStatus(JobLog.JOB_STATUS_FAIL);
		}
		saveQrtzLog(qrtzLog);//记日志
	 }

}
