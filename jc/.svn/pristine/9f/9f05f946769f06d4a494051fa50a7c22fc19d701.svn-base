package com.hgsoft.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DirUtils {
	private final Log logger = LogFactory.getLog(DirUtils.class);
	/**
	 * 临时上传文件夹目录 如 D:\
	 */
	private String path;
	/**
	 * 临时上传文件夹的基础命名 cmb_
	 */
	private String dirBaseName;
	/**
	 * 下载文件夹目录
	 */
	private String dpath;
	/**
	 * 完整的文件夹路径
	 */
	private String wholePath;
	
	
	
	public String getDpath() {
		return dpath;
	}

	public void setDpath(String dpath) {
		this.dpath = dpath;
	}

	public String getPath() {
		return path;
	}

	public String getDirBaseName() {
		return dirBaseName;
	}

	public String getWholePath() {
		return wholePath;
	}

	public void setWholePath(String wholePath) {
		this.wholePath = wholePath;
	}

	public void setDirBaseName(String dirBaseName) {
		this.dirBaseName = dirBaseName;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 按天生成目录
	 */
	public void createDir() {
		Calendar calendar = commonCal();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		String monthStr = month+"";
		String dayStr = day+"";
		
		if(month < 10)
		{
			monthStr = "0"+month;
		}
		
		if(day < 10)
		{
			dayStr = "0"+day;
		}
		
		
		String wholePath = path+dirBaseName+year+monthStr+dayStr;
		
		File dir = new File(wholePath);
		File downloadDir = new File(dpath);
		
		//如果目录不存在
		if(!dir.exists())
		{
			dir.mkdirs();
			logger.info("创建临时上传目录: " + wholePath);
		}
		else
		{
			logger.info("临时上传目录: " + wholePath+"已存在");
		}
		this.wholePath  = wholePath;
		
		if(!downloadDir.exists())
		{
			downloadDir.mkdirs();
			logger.info("创建临时下载目录: " + this.dpath);
		}
		else
		{
			logger.info("临时下载目录: " + this.dpath+"已存在");
		}
		
		
	}
	
	/**
	 * 获取T-1天的Calendar
	 * @return
	 */
	public static Calendar commonCal()
	{
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		
		return calendar;
	}
}
