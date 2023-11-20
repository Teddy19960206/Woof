package com.woof.trainer.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.administrator.entity.Administrator;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.skill.entity.Skill;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;

@WebServlet("/trainer/*")
@MultipartConfig
public class TrainerServlet  extends HttpServlet {

	private TrainerService trainerService;

	@Override
	public void init() throws ServletException {
		trainerService = new TrainerServiceImpl();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request ,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String pathInfo = request.getPathInfo();

		switch (pathInfo){
			case "/getTrainers":
				getAllTrainers(request ,response);
				return;
			case "/getClassDate":
				getTrainerClass(request, response);
				return;
			case "/getTrainerSkills":
				getTrainerSkills(request, response);
				return;
			case "/getTrainerProfile":
				getTrainerProfile(request ,response);
				return;
			default:

		}
	}

	private void getAllTrainers(HttpServletRequest request , HttpServletResponse response) throws IOException {

		List<Trainer> allTrainers = trainerService.getAllTrainers();

		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd")
				.create();

		String json = gson.toJson(allTrainers);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);

	}

	private void getTrainerClass(HttpServletRequest request , HttpServletResponse response) throws IOException {
		String trainerId = request.getParameter("trainerId");
		String json = null;

		if (trainerId != null && trainerId.length() != 0) {
			Integer Id = Integer.valueOf(trainerId);
			Set<GroupScheduleDetail> groupDetail = trainerService.getGroupDetail(Id);
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()
					.setDateFormat("yyyy-MM-dd")
					.create();
			json = gson.toJson(groupDetail);
		}

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
	}

	private void getTrainerSkills(HttpServletRequest request , HttpServletResponse response) throws IOException {

		Administrator administrator = (Administrator) request.getSession().getAttribute("administrator");
		Trainer trainer = trainerService.getByAdmin(administrator.getAdminNo());


		Set<Skill> trainerSkills = trainerService.getTrainerSkills(trainer.getTrainerNo());

		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd")
				.create();
		String json = gson.toJson(trainerSkills);


		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
	}

	private void getTrainerProfile(HttpServletRequest request , HttpServletResponse response) throws IOException {
		String trainerNo = request.getParameter("trainerNo");
		String json = null;

		if (trainerNo != null && trainerNo.length() != 0){
			Integer id = Integer.valueOf(trainerNo);
			Trainer trainer = trainerService.findTrainerByTrainerNo(id);

			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()
					.setDateFormat("yyyy-MM-dd")
					.create();
			json = gson.toJson(trainer);
		}

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	private void getAll(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		if (request.getSession().getAttribute("PTAPageQty") == null) {
			int PTAPageQty = trainerService.getPageTotal();
			request.getSession().setAttribute("PTAPageQty", PTAPageQty);
		}
		List<Trainer> allTrainers = trainerService
				.getAllTrainers2(currentPage);

//		List<Trainer> allTrainers =  trainerService.getAllTrainers();

//		Integer member = trainerService.findTrainerByPtaNo(ptaNo).getMember().getMemNo();
//		Integer trainer = trainerService.findTrainerByPtaNo(ptaNo).getTrainer().getTrainerNo();

		request.setAttribute("trainers", allTrainers);
		request.setAttribute("currentPage", currentPage);

	}
}


