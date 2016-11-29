package com.hgsoft.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.main.jcManange.service.ProGrayListService;

public class AutoGetProGrayList extends JobLog implements Job{
	
	
	
	private static WebApplicationContext applicationContext = ContextLoader
            .getCurrentWebApplicationContext();
			
	
	public void execute(JobExecutionContext jobExecutionContext)
	            throws JobExecutionException {
		
		QrtzLog qrtzLog = getQrtzLogByClassName(this.getClass().getName());
		 
		ProGrayListService  proGrayListService = (ProGrayListService)applicationContext.getBean("proGrayListService");
		 
		proGrayListService.saveProGrayList(qrtzLog);
		
		saveQrtzLog(qrtzLog);//记日志
	 }

}
