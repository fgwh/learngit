package com.hgsoft.job;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.main.service.QrtzLogService;

/**
 * 继承此类，即可用于quartz任务记日志
 * 
 * @version 1.0
 * @date 2015-08-10
 * @author wubiao
 *
 */
public class JobLog {

	/**Job执行状态，成功：1*/
	public static final Short JOB_STATUS_SUCC = 1;
	/**Job执行状态，失败：0*/
	public static final Short JOB_STATUS_FAIL = 0;
	
	protected static WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
	private QrtzLogService qrtzLogService = (QrtzLogService)applicationContext.getBean("qrtzLogService");
	
	/**
	 * 根据jobClassName构造QrtzLog
	 * 
	 * @param jobClassName
	 * @return
	 */
	public QrtzLog getQrtzLogByClassName(String jobClassName) {
		
		return qrtzLogService.getQrtzLogByClassName(jobClassName);
	}
	
	public void saveQrtzLog(QrtzLog qrtzLog) {
		
		qrtzLogService.saveQrtzLog(qrtzLog);
	}
}
