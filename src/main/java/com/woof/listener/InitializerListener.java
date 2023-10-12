package com.woof.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.woof.util.HibernateUtil;



public class InitializerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context started");
		HibernateUtil.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context ended");
		HibernateUtil.shutdown();
	}

}
