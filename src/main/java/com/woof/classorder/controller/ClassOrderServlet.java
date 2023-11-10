package com.woof.classorder.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import com.google.gson.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.classorder.entity.ClassOrder;
import com.woof.classorder.service.ClassOrderService;
import com.woof.classorder.service.ClassOrderServiceImpl;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupcourseschedule.service.GroupGourseScheduleService;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.groupscheduledetail.service.GroupScheduleDetailService;
import com.woof.groupscheduledetail.service.GroupScheduleDetailServiceImpl;
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
        if (secondSlashIndex > 0){
            result = Integer.valueOf(pathInfo.substring(secondSlashIndex + 1));
        }

        String forwardPath = "";
        switch (pathInfo){
            case "/getOneOrder":
                getOneOrder(request , response);
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
	
	private void getOneOrder(HttpServletRequest req , HttpServletResponse res) throws IOException {
		Integer coNo = Integer.valueOf(req.getParameter("coNo"));
		ClassOrder classOrder = classOrderService.findClassOrderByCoNo(coNo);
		
		 Gson gson = new GsonBuilder()
	                .excludeFieldsWithoutExposeAnnotation()
	                .setDateFormat("yyyy-MM-dd")
	                .create();
		String json = gson.toJson(classOrder);
        res.setContentType("application/json;charset=UTF-8");
        res.getWriter().write(json);
	}
	synchronized private void check(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {

//      取得所有資訊
      Member member = (Member) request.getSession(false).getAttribute("member");
      Integer coBc = Integer.valueOf(request.getParameter("coBc"));
      Integer coPm = Integer.valueOf(request.getParameter("coPm"));
      String coSmmp = request.getParameter("coSmmp");
      Integer coStatus;
      
      String email = request.getParameter("email");
      Integer actualAmount = Integer.valueOf(request.getParameter("actualAmount"));
      Integer payment = Integer.valueOf(request.getParameter("payment"));
    

      List<String> errorMsgs = new LinkedList<>();

      request.setAttribute("errorMsgs", errorMsgs);

      Integer smmp = 0;
      if (coSmmp != null){
          smmp = Integer.valueOf(coSmmp);
          if (member.getMomoPoint() < smmp){
              errorMsgs.add("超出所擁有的毛毛幣");
          }
      }

      if (email == null || email.trim().length() == 0){
          errorMsgs.add("信箱請勿空白");
      } else if (!EmailValidator.isValidEmail(email)) {
          errorMsgs.add("信箱格式錯誤");
      }
//下面未修改
      if (!errorMsgs.isEmpty()){
          request.getRequestDispatcher("/groupOrder/getGroupInfo/1")
                  .forward(request, response);
          return;
      }

//      使用信用卡0 預設為已付款1
//      使用匯款 1 預設為未付款0
//      使用綠界 2 預設為已付款1
      Integer status = 0;
      if (payment == 0 || payment == 2){
          status = 1;
      }
      Integer orderNo = null;
      try{
//        新增Order
          orderNo = groupCourseOrderService.addOrder(member, groupCourseSchedule, payment, smmp, actualAmount, status);

          GroupScheduleDetailService groupScheduleDetailService = new GroupScheduleDetailServiceImpl();

//        取得上課日期
          List<GroupScheduleDetail> details = groupScheduleDetailService.getByGroupSchedule(groupCourseSchedule.getGcsNo());
          Set<Date> dates = new HashSet<>();
          for (GroupScheduleDetail detail : details){
              dates.add(detail.getClassDate());
          }


          MailService mailService = new MailService();

          new Thread(() -> mailService.sendMail("trick95710@gmail.com" ,
                  "報名成功" ,
                  MailService.groupOrderhtml(member.getMemName() ,                            // 報名人姓名
                          groupCourseSchedule.getGroupCourse().getClassType().getCtName(),    // 班級名稱
                          dates,                                                              // 上課日期
                          groupCourseSchedule.getGroupCourse().getCourseContent()))).start(); // 課程內容



      }catch (Exception e){
          e.printStackTrace();
          AppLogger.getLogger().log(Level.ALL, "發生例外，新增失敗：" + e);
      }
      request.getSession().setAttribute("orderNo" , orderNo);
      response.sendRedirect(request.getContextPath()+"/frontend/group/orderPage.jsp");
  }
}
