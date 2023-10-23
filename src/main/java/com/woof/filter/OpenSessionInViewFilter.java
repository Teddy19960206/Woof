package com.woof.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;

import com.woof.util.HibernateUtil;


@WebFilter("/*")
public class OpenSessionInViewFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			factory.getCurrentSession().beginTransaction();
			System.out.println("Begin Transaction");
			chain.doFilter(request, response);
			factory.getCurrentSession().getTransaction().commit();
			System.out.println("Begin Commit");
		} catch (Exception e) {
			factory.getCurrentSession().getTransaction().rollback();
			System.out.println("rollback Transaction");
			e.printStackTrace();
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

}
