package com.woof.classorder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.classorder.service.ClassOrderService;
import com.woof.classorder.service.ClassOrderServiceImpl;

@WebServlet("/classorder")
public class ClassOrderServlet extends HttpServlet {

private ClassOrderService classOrderService;
	
	@Override
	public void init() throws ServletException{
		classOrderService = new ClassOrderServiceImpl();
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String classorder = req.getParameter("Type");

        resp.getWriter().println(classorder);
    }
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        //PrintWriter writer = resp.getWriter();
        //System.out.println("123");
        System.out.println(classOrderService.getAllClassOrders());
        
    }
}
