package com.hgsoft.main.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.quartz.Calendar;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.factory.JobFactory;
import com.hgsoft.main.dao.JobDao;
import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.util.Pager;

@Service("jobTaskService")
@SuppressWarnings("unchecked")
public class JobTaskService {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private Scheduler schedulerFactory;
    @Autowired
    private JobFactory jobFactory;

    private final static String prefix = "yyyy-MM-dd HH:mm:ss";
    private final static SimpleDateFormat sdf = new SimpleDateFormat(prefix);

//	private final static String JOB_DETAILS = Constants.DEFAULT_TABLE_PREFIX
//			+ Constants.TABLE_JOB_DETAILS;

    // public void setJobDao(JobDao jobDao) {
    // this.jobDao = jobDao;
    // }

    // public void setScheduler(Scheduler scheduler) {
    // this.scheduler = scheduler;
    // }

    // public void setJobFactory(JobFactory jobFactory) {
    // this.jobFactory = jobFactory;
    // }

    @PostConstruct
    void start() throws Exception {
        // System.out.println("======================scheduler start====================");
        if (jobFactory != null) {
            schedulerFactory.setJobFactory(jobFactory);
        }
        schedulerFactory.start();
    }

    @PreDestroy
    void shutdown() throws Exception {
        sanityCheck();
        // 获取所有的触发器组
        List<String> groupNames = getTriggerGroupNames();
        if (null != groupNames && groupNames.size() > 0) {
            int len = groupNames.size();
            int t_len = 0;
            List<String> triggerNames = null;
            String sql = "";
            for (int i = 0; i < len; i++) {

                triggerNames = getTriggerNames(groupNames.get(i));
                if (null != triggerNames && triggerNames.size() > 0) {
                    t_len = triggerNames.size();
                    for (int j = 0; j < t_len; j++) {

                        TriggerState state = getTriggerState(triggerNames.get(j),
                                groupNames.get(i));
                        // System.out.println("triggername: "+triggerNames[j]+",groupname: "+groupNames[i]+",state: "+state);
                        // 如果当前触发器是运行状态，即将其更改为暂停
                        if (state == Trigger.TriggerState.NORMAL) {
                            this.pauseTrigger(triggerNames.get(j), groupNames.get(i));
                            sql = "insert into tb_Quartz_Lock(TRIGGER_NAME,TRIGGER_GROUP) values('"
                                    + triggerNames.get(j)
                                    + "','"
                                    + groupNames.get(i)
                                    + "')";
                            updOrDelWithSql(sql);
                        }
                    }
                }
            }
        }
        if (!schedulerFactory.isShutdown()) {
            schedulerFactory.shutdown(false);
        }
        // System.out.println("======================scheduler shutdown====================");
    }

    /**
     * 根据Hql执行更新或删除操作
     *
     * @param hql
     */
    public void updOrDelWithHql(String hql) {
        this.jobDao.updOrDelWithHql(hql);
    }

    /**
     * 根据Sql执行更新或删除操作
     *
     * @param sql
     */
    public void updOrDelWithSql(String sql) {
        this.jobDao.updOrDelWithSql(sql);
    }

    void sanityCheck() throws Exception {
        if (schedulerFactory == null) {
            throw new Exception(
                    "The manager object is not initialized correctly.");
        }
    }

    /**
     * 增加新任务
     *
     * @param jobDetail
     * @param replace
     * @throws Exception
     */
    public void addJob(JobDetail jobDetail, boolean replace) throws Exception {
        sanityCheck();
        // replace是否替换原来存在的数据，新增false,修改ture
        schedulerFactory.addJob(jobDetail, replace);
    }

    /**
     * 删除日历
     *
     * @param calName
     * @return
     * @throws Exception
     */
    public boolean deleteCalendar(String calName) throws Exception {
        sanityCheck();
        return schedulerFactory.deleteCalendar(calName);
    }

    /**
     * 删除任务
     *
     * @param jobName   任务名
     * @param groupName 组名
     * @return
     * @throws Exception
     */
    public boolean deleteJob(String jobName, String groupName) throws Exception {
        sanityCheck();
        JobKey jobKey = new JobKey(jobName, groupName);
        return schedulerFactory.deleteJob(jobKey);
    }

    /**
     * 根据日历名获取日历
     *
     * @param calName
     * @return
     * @throws Exception
     */
    public Calendar getCalendar(String calName) throws Exception {
        sanityCheck();
        return schedulerFactory.getCalendar(calName);
    }

    /**
     * 获取所有日历名
     *
     * @return
     * @throws Exception
     */
    public List<String> getCalendarNames() throws Exception {
        sanityCheck();
        return schedulerFactory.getCalendarNames();
    }

    /**
     * 获取当前正在执行的任务
     *
     * @return
     * @throws Exception
     */
    public List<?> getCurrentlyExecutingJobs() throws Exception {
        sanityCheck();
        return schedulerFactory.getCurrentlyExecutingJobs();
    }

    /**
     * 根据任务名及组名查询任务明细
     *
     * @param jobName  任务名
     * @param jobGroup 组名
     * @return
     * @throws Exception
     */
    public JobDetail getJobDetail(String jobName, String jobGroup)
            throws Exception {
        sanityCheck();
        JobKey jobKey = new JobKey(jobName, jobGroup);
        return schedulerFactory.getJobDetail(jobKey);
    }

    /**
     * 获取所有组别名
     *
     * @return
     * @throws Exception
     */
    public List<String> getJobGroupNames() throws Exception {
        sanityCheck();
        return schedulerFactory.getJobGroupNames();
    }


    /**
     * 获取所有已暂停的触发器组
     *
     * @return
     * @throws Exception
     */
    public Set<?> getPausedTriggerGroups() throws Exception {
        sanityCheck();
        return schedulerFactory.getPausedTriggerGroups();
    }

    /**
     * 根据触发器名和触发器组获取指定触发器对象
     *
     * @param triggerName  触发器名
     * @param triggerGroup 触发器组
     * @return
     * @throws Exception
     */
    public Trigger getTrigger(String triggerName, String triggerGroup)
            throws Exception {
        sanityCheck();
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        return schedulerFactory.getTrigger(triggerKey);
    }

    /**
     * 获取所有触发器组别名
     *
     * @return
     * @throws Exception
     */
    public List<String> getTriggerGroupNames() throws Exception {
        sanityCheck();
        return schedulerFactory.getTriggerGroupNames();
    }

    /**
     * 根据组别名获取指定触发器名
     *
     * @param groupName
     * @return
     * @throws Exception
     */
    public List<String> getTriggerNames(String groupName) throws Exception {
        sanityCheck();
        return schedulerFactory.getTriggerNames(groupName);
    }

    /**
     * 根据触发器名和触发器组别名获取指定触发器状态
     *
     * @param triggerName  触发器名
     * @param triggerGroup 触发器组别名
     * @return
     * @throws Exception
     */
    public TriggerState getTriggerState(String triggerName, String triggerGroup)
            throws Exception {
        sanityCheck();
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        TriggerState triggerState = schedulerFactory.getTriggerState(triggerKey);
//		System.out.println("getTriggerState: "+ triggerState.ordinal());
//		System.out.println("getTriggerState: "+ triggerState.name());
        return triggerState;
    }

    /**
     * 根据任务名和组别名获取触发器
     *
     * @param jobName   任务名
     * @param groupName 组别名
     * @return
     * @throws Exception
     */
    public List<Trigger> getTriggersOfJob(String jobName, String groupName)
            throws Exception {
        sanityCheck();
        JobKey jobKey = new JobKey(jobName, groupName);
        return (List<Trigger>) schedulerFactory.getTriggersOfJob(jobKey);
    }

    /**
     * 根据任务名和组别名来中断指定的任务
     *
     * @param jobName
     * @param groupName
     * @return
     * @throws Exception
     */
    public boolean interrupt(String jobName, String groupName) throws Exception {
        sanityCheck();
        JobKey jobKey = new JobKey(jobName, groupName);
        return schedulerFactory.interrupt(jobKey);
    }

    public boolean isInStandbyMode() throws Exception {
        sanityCheck();
        return schedulerFactory.isInStandbyMode();
    }

    /**
     * 判断任务调度器是否关闭
     *
     * @return
     * @throws Exception
     */
    public boolean isShutdown() throws Exception {
        sanityCheck();
        return schedulerFactory.isShutdown();
    }

    /**
     * 判断任务调度器是否开启
     *
     * @return
     * @throws Exception
     */
    public boolean isStarted() throws Exception {
        sanityCheck();
        return schedulerFactory.isStarted();
    }

    /**
     * 暂停所有任务
     *
     * @throws Exception
     */
    public void pauseAll() throws Exception {
        sanityCheck();
        schedulerFactory.pauseAll();
    }

    /**
     * 暂停指定任务
     *
     * @param jobName   任务名
     * @param groupName 组别名
     * @throws Exception
     */
    public void pauseJob(String jobName, String groupName) throws Exception {
        sanityCheck();
        JobKey jobKey = new JobKey(jobName, groupName);
        schedulerFactory.pauseJob(jobKey);
    }

    /**
     * 暂停指定任务组任务
     *
     * @param groupName 组别名
     * @throws Exception
     */
    public void pauseJobGroup(String groupName) throws Exception {
        sanityCheck();

        GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupEquals(groupName);
        schedulerFactory.pauseJobs(matcher);
    }


    /**
     * 暂停trigger
     *
     * @param triggerName
     * @param groupName
     * @throws Exception
     */
    public void pauseTrigger(String triggerName, String groupName)
            throws Exception {
        sanityCheck();
        TriggerKey triggerKey = new TriggerKey(triggerName, groupName);
        schedulerFactory.pauseTrigger(triggerKey);
    }

    /**
     * 暂停指定任务组的触发器
     *
     * @param groupName 组别名
     * @throws Exception
     */
    public void pauseTriggerGroup(String groupName) throws Exception {
        sanityCheck();

        GroupMatcher<TriggerKey> matcher = GroupMatcher.groupEquals(groupName);
        schedulerFactory.pauseTriggers(matcher);
    }

    /**
     * 重置任务调度器
     *
     * @param triggerName 触发器名
     * @param groupName   组别名
     * @param newTrigger  新触发器
     * @return
     * @throws Exception
     */
    public Date rescheduleJob(String triggerName, String groupName,
                              Trigger newTrigger) throws Exception {
        sanityCheck();
        return schedulerFactory.rescheduleJob(new TriggerKey(triggerName, groupName), newTrigger);
    }

    /**
     * 恢复所有任务
     *
     * @throws Exception
     */
    public void resumeAll() throws Exception {
        sanityCheck();
        schedulerFactory.resumeAll();
    }

    /**
     * 恢复任务
     *
     * @param jobName
     * @param groupName
     * @throws Exception
     */
    public void resumeJob(String jobName, String groupName) throws Exception {
        sanityCheck();
        schedulerFactory.resumeJob(new JobKey(jobName, groupName));
    }

    /**
     * 恢复指定任务组任务
     *
     * @param groupName 组别名
     * @throws Exception
     */
    public void resumeJobGroup(String groupName) throws Exception {
        sanityCheck();
        schedulerFactory.resumeJobs(GroupMatcher.jobGroupEquals(groupName));
    }

    /**
     * 恢复trigger
     *
     * @param triggerName
     * @param groupName
     * @throws Exception
     */
    public void resumeTrigger(String triggerName, String groupName)
            throws Exception {
        sanityCheck();
        schedulerFactory.resumeTrigger(new TriggerKey(triggerName, groupName));
    }

    /**
     * 恢复指定任务组触发器
     *
     * @param groupName
     * @throws Exception
     */
    public void resumeTriggerGroup(String groupName) throws Exception {
        sanityCheck();
        GroupMatcher<TriggerKey> matcher = GroupMatcher.groupEquals(groupName);
        schedulerFactory.resumeTriggers(matcher);
    }

    public Date scheduleJob(JobDetail jobDetail, Trigger trigger)
            throws Exception {
        sanityCheck();
        return schedulerFactory.scheduleJob(jobDetail, trigger);
    }

    public Date scheduleJob(Trigger trigger) throws Exception {
        sanityCheck();
        return schedulerFactory.scheduleJob(trigger);
    }

    public void standby() throws Exception {
        sanityCheck();
        schedulerFactory.standby();
    }

    /**
     * 开始一次任务
     *
     * @param jobName   　任务名
     * @param groupName 　组别名
     * @param data      　任务参数
     * @throws Exception
     */
    public void triggerJob(String jobName, String groupName)
            throws Exception {
        sanityCheck();
        schedulerFactory.triggerJob(new JobKey(jobName, groupName));
    }

//	/**
//	 * 开始一次任务
//	 * 
//	 * @param jobName
//	 *            　任务名
//	 * @param groupName
//	 *            　组别名
//	 * @param data
//	 *            　任务参数
//	 * @throws Exception
//	 */
//	public void triggerJob(String jobName, String groupName, JobDataMap data)
//			throws Exception {
//		sanityCheck();
//		schedulerFactory.triggerJob(jobName, groupName, data);
//	}

//	public void triggerJobWithVolatileTrigger(String jobName, String groupName,
//			JobDataMap data) throws Exception {
//		sanityCheck();
//		schedulerFactory
//				.triggerJobWithVolatileTrigger(jobName, groupName, data);
//	}

    public boolean unscheduleJob(String triggerName, String groupName)
            throws Exception {
        sanityCheck();
        return schedulerFactory.unscheduleJob(new TriggerKey(triggerName, groupName));
    }

    /**
     * 新增一个任务(SimpleTriggers)
     *
     * @param jobDetail   任务明细
     * @param cTrigger    触发器
     * @param triggerDesc 触发器描述
     * @throws Exception
     */
    public void saveNewStJob(JobDetailImpl jobDetail, Trigger trigger,
                             String triggerDesc) throws Exception {
        jobDetail.setTriggerType(0);
        jobDetail.setTriggerDesc(triggerDesc);

        schedulerFactory.scheduleJob(jobDetail, trigger);


//		String sql = "update " + JOB_DETAILS + " set "
//				+ Constants.COL_MY_TRIGGER_TYPE + "=0,"
//				+ Constants.COL_MY_TRIGGER_DESC + "='" + triggerDesc
//				+ "' where " + Constants.COL_JOB_NAME + "='"
//				+ jobDetail.getName() + "' and " + Constants.COL_JOB_GROUP
//				+ "='" + jobDetail.getGroup() + "'";
//		this.jobDao.updOrDelWithSql(sql);
    }

    /**
     * 新增一个任务
     *
     * @param jobDetail   任务明细
     * @param cTrigger    触发器
     * @param triggerDesc 触发器描述
     * @throws Exception
     */
    public void saveNewJob(JobDetailImpl jobDetail, Trigger cTrigger,
                           String triggerDesc) throws Exception {
        jobDetail.setTriggerType(1);
        jobDetail.setTriggerDesc(triggerDesc);

        // 新增任务
        this.addJob(jobDetail, false);

//		String sql = "update " + JOB_DETAILS + " set "
//				+ Constants.COL_MY_TRIGGER_TYPE + "=1,"
//				+ Constants.COL_MY_TRIGGER_DESC + "='" + triggerDesc
//				+ "' where " + Constants.COL_JOB_NAME + "='"
//				+ jobDetail.getName() + "' and " + Constants.COL_JOB_GROUP
//				+ "='" + jobDetail.getGroup() + "'";
//		this.jobDao.updOrDelWithSql(sql);

        // 开始任务
        this.scheduleJob(cTrigger);

    }

    /**
     * 根据任务名和组别名暂停指定任务
     *
     * @param jobname   任务名
     * @param groupname 组别名
     * @throws Exception
     */
    public void pauseAllJob(String jobname, String groupname) throws Exception {
        // 暂停任务
        pauseJob(jobname, groupname);
        // 暂停触发器
        pauseTrigger(jobname, groupname);
    }

    /**
     * 根据任务名和组别名恢复指定任务
     *
     * @param jobname   任务名
     * @param groupname 组别名
     * @throws Exception
     */
    public void resumeAllJob(String jobname, String groupname) throws Exception {
        // 恢复任务
        resumeJob(jobname, groupname);
        // 恢复触发器
        resumeTrigger(jobname, groupname);
    }

    /**
     * 更新任务
     *
     * @param jobDetail 任务明细
     * @param cTrigger  触发器
     * @throws Exception
     */
    public void updateJob(JobDetailImpl jobDetail, CronTrigger cTrigger,
                          String triggerDesc) throws Exception {
        if (StringUtils.isNotEmpty(triggerDesc)
                && StringUtils.isNotBlank(triggerDesc)) {
//			String sql = "update " + JOB_DETAILS + " set "
//					+ Constants.COL_MY_TRIGGER_DESC + "='" + triggerDesc
//					+ "' where " + Constants.COL_JOB_NAME + "='"
//					+ jobDetail.getName() + "' and " + Constants.COL_JOB_GROUP
//					+ "='" + jobDetail.getGroup() + "'";
//			this.jobDao.updOrDelWithSql(sql);

            jobDetail.setTriggerDesc(triggerDesc);
        }

        // 替换原有任务
        this.addJob(jobDetail, true);

        Trigger trigger = this.getTrigger(jobDetail.getName(),
                jobDetail.getGroup());

        if (trigger != null) {
            // 删除原有触发器
            this.unscheduleJob(jobDetail.getName(), jobDetail.getGroup());
        }


        // 开始任务
        scheduleJob(cTrigger);
    }

    /**
     * 以分页形式查询（带条件）
     *
     * @param pager 分页类
     * @return
     */
    public Object findByPager(Pager pager, String[] params) {
        String hql = "select count(id) from QrtzLog";
        String where = queryString(params);

        hql += where;
        // System.out.println("hql >> "+hql);

        List<Long> counts = (List<Long>) jobDao.findWitHql(hql);

        Long count = (counts == null || counts.isEmpty()) ? 0 : counts.get(0);

        pager.setTotalSize(count);

        hql = "from QrtzLog";
        hql += where;
        hql += " order by id desc";
        List<QrtzLog> taskLogs = (List<QrtzLog>) jobDao.findWithHql(pager, hql,
                null);

        return taskLogs;
    }

    /**
     * 拼接查询条件
     *
     * @param params 传入查询条件
     * @return 拼接后sql
     */
    private final String queryString(String[] params) {
        String query = "";
        StringBuffer sb = new StringBuffer(" where 1=1 ");
        String jobname = params[0];
        String groupname = params[1];

        if (StringUtils.isNotEmpty(jobname) && StringUtils.isNotBlank(jobname)) {
            sb.append(" and job_Name like '" + jobname.trim() + "%'");
        }

        if (StringUtils.isNotEmpty(groupname)
                && StringUtils.isNotBlank(groupname)) {
            sb.append(" and job_Group like '" + groupname.trim() + "%'");
        }

        query = sb.toString();
        return query;
    }

    /**
     * 通过Hql查询
     *
     * @param hql 待处理hql串
     * @return 返回查询结果
     */
    public Object findWithSql(String sql) {
        return jobDao.findWithSql(sql);
    }

    /**
     * 通过Hql查询
     *
     * @param hql 待处理hql串
     * @return 返回查询结果
     */
    public Object findWithSql(String sql, Class clazz) {
        return jobDao.getSession().createSQLQuery(sql).addEntity(clazz).list();
    }

    /**
     * 通过Hql查询
     *
     * @param hql 待处理hql串
     * @return 返回查询结果
     */
    public Object findWithHql(String hql) {
        return jobDao.findWitHql(hql);
    }

    /**
     * 保存实体
     *
     * @param entity 游离对象
     * @return
     */
    public Object save(Object entity) {
        return jobDao.save(entity);
    }

    public List<JobDetailImpl> findJobsByRam(String[] params) throws Exception {
        if (null == params || params.length == 0)
            throw new Exception("传入参数数组有误");

        String jobName = params[0];
        String groupName = params[1];
        String triggerType = params[2];

        List<JobDetailImpl> jobDetailImpls = new ArrayList<JobDetailImpl>();
        List<String> jobGroupNames = schedulerFactory.getJobGroupNames();
        for (String jobGroupName : jobGroupNames) {
            GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupEquals(jobGroupName);
            Set<JobKey> jobKeys = schedulerFactory.getJobKeys(matcher);

            for (JobKey jobKey : jobKeys) {
                JobDetail jobDetail = schedulerFactory.getJobDetail(jobKey);
                JobDetailImpl jobDetailImpl = (JobDetailImpl) jobDetail;


                if (StringUtils.isNotEmpty(jobName) && StringUtils.isNotBlank(jobName)) {
                    if (!jobName.equals(jobDetailImpl.getName())) {
                        continue;
                    }
                }

                if (StringUtils.isNotEmpty(groupName)
                        && StringUtils.isNotBlank(groupName)) {
                    if (!groupName.equals(jobDetailImpl.getGroup())) {
                        continue;
                    }
                }

                if (StringUtils.isNotEmpty(triggerType)
                        && StringUtils.isNotBlank(triggerType)
                        && !StringUtils.equals(triggerType, "-1")) {
                    if (!triggerType.equals(jobDetailImpl.getTriggerType() + "")) {
                        continue;
                    }
                }


                TriggerKey triggerKey = new TriggerKey(jobDetailImpl.getName(), jobDetailImpl.getGroup());
                TriggerState triggerState = schedulerFactory.getTriggerState(triggerKey);
                Trigger trigger = schedulerFactory.getTrigger(triggerKey);
                if (trigger != null) {
                    if (trigger.getNextFireTime() == null) {
                        jobDetailImpl.setNextFireTime("");
                    } else {
                        jobDetailImpl.setNextFireTime(sdf.format(trigger.getNextFireTime()));
                    }

                    if (trigger.getPreviousFireTime() == null) {
                        jobDetailImpl.setPrevFireTime("");
                    } else {
                        jobDetailImpl.setPrevFireTime(sdf.format(trigger.getPreviousFireTime()));
                    }
                }

                if (triggerState != null) {
                    jobDetailImpl.setTriggerState(triggerState.name());
                }
                //System.out.println("jobDetailImpl: "+jobDetailImpl+",triggerState: "+triggerState);
                jobDetailImpls.add(jobDetailImpl);
            }

        }
        return jobDetailImpls;
    }

    public List<JobDetailImpl> findJobsByJdbc(String[] params) throws Exception

    {

        return schedulerFactory.getAllJobDetail(params);
    }

    public List<Object[]> findJobsBySql(Pager pager, String[] params)
            throws Exception {
        if (null == params || params.length == 0)
            throw new Exception("传入参数数组有误");

        String jobName = params[0];
        String groupName = params[1];
        String triggerType = params[2];

        StringBuffer sb = new StringBuffer(
                "select [0] from QRTZ_JOB_DETAILS d left join QRTZ_TRIGGERS t on d.JOB_NAME = t.JOB_NAME and d.JOB_GROUP = t.JOB_GROUP where 1=1 ");

        if (StringUtils.isNotEmpty(jobName) && StringUtils.isNotBlank(jobName)) {
            sb.append(" and d.JOB_NAME like '%" + jobName.trim() + "%'");
        }

        if (StringUtils.isNotEmpty(groupName)
                && StringUtils.isNotBlank(groupName)) {
            sb.append(" and d.JOB_GROUP like '%" + groupName.trim() + "%'");
        }

        if (StringUtils.isNotEmpty(triggerType)
                && StringUtils.isNotBlank(triggerType)
                && !StringUtils.equals(triggerType, "-1")) {
            sb.append(" and d.TRIGGER_TYPE = '" + triggerType.trim() + "'");
        }

        String sql = sb.toString();

        String list = sql
                .replace(
                        "[0]",
                        "d.JOB_NAME,d.JOB_GROUP,t.TRIGGER_STATE,d.TRIGGER_TYPE,d.DESCRIPTION,t.PREV_FIRE_TIME,t.NEXT_FIRE_TIME");
        List<Object[]> results = (List<Object[]>) jobDao.findWithSql(list);

        if (!results.isEmpty()) {
            BigInteger _PREV_FIRE_TIME;
            BigInteger _NEXT_FIRE_TIME;
            Long NEXT_FIRE_TIME;
            Long PREV_FIRE_TIME;
            for (Object[] o : results) {

                if (o[5] != null && o[6] != null) {
                    _PREV_FIRE_TIME = (BigInteger) o[5];
                    _NEXT_FIRE_TIME = (BigInteger) o[6];

                    PREV_FIRE_TIME = _PREV_FIRE_TIME.longValue();
                    NEXT_FIRE_TIME = _NEXT_FIRE_TIME.longValue();

                    if ((long) -1 != (long) PREV_FIRE_TIME) {
                        o[5] = sdf.format(new Date(PREV_FIRE_TIME));
                    }
                    if ((long) -1 != (long) NEXT_FIRE_TIME) {
                        o[6] = sdf.format(new Date(NEXT_FIRE_TIME));
                    }
                }
            }
        }

        String countSql = sql.replace("[0]", "count(*)");

        List<Integer> counts = (List<Integer>) jobDao.findWithSql(countSql);
        if (counts != null && !counts.isEmpty()) {
            pager.setTotalSize(counts.get(0));
        } else {
            pager.setTotalSize(0);
        }

        return results;
    }

    public boolean nameExist(String jobName) {
        String sql = "select * from QRTZ_JOB_DETAILS where JOB_NAME='" + jobName + "'";
        List<?> list = (List<?>) jobDao.findWithSql(sql);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }
}
