package com.woof.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.woof.util.HibernateUtil;
import com.woof.util.JedisUtil;


//@WebListener
public class InitializerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context started");
		HibernateUtil.getSessionFactory();
		System.out.println("產生 Redis Pool");
		JedisUtil.getJedisPool();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("關閉 Redis Pool");
		JedisUtil.shutdownJedisPool();
		System.out.println("context ended");
		HibernateUtil.shutdown();
	}

}
