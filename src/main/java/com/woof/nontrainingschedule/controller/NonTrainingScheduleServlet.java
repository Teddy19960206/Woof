package com.woof.nontrainingschedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.nontrainingschedule.service.NonTrainingScheduleService;
import com.woof.nontrainingschedule.service.NonTrainingScheduleServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;

@WebServlet("/nontrainingschedule/*")
public class NonTrainingScheduleServlet extends HttpServlet {

	private NonTrainingScheduleService nonTrainingScheduleService;

	@Override
	public void init() throws ServletException {
		nonTrainingScheduleService = new NonTrainingScheduleServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		String forwardPath = "";
		if (action != null) {
			switch (action) {
			case "gettoadd":
				beforeadd(req, resp);
				forwardPath = "/frontend/nontrainingschedule/nonTrainingSchedule_add.jsp";
				break;
			case "add":
				add(req, resp);
				return;
			default:
				forwardPath = "/frontend/nontrainingschedule/nonTrainingSchedule.jsp";
			}
			req.getRequestDispatcher(forwardPath).forward(req, resp);
			resp.getWriter().println(action);
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	private void beforeadd(HttpServletRequest req, HttpServletResponse resp) {
		
		TrainerService trainerservice = new TrainerServiceImpl();
		List<Trainer> allTrainers = trainerservice.getAllTrainers();
		req.setAttribute("trainers", allTrainers);
	}
	
	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Integer trainerNo = Integer.valueOf(req.getParameter("trainer"));
		TrainerService trainerService = new TrainerServiceImpl();
		Trainer trainer = trainerService.findTrainerByTrainerNo(trainerNo);
		
		String selectedDateStr = req.getParameter("selectedDate");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("========================");
		
		int result;
		try {
		    java.util.Date parsedDate = dateFormat.parse(selectedDateStr); // 將字串轉換為 java.util.Date
		    java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // 將 java.util.Date 轉換為 java.sql.Date
		    System.out.println("轉換後的日期: " + sqlDate);
		    result = nonTrainingScheduleService.addNts(trainer, sqlDate);
		} catch (Exception e) {
		    result = -1;
		}
		
		if (result == 1) {
			System.out.println("新增成功");
			req.setAttribute("successMessage", "新增成功");
		} else {
			System.out.println("新增失敗");
			req.setAttribute("errorMessage", "新增失敗");
		}
		resp.sendRedirect(req.getContextPath()
				+ "/frontend/nontrainingschedule/nonTrainingSchedule.jsp");
	}
}
