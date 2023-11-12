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

import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormService;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;

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
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm_getAll.jsp";
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
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm_getByTrainerNo.jsp";
				break;
			case "commentbymember":
				getByMemNo(req, resp);
				forwardPath = "/frontend/privatetrainingappointmentform/commentByMember.jsp";
				break;
			case "comment":
				forwardPath = "/frontend/privatetrainingappointmentform/commenting.jsp";
				break;
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
}
