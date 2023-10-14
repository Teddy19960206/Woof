package com.woof.appointmentdetail.controller;

import com.woof.appointmentdetail.service.AppointmentDetailService;
import com.woof.appointmentdetail.service.AppointmentDetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/appointmentdetail")
public class AppointmentDetailServlet extends HttpServlet {
	
	private AppointmentDetailService appointmentDetailService;
	
	@Override
	public void init() throws ServletException{
		appointmentDetailService = new AppointmentDetailServiceImpl();
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String appointmentdetail = req.getParameter("Type");

        resp.getWriter().println(appointmentdetail);
    }
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        //PrintWriter writer = resp.getWriter();
        //System.out.println("123");
        System.out.println(appointmentDetailService.getAllAppointmentDetails());
        
    }
    
}
