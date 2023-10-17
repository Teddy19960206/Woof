package com.woof.functionpermission.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.functionpermission.entity.FunctionPermission;
import com.woof.functionpermission.service.FunctionPermissionService;
import com.woof.functionpermission.service.FunctionPermissionServiceImpl;

public class FunctionPermissionServlet extends HttpServlet {
	private FunctionPermissionService functionPermissionService;

	@Override
	public void init() throws ServletException {
		functionPermissionService = new FunctionPermissionServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String functionPermissionType = req.getParameter("functionpermission");

		res.getWriter().println(functionPermissionType);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
		PrintWriter writer =res.getWriter();
		System.out.println("123");
		System.out.println(functionPermissionService.getAllFunctionPermissions());
//		res.setCharacterEncoding("UTF-8");
//		res.setContentType("text/html; charset=UTF-8");
//		PrintWriter writer = res.getWriter();
//
//		// Example: convert the permissions to a display format and send to client
//		writer.println("<html><body>");
//		for (FunctionPermission perm : functionPermissionService.getAllFunctionPermissions()) {
//			writer.println("<p>" + perm.toString() + "</p>");
//		}
//		writer.println("</body></html>");
	}
}