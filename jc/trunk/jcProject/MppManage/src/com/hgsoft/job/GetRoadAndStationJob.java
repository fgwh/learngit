package com.hgsoft.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hgsoft.job.service.RoadAndStationService;
import com.hgsoft.main.entity.QrtzLog;

/**
 * 获取收费流水中的路段和站编号
 */
public class GetRoadAndStationJob extends JobLog implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		QrtzLog qrtzLog = getQrtzLogByClassName(this.getClass().getName());
		new RoadAndStationService().saveAllRoadAndStationData(qrtzLog);
		saveQrtzLog(qrtzLog);//记日志
	}

}
