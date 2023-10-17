package com.woof.privatetrainingappointmentform.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormService;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl;

@WebServlet("/privatetrainingappointmentform")
public class PrivateTrainingAppointmentFormServlet extends HttpServlet {

	private PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService;

	@Override
	public void init() throws ServletException {
		privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String privatetrainingappointmentform = req.getParameter("Type");

		resp.getWriter().println(privatetrainingappointmentform);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		System.out.println(privateTrainingAppointmentFormService.getAllPrivateTrainingAppointmentForms());
	}

}
