package com.woof.appointmentdetail.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.woof.administrator.entity.Administrator;
import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.appointmentdetail.service.AppointmentDetailService;
import com.woof.appointmentdetail.service.AppointmentDetailServiceImpl;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormService;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;
import com.woof.util.JsonIgnoreExclusionStrategy;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/appointmentdetail/*")
@MultipartConfig
public class AppointmentDetailServlet extends HttpServlet {
	
	private AppointmentDetailService appointmentDetailService;
	
	@Override
	public void init() throws ServletException{
		appointmentDetailService = new AppointmentDetailServiceImpl();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		String forwardPath = "";
		if (action != null) {
			switch (action) {
			case "getdetail2":
				getByPtaNo2(req, resp);
				break;
			case "getdetail":
				getByPtaNo(req, resp);
				forwardPath = "/backend/appointment/appointmentDetail.jsp";
				break;
			case "getdetail3":
				getByPtaNo(req, resp);
				forwardPath = "/frontend/member/login/appointmentDetail.jsp";
				break;
			case "cancel":
				cancel(req, resp);
				forwardPath = "/frontend/member/login/appointmentDetail.jsp";
				break;
			case "gettoadd":
				beforeAdd(req, resp);
				forwardPath = "/backend/appointment/appointmentAdd.jsp";
				break;
			case "add":
				add(req, resp);
				forwardPath = "/backend/appointment/appointmentDetail.jsp";
				break;
			case "gettoupdate":
				forwardPath = "/backend/appointment/appointmentDetailUpdate.jsp";
				break;
			case "update":
				update(req, resp);
				forwardPath = "/backend/appointment/appointmentDetail.jsp";
				break;
			case "delete":
				delete(req, resp);
				forwardPath = "/backend/appointment/appointmentDetail.jsp";
				break;
			case "cancelFromTrainer":
				cancelFromTrainer(req , resp);
				return;
			case "cancelAppointment":
				cancelAppointment(req , resp);
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
    private void getByPtaNo(HttpServletRequest req, HttpServletResponse res) {
    	
    	String ptaNoStr = req.getParameter("ptaNo");
    	Integer ptaNo = Integer.parseInt(ptaNoStr);
    	List<AppointmentDetail> appointmentDetails = appointmentDetailService.findAdByPtaNo(ptaNo);
    	req.setAttribute("appointmentDetails",appointmentDetails);
//    	req.getSession().setAttribute("ptaNo",ptaNo);
    	req.setAttribute("ptaNo",ptaNo);
    }
    private void getByPtaNo2(HttpServletRequest req, HttpServletResponse res) {
    	
    	String ptaNoStr = req.getParameter("ptaNo");
    	Integer ptaNo = Integer.parseInt(ptaNoStr);
    	List<AppointmentDetail> appointmentDetails = appointmentDetailService.findAdByPtaNo(ptaNo);
    	req.setAttribute("appointmentDetails",appointmentDetails);
    	req.setAttribute("ptaNo",ptaNo);
    	
    	Gson gson = new GsonBuilder()
                .setExclusionStrategies()
                .addSerializationExclusionStrategy(new JsonIgnoreExclusionStrategy(true))
                .addDeserializationExclusionStrategy(new JsonIgnoreExclusionStrategy(false))
                .setDateFormat("yyyy-MM-dd")
                .create();
    	
    	JsonObject jsonResponse = new JsonObject();
        jsonResponse.add("data" , gson.toJsonTree(appointmentDetails));
        res.setContentType("application/json;charset=UTF-8");
        try {
			res.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void beforeAdd(HttpServletRequest req, HttpServletResponse res) {
 
    	Integer ptaNo = Integer.parseInt(req.getParameter("ptaNo"));
    	req.setAttribute("ptaNo",ptaNo); 
    }
    
    private void add(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	
    	// 有預約單要新增明細
    	Integer ptaNo = Integer.parseInt(req.getParameter("ptaNo"));
    	PrivateTrainingAppointmentFormService ptaService =  new PrivateTrainingAppointmentFormServiceImpl();
    	PrivateTrainingAppointmentForm pta = ptaService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo);
    	
    	String selectedDateStr = req.getParameter("date");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(selectedDateStr);// 將字串轉換為 java.util.Date
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // 將 java.util.Date 轉換為 java.sql.Date
    	
		Integer appStatus = Integer.parseInt(req.getParameter("appStatus"));
		
		int result = appointmentDetailService.addAd(pta , sqlDate , appStatus);
		
		if (result == 1) {
			System.out.println("新增成功");
			req.setAttribute("successMessage", "新增成功");
		} else {
			System.out.println("新增失敗");
			req.setAttribute("errorMessage", "新增失敗");
		}
		// 使用預約單的修改方法修改數量
		Integer ptaClass = (int) appointmentDetailService.getTotalByPtaNo(ptaNo);
		
		String memNo = req.getParameter("memNo");
		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.findMemberByNo(memNo);

		Integer trainerNo = Integer.valueOf(req.getParameter("trainerNo"));
		TrainerService trainerService = new TrainerServiceImpl();
		Trainer trainer = trainerService.findTrainerByTrainerNo(trainerNo);
		
		
		PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
		Timestamp commentTime =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentTime();
		Timestamp commentUpTime =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentUpTime();
		String ptaComment =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getPtaComment();
		privateTrainingAppointmentFormService.updatePrivateTrainingAppointmentForm(ptaNo, member, trainer,
					ptaClass,ptaComment,commentTime,commentUpTime);
		 // 更新會員課堂數
		Integer totalClass = member.getTotalClass();
		if (appStatus == 0) {
			memberService.updateMemberClass(memNo, totalClass + 1);
		}else {
			memberService.updateMemberClass(memNo, totalClass - 1);
		}
		 // 用查詢展示跳轉的效果
		getByPtaNo(req,res);
//		res.sendRedirect(req.getContextPath() + "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp");
	}
    private void update(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	
    	Integer adNo = Integer.parseInt(req.getParameter("adNo"));
    	
    	Integer ptaNo = Integer.parseInt(req.getParameter("ptaNo"));
    	PrivateTrainingAppointmentFormService ptaService =  new PrivateTrainingAppointmentFormServiceImpl();
    	PrivateTrainingAppointmentForm pta = ptaService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo);
    	
    	
    	String selectedDateStr = req.getParameter("date");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	
    	java.util.Date parsedDate = null;
    	try {
    		parsedDate = dateFormat.parse(selectedDateStr);// 將字串轉換為 java.util.Date
    	} catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} 
    	java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // 將 java.util.Date 轉換為 java.sql.Date
    	
    	Integer appStatus = Integer.parseInt(req.getParameter("appStatus"));
    	
    	int result = appointmentDetailService.updateAd(adNo , pta , sqlDate , appStatus);
    	
    	if (result == 1) {
    		System.out.println("更新成功");
    		req.setAttribute("successMessage", "更新成功");
    	} else {
    		System.out.println("更新失敗");
    		req.setAttribute("errorMessage", "更新失敗");
    	}
    	
    	// 使用預約單的修改方法修改數量
    	Integer ptaClass = (int) appointmentDetailService.getTotalByPtaNo(ptaNo);
    	
    	String memNo = req.getParameter("memNo");
		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.findMemberByNo(memNo);

		Integer trainerNo = Integer.valueOf(req.getParameter("trainerNo"));
		TrainerService trainerService = new TrainerServiceImpl();
		Trainer trainer = trainerService.findTrainerByTrainerNo(trainerNo);
		
		PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
		Timestamp commentTime =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentTime();
		Timestamp commentUpTime =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentUpTime();
		String ptaComment =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getPtaComment();
		privateTrainingAppointmentFormService.updatePrivateTrainingAppointmentForm(ptaNo, member, trainer,
					ptaClass,ptaComment,commentTime,commentUpTime);
		 // 更新會員課堂數
		Integer totalClass = member.getTotalClass();
		if (appStatus == 0) {
			memberService.updateMemberClass(memNo, totalClass + 1);
		}else {
			memberService.updateMemberClass(memNo, totalClass - 1);
		}
		 // 用查詢展示跳轉的效果
    	getByPtaNo(req,res);
//    	res.sendRedirect(req.getContextPath() + "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp");
    }
    
    private void delete(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	// 尚未做更新會員課程數量的設置
    	Integer adNo = Integer.parseInt(req.getParameter("adNo"));
    	int result = appointmentDetailService.deleteAd(adNo);
    	if (result == 1) {
    		System.out.println("刪除成功");
    		req.setAttribute("successMessage", "刪除成功");
    	} else {
    		System.out.println("刪除失敗");
    		req.setAttribute("errorMessage", "刪除失敗");
    	}
    	Integer ptaNo = Integer.parseInt(req.getParameter("ptaNo"));
    	Integer ptaClass = (int) appointmentDetailService.getTotalByPtaNo(ptaNo);
		
		String memNo = req.getParameter("memNo");
		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.findMemberByNo(memNo);

		Integer trainerNo = Integer.valueOf(req.getParameter("trainerNo"));
		TrainerService trainerService = new TrainerServiceImpl();
		Trainer trainer = trainerService.findTrainerByTrainerNo(trainerNo);
		
		PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
		Timestamp commentTime =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentTime();
		Timestamp commentUpTime =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentUpTime();
		String ptaComment =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getPtaComment();
		privateTrainingAppointmentFormService.updatePrivateTrainingAppointmentForm(ptaNo, member, trainer,
					ptaClass,ptaComment,commentTime,commentUpTime);
    	getByPtaNo(req,res);
//    	res.sendRedirect(req.getContextPath() + "/backend/appointment/appointmentDetail.jsp");
    }
    private void cancel(HttpServletRequest req, HttpServletResponse res) {
    	
    	String adNoStr = req.getParameter("adNo");
    	Integer adNo = Integer.parseInt(adNoStr);
    	PrivateTrainingAppointmentForm pta = appointmentDetailService.findAdByAdNo(adNo).getPrivateTrainingAppointmentForm();
    	Date appTime = appointmentDetailService.findAdByAdNo(adNo).getAppTime();

    	appointmentDetailService.updateAd(adNo, pta, appTime, 1);
    	
    	Integer ptaNo = pta.getPtaNo();
    	
    	Integer ptaClass = (int) appointmentDetailService.getTotalByPtaNo(ptaNo);
    	
    	PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
		Member member = privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getMember();

		/* 退回課堂數 */
		new MemberServiceImpl().cancelPrivateClass(member.getMemNo());

		Trainer trainer = privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getTrainer();

		Timestamp commentTime =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentTime();
		Timestamp commentUpTime =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentUpTime();
		String ptaComment =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getPtaComment();
		privateTrainingAppointmentFormService.updatePrivateTrainingAppointmentForm(ptaNo, member, trainer,
					ptaClass,ptaComment,commentTime,commentUpTime);
    	
    	getByPtaNo(req, res);
    }

	private void cancelFromTrainer(HttpServletRequest request , HttpServletResponse response) throws IOException {

		Administrator administrator = (Administrator) request.getSession(false).getAttribute("administrator");
		Trainer trainer = new TrainerServiceImpl().getByAdmin(administrator.getAdminNo());

		Date date = Date.valueOf(request.getParameter("date"));
		AppointmentDetail appointmentDetail = appointmentDetailService.getOneByDate(trainer.getTrainerNo(), date);
		appointmentDetailService.updateAd(appointmentDetail.getAdNo(), appointmentDetail.getPrivateTrainingAppointmentForm() , date , 1);
		new MemberServiceImpl().cancelPrivateClass(appointmentDetail.getPrivateTrainingAppointmentForm().getMember().getMemNo());


//		???

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write("{\"message\" : \"取消成功\"}");
	}

	private void addPta(HttpServletRequest req, HttpServletResponse res) {
		
		// 沒預約單要新增明細
		
	}
	
	private void cancelAppointment(HttpServletRequest req, HttpServletResponse res) {
		
		String adNoStr = req.getParameter("adNo");
    	Integer adNo = Integer.parseInt(adNoStr);
    	PrivateTrainingAppointmentForm pta = appointmentDetailService.findAdByAdNo(adNo).getPrivateTrainingAppointmentForm();
    	Date appTime = appointmentDetailService.findAdByAdNo(adNo).getAppTime();

    	appointmentDetailService.updateAd(adNo, pta, appTime, 1);
    	
    	Integer ptaNo = pta.getPtaNo();
    	
    	Integer ptaClass = (int) appointmentDetailService.getTotalByPtaNo(ptaNo);
    	
    	PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
		Member member = privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getMember();

		/* 退回課堂數 */
		new MemberServiceImpl().cancelPrivateClass(member.getMemNo());

		Trainer trainer = privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getTrainer();

		Timestamp commentTime =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentTime();
		Timestamp commentUpTime =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getCommentUpTime();
		String ptaComment =privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo).getPtaComment();
		privateTrainingAppointmentFormService.updatePrivateTrainingAppointmentForm(ptaNo, member, trainer,
					ptaClass,ptaComment,commentTime,commentUpTime);
	}
}
