package com.hgsoft.main.action;

import com.hgsoft.job.AbnorCarJob;
import com.hgsoft.job.GetLaneEnListDataJob;
import com.hgsoft.job.GetLaneExListDataJob;
import com.hgsoft.job.JobLog;
import com.hgsoft.main.entity.Conditions;
import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.main.laneenlist.service.LaneEnListService;
import com.hgsoft.main.laneexlist.service.LaneExListService;
import com.hgsoft.main.service.JobTaskService;
import com.hgsoft.main.service.QrtzLogService;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.operLog.annotation.Description;
import com.hgsoft.security.operLog.service.OperLogService;
import com.hgsoft.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huang_cx
 * @version 1.0
 * @date 2013-05-14
 * @Description 任务管理
 */
@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class JobTaskAction extends BaseAction {

	private static Log logger = LogFactory.getLog(JobTaskAction.class);
	private final String context = "JobTaskAction";
	private final static String TIME_SPLIT = ":";
	private final static String QUESTION_MARK = "?";
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

	private final static String WILDCARD = "*";
	private OperLogService operLogService = (OperLogService) ContextLoader.getCurrentWebApplicationContext()
			.getBean("operLogService");

	private Conditions conditions;

	@Autowired
	private JobTaskService jobTaskService;

	@Autowired
	private QrtzLogService qrtzLogService;

	private LaneEnListService laneEnListService;

	// 注入入口流水service
	@Autowired
	public void setLaneEnListService(LaneEnListService laneEnListService) {
		this.laneEnListService = laneEnListService;
	}

	// 注入出口流水service
	@Autowired
	private LaneExListService laneExListService;

	String message = "";

	private String jobNameExist;

	/**
	 * 任务执行频率
	 *
	 * @author huang
	 */
	public static class TaskRate {
		public final static String DAY = "day";
		public final static String WEEK = "week";
		public final static String MONTH = "month";
	}

	/**
	 * 占位符
	 *
	 * @author huang
	 */
	public static class Placeholder {
		public final static String p1 = "{1}";
		public final static String p2 = "{2}";
		public final static String p3 = "{3}";
		public final static String p4 = "{4}";
		public final static String p5 = "{5}";
		public final static String p6 = "{6}";
	}

	/**
	 * 跳转至新增任务界面
	 */
	public String add() {
		return "add";
	}

	private final void checkValid(String field, String showName) throws Exception {
		if (StringUtils.isEmpty(field) || StringUtils.isBlank(field)) {
			// message = "输入参数'"+showName+"'有误";
			throw new Exception("输入参数'" + showName + "'有误");
		}
	}

	private final String obtainVal(HttpServletRequest request, String fieldName) {
		return request.getParameter(fieldName);
	}

	/**
	 * 手动删除日志
	 *
	 * @return
	 */
	@Description(details = "手动删除日志")
	public String deleteOperLog() {
		QrtzLog qrtzLog = qrtzLogService.getQrtzLogByClassName(this.getClass().getName());
		qrtzLog.setCreatetime(new Date());
		try {

			operLogService.deleteByOutTime(sdf2.parse(conditions.getStartQueryDate()));
			qrtzLog.setDescription("手动删除" + conditions.getStartQueryDate() + "之前的的数据成功");
			qrtzLog.setJobStatus(JobLog.JOB_STATUS_SUCC);

		} catch (Exception e) {
			e.printStackTrace();
			qrtzLog.setDescription("手动删除" + conditions.getStartQueryDate() + "之前的的数据失败");
			qrtzLog.setJobStatus(JobLog.JOB_STATUS_FAIL);
		}

		qrtzLog.setJobName("手动删除操作日志");
		qrtzLog.setJobGroup("手动删除操作日志");
		qrtzLog.setJobClassName("com.hgsoft.main.action.JobTaskAction.deleteOperLog");

		qrtzLogService.saveQrtzLog(qrtzLog);// 记日志
		return "success";
	}

	@Description(details = "手动可疑车辆分析")
	public String doAbnorCarJob() {
		QrtzLog qrtzLog = qrtzLogService.getQrtzLogByClassName(this.getClass().getName());
		qrtzLog.setCreatetime(new Date());

		AbnorCarJob abnorCarJob = new AbnorCarJob();
		try {
			abnorCarJob.getAbnorCarData(qrtzLog, sdf2.parse(conditions.getStartQueryDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		qrtzLog.setJobName("手动执行可疑车辆分析");
		qrtzLog.setJobGroup("手动执行可疑车辆分析");
		qrtzLog.setJobClassName("com.hgsoft.main.action.JobTaskAction.doAbnorCarJob");
		logger.info("手动执行可疑车辆分析");
		qrtzLogService.saveQrtzLog(qrtzLog);// 记日志

		return "success";
	}

	/**
	 * 增加任务
	 */
	public String save() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();

			String[] dataKey = request.getParameterValues("dataKey");// 参数名
			String[] dataValue = request.getParameterValues("dataValue");// 参数值

			String name = obtainVal(request, "name");// 任务名
			String group = obtainVal(request, "group");// 组别
			String className = obtainVal(request, "className");// 类名
			String description = obtainVal(request, "description");// 描述

			String volatility = obtainVal(request, "volatility");//
			String durability = obtainVal(request, "durability");
			String cronExpression = obtainVal(request, "cronExpression");

			// 执行类型 -1 请选择 1-重复执行(CronTrigger) 2-执行一次(SimpleTrigger)
			String taskTypeSel = obtainVal(request, "taskTypeSel");

			if (StringUtils.isEmpty(name) || StringUtils.isBlank(name)) {
				throw new Exception("任务名不能为空！");
			} else {
				name = name.trim();
			}

			if (StringUtils.isEmpty(group) || StringUtils.isBlank(group)) {
				throw new Exception("任务组不能为空！");
			} else {
				group = group.trim();
			}

			Class<Job> jobClass = null;
			if (StringUtils.isNotEmpty(className) && StringUtils.isNotBlank(className)) {
				try {
					jobClass = (Class<Job>) Class.forName(className.trim());
				} catch (ClassNotFoundException e) {
					throw new Exception("待执行的任务类不存在！", e);
				}
			}
			// 初始化任务详细信息
			// JobDetailImpl jobDetail = new JobDetailImpl();
			JobDetailImpl jobDetail = (JobDetailImpl) JobBuilder.newJob(jobClass).withIdentity(name, group).build();
			jobDetail.setName(name);// 设置任务名
			jobDetail.setGroup(group);// 设置组名

			DWRJobForm job = new DWRJobForm();

			if (StringUtils.isNotEmpty(description) && StringUtils.isNotBlank(description)) {
				job.setDescription(description.trim());
			}
			job.setClassName(className.trim());// 设置类名
			job.setVolatility(Boolean.parseBoolean(volatility));
			job.setDurability(Boolean.parseBoolean(durability));
			job.setCronExpression(cronExpression);
			job.copyTo(jobDetail);

			JobDataMap jobDataMap = new JobDataMap();
			if (dataKey != null && dataKey.length > 0) {
				for (int i = 0; i < dataKey.length; i++) {
					if (!"".equals(dataValue[i].trim()))
						jobDataMap.put(dataKey[i], dataValue[i]);// 放置任务参数
				}
				if (jobDataMap.size() > 0)
					jobDetail.setJobDataMap(jobDataMap);
			}

			// 表达式没有输入，则通过界面配置
			if (StringUtils.isEmpty(cronExpression) || StringUtils.isBlank(cronExpression)) {
				// throw new Exception("表达式不能为空！");
				String taskDescription = "从";// 描述
				// 重复执行
				if (StringUtils.equals(taskTypeSel, "1")) {

					// [秒] [分] [小时] [日] [月] [周] [年]
					String taskRate = obtainVal(request, "taskRate");// 执行频率 -1
																		// 请选择
																		// day
																		// 每天
																		// week每周
																		// month每月
					String startTime = obtainVal(request, "startTime");// 开始时间
					taskDescription += startTime + "开始,";

					String endTime = null;// 结束时间
					String endTimeFlag = obtainVal(request, "endTimeFlag");// 有无结束日期标识
					Date _startTime = null, _endTime = null;

					// 持续时间
					checkValid(endTimeFlag, "结束日期标识");
					checkValid(startTime, "开始时间");
					_startTime = sdf.parse(startTime);

					if (StringUtils.equals(endTimeFlag, "yes")) {
						endTime = obtainVal(request, "endTime");// 结束时间
						checkValid(endTime, "结束时间 ");
						_endTime = sdf.parse(endTime);

						taskDescription += endTime + "结束,";
					} else if (StringUtils.equals(endTimeFlag, "no")) {

					} else {

					}

					String cron = "{1} {2} {3} {4} {5} {6}";// 少了 "年" 的配置

					// 每天
					if (StringUtils.equals(taskRate, TaskRate.DAY)) {
						cron = cron.replace(Placeholder.p4, QUESTION_MARK);// 替换日
						cron = cron.replace(Placeholder.p5, WILDCARD);// 替换月
																		// 每月触发
						cron = cron.replace(Placeholder.p6, WILDCARD);// 替换周

						taskDescription += "在每天";
					}
					// 每周
					else if (StringUtils.equals(taskRate, TaskRate.WEEK)) {
						String weekTimes = obtainVal(request, "weekTimes");// 每周多少执行
						String[] weekTimesVals = request.getParameterValues("weekTimesVal");// 周明细
						String str = "";
						if (weekTimes == null || "".equals(weekTimes)) {
							weekTimes = "0";
						}

						weekTimes = weekTimes.trim();
						int weekTimesInt = 0;
						try {
							if (StringUtils.equals(weekTimes, "1")) {
								weekTimesInt = 0;
							} else {
								weekTimesInt = Integer.parseInt(weekTimes);
							}

						} catch (Exception e) {
							weekTimesInt = 0;
						}

						if (weekTimesVals != null && weekTimesVals.length > 0) {
							taskDescription += "在每" + (weekTimesInt == 0 ? "" : weekTimesInt) + "周,";
							final int weekLen = weekTimesVals.length;

							for (int i = 0; i < weekLen; i++) {
								// 需要每隔*周执行
								if (weekTimesInt > 0) {
									str += weekTimesVals[i] + "/" + weekTimesInt + ",";
								} else {
									str += weekTimesVals[i] + ",";
								}
								// 拼接描述语句
								if (i == (weekLen - 1)) {
									taskDescription += JobTaskUtils.Week.obtainWeekDetial(weekTimesVals[i]);
								} else {
									taskDescription += JobTaskUtils.Week.obtainWeekDetial(weekTimesVals[i]) + "、";
								}

							}

							if (str.endsWith(",")) {
								str = str.substring(0, str.length() - 1);
							}
							cron = cron.replace(Placeholder.p4, QUESTION_MARK);// 替换日
							cron = cron.replace(Placeholder.p5, WILDCARD);// 替换月
																			// 每月触发
							cron = cron.replace(Placeholder.p6, str);// 替换周

						} else {

							// 需要每隔*周执行
							if (weekTimesInt > 0) {
								taskDescription += "在每" + (weekTimesInt == 0 ? "" : weekTimesInt) + "周";
								str += "1/" + weekTimesInt + ",2/" + weekTimesInt + ",3/" + weekTimesInt + ",4/"
										+ weekTimesInt + ",5/" + weekTimesInt + ",6/" + weekTimesInt + ",7/"
										+ weekTimesInt;
								cron = cron.replace(Placeholder.p4, QUESTION_MARK);// 替换日
							} else {
								taskDescription += "在每周";
								str = QUESTION_MARK;
								cron = cron.replace(Placeholder.p4, WILDCARD);// 替换日
							}

							cron = cron.replace(Placeholder.p5, WILDCARD);// 替换月
																			// 每月触发
							cron = cron.replace(Placeholder.p6, str);// 替换周
						}

					}
					// 每月
					else if (StringUtils.equals(taskRate, TaskRate.MONTH)) {
						taskDescription += "在每月,";
						/**
						 * -1 请选择 ;1 第一个 ;2 第二个 ;3 第三个 ;4 第四个 ;L 最后一个
						 */
						String monthFrontSel = obtainVal(request, "monthFrontSel");

						/**
						 * -1 请选择;1 星期日;2 星期一 ;3 星期二;4 星期三 ;5 星期四 ;6 星期五 ;7 星期六
						 * ; workday 工作日 ;restday 休息日
						 */
						String monthBackSel = obtainVal(request, "monthBackSel");

						if (StringUtils.isEmpty(monthFrontSel) || StringUtils.equals(monthFrontSel, "-1")) {
							throw new Exception("需要选择每月触发频率！");
						}

						if (monthFrontSel.equals("L")) {
							taskDescription += "最后一个";
						} else {
							taskDescription += "第" + monthFrontSel + "个";
						}

						if (StringUtils.isEmpty(monthBackSel) || StringUtils.equals(monthBackSel, "-1")) {
							throw new Exception("需要选择每月触发频率！");
						}
						taskDescription += JobTaskUtils.Month.obtainMonthBackSel(monthBackSel);

						String monthStr = "";
						// 最后一个
						if (StringUtils.equals(monthFrontSel, "L")) {
							monthStr = monthBackSel + monthFrontSel;
						} else {
							monthStr = monthBackSel + "#" + monthFrontSel;
						}

						cron = cron.replace(Placeholder.p4, QUESTION_MARK);// 替换日
						cron = cron.replace(Placeholder.p5, WILDCARD);// 替换月
																		// 每月触发
						cron = cron.replace(Placeholder.p6, monthStr);// 替换周
					} else {

					}
					/**** 共有属性 ***/
					// [秒] [分] [小时] [日] [月] [周] [年]
					// 每天频率 -1 请选择 one-执行一次 more-间隔执行
					String taskRateDaySel = obtainVal(request, "taskRateDaySel");
					// 执行一次
					if (StringUtils.equals(taskRateDaySel, "one")) {
						String oneTime = obtainVal(request, "oneTime");// 在每天**执行

						checkValid(oneTime, "执行一次，时间");

						String[] times = oneTime.split(TIME_SPLIT);

						String hour = times[0];// 时
						if (hour.equals("00")) {
							hour = "0";
						}

						String min = times[1];// 分
						if (min.equals("00")) {
							min = "0";
						}

						String sec = times[2];// 秒
						if (sec.equals("00")) {
							sec = "0";
						}

						cron = cron.replace(Placeholder.p1, sec);
						cron = cron.replace(Placeholder.p2, min);
						cron = cron.replace(Placeholder.p3, hour);

						taskDescription += "," + hour + "时" + min + "分" + sec + "秒执行";

					}
					// 间隔执行
					else if (StringUtils.equals(taskRateDaySel, "more")) {
						String betweenTimes = obtainVal(request, "betweenTimes");// 在每**执行
						String betweenTimeSel = obtainVal(request, "betweenTimeSel");// hour-时
																						// minute-分
																						// second-秒

						checkValid(betweenTimes, "执行间隔");
						// Date now = new Date();
						// String hour=now.getHours()+"", min =
						// now.getMinutes()+"",sec = "0";
						String hour = "*", min = "*", sec = "0";
						if (StringUtils.equals(betweenTimeSel, "hour")) {
							// hour=now.getHours()+"";
							// hour = hour+"/"+betweenTimes;//时

							hour = "0/" + betweenTimes;// 时

							taskDescription += ",每隔" + betweenTimes + "小时重复执行";
						} else if (StringUtils.equals(betweenTimeSel, "minute")) {
							// min = now.getMinutes()+"";
							// min = min+"/"+betweenTimes;//分

							min = "0/" + betweenTimes;// 分

							taskDescription += ",每隔" + betweenTimes + "分钟重复执行";
						} else if (StringUtils.equals(betweenTimeSel, "second")) {
							sec = "0/" + betweenTimes;// 秒

							taskDescription += ",每隔" + betweenTimes + "秒钟重复执行";
						} else {
						}

						cron = cron.replace(Placeholder.p1, sec);
						cron = cron.replace(Placeholder.p2, min);
						cron = cron.replace(Placeholder.p3, hour);

					} else {
					}
					/**** 共有属性 ***/

					CronExpression expression = null;
					try {
						expression = new CronExpression(cron);
					} catch (ParseException e) {
						throw new Exception("表达式'" + cron + "'生成有误！", e);
					}
					// CronTriggerImpl cTrigger = new
					// CronTriggerImpl(name,group, name, group,_startTime
					// ,_endTime ,cron);
					CronTriggerImpl cTrigger = (CronTriggerImpl) TriggerBuilder.newTrigger().withIdentity(name, group)
							.withSchedule(CronScheduleBuilder.cronSchedule(expression)).build();
					cTrigger.setJobName(name);
					cTrigger.setJobGroup(group);
					cTrigger.setName(name);
					cTrigger.setGroup(group);
					cTrigger.setStartTime(_startTime);
					cTrigger.setEndTime(_endTime);
					cTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
					// shouldRecover属性为true，则当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务。
					jobDetail.setRequestsRecovery(true);
					this.jobTaskService.saveNewJob(jobDetail, cTrigger, taskDescription);
					// System.out.println("=====cron===="+cron);
					job.copyFrom(cTrigger);
				}

				// 执行一次
				else if (StringUtils.equals(taskTypeSel, "2")) {
					String taskDate = obtainVal(request, "taskDate");// 日期
					String taskTime = obtainVal(request, "taskTime");// 时间

					Date runTime;
					String source = "";
					if (StringUtils.isEmpty(taskDate) || StringUtils.isBlank(taskDate) || StringUtils.isEmpty(taskTime)
							|| StringUtils.isBlank(taskTime)) {
						runTime = new Date();
						source = sdf.format(runTime);

					} else {
						source = taskDate + " " + taskTime;
						runTime = sdf.parse(source);

					}
					// SimpleTriggerImpl trigger = new SimpleTriggerImpl(name,
					// group, runTime);
					SimpleTriggerImpl trigger = (SimpleTriggerImpl) TriggerBuilder.newTrigger()
							.withIdentity(name, group).startAt(runTime).build();
					trigger.setJobName(name);
					trigger.setJobGroup(group);
					trigger.setName(name);
					trigger.setGroup(group);

					taskDescription = "在" + source + "执行一次";
					trigger.setMisfireInstruction(
							SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
					// shouldRecover属性为true，则当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务。
					jobDetail.setRequestsRecovery(true);
					this.jobTaskService.saveNewStJob(jobDetail, trigger, taskDescription);
					// System.out.println("日期："+taskDate+",时间："+taskTime);
				}
				// 请选择
				else if (StringUtils.equals(taskTypeSel, "-1")) {
					/** 空方法 */
				} else {
					/** 空方法 */
				}
				// System.out.println(taskDescription);
			} else {
				cronExpression = cronExpression.trim();

				// 执行中应用发生故障，需要重新执行
				// jobDetail.setRequestsRecovery(true);
				// 即使没有Trigger关联时，也不需要删除该JobDetail
				// jobDetail.setDurability(true);
				CronExpression expression = null;
				try {
					expression = new CronExpression(cronExpression);
				} catch (ParseException e) {
					throw new Exception("表达式Cron输入有误！", e);
				}

				// CronTriggerImpl cTrigger = new
				// CronTriggerImpl(jobDetail.getName(),
				// jobDetail.getGroup(), jobDetail.getName(), jobDetail
				// .getGroup(), cronExpression);
				CronTriggerImpl cTrigger = (CronTriggerImpl) TriggerBuilder.newTrigger()
						.withIdentity(jobDetail.getName(), jobDetail.getGroup())
						.withSchedule(CronScheduleBuilder.cronSchedule(expression)).build();
				cTrigger.setJobName(jobDetail.getName());
				cTrigger.setJobGroup(jobDetail.getGroup());
				cTrigger.setGroup(jobDetail.getGroup());
				cTrigger.setName(jobDetail.getName());
				cTrigger.setCronExpression(expression);

				// cTrigger.setPriority(9);
				cTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
				// shouldRecover属性为true，则当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务。
				jobDetail.setRequestsRecovery(true);
				this.jobTaskService.saveNewJob(jobDetail, cTrigger, "");

				job.copyFrom(cTrigger);

			}
			job.copyFrom(jobDetail);

			Admin loginUser = this.operator;

			logger.debug(context + "：新增一条Job任务,任务名称：[" + jobDetail.getName() + "],任务分组：[" + jobDetail.getGroup()
					+ "],操作用户：[" + loginUser.getUsername() + "]");

		} catch (Exception e) {
			message = e.getMessage();
			return "add";
		}

		return list();
	}

	/**
	 * 根据分组、状态查询任务调度所有信息
	 */
	public String list() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String jobName = request.getParameter("jobName");// jobForm.getGroupNameEqualFilter();
		String groupName = request.getParameter("groupName");// jobForm.getGroupNameEqualFilter();
		String triggerType = request.getParameter("triggerType");

		String jobNameParam = null;
		String groupNameParam = null;
		if (jobName != null) {
			jobNameParam = "%" + jobName + "%";
		}
		if (groupName != null) {
			groupNameParam = "%" + groupName + "%";
		}
		String[] params = new String[] { jobNameParam, groupNameParam, triggerType };

		// List<Object[]> dwrJobs = jobTaskService.findJobsBySql(pager,params);
		List<JobDetailImpl> dwrJobs = null;
		try {
			dwrJobs = jobTaskService.findJobsByJdbc(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// List<JobDetailImpl> dwrJobs = jobTaskService.findJobsByRam(params);

		request.setAttribute("jobName", jobName);
		request.setAttribute("groupName", groupName);
		request.setAttribute("dwrJobs", dwrJobs);
		request.setAttribute("triggerType", triggerType);

		return "list";
	}

	/**
	 * 修改任务跳转
	 *
	 * @return
	 * @throws Exception
	 */
	public String update() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String jobname = request.getParameter("jobname");// 任务名
		String groupname = request.getParameter("groupname");// 组名

		try {
			check(jobname, groupname);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, JobDetail> executings = new HashMap<String, JobDetail>();
		// 获取正在执行的任务
		List<JobExecutionContext> execContexts;
		try {
			execContexts = (List<JobExecutionContext>) this.jobTaskService.getCurrentlyExecutingJobs();
			int len = execContexts.size();
			for (int i = 0; i < len; i++) {
				JobExecutionContext execContext = execContexts.get(i);
				JobDetailImpl jobDetailImpl = (JobDetailImpl) execContext.getJobDetail();
				executings.put(jobDetailImpl.getFullName(), execContext.getJobDetail());
			}

			JobDetailImpl jobDetail = (JobDetailImpl) this.jobTaskService.getJobDetail(jobname, groupname);

			if (jobDetail != null) {
				DWRJobForm dwrJob = new DWRJobForm();
				dwrJob.copyFrom(jobDetail);

				Trigger trigger = this.jobTaskService.getTrigger(jobDetail.getName(), jobDetail.getGroup());
				if (trigger != null && trigger instanceof CronTrigger) {
					CronTrigger cTrigger = (CronTrigger) trigger;
					dwrJob.copyFrom(cTrigger);
				}

				dwrJob.setTriggerDesc(jobDetail.getTriggerDesc());

				if (executings.containsKey(jobDetail.getFullName())) {
					dwrJob.setStatus(DWRJobForm.STATUS_RUNNING);
				} else {
					dwrJob.setStatus(DWRJobForm.STATUS_STOPPED);
				}

				// 触发器状态
				TriggerState state = jobTaskService.getTriggerState(jobname, groupname);
				;
				// int triggerStatus = jobTaskService.getTriggerState(jobname,
				// groupname);
				dwrJob.setTriggerStatus(state);

				int triggerType = jobDetail.getTriggerType();// 获得触发器类型
				// 0为SimpleTrigger,此处表示SimpleTrigger已经执行完
				if (triggerType == 0 && TriggerState.COMPLETE == state) {
					request.setAttribute("SimpleTrigger", "-1");
				}

				request.setAttribute("dwrJob", dwrJob);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(state);
		return "edit";

	}

	/**
	 * 删除任务
	 *
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String jobname = request.getParameter("jobname");
		String groupname = request.getParameter("groupname");
		// 检查任务名及组名是否合法
		try {
			check(jobname, groupname);
			this.jobTaskService.deleteJob(jobname, groupname);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Admin loginUser = this.operator;

		logger.debug(context + "：删除一条Job任务,任务名称：[" + jobname + "],任务分组：[" + groupname + "],操作用户：["
				+ loginUser.getUsername() + "]");

		return list();
	}

	/**
	 * 非定时的开始执行一条Job任务
	 *
	 * @return
	 * @throws Exception
	 */
	public String start() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		String jobname = request.getParameter("jobname");
		String groupname = request.getParameter("groupname");
		// 检查任务名及组名是否合法
		check(jobname, groupname);

		String[] dataKey = request.getParameterValues("dataKey");
		String[] dataValue = request.getParameterValues("dataValue");

		JobDataMap data = new JobDataMap();

		if (dataKey != null && dataKey.length > 0) {
			for (int i = 0; i < dataKey.length; i++) {
				if (!"".equals(dataValue[i].trim())) {
					data.put(dataKey[i], dataValue[i]);
				}
			}
		}

		// 开始任务 只触发一次
		/**
		 * Trigger trig = new org.quartz.SimpleTrigger(newTriggerId(),
		 * Scheduler.DEFAULT_MANUAL_TRIGGERS, jobName, groupName, new Date(),
		 * null, 0, 0);
		 */
		// this.jobTaskService.triggerJob(jobname, groupname, data);
		this.jobTaskService.triggerJob(jobname, groupname);

		// 根据任务名和组名查询查询任务明细
		JobDetailImpl jobDetail = (JobDetailImpl) this.jobTaskService.getJobDetail(jobname, groupname);

		DWRJobForm form = new DWRJobForm();
		form.copyFrom(jobDetail);

		List<JobExecutionContext> execContexts = (List<JobExecutionContext>) this.jobTaskService
				.getCurrentlyExecutingJobs();
		for (int i = 0; i < execContexts.size(); i++) {
			JobExecutionContext execContext = execContexts.get(i);

			if (execContext.getJobDetail().equals(jobDetail)) {
				form.setStatus(DWRJobForm.STATUS_RUNNING);
			} else {
				form.setStatus(DWRJobForm.STATUS_STOPPED);
			}
		}

		Admin loginUser = this.operator;
		logger.debug(context + "：非定时的开始执行一条Job任务,任务名称：[" + jobDetail.getName() + "],任务分组：[" + jobDetail.getGroup()
				+ "],操作用户：[" + loginUser.getUsername() + "]");
		return list();
	}

	/**
	 * 暂停服务
	 *
	 * @return
	 * @throws Exception
	 */
	public String pause() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jobname = request.getParameter("jobname");
		String groupname = request.getParameter("groupname");

		check(jobname, groupname);

		this.jobTaskService.pauseAllJob(jobname, groupname);

		Admin loginUser = this.operator;
		logger.debug(context + "：暂停一条Job任务,任务名称：[" + jobname + "],任务分组：[" + groupname + "],操作用户：["
				+ loginUser.getUsername() + "]");

		return list();
	}

	/**
	 * 恢复服务
	 *
	 * @return
	 * @throws Exception
	 */
	public String resume() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jobname = request.getParameter("jobname");
		String groupname = request.getParameter("groupname");

		check(jobname, groupname);

		this.jobTaskService.resumeAllJob(jobname, groupname);

		Admin loginUser = this.operator;
		logger.debug(context + "：恢复一条Job任务,任务名称：[" + jobname + "],任务分组：[" + groupname + "],操作用户：["
				+ loginUser.getUsername() + "]");

		return list();
	}

	/**
	 * 检查传入条件合法性
	 *
	 * @param jobname
	 *            任务名
	 * @param groupname
	 *            组名
	 * @param params
	 *            其余参数
	 * @throws Exception
	 */
	private final void check(String jobname, String groupname, String[]... params) throws Exception {
		if (StringUtils.isEmpty(jobname) || StringUtils.isBlank(jobname)) {
			throw new Exception("任务名不能为空！");
		} else {
			jobname = jobname.trim();
		}

		if (StringUtils.isEmpty(groupname) || StringUtils.isBlank(groupname)) {
			throw new Exception("任务组不能为空！");
		} else {
			groupname = groupname.trim();
		}

	}

	/**
	 * 修改一个任务
	 *
	 * @return
	 * @throws Exception
	 */
	public String modify() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 任务参数
		String[] dataKey = request.getParameterValues("dataKey");
		String[] dataValue = request.getParameterValues("dataValue");

		String jobname = request.getParameter("jobname");
		String groupname = request.getParameter("groupname");
		String className = request.getParameter("className");
		String description = request.getParameter("description");
		// String volatility=request.getParameter("volatility");
		String durability = request.getParameter("durability");
		String cronExpression = request.getParameter("cronExpression");

		// 执行类型 -1 请选择 1-重复执行(CronTrigger) 2-执行一次(SimpleTrigger)
		String taskTypeSel = obtainVal(request, "taskTypeSel");

		check(jobname, groupname);
		Class<Job> jobClass = null;
		if (StringUtils.isNotEmpty(className) && StringUtils.isNotBlank(className)) {
			try {
				jobClass = (Class<Job>) Class.forName(className);
			} catch (ClassNotFoundException e) {
				throw new Exception("待执行的任务类不存在！", e);
			}
		}

		// JobDetailImpl jobDetail = new JobDetailImpl();
		JobDetailImpl jobDetail = (JobDetailImpl) JobBuilder.newJob(jobClass).withIdentity(jobname, groupname).build();
		jobDetail.setName(jobname);// 任务名
		jobDetail.setGroup(groupname);// 组名

		if (StringUtils.isNotEmpty(description) && StringUtils.isNotBlank(description)) {
			jobDetail.setDescription(description);
		}

		jobDetail.setDurability(Boolean.parseBoolean(durability));

		JobDataMap data = new JobDataMap();
		if (dataKey != null && dataKey.length > 0) {
			for (int i = 0; i < dataKey.length; i++) {
				if (!"".equals(dataValue[i].trim())) {
					data.put(dataKey[i], dataValue[i]);
				}
			}
			if (data.size() > 0) {
				jobDetail.setJobDataMap(data);
			}
		}

		/** mod by 20130621 **/
		// 表达式没有输入，则通过界面配置
		if (StringUtils.isEmpty(cronExpression) || StringUtils.isBlank(cronExpression)) {
			// throw new Exception("表达式不能为空！");
			String taskDescription = "从";// 描述
			// 重复执行
			if (StringUtils.equals(taskTypeSel, "1")) {

				// [秒] [分] [小时] [日] [月] [周] [年]
				String taskRate = obtainVal(request, "taskRate");// 执行频率 -1 请选择
																	// day 每天
																	// week每周
																	// month每月
				String startTime = obtainVal(request, "startTime");// 开始时间
				taskDescription += startTime + "开始,";

				String endTime = null;// 结束时间
				String endTimeFlag = obtainVal(request, "endTimeFlag");// 有无结束日期标识
				Date _startTime = null, _endTime = null;

				// 持续时间
				checkValid(endTimeFlag, "结束日期标识");
				checkValid(startTime, "开始时间");
				_startTime = sdf.parse(startTime);

				if (StringUtils.equals(endTimeFlag, "yes")) {
					endTime = obtainVal(request, "endTime");// 结束时间
					checkValid(endTime, "结束时间 ");
					_endTime = sdf.parse(endTime);

					taskDescription += endTime + "结束,";
				} else if (StringUtils.equals(endTimeFlag, "no")) {

				} else {

				}

				String cron = "{1} {2} {3} {4} {5} {6}";// 少了 "年" 的配置

				// 每天
				if (StringUtils.equals(taskRate, TaskRate.DAY)) {
					cron = cron.replace(Placeholder.p4, QUESTION_MARK);// 替换日
					cron = cron.replace(Placeholder.p5, WILDCARD);// 替换月 每月触发
					cron = cron.replace(Placeholder.p6, WILDCARD);// 替换周

					taskDescription += "在每天";
				}
				// 每周
				else if (StringUtils.equals(taskRate, TaskRate.WEEK)) {
					String weekTimes = obtainVal(request, "weekTimes");// 每周多少执行
					String[] weekTimesVals = request.getParameterValues("weekTimesVal");// 周明细
					String str = "";
					if (weekTimes == null || "".equals(weekTimes)) {
						weekTimes = "0";
					}

					weekTimes = weekTimes.trim();
					int weekTimesInt = 0;
					try {
						if (StringUtils.equals(weekTimes, "1")) {
							weekTimesInt = 0;
						} else {
							weekTimesInt = Integer.parseInt(weekTimes);
						}

					} catch (Exception e) {
						weekTimesInt = 0;
					}

					if (weekTimesVals != null && weekTimesVals.length > 0) {
						taskDescription += "在每" + (weekTimesInt == 0 ? "" : weekTimesInt) + "周,";
						final int weekLen = weekTimesVals.length;
						for (int i = 0; i < weekLen; i++) {
							// 需要每隔*周执行
							if (weekTimesInt > 0) {
								str += weekTimesVals[i] + "/" + weekTimesInt + ",";
							} else {
								str += weekTimesVals[i] + ",";
							}
							// 拼接描述语句
							if (i == (weekLen - 1))
								taskDescription += JobTaskUtils.Week.obtainWeekDetial(weekTimesVals[i]);
							else
								taskDescription += JobTaskUtils.Week.obtainWeekDetial(weekTimesVals[i]) + "、";
						}

						if (str.endsWith(",")) {
							str = str.substring(0, str.length() - 1);
						}
						cron = cron.replace(Placeholder.p4, QUESTION_MARK);// 替换日
						cron = cron.replace(Placeholder.p5, WILDCARD);// 替换月
																		// 每月触发
						cron = cron.replace(Placeholder.p6, str);// 替换周

					} else {
						// 需要每隔*周执行
						if (weekTimesInt > 0) {
							taskDescription += "在每" + (weekTimesInt == 0 ? "" : weekTimesInt) + "周";
							str += "1/" + weekTimesInt + ",2/" + weekTimesInt + ",3/" + weekTimesInt + ",4/"
									+ weekTimesInt + ",5/" + weekTimesInt + ",6/" + weekTimesInt + ",7/" + weekTimesInt;
							cron = cron.replace(Placeholder.p4, QUESTION_MARK);// 替换日
						} else {
							taskDescription += "在每周";
							str = QUESTION_MARK;
							cron = cron.replace(Placeholder.p4, WILDCARD);// 替换日
						}

						cron = cron.replace(Placeholder.p5, WILDCARD);// 替换月
																		// 每月触发
						cron = cron.replace(Placeholder.p6, str);// 替换周
					}

				}
				// 每月
				else if (StringUtils.equals(taskRate, TaskRate.MONTH)) {
					taskDescription += "在每月,";
					/**
					 * -1 请选择 ;1 第一个 ;2 第二个 ;3 第三个 ;4 第四个 ;L 最后一个
					 */
					String monthFrontSel = obtainVal(request, "monthFrontSel");
					/**
					 * -1 请选择;1 星期日;2 星期一 ;3 星期二;4 星期三 ;5 星期四 ;6 星期五 ;7 星期六 ;
					 * workday 工作日 ;restday 休息日
					 */
					String monthBackSel = obtainVal(request, "monthBackSel");

					if (StringUtils.isEmpty(monthFrontSel) || StringUtils.equals(monthFrontSel, "-1")) {
						throw new Exception("需要选择每月触发频率！");
					}

					if (monthFrontSel.equals("L")) {
						taskDescription += "最后一个";
					} else {
						taskDescription += "第" + monthFrontSel + "个";
					}

					if (StringUtils.isEmpty(monthBackSel) || StringUtils.equals(monthBackSel, "-1")) {
						throw new Exception("需要选择每月触发频率！");
					}
					taskDescription += JobTaskUtils.Month.obtainMonthBackSel(monthBackSel);

					String monthStr = "";
					// 最后一个
					if (StringUtils.equals(monthFrontSel, "L")) {
						monthStr = monthBackSel + monthFrontSel;
					} else {
						monthStr = monthBackSel + "#" + monthFrontSel;
					}

					cron = cron.replace(Placeholder.p4, QUESTION_MARK);// 替换日
					cron = cron.replace(Placeholder.p5, WILDCARD);// 替换月 每月触发
					cron = cron.replace(Placeholder.p6, monthStr);// 替换周
				} else {

				}
				/**** 共有属性 ***/
				// [秒] [分] [小时] [日] [月] [周] [年]
				// 每天频率 -1 请选择 one-执行一次 more-间隔执行
				String taskRateDaySel = obtainVal(request, "taskRateDaySel");
				// 执行一次
				if (StringUtils.equals(taskRateDaySel, "one")) {
					String oneTime = obtainVal(request, "oneTime");// 在每天**执行

					checkValid(oneTime, "执行一次，时间");

					String[] times = oneTime.split(TIME_SPLIT);

					String hour = times[0];// 时
					if (hour.equals("00")) {
						hour = "0";
					}

					String min = times[1];// 分
					if (min.equals("00")) {
						min = "0";
					}

					String sec = times[2];// 秒
					if (sec.equals("00")) {
						sec = "0";
					}

					cron = cron.replace(Placeholder.p1, sec);
					cron = cron.replace(Placeholder.p2, min);
					cron = cron.replace(Placeholder.p3, hour);

					taskDescription += "," + hour + "时" + min + "分" + sec + "秒执行";

				}
				// 间隔执行
				else if (StringUtils.equals(taskRateDaySel, "more")) {
					String betweenTimes = obtainVal(request, "betweenTimes");// 在每**执行
					String betweenTimeSel = obtainVal(request, "betweenTimeSel");// hour-时
																					// minute-分
																					// second-秒

					checkValid(betweenTimes, "执行间隔");
					// Date now = new Date();
					// String hour=now.getHours()+"", min =
					// now.getMinutes()+"",sec = "0";
					String hour = "*", min = "*", sec = "0";
					if (StringUtils.equals(betweenTimeSel, "hour")) {
						min = "0";
						hour = "0/" + betweenTimes;// 时
						taskDescription += ",每隔" + betweenTimes + "小时执行";
					} else if (StringUtils.equals(betweenTimeSel, "minute")) {
						// min = now.getMinutes()+"";
						// min = min+"/"+betweenTimes;//分
						min = "0/" + betweenTimes;// 分

						taskDescription += ",每隔" + betweenTimes + "分钟执行";
					} else if (StringUtils.equals(betweenTimeSel, "second")) {
						sec = "0/" + betweenTimes;// 秒

						taskDescription += ",每隔" + betweenTimes + "秒钟执行";
					} else {
					}

					cron = cron.replace(Placeholder.p1, sec);
					cron = cron.replace(Placeholder.p2, min);
					cron = cron.replace(Placeholder.p3, hour);

				} else {
				}
				/**** 共有属性 ***/

				// System.out.println(taskDescription);
				CronExpression expression = null;
				try {
					expression = new CronExpression(cron);
				} catch (ParseException e) {
					throw new Exception("表达式'" + cron + "'生成有误！", e);
				}
				// System.out.println("=====cron===="+cron);
				// CronTriggerImpl cTrigger = new
				// CronTriggerImpl(jobname,groupname, jobname,
				// groupname,_startTime ,_endTime ,cron);

				CronTriggerImpl cTrigger = (CronTriggerImpl) TriggerBuilder.newTrigger()
						.withIdentity(jobname, groupname).withSchedule(CronScheduleBuilder.cronSchedule(expression))
						.build();
				cTrigger.setJobName(jobname);
				cTrigger.setJobGroup(groupname);
				cTrigger.setName(jobname);
				cTrigger.setGroup(groupname);
				cTrigger.setStartTime(_startTime);
				cTrigger.setEndTime(_endTime);
				cTrigger.setCronExpression(expression);
				cTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

				// shouldRecover属性为true，则当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务。
				jobDetail.setRequestsRecovery(true);
				jobDetail.setTriggerType(1);
				this.jobTaskService.updateJob(jobDetail, cTrigger, taskDescription);
			}

			// 执行一次
			else if (StringUtils.equals(taskTypeSel, "2")) {
				// String taskDate = obtainVal(request,"taskDate");//日期
				// String taskTime = obtainVal(request,"taskTime");//时间
				//
				// Date runTime;
				// if(StringUtils.isEmpty(taskDate)||StringUtils.isBlank(taskDate)||StringUtils.isEmpty(taskTime)||StringUtils.isBlank(taskTime))
				// {
				// runTime = new Date();
				// }
				// else
				// {
				// String source = taskDate+" "+taskTime;
				// runTime = sdf.parse(source);
				// }
				// SimpleTrigger trigger = new SimpleTrigger(jobname, groupname,
				// runTime);
				//
				// this.jobTaskService.saveNewStJob(jobDetail, trigger);
				// System.out.println("日期："+taskDate+",时间："+taskTime);
			}
			// 请选择
			else if (StringUtils.equals(taskTypeSel, "-1")) {
				/** 空方法 */
			} else {
				/** 空方法 */
			}

		} else {
			cronExpression = cronExpression.trim();

			// 执行中应用发生故障，需要重新执行
			// jobDetail.setRequestsRecovery(true);
			// 即使没有Trigger关联时，也不需要删除该JobDetail
			// jobDetail.setDurability(true);
			CronExpression expression = null;
			try {
				expression = new CronExpression(cronExpression);
			} catch (ParseException e) {
				throw new Exception("表达式Cron输入有误！", e);
			}

			// CronTriggerImpl cTrigger = new CronTriggerImpl(jobname,groupname,
			// jobname, groupname ,cronExpression);
			CronTriggerImpl cTrigger = (CronTriggerImpl) TriggerBuilder.newTrigger().withIdentity(jobname, groupname)
					.withSchedule(CronScheduleBuilder.cronSchedule(expression)).build();
			cTrigger.setJobName(jobname);
			cTrigger.setJobGroup(groupname);
			cTrigger.setName(jobname);
			cTrigger.setGroup(groupname);
			cTrigger.setCronExpression(expression);
			cTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

			// shouldRecover属性为true，则当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务。
			jobDetail.setRequestsRecovery(true);
			jobDetail.setTriggerType(1);
			this.jobTaskService.updateJob(jobDetail, cTrigger, null);

		}

		/** mod by 20130621 **/

		Admin loginUser = this.operator;
		logger.debug(context + "：修改一条Job任务,任务名称：[" + jobDetail.getName() + "],任务分组：[" + jobDetail.getGroup()
				+ "],操作用户：[" + loginUser.getUsername() + "]");
		return list();
	}

	/**
	 * 任务日志管理
	 *
	 * @return
	 */
	public String logcheck() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jobname = null; // 任务名
		String groupname = null;// 组名
		String flag = request.getParameter("flag");// 标识
		// 从任务配置界面进去
		if (StringUtils.isNotEmpty(flag) && StringUtils.equals(flag, "detail")) {
			jobname = request.getParameter("ljobname");
			groupname = request.getParameter("lgroupname");
			request.setAttribute("flag", flag);
		} else {
			jobname = request.getParameter("jobName");
			groupname = request.getParameter("groupName");
		}

		final String[] params = new String[] { jobname, groupname };
		list = (List<QrtzLog>) jobTaskService.findByPager(pager, params);

		// 将条件回传到页面
		request.setAttribute("jobName", jobname);
		request.setAttribute("groupName", groupname);

		return "logcheck";
	}

	/**
	 * 自动绑定流水
	 *
	 * @return
	 */
	public String manualBindingLiuShui() {
		// 获取开始时间
		String startQueryDate = this.conditions.getStartQueryDate();
		String days = this.conditions.getDays();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStart = null;
		try {
			dateStart = dateFormat.parse(startQueryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//
		String endQuertDate = DateUtil.addDay(dateStart, days);
		System.out.println(endQuertDate);
		QrtzLog qrtzLog1 = qrtzLogService.getQrtzLogByClassName(GetLaneEnListDataJob.class.getName());
		QrtzLog qrtzLog2 = qrtzLogService.getQrtzLogByClassName(GetLaneExListDataJob.class.getName());
		this.laneEnListService.saveLaneEnList(qrtzLog1, startQueryDate, endQuertDate);
		this.laneExListService.saveLaneExList(qrtzLog2, startQueryDate, endQuertDate);

		qrtzLog1.setJobName("自动获取入口流水");
		qrtzLog1.setJobGroup("流水组");
		qrtzLog1.setJobClassName("com.hgsoft.job.GetLaneEnListDataJob");
		qrtzLog2.setJobName("自动获取出口流水");
		qrtzLog2.setJobGroup("流水组");
		qrtzLog2.setJobClassName("com.hgsoft.job.GetLaneExListDataJob");

		qrtzLogService.saveQrtzLog(qrtzLog1);
		qrtzLogService.saveQrtzLog(qrtzLog2);

		map.put("msg", "自动获取入口出口数据成功!");
		return "success";
	}

	public String nameExist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jobName = request.getParameter("jobName");// jobForm.getGroupNameEqualFilter();
		boolean flag = jobTaskService.nameExist(jobName);
		if (flag == true) {
			setJobNameExist("yes");
		} else {
			setJobNameExist("no");
		}
		return "nameExist";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setJobNameExist(String jobNameExist) {
		this.jobNameExist = jobNameExist;
	}

	public String getJobNameExist() {
		return jobNameExist;
	}

	public Conditions getConditions() {
		return conditions;
	}

	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}
}