package com.woof.administratorpermission.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.woof.administratorpermission.service.AdministratorPermissionServiceImpl;
import com.woof.administratorpermission.service.AdministratorPermissionService;
import com.woof.administrator.entity.Administrator;
import com.woof.administrator.service.AdministratorService;
import com.woof.administrator.service.AdministratorServiceImpl;
import com.woof.functionpermission.service.FunctionPermissionServiceImpl;
import com.woof.functionpermission.service.FunctionPermissionService;
import com.woof.functionpermission.entity.FunctionPermission;

@WebServlet("/AdministratorPermission/*")
public class AdministratorPermissionServlet extends HttpServlet {
	
	
	    private AdministratorPermissionService administratorPermissionService;

	    @Override
	    public void init() throws ServletException {
	        administratorPermissionService = new AdministratorPermissionServiceImpl();
	    }
	    @Override
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request,response);
	    }

	    @Override
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	         request.setCharacterEncoding("UTF-8");
	         String pathInfo = request.getPathInfo();

	         switch (pathInfo){
	             case "/addAdministratorPermission":
	                 addAdministratorPermission(request , response);
	                 return;
	             default:
	         }

	    }

	    private void addAdministratorPermission(HttpServletRequest request , HttpServletResponse response){

//	        若有member session 則可刪除
	    	String adminNo = request.getParameter("adminNo");
	        AdministratorService administratorService = new AdministratorServiceImpl();
	        
	        Administrator administrator1 = administratorService.findAdministratorByAdminNo(adminNo);

	        HttpSession session = request.getSession();
	        session.setAttribute("administrator" , administrator1);

	        Administrator administrator = (Administrator) session.getAttribute("administrator");

	        Integer funcNo = Integer.valueOf(request.getParameter("functionPermission"));
	        FunctionPermissionService functionPermissionService = new FunctionPermissionServiceImpl();
	        FunctionPermission functionPermission = functionPermissionService.findFunctionPermissionByFuncNo(funcNo);

	        administratorPermissionService.AddAdministratorPermission(administrator.getAdminNo() , functionPermission.getFuncNo());

	    }
	}


