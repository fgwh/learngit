package com.hgsoft.factory;

import javax.sql.DataSource;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.simpl.PropertySettingJobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hgsoft.job.BaseJob;
import com.hgsoft.main.service.JobTaskService;
import com.hgsoft.main.service.SettlementService;


@Component("jobFactory")
public class JobFactory extends PropertySettingJobFactory {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JobTaskService jobTaskService;
	@Autowired
	private SettlementService settlementService;

	
	public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
		Job job = super.newJob(bundle,scheduler);
		/**
		 * 为自定义任务绑定DateSource及JobTaskService对象
		 */
		if (job instanceof BaseJob) {
			BaseJob baseJob = (BaseJob) job;
			//baseJob.setDataSourceE(dataSourceE);
			baseJob.setDataSource(dataSource);
			baseJob.setJobTaskService(jobTaskService);
			baseJob.setSettlementService(settlementService);

		}
		
		return job;
	}
}
