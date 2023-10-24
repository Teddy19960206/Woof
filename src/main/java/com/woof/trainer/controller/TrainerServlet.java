package com.woof.trainer.controller;


	
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;
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
				case "/getTrianers":
					getAllTrainerClass(request ,response);
					return;
				case "/getClassDate":
					getTrainerClass(request, response);
					return;
				default:

			}


		}

		private void getAllTrainerClass(HttpServletRequest request , HttpServletResponse response) throws IOException {

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
			String json = null	;

			if (trainerId != null && trainerId.length() != 0) {
				Integer Id = Integer.valueOf(trainerId);
				Set<GroupScheduleDetail> groupDetail = trainerService.getGroupDetail(Id);
				System.out.println(groupDetail);
				Gson gson = new GsonBuilder()
						.excludeFieldsWithoutExposeAnnotation()
						.setDateFormat("yyyy-MM-dd")
						.create();
				json = gson.toJson(groupDetail);
			}

			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(json);
		}
	}


