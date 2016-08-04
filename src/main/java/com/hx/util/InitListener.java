package com.hx.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener   {

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("我是监听器，我刚启动,我负责监听客户端人数");
		
		String basePath = sce.getServletContext().getInitParameter("basePath");
		sce.getServletContext().setAttribute("basePath", basePath);
		System.out.println("basePath:"+basePath);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("我是监听器，我被销毁");
	}

}
