package com.hgsoft.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hgsoft.job.service.EtcPictureService;
import com.hgsoft.main.entity.QrtzLog;

public class GetEtcPictureJob extends JobLog implements Job {
	private static WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();

    private static Logger logger = Logger.getLogger(GetEtcPictureJob.class);
	
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		QrtzLog qrtzLog = getQrtzLogByClassName(this.getClass().getName());
        EtcPictureService etcPictureService = (EtcPictureService) applicationContext.getBean("etcPictureService");
        
        int count = etcPictureService.successParseEtcPicture();
        
        qrtzLog.setCreatetime(new Date());
        qrtzLog.setDescription(count + "条ETC图像数据到打逃数据库成功。。。");
		qrtzLog.setJobStatus(JobLog.JOB_STATUS_SUCC);
        saveQrtzLog(qrtzLog);//记日志
        
        logger.info(count+"条ETC数据保存到本地数据库成功。。。");
	}
}
