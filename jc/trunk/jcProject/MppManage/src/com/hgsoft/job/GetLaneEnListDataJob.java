package com.hgsoft.job;

import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.main.laneenlist.service.LaneEnListService;
import com.hgsoft.util.DateUtil;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取中间库入口流水表数据job
 *
 * @author 郭志强
 * @time 2016/9/8 9:55
 */
public class GetLaneEnListDataJob extends JobLog implements Job {
    private static WebApplicationContext applicationContext = ContextLoader
            .getCurrentWebApplicationContext();

    private static Logger logger = Logger.getLogger(GetLaneEnListDataJob.class);



    /**
     * 任务调度方法
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        QrtzLog qrtzLog = getQrtzLogByClassName(this.getClass().getName());

        // 获取前一个小时时间
        String date = DateUtil.getYesDay("yyyy-MM-dd");
        // 根据时间从中间库中取出数据，并保存到本地库
        LaneEnListService laneEnListService = (LaneEnListService) applicationContext.getBean("laneEnListService");
        laneEnListService.saveLaneEnList(qrtzLog,date,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        saveQrtzLog(qrtzLog);//记日志

    }

    /**
     * 获取前一个小时时间
     *
     * @return
     */
    public String getPreOneHourseTime() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.HOUR);
        calendar.set(Calendar.HOUR, i - 2);
        Date time = calendar.getTime();
        String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        return timeStr;
    }



}
