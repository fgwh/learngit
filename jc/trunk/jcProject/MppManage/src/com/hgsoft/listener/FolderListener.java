package com.hgsoft.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hgsoft.cxf.common.PropertiesUtil;

/**
 * 系统启动时创建系统文件夹
 * 
 * @version 1.0
 * @date 2015-8-21
 * @author wubiao
 *
 */
public class FolderListener implements ServletContextListener {

	/*文件上传根目录*/
	public static final String BASE_DIR = PropertiesUtil.getProperty("baseDir");
	/*及时通讯文件存放目录*/
	public static final String IM = BASE_DIR + "/" + PropertiesUtil.getProperty("IM_SAVE_PATH");
	/*货物检测文件存放目录*/
	public static final String HWJC = BASE_DIR + "/" + PropertiesUtil.getProperty("FILE_SAVE_DIR");
	/*app文件存放目录*/
	public static final String APP = BASE_DIR + "/" + PropertiesUtil.getProperty("uploadPath");
	/*log文件存放目录*/
	public static final String LOG = BASE_DIR + "/" + PropertiesUtil.getProperty("operLogPath");
	
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent e) {
		
		File baseDir = new File(BASE_DIR);
		
		if(baseDir.exists()) {
			if(!baseDir.isDirectory()){
				baseDir.delete();
				
				mkAllFolder();
			}else {
				mkFolder(IM);
				mkFolder(HWJC);
				mkFolder(APP);
				mkFolder(LOG);
			}
		}else {
			mkAllFolder();
		}
	}
	
	/*创建系统用到的所有目录*/
	private void mkAllFolder() {
		mkFolder(BASE_DIR);
		mkFolder(IM);
		mkFolder(HWJC);
		mkFolder(APP);
		mkFolder(LOG);
	}
	
	/**
	 * 根据路径创建文件夹
	 * 
	 * @param dir 文件夹路径
	 */
	private void mkFolder(String dir) {
		File folder = new File(dir);
		if(!folder.exists()) {
			folder.mkdir();
			System.out.println(folder.getAbsolutePath() + " 目录创建成功。");
		}else {
			System.out.println(folder.getAbsolutePath() + " 目录已存在。");
		}
	}

}
