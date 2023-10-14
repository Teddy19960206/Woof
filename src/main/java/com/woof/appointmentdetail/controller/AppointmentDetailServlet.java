package com.woof.appointmentdetail.controller;

import com.woof.classtype.service.ClassTypeService;
import com.woof.classtype.service.ClassTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/classType")
public class AppointmentDetailServlet extends HttpServlet {
	
	private ClassTypeService classTypeService;
	
	@Override
	public void init() throws ServletException{
		classTypeService = new ClassTypeServiceImpl();
	}
	

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        //PrintWriter writer = resp.getWriter();
        //System.out.println("123");
        System.out.println(classTypeService.getAllClassTypes());
        
    }
    
}
