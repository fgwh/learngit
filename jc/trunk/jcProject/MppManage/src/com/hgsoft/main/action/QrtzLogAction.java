package com.hgsoft.main.action;

import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.main.service.QrtzLogService;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.StringUtil;

@Controller
@Scope("prototype")
public class QrtzLogAction extends BaseAction{
	@Resource
    private QrtzLogService qrtzLogService;
	private String jobName;
	private String jobStatus;
	private String startQueryDate;
	private String endQueryDate;
	
	public String list(){
		Calendar calendar = Calendar.getInstance();
		// 默认的时间范围为当天
		if (StringUtil.isTrimEmpty(startQueryDate) || StringUtil.isTrimEmpty(endQueryDate)) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		    
			startQueryDate = (String)DateUtil.fromatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			endQueryDate = (String)DateUtil.fromatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
		}
		
        Object[] obj = new Object[]{jobName, jobStatus, startQueryDate, endQueryDate};
        list = this.qrtzLogService.queryQrtzLog(pager, obj);

        return "list";
	}
	
	
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getStartQueryDate() {
		return startQueryDate;
	}
	public void setStartQueryDate(String startQueryDate) {
		this.startQueryDate = startQueryDate;
	}
	public String getEndQueryDate() {
		return endQueryDate;
	}
	public void setEndQueryDate(String endQueryDate) {
		this.endQueryDate = endQueryDate;
	}
}
