package com.hgsoft.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.hgsoft.util.CacheUtil;

/**
 * 启动时加载缓存
 * 
 * @2015-11-16
 * @author wu
 *
 */
public class CacheInit implements ServletContextListener {

	private static Logger logger = Logger.getLogger(CacheInit.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		
		CacheUtil.init();
		logger.info("启动时加载缓存完成");
		System.out.println("===================================缓存加载完成===================================");
	}

}
