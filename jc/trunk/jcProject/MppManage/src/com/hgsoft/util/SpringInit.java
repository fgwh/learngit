package com.hgsoft.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: SpringInit.java
 * @Date: 14-6-4
 * @Time: 下午2:32
 */
public class SpringInit implements ServletContextListener {

    private static WebApplicationContext springContext;

    public SpringInit() {
        super();
    }

    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }


    public void contextDestroyed(ServletContextEvent event) {
    }

    public static ApplicationContext getApplicationContext() {
        return springContext;
    }
}
