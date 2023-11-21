package com.woof.trainer.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.administrator.entity.Administrator;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
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
			case "/getAll":
				getAll(request ,response);
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
//		if (request.getSession().getAttribute("TrainerPageQty") == null) {
			int TrainerPageQty = trainerService.getPageTotal();
			request.getSession().setAttribute("TrainerPageQty", TrainerPageQty);
//		}
		List<Trainer> allTrainers = trainerService
				.getAllTrainers2(currentPage);
		//================隨機取三筆評論的區塊======================
		// 對每個訓練師處理評論
//		for (Trainer trainer : allTrainers) {
//		    List<PrivateTrainingAppointmentForm> ptas = new ArrayList<>(trainer.getPrivateTrainingAppointmentForms());
//
//		    // 使用 Collections.shuffle() 來隨機排列評論
//		    Collections.shuffle(ptas);
//
//		    // 獲取非空的前幾筆評論，這裡是獲取前三筆
//		    List<PrivateTrainingAppointmentForm> nonEmptyComments = new ArrayList<>();
//		    for (PrivateTrainingAppointmentForm pta : ptas) {
//		        if (pta.getPtaComment() != null && !pta.getPtaComment().isEmpty()) {
//		            nonEmptyComments.add(pta);
//		        }
//		    }
//		    
//		    List<PrivateTrainingAppointmentForm> randomNonEmptyComments = nonEmptyComments.subList(0, Math.min(3, nonEmptyComments.size()));
//
//		    // 將處理後的非空評論設置回訓練師物件中
//		    trainer.setPrivateTrainingAppointmentForms(new HashSet<>(randomNonEmptyComments));
//		}
		//=======================================================================================
		
		//=====================按照評論時間排序評論================================
		// 對每個訓練師處理評論
		for (Trainer trainer : allTrainers) {
		    List<PrivateTrainingAppointmentForm> ptas = new ArrayList<>(trainer.getPrivateTrainingAppointmentForms());

		    // 篩選非空的評論
		    List<PrivateTrainingAppointmentForm> nonEmptyComments = ptas.stream()
		            .filter(pta -> pta.getPtaComment() != null && !pta.getPtaComment().isEmpty())
		            .collect(Collectors.toList());

		    // 自訂 Comparator 來排序評論
		    nonEmptyComments.sort((pta1, pta2) -> {
		        Timestamp commentUpTime1 = pta1.getCommentUpTime();
		        Timestamp commentUpTime2 = pta2.getCommentUpTime();

		        // 如果 commentUpTime 存在，以 commentUpTime 比較
		        if (commentUpTime1 != null && commentUpTime2 != null) {
		            return commentUpTime2.compareTo(commentUpTime1); // 降序排序
		        } else if (commentUpTime1 == null && commentUpTime2 != null) {
		            return 1; // pta1 沒有 commentUpTime，排後面
		        } else if (commentUpTime1 != null) {
		            return -1; // pta2 沒有 commentUpTime，排後面
		        } else {
		            // 若兩者皆為 null，則比較 commentTime
		            return pta2.getCommentTime().compareTo(pta1.getCommentTime()); // 降序排序
		        }
		    });

		    // 獲取前三筆評論
		    List<PrivateTrainingAppointmentForm> randomNonEmptyComments = nonEmptyComments.subList(0, Math.min(3, nonEmptyComments.size()));

		    // 將處理後的非空評論設置回訓練師物件中
		    trainer.setPrivateTrainingAppointmentForms(new HashSet<>(randomNonEmptyComments));
		}
		//==================================================================================
		request.setAttribute("trainers", allTrainers);
		request.setAttribute("currentPage", currentPage);
		try {
			request.getRequestDispatcher("/frontend/privatetrainer/privateTrainer.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


