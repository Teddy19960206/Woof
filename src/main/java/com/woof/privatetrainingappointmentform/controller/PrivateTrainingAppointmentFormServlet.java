package com.woof.privatetrainingappointmentform.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormService;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;
import com.woof.util.JsonIgnoreExclusionStrategy;

@WebServlet("/privatetrainingappointmentform/*")
@MultipartConfig
public class PrivateTrainingAppointmentFormServlet extends HttpServlet {

	private PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService;

	@Override
	public void init() throws ServletException {
		privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		String forwardPath = "";
		if (action != null) {
			switch (action) {
			case "gettoadd":
				getSelectInfo(req, resp);
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm_add.jsp";
				break;
			case "add":
				add(req, resp);
				return;
			case "gettoupdate":
				beforeUpdate(req, resp);
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm_update.jsp";
				break;
			case "gettoupdate2":
				beforeUpdate(req, resp);
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm_update2.jsp";
				break;
			case "update":
				update(req, resp);
				return;
			case "gettoupdelete":
				beforedelete(req, resp);
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm_delete.jsp";
				break;
			case "delete":
				delete(req, resp);
				return;
			case "getall":
				getAll(req, resp);
				forwardPath = "/backend/appointment/appointment.jsp";
				break;
			case "getbymemname":
				getByMemName(req, resp);
				forwardPath = "/backend/appointment/appointmentByMemName.jsp";
				break;
			case "getone":
				getOne(req, resp);
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm_getOne.jsp";
				break;
			case "gettoselect":
				beforeSelect(req, resp);
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm_select.jsp";
				break;
			case "getbymemno":
				getByMemNo(req, resp);
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm_getByMemNo.jsp";
				break;
			case "getbytrainerno":
				getByTrainerNo(req, resp);
				forwardPath = "/backend/appointment/appointmentByTrainerNo.jsp";
				break;
			case "commentbymember":
				getByMemNo(req, resp);
				forwardPath = "/frontend/privatetrainingappointmentform/commentByMember.jsp";
				break;
			case "comment":
				forwardPath = "/frontend/privatetrainingappointmentform/commenting.jsp";
				break;

			case "getAllByTrainer":
				getAllByTrainer(req , resp);
				return;


			case "getbyboth":
				getByBoth(req,resp);
				forwardPath = "/backend/appointment/appointmentByBoth.jsp";
				break;
			case "getboth":
				getByMemberOrTrainer(req,resp);	
				return;

			case "updatecomment":
				try {
					updateComment(req, resp);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return;
			default:
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp";
			}
		} else {
			forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp";
		}
		req.getRequestDispatcher(forwardPath).forward(req, resp);
		resp.getWriter().println(action);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String memNo = request.getParameter("member");
		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.findMemberByNo(memNo);

		Integer trainerNo = Integer.valueOf(request.getParameter("trainer"));
		TrainerService trainerService = new TrainerServiceImpl();
		Trainer trainer = trainerService.findTrainerByTrainerNo(trainerNo);

		String ptaClassStr = request.getParameter("number");

		int result;

		try {
			Integer ptaClass = Integer.parseInt(ptaClassStr);
			result = privateTrainingAppointmentFormService.addPrivateTrainingAppointmentForm(member, trainer, ptaClass);
		} catch (NumberFormatException e) {
			result = -1;
		}

		if (result == 1) {
			System.out.println("新增成功");
			request.setAttribute("successMessage", "新增成功");
		} else {
			System.out.println("新增失敗");
			request.setAttribute("errorMessage", "新增失敗");
		}
//		request.getRequestDispatcher("/frontend/privatetrainingappointmentform/privatetrainingappointmentform.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()
				+ "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp");
	}

	private void getSelectInfo(HttpServletRequest req, HttpServletResponse resp) {

		MemberService memberservice = new MemberServiceImpl();
		List<Member> allMembers = memberservice.getAllMembers();

		TrainerService trainerservice = new TrainerServiceImpl();
		List<Trainer> allTrainers = trainerservice.getAllTrainers();

		req.setAttribute("members", allMembers);
		req.setAttribute("trainers", allTrainers);

	}

	private void beforeUpdate(HttpServletRequest req, HttpServletResponse resp) {

		PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
		List<PrivateTrainingAppointmentForm> allPrivateTrainingAppointmentForms = privateTrainingAppointmentFormService
				.getAllPrivateTrainingAppointmentForms();
		MemberService memberservice = new MemberServiceImpl();
		List<Member> allMembers = memberservice.getAllMembers();

		TrainerService trainerservice = new TrainerServiceImpl();
		List<Trainer> allTrainers = trainerservice.getAllTrainers();

		req.setAttribute("privateTrainingAppointmentForms", allPrivateTrainingAppointmentForms);
		req.setAttribute("members", allMembers);
		req.setAttribute("trainers", allTrainers);

	}

	private void beforedelete(HttpServletRequest req, HttpServletResponse resp) {

		List<PrivateTrainingAppointmentForm> allPrivateTrainingAppointmentForms = privateTrainingAppointmentFormService
				.getAllPrivateTrainingAppointmentForms();

		req.setAttribute("privateTrainingAppointmentForms", allPrivateTrainingAppointmentForms);

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Integer ptaNo = Integer.valueOf(request.getParameter("ptaNo"));

		String memNo = request.getParameter("member");
		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.findMemberByNo(memNo);

		Integer trainerNo = Integer.valueOf(request.getParameter("trainer"));
		TrainerService trainerService = new TrainerServiceImpl();
		Trainer trainer = trainerService.findTrainerByTrainerNo(trainerNo);

		String ptaClassStr = request.getParameter("number");

		int result;

		try {
			Integer ptaClass = Integer.parseInt(ptaClassStr);
			result = privateTrainingAppointmentFormService.updatePrivateTrainingAppointmentForm(ptaNo, member, trainer,
					ptaClass);
		} catch (NumberFormatException e) {
			result = -1;
		}

		if (result == 1) {
			System.out.println("修改成功");
			request.setAttribute("successMessage", "修改成功");
		} else {
			System.out.println("修改失敗");
			request.setAttribute("errorMessage", "修改失敗");
		}
//		request.getRequestDispatcher("/frontend/privatetrainingappointmentform/privatetrainingappointmentform.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()
				+ "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Integer ptaNo = Integer.valueOf(request.getParameter("ptaNo"));
		int result = privateTrainingAppointmentFormService.deletePrivateTrainingAppointmentForm(ptaNo);
		if (result == 1) {
			System.out.println("刪除成功");
			request.setAttribute("successMessage", "刪除成功");
		} else {
			System.out.println("刪除失敗");
			request.setAttribute("errorMessage", "刪除失敗");
			response.sendRedirect(request.getContextPath()
					+ "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp");
		}
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		if (request.getSession().getAttribute("PTAFPageQty") == null) {
			int PTAFPageQty = privateTrainingAppointmentFormService.getPageTotal();
			request.getSession().setAttribute("PTAFPageQty", PTAFPageQty);
		}
		List<PrivateTrainingAppointmentForm> allPrivateTrainingAppointmentForms = privateTrainingAppointmentFormService
				.getAllPTAFs(currentPage);

//		List<PrivateTrainingAppointmentForm> allPrivateTrainingAppointmentForms =  privateTrainingAppointmentFormService.getAllPrivateTrainingAppointmentForms();

//		Integer member = privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getMember().getMemNo();
//		Integer trainer = privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getTrainer().getTrainerNo();

		request.setAttribute("privateTrainingAppointmentForms", allPrivateTrainingAppointmentForms);
		request.setAttribute("currentPage", currentPage);

	}

	private void getOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Integer ptaNo = Integer.valueOf(request.getParameter("ptaNo"));

		PrivateTrainingAppointmentForm pta = privateTrainingAppointmentFormService
				.findPrivateTrainingAppointmentFormByPtaNo(ptaNo);
		request.setAttribute("pta", pta);

	}

	private void beforeSelect(HttpServletRequest req, HttpServletResponse resp) {

		PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
		List<PrivateTrainingAppointmentForm> allPrivateTrainingAppointmentForms = privateTrainingAppointmentFormService
				.getAllPrivateTrainingAppointmentForms();
		MemberService memberservice = new MemberServiceImpl();
		List<Member> allMembers = memberservice.getAllMembers();

		TrainerService trainerservice = new TrainerServiceImpl();
		List<Trainer> allTrainers = trainerservice.getAllTrainers();

		req.setAttribute("privateTrainingAppointmentForms", allPrivateTrainingAppointmentForms);
		req.setAttribute("members", allMembers);
		req.setAttribute("trainers", allTrainers);

	}


	private void getByMemNo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String memNo = request.getParameter("memNo");
		String page = request.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int PTAPageQty2 = privateTrainingAppointmentFormService.getPageTotal2(memNo);
		request.getSession().setAttribute("PTAPageQty2", PTAPageQty2);
		
		List<PrivateTrainingAppointmentForm> members = privateTrainingAppointmentFormService
				.findPrivateTrainingAppointmentFormByMemNo(memNo , currentPage); 

		request.setAttribute("members", members);
		request.setAttribute("currentPage", currentPage);
		
	}
	private void getByTrainerNo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Integer trainerNo = Integer.valueOf(request.getParameter("trainerNo"));
		String page = request.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int PTAPageQty3 = privateTrainingAppointmentFormService.getPageTotal3(trainerNo);
		request.getSession().setAttribute("PTAPageQty3", PTAPageQty3);
		List<PrivateTrainingAppointmentForm> trainers = privateTrainingAppointmentFormService
				.findPrivateTrainingAppointmentFormByTrainerNo(trainerNo , currentPage);
		
		request.setAttribute("trainers", trainers);
		request.setAttribute("currentPage", currentPage);
		
	}
	
	private void updateComment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {

		Integer ptaNo = Integer.valueOf(request.getParameter("ptaNo"));

		String memNo = request.getParameter("member");
		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.findMemberByNo(memNo);

		Integer trainerNo = Integer.valueOf(request.getParameter("trainer"));
		TrainerService trainerService = new TrainerServiceImpl();
		Trainer trainer = trainerService.findTrainerByTrainerNo(trainerNo);

		String ptaClassStr = request.getParameter("number");
        
		int result;
		Integer ptaClass = null;
		try {
			 ptaClass = Integer.parseInt(ptaClassStr);	
			 result = 1;
		} catch (NumberFormatException e) {
			result = -1;
		}
		String ptaComment = request.getParameter("comment");
		
		Timestamp commentTime = privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentTime();
		if(commentTime == null) {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String commentTimeStr = dateFormat.format(now);
			Date parsedDate = dateFormat.parse(commentTimeStr);
			commentTime = new Timestamp(parsedDate.getTime());
			result = privateTrainingAppointmentFormService.insertComment(ptaNo, member, trainer,
					ptaClass, ptaComment, commentTime);
		}else {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String commentUpTimeStr = dateFormat.format(now);
			Date parsedDate = dateFormat.parse(commentUpTimeStr);
			Timestamp commentUpTime = new Timestamp(parsedDate.getTime());
			result = privateTrainingAppointmentFormService.updateComment(ptaNo, member, trainer,
					ptaClass, ptaComment, commentTime, commentUpTime);
		}

		if (result == 1) {
			System.out.println("新增內容成功");
			request.setAttribute("successMessage", "新增內容成功");
		} else {
			System.out.println("新增內容失敗");
			request.setAttribute("errorMessage", "新增內容失敗");
		}
		response.sendRedirect(request.getContextPath()
				+ "/frontend/privatetrainingappointmentform/comment.jsp");
	}



	private void getAllByTrainer(HttpServletRequest request , HttpServletResponse response) throws IOException {
		Integer trainerNo = Integer.valueOf(request.getParameter("trainerNo"));
		Integer year = Integer.valueOf(request.getParameter("year"));
		Integer month = Integer.valueOf(request.getParameter("month"));

		List<java.sql.Date> dates = privateTrainingAppointmentFormService.getByTrainer(year, month, trainerNo);

		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
						.setDateFormat("yyyy-MM-dd")
								.create();

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(gson.toJson(dates));
	}


	
	private void getByMemName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String memName = request.getParameter("memName");
//		System.out.println("======================================================="+memName);
		String page = request.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int PTAPageQty4 = privateTrainingAppointmentFormService.getPageTotal4(memName);
		System.out.println("==================================================================="+PTAPageQty4);
		request.getSession().setAttribute("PTAPageQty4", PTAPageQty4);
		
		List<PrivateTrainingAppointmentForm> members = privateTrainingAppointmentFormService
				.findPrivateTrainingAppointmentFormByMemName(memName , currentPage); 
//		System.out.println("============================================================"+members);
		request.setAttribute("members", members);
		request.setAttribute("currentPage", currentPage);
	}
	private void getByBoth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String memName = request.getParameter("memName");
		Integer trainerNo = Integer.valueOf(request.getParameter("trainerNo"));
//		System.out.println("======================================================="+memName);
		String page = request.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int PTAPageQty5 = privateTrainingAppointmentFormService.getPageTotal5(memName,trainerNo);
		System.out.println("==================================================================="+PTAPageQty5);
		request.getSession().setAttribute("PTAPageQty5", PTAPageQty5);
		
		List<PrivateTrainingAppointmentForm> both = privateTrainingAppointmentFormService
				.findByBoth(memName ,trainerNo, currentPage); 
//		System.out.println("============================================================"+members);
		request.setAttribute("both", both);
		request.setAttribute("currentPage", currentPage);
		
	}
	private void getByMemberOrTrainer(HttpServletRequest request, HttpServletResponse response) {
		
		String trainerNoStr = request.getParameter("trainerNo");
		Integer trainerNo = (trainerNoStr == null || trainerNoStr.trim().length() == 0) ? 0 : Integer.valueOf(trainerNoStr);
		
        String memNameStr = request.getParameter("memName");
        String memName = (memNameStr == null || memNameStr.trim().length() == 0) ? null : memNameStr;
        
        String forwardPath = "";
        if(trainerNo == 0 && memName == null) {
        	getAll(request, response);
        	forwardPath = "/backend/appointment/appointment.jsp";
//        	System.out.println("1111111111111111111111111111111111111111111111111 trainerNo == 0 && memName == null");
        }else if(trainerNo == 0 && memName != null){
        	try {
				getByMemName(request, response);
				forwardPath = "/backend/appointment/appointmentByMemName.jsp";
//				System.out.println("22222222222222222222222222222222222222222222 trainerNo == 0 && memName != null");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else if(trainerNo != 0 && memName == null){
        	try {
				getByTrainerNo(request, response);
				forwardPath = "/backend/appointment/appointmentByTrainerNo.jsp";
//				System.out.println("33333333333333333333333333333333333333333 trainerNo != 0 && memName == null");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else if(trainerNo != 0 && memName != null){
        	try {
				getByBoth(request, response);
				forwardPath = "/backend/appointment/appointmentByBoth.jsp";
//				System.out.println("444444444444444444444444444444444444 trainerNo != 0 && memName != null");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        try {
			request.getRequestDispatcher(forwardPath).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		resp.getWriter().println(action);

	}
}
