package com.woof.nontrainingschedule.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.nontrainingschedule.service.NonTrainingScheduleService;
import com.woof.nontrainingschedule.service.NonTrainingScheduleServiceImpl;

@WebServlet("/nontrainingschedule")
public class NonTrainingScheduleServlet extends HttpServlet{

private NonTrainingScheduleService nonTrainingScheduleService;
	
	@Override
	public void init() throws ServletException{
		nonTrainingScheduleService = new NonTrainingScheduleServiceImpl();
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nontrainingschedule = req.getParameter("Type");

        resp.getWriter().println(nontrainingschedule);
    }
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        //PrintWriter writer = resp.getWriter();
        //System.out.println("123");
        System.out.println(nonTrainingScheduleService.getAllNonTrainingSchedules());
        
    }
}