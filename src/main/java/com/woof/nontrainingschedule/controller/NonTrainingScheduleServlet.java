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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.administrator.entity.Administrator;
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
			case "getByNtsDate":
				getByNtsDate(req, res);
				return;
			case "deleteByDate":
				deleteByDate(req, res);
				return;
			case "addForTrainer":
				addForTrainer(req ,res);
				return;
			case "getByNtsDateForCalendar":
				getByNtsDateForCalendar(req ,res);
				return;
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
		Integer year = Integer.valueOf(req.getParameter("year"));
        Integer month = Integer.valueOf(req.getParameter("month"));
		
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
//		if (req.getSession().getAttribute("NTSPageQty2") == null) {
		int NTSPageQty2 = nonTrainingScheduleService.getPageTotal2(trainerNo , year , month);
		req.getSession().setAttribute("NTSPageQty2", NTSPageQty2);
//		}
		List<NonTrainingSchedule> trainers = nonTrainingScheduleService.findNtsByTrainerNo(trainerNo, year , month , currentPage);

		req.setAttribute("trainers", trainers);
		req.setAttribute("currentPage", currentPage);

	}

	private void getByNtsDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		Integer year = Integer.valueOf(request.getParameter("year"));
        Integer month = Integer.valueOf(request.getParameter("month"));
//		Integer trainerNo = Integer.valueOf(request.getParameter("trainerNo"));

		Administrator administrator = (Administrator) request.getSession(false).getAttribute("administrator");
		Trainer trainer = new TrainerServiceImpl().getByAdmin(administrator.getAdminNo());

		List<Date> allByTrainer = nonTrainingScheduleService.getAllByTrainer(year, month, trainer.getTrainerNo());

		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd")
				.create();

		String json = gson.toJson(allByTrainer);

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);

	}

	private void getByNtsDateForCalendar(HttpServletRequest request, HttpServletResponse response) throws IOException {


		Integer year = Integer.valueOf(request.getParameter("year"));
		Integer month = Integer.valueOf(request.getParameter("month"));
		Integer trainerNo = Integer.valueOf(request.getParameter("trainerNo"));

		List<Date> allByTrainer = nonTrainingScheduleService.getAllByTrainer(year, month, trainerNo);

		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd")
				.create();

		String json = gson.toJson(allByTrainer);

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);

	}

	private void deleteByDate(HttpServletRequest request , HttpServletResponse response) throws IOException {

		Date date = Date.valueOf(request.getParameter("date"));

		Administrator administrator = (Administrator) request.getSession(false).getAttribute("administrator");
		Trainer trainer = new TrainerServiceImpl().getByAdmin(administrator.getAdminNo());

		nonTrainingScheduleService.deleteByDate(trainer.getTrainerNo() , date);

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write("{\"message\" : \"刪除成功\" }");
	}

	private void addForTrainer(HttpServletRequest request , HttpServletResponse response) throws IOException {
		Administrator administrator = (Administrator) request.getSession(false).getAttribute("administrator");
		Trainer trainer = new TrainerServiceImpl().getByAdmin(administrator.getAdminNo());

		Date date = Date.valueOf(request.getParameter("date"));

		nonTrainingScheduleService.addNts(trainer,date);

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write("{\"message\" : \"新增成功\"}");
	}
}
