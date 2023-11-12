package com.woof.classorder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import com.google.gson.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.classorder.entity.ClassOrder;
import com.woof.classorder.service.ClassOrderService;
import com.woof.classorder.service.ClassOrderServiceImpl;
import com.woof.member.entity.Member;
import com.woof.util.AppLogger;
import com.woof.util.EmailValidator;
import com.woof.util.MailService;

@WebServlet("/classorder/*")
public class ClassOrderServlet extends HttpServlet {

	private ClassOrderService classOrderService;

	@Override
	public void init() throws ServletException {
		classOrderService = new ClassOrderServiceImpl();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String pathInfo = request.getPathInfo();

		int secondSlashIndex = pathInfo.indexOf('/', 2);
		Integer result = null;
		if (secondSlashIndex > 0) {
			result = Integer.valueOf(pathInfo.substring(secondSlashIndex + 1));
		}

		String forwardPath = "";
		switch (pathInfo) {
		case "/getOneOrder":
			getOneOrder(request, response);
			return;
		case "/check":
			try {
				check(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		default:
			forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp";
		}
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	private void getOneOrder(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer coNo = Integer.valueOf(req.getParameter("coNo"));
		ClassOrder classOrder = classOrderService.findClassOrderByCoNo(coNo);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(classOrder);
		res.setContentType("application/json;charset=UTF-8");
		res.getWriter().write(json);
	}

	synchronized private void check(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {

//      取得所有資訊
		Member member = (Member) request.getSession(false).getAttribute("member");
		Integer coBc = Integer.valueOf(request.getParameter("coBc"));
		Integer coPm = Integer.valueOf(request.getParameter("coPm"));
		String coSmmp = request.getParameter("coSmmp");

		String email = request.getParameter("email");
		Integer actualAmount = Integer.valueOf(request.getParameter("actualAmount"));


		java.util.Date now = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String coTimeStr = dateFormat.format(now);
		java.util.Date parsedDate = dateFormat.parse(coTimeStr);
		Timestamp coTime = new Timestamp(parsedDate.getTime());

		List<String> errorMsgs = new LinkedList<>();

		request.setAttribute("errorMsgs", errorMsgs);

		Integer smmp = 0;
		if (coSmmp != null) {
			smmp = Integer.valueOf(coSmmp);
			if (member.getMomoPoint() < smmp) {
				errorMsgs.add("超出所擁有的毛毛幣");
			}
		}

		if (email == null || email.trim().length() == 0) {
			errorMsgs.add("信箱請勿空白");
		} else if (!EmailValidator.isValidEmail(email)) {
			errorMsgs.add("信箱格式錯誤");
		}

		if (!errorMsgs.isEmpty()) {
			request.getRequestDispatcher("/frontend/privatetrainer/buyClass.jsp").forward(request, response);
			return;
		}

//      使用信用卡0 預設為已付款1
//      使用匯款 1 預設為未付款0
//      使用綠界 2 預設為已付款1
		Integer coStatus = 0;
		if (coPm == 0 || coPm == 2) {
			coStatus = 1;
		}
		
		String coPmStr;
		switch (coPm) {
		case 0:
			coPmStr = "信用卡";
			break;
		case 1:
			coPmStr = "匯款";
			break;
		case 2:
			coPmStr = "綠界";
			break;
		default:
			coPmStr = "錯誤";
		}
		
		String coStatusStr;
		switch (coStatus) {
		case 0:
			coStatusStr = "未付款";
			break;
		case 1:
			coStatusStr = "已付款";
			break;
		default:
			coStatusStr = "錯誤";
		}
		
		Integer coNo = null;
		try {
//        新增classOrder
			coNo = classOrderService.addClassOrder(member, coBc, coPm, smmp, coTime, coStatus, actualAmount);
			
			MailService mailService = new MailService();

            new Thread(() -> mailService.sendMail("trick95710@gmail.com" ,
                    "購買成功" ,
                    MailService.classOrderhtml(coBc ,                            
                    		member.getMemName()))).start();
		} catch (Exception e) {
			e.printStackTrace();
			AppLogger.getLogger().log(Level.ALL, "發生例外，新增失敗：" + e);
		}
		String memNo = member.getMemNo();
		String memName = member.getMemName();
		request.getSession().setAttribute("coNo", coNo);
		request.getSession().setAttribute("memNo", memNo);
		request.getSession().setAttribute("memName", memName);
		request.getSession().setAttribute("coBc", coBc);
		request.getSession().setAttribute("coTime", coTime);
		request.getSession().setAttribute("coPmStr", coPmStr);
		request.getSession().setAttribute("smmp", smmp);
		request.getSession().setAttribute("actualAmount", actualAmount);
		request.getSession().setAttribute("coStatusStr", coStatusStr);
		
		Integer momo = member.getMomoPoint() - smmp;
		member.setMomoPoint(momo);
	
		response.sendRedirect(request.getContextPath() + "/frontend/privatetrainer/classOrder.jsp");
	}
}
