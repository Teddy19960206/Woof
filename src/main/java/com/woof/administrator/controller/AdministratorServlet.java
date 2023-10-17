package com.woof.administrator.controller;

import com.woof.administrator.service.AdministratorService;
import com.woof.administrator.service.AdministratorServiceImpl;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import java.io.IOException;
	import java.io.PrintWriter;
	@WebServlet("/administrator")
	public class AdministratorServlet extends HttpServlet {
	
		
		private AdministratorService administratorService;
		
		@Override
		public void init() throws ServletException{
			administratorService = new AdministratorServiceImpl();
		}
		
		@Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        req.setCharacterEncoding("UTF-8");
	        String conmain = req.getParameter("conmain");
	        System.out.println(administratorService.getAllAdministrator());
	    }
		
	    @Override
	    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	doPost(req, resp);

	        
	        
	    }
	    
	}


