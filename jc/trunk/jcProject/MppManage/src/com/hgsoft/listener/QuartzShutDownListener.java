package com.hgsoft.listener;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.jdbcjobstore.Constants;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.hgsoft.main.service.JobTaskService;


/**
 * @author huang_cx
 * @date 2013-06-25
 * @version 1.0
 * @Description 在服务器启动时，开启检查线程，并在服务器关闭时，停止检查线程。
 *              1、检查是否有暂停任务。
 *              2、在指定频率下检查异常任务。
 */
@SuppressWarnings("unchecked")
public class QuartzShutDownListener implements ServletContextListener {
	Log log = LogFactory.getLog(QuartzShutDownListener.class);
    private final static String context = "QuartzShutDownListener";
    private final static String USERDIR = "user.dir";
    
    /** Spring上下文对象 */
	private static WebApplicationContext applicationContext;
	private static DataSource dataSource;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static DataSource getDataSource() {
		return dataSource;
	}

	public static WebApplicationContext getApplicationContext() {
		return applicationContext;
	}


	private static JobTaskService jobTaskService;
	private static ContextLoader contextLoader;  

	// private boolean waitForJobsToCompleteOnShutdown = false;
	
	private static boolean startSchedule = true;
	
	@SuppressWarnings("rawtypes")
	private static ScheduledFuture scheduledFuture;
	private static ScheduledExecutorService executorService;
	private static final long initialDelay = 60l;//任务延迟时间，单位秒
	private static final long period = 60l;//任务间隔时间，单位秒
	
	private static final String TAB_TB_QUARTZ_LOCK  = "Tb_Quartz_Lock";
	private static final String TAB_QRTZ_TRIGGERS  = "QRTZ_TRIGGERS";
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if(null != applicationContext)
		{
			if(null == jobTaskService){
				jobTaskService = (JobTaskService) applicationContext.getBean("jobTaskService");
			}
			
			if(null != jobTaskService)
			{
				try {
					//获取所有的触发器组
					List<String> groupNames = jobTaskService.getTriggerGroupNames();
					if(null != groupNames && groupNames.size() > 0)
					{
						int len = groupNames.size();
						int t_len = 0;
						List<String> triggerNames = null;
						String sql = "";
						for(int i = 0; i < len;i++)
						{
							
							triggerNames = jobTaskService.getTriggerNames(groupNames.get(i));
							if(null != triggerNames && triggerNames.size() > 0)
							{
								t_len = triggerNames.size();
								for(int j = 0; j < t_len;j++)
								{
									
									TriggerState state = jobTaskService.getTriggerState(triggerNames.get(j),  groupNames.get(i));
									//System.out.println("triggername: "+triggerNames[j]+",groupname: "+groupNames[i]+",state: "+state);
									//如果当前触发器是运行状态，即将其更改为暂停
									if(state == TriggerState.NORMAL)
									{
										//System.out.println("==============Trigger Paused================");
										jobTaskService.pauseTrigger(triggerNames.get(j), groupNames.get(i));
										sql = "insert into "+TAB_TB_QUARTZ_LOCK+"(TRIGGER_NAME,TRIGGER_GROUP) values('"+triggerNames.get(j)+"','"+groupNames.get(i)+"')";
										jobTaskService.updOrDelWithSql(sql);
									}
								}
							}
						}
					}
				} catch (Exception e1) {
					log.error(context+"[ERROR >> "+e1.getMessage()+"]");
				}
			}
			
			Object object = applicationContext.getBean("schedulerFactory");
			if(object instanceof  StdScheduler)
			{
				StdScheduler stdScheduler = (StdScheduler)object;
				if(!stdScheduler.isShutdown())
				{
					log.info(context+"==============StdScheduler Shutdown================");
					stdScheduler.shutdown(false);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					//e.printStackTrace();
					log.error(context+"[ERROR >> "+e.getMessage()+"]");
				}
			}
		}
		
		if(startSchedule)
		{
			if(executorService != null)
			{
				if(scheduledFuture != null)
				{
					scheduledFuture.cancel(false);
					
				}
				/**
				 * 关闭任务池
				 */
				if(!executorService.isShutdown())
				{
					log.info(context+"==============ExecutorService ShutDown================");
					executorService.shutdown();
				}
				
				try {
					Thread.sleep(1000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//提示jvm进行垃圾回收
				scheduledFuture = null;
				executorService = null;
			}
		}

        sessionFactory =null;
		dataSource = null;
		jobTaskService = null;
		applicationContext = null;
		log.info(context+"==============ServletContext destory================");
	}

	/**
	 * 获取Tomcat中指定的环境变量
	 * @param event
	 */
	protected final void init(ServletContextEvent event)
	{
		Context initCtx = null;  
		Context envCtx = null;
        try  
        {  
            initCtx = new InitialContext();  
            final String comp = "java:comp/env";
            envCtx =  (Context) initCtx.lookup(comp);  
  
            String config = (String) envCtx.lookup(USERDIR);  
            System.setProperty(USERDIR, config);  
  
        }  
        catch (NamingException e)  
        {  
            // e.printStackTrace();   
            System.setProperty(USERDIR, "");  
        }  
        catch (Exception ex)  
        {  
            // ex.printStackTrace();   
            System.setProperty(USERDIR, "");  
        }  
        finally
        {
        	 if(envCtx != null)
				try {
					envCtx.close();
				} catch (NamingException e) {
					//e.printStackTrace();
				}  
        	 if(initCtx != null)
				try {
					initCtx.close();
				} catch (NamingException e) {
					//e.printStackTrace();
				}  
        }
        
        createContextLoader(); 
        
        contextLoader.initWebApplicationContext(event.getServletContext());  
	}
	
	/**
	 * 初始化ContextLoader对象
	 */
    protected void createContextLoader()  
    {  
    	if(contextLoader == null)
    	{
    		contextLoader =  new ContextLoader();  
    	}
    }  

	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//读取Tomcat环境变量
		//init(event);
		
		//获得ApplicationContext对象
		applicationContext = ContextLoader.getCurrentWebApplicationContext();

		if (applicationContext != null) {
			dataSource = (DataSource)applicationContext.getBean("dataSource");
			jobTaskService = (JobTaskService) applicationContext.getBean("jobTaskService");
            sessionFactory =  (SessionFactory) applicationContext.getBean("sessionFactory");

			if(jobTaskService == null)
				return;
			
			final String sql = "select TRIGGER_NAME,TRIGGER_GROUP from "+TAB_TB_QUARTZ_LOCK;
			final List<Object[]> triggers = (List<Object[]>) jobTaskService.findWithSql(sql);

			if (null != triggers && !triggers.isEmpty()) {
				/**
				 * 检查是否有暂停的任务
				 */
				new Thread(new Runnable() {
					@Override
					public void run() {
						log.info(context+"==============Run New Thread================");
						String triggerName = "";
						String groupName = "";
						String delSql = "";

						try {
							for (Object[] trigger : triggers) {
								triggerName = trigger[0] + "";
								groupName = trigger[1] + "";

								// 恢复触发器
								jobTaskService.resumeTrigger(triggerName,
										groupName);
								// 删除记录表
								delSql = "delete from tb_Quartz_Lock where TRIGGER_NAME='"
										+ triggerName
										+ "' and TRIGGER_GROUP='"
										+ groupName + "'";
								jobTaskService.updOrDelWithSql(delSql);

							}
						} catch (Exception e) {
							log.error(context+"[ERROR >> "+e.getMessage()+"]");
						}
					}
					
					
				}).start();

			}
			
			
			
			//获取单一任务线程池
			//检查错误任务
			if(startSchedule)
			{
//				static protected final Executor DEFAULT_CONNECTION_EXECUTOR = new ScheduledThreadPoolExecutor(5, new ThreadFactory() {
//		            public Thread newThread(Runnable run) {
//		                Thread thread = new Thread(run);
//		                thread.setPriority(ThreadPriorities.INBOUND_CLIENT_CONNECTION);
//		                return thread;
//		            }
//		        });
				executorService = Executors.newSingleThreadScheduledExecutor();
				
				scheduledFuture = executorService.scheduleAtFixedRate(new Runnable(){
					@Override
					public void run() {
						
						final String sql = "select TRIGGER_NAME,TRIGGER_GROUP from "+TAB_QRTZ_TRIGGERS+" where "+Constants.COL_TRIGGER_STATE+"='"+ Constants.STATE_ERROR+"'";
						final List<Object[]> triggers = (List<Object[]>) jobTaskService.findWithSql(sql);
						String triggerName = "";
						String groupName = "";
						try {
							for (Object[] trigger : triggers) {
								triggerName = trigger[0] + "";
								groupName = trigger[1] + "";
		
								// 恢复触发器
								jobTaskService.resumeAllJob(triggerName,groupName);
								log.info(context+"==============Executed Task================");
							
							}
						} catch (Exception e) {
							log.error(context+"[ERROR >> "+e.getMessage()+"]");
						}
					}
					
				}, initialDelay, period, TimeUnit.SECONDS);
	
			}
		}
		
		//销毁对象
		contextLoader = null;
		
		log.info(context+"==============ServletContext Init Finished================");
	}

}
