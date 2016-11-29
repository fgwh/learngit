package com.hgsoft.job;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.OperLog;
import com.hgsoft.security.operLog.service.OperLogService;
import com.hgsoft.util.DateUtil;

public class MobileOperLogJob extends JobLog implements Job{

	private  OperLogService operLogService=(OperLogService)ContextLoader.getCurrentWebApplicationContext().getBean("operLogService");;
	private final static String FIELPATH = PropertiesUtil.getProperty("baseDir") + "/" + PropertiesUtil.getProperty("operLogPath")+"/";
	private static Logger logger = Logger.getLogger(MobileOperLogJob.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {
		QrtzLog qrtzLog = getQrtzLogByClassName(this.getClass().getName());
		addOperLog(qrtzLog);
		saveQrtzLog(qrtzLog);//记日志
	}
	
	/*
	 * 插入移动端日志
	 */
	private void addOperLog(QrtzLog qrtzLog) {
		qrtzLog.setCreatetime(new Date());
		try{
			List<OperLog> list = parseStringToEntity();
			for(OperLog operLog:list){
				operLogService.save(operLog);
			}
			if(list==null){
				logger.info("0移动端操作日志保存到本地数据库成功。");
				qrtzLog.setDescription("0移动端操作日志文件保存到本地数据库成功。");
			} else{
				logger.info(list.size() + "个移动端操作日志保存到本地数据库成功。");
				qrtzLog.setDescription(list.size() + "个移动端操作日志保存到本地数据库成功。");
			}
			
			qrtzLog.setJobStatus(JobLog.JOB_STATUS_SUCC);
		} catch(Exception e){
			logger.info("移动端操作日志保存到本地数据库失败。");
			qrtzLog.setDescription("移动端操作日志保存到本地数据库失败。");
			qrtzLog.setJobStatus(JobLog.JOB_STATUS_FAIL);
			e.printStackTrace();
		}
	}

	/*
	 * 拼接对象
	 */
	private List<OperLog> parseStringToEntity() throws IOException, ParseException{
		String operateType = "5";
		Admin admin = new Admin();
		List<OperLog> list = new ArrayList<OperLog>();
		File file = new File(FIELPATH);
		if(file.isDirectory()){
			File [] files = file.listFiles();
			for(File f:files){
				String str = FileUtils.readFileToString(f);
				String [] arr = str.split("\\n");
				for(String st:arr){
					String [] ar = st.split("\\&&&&");
					OperLog operLog = new OperLog();
					//new String(ar[0], UTF-8);
//System.out.println("转码：：：：："+new String(ar[0].getBytes("GBK"`),"UTF-8"));
					operLog.setDetails(ar[0]);
					admin.setId(ar[1]);
					operLog.setAdmin(admin);
					operLog.setOperTime(format.parse((ar[2])));
					operLog.setIp(ar[3]);
					operLog.setOperType(operateType);
					operLog.setRemark("移动端操作日志");
					//operLog.setRemark(new String(ar[4].getBytes("GBK"),"UTF-8"));
					list.add(operLog);
				}
				f.delete();
			}
		}
		return list;
	}
}
