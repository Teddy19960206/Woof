package com.woof.nontrainingschedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.nontrainingschedule.entity.NonTrainingSchedule;
import com.woof.nontrainingschedule.service.NonTrainingScheduleService;
import com.woof.nontrainingschedule.service.NonTrainingScheduleServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;

@WebServlet("/nontrainingschedule/*")
@MultipartConfig
public class NonTrainingScheduleServlet extends HttpServlet {

	private NonTrainingScheduleService nonTrainingScheduleService;

	@Override
	public void init() throws ServletException {
		nonTrainingScheduleService = new NonTrainingScheduleServiceImpl();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		String forwardPath = "";
		if (action != null) {
			switch (action) {
			case "gettoadd":
				beforeAdd(req, res);
				forwardPath = "/frontend/nontrainingschedule/nonTrainingSchedule_add.jsp";
				break;
			case "add":
				add(req, res);
				return;
			case "getall":
				getAll(req, res);
				forwardPath = "/frontend/nontrainingschedule/nonTrainingSchedule_getAll.jsp";
				break;
			case "gettoupdate":
				beforeUpdate(req, res);
				forwardPath = "/frontend/nontrainingschedule/nonTrainingSchedule_update.jsp";
				break;
			case "update":
				update(req, res);
				return;
			case "delete":
				deleteOne(req, res);
				return;
			case "getbytrainer":
				getByTrainerNo(req, res);
				forwardPath = "/frontend/nontrainingschedule/nonTrainingSchedule_getByTrainerNo.jsp";
				break;
			case "getbyntsdate":
				getByNtsDate(req, res);
				forwardPath = "/frontend/nontrainingschedule/nonTrainingSchedule_getByNtsDate.jsp";
				break;
			default:
				forwardPath = "/frontend/nontrainingschedule/nonTrainingSchedule.jsp";
			}
			req.getRequestDispatcher(forwardPath).forward(req, res);
			res.getWriter().println(action);
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	private void beforeAdd(HttpServletRequest req, HttpServletResponse res) {

		TrainerService trainerservice = new TrainerServiceImpl();
		List<Trainer> allTrainers = trainerservice.getAllTrainers();
		req.setAttribute("trainers", allTrainers);
	}

	private void add(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Integer trainerNo = Integer.valueOf(req.getParameter("trainer"));
		TrainerService trainerService = new TrainerServiceImpl();
		Trainer trainer = trainerService.findTrainerByTrainerNo(trainerNo);

		String selectedDateStr = req.getParameter("selectedDate");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
		res.sendRedirect(req.getContextPath() + "/frontend/nontrainingschedule/nonTrainingSchedule.jsp");
	}

	private void getAll(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		if (req.getSession().getAttribute("NTSPageQty") == null) {
			int NTSPageQty = nonTrainingScheduleService.getPageTotal();
			req.getSession().setAttribute("NTSPageQty", NTSPageQty);
		}
		List<NonTrainingSchedule> allNonTrainingSchedules = nonTrainingScheduleService.getAllNTSs(currentPage);

		req.setAttribute("nonTrainingSchedules", allNonTrainingSchedules);
		req.setAttribute("currentPage", currentPage);

	}

	private void beforeUpdate(HttpServletRequest req, HttpServletResponse res) {

		TrainerService trainerservice = new TrainerServiceImpl();
		List<Trainer> allTrainers = trainerservice.getAllTrainers();

		req.setAttribute("trainers", allTrainers);
	}

	private void update(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Integer ntsNo = Integer.valueOf(req.getParameter("ntsNo"));

		Integer trainerNo = Integer.valueOf(req.getParameter("trainer"));
		TrainerService trainerService = new TrainerServiceImpl();
		Trainer trainer = trainerService.findTrainerByTrainerNo(trainerNo);

		String selectedDateStr = req.getParameter("selectedDate");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		int result;
		try {
			java.util.Date parsedDate = dateFormat.parse(selectedDateStr); // 將字串轉換為 java.util.Date
			java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // 將 java.util.Date 轉換為 java.sql.Date
			System.out.println("轉換後的日期: " + sqlDate);
			result = nonTrainingScheduleService.updateNts(ntsNo, trainer, sqlDate);
		} catch (Exception e) {
			result = -1;
		}

		if (result == 1) {
			System.out.println("更新成功");
			req.setAttribute("successMessage", "更新成功");
		} else {
			System.out.println("更新失敗");
			req.setAttribute("errorMessage", "更新失敗");
		}
		res.sendRedirect(req.getContextPath() + "/frontend/nontrainingschedule/nonTrainingSchedule.jsp");
	}

	private void deleteOne(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Integer ntsNo = Integer.valueOf(req.getParameter("ntsNo"));

		int result = nonTrainingScheduleService.deleteNts(ntsNo);

		if (result == 1) {
			System.out.println("刪除成功");
			req.setAttribute("successMessage", "刪除成功");
		} else {
			System.out.println("刪除失敗");
			req.setAttribute("errorMessage", "刪除失敗");
		}
		res.sendRedirect(req.getContextPath() + "/frontend/nontrainingschedule/nonTrainingSchedule.jsp");
	}

	private void getByTrainerNo(HttpServletRequest req, HttpServletResponse res) {

		Integer trainerNo = Integer.valueOf(req.getParameter("trainerNo"));
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
//		if (req.getSession().getAttribute("NTSPageQty2") == null) {
		int NTSPageQty2 = nonTrainingScheduleService.getPageTotal2(trainerNo);
		req.getSession().setAttribute("NTSPageQty2", NTSPageQty2);
//		}
		List<NonTrainingSchedule> trainers = nonTrainingScheduleService.findNtsByTrainerNo(trainerNo, currentPage);

		req.setAttribute("trainers", trainers);
		req.setAttribute("currentPage", currentPage);

	}

	private void getByNtsDate(HttpServletRequest req, HttpServletResponse res){

		String selectedDateStr = req.getParameter("selectedDate");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(selectedDateStr);// 將字串轉換為 java.util.Date
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // 將 java.util.Date 轉換為 java.sql.Date
		
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int NTSPageQty3 = nonTrainingScheduleService.getPageTotal3(sqlDate);
		req.getSession().setAttribute("NTSPageQty3", NTSPageQty3);
		List<NonTrainingSchedule> ntsDates = nonTrainingScheduleService.findNtsByNtsDate(sqlDate, currentPage);

		req.setAttribute("ntsDates", ntsDates);
		req.setAttribute("currentPage", currentPage);

	}
}
