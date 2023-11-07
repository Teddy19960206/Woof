package com.woof.groupcourseorder.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.groupcourseorder.service.GroupCourseOrderService;
import com.woof.groupcourseorder.service.GroupCourseOrderServiceImpl;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupcourseschedule.service.GroupGourseScheduleService;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.util.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@WebServlet("/groupOrder/*")
@MultipartConfig
public class GroupCourseOrderServlet extends HttpServlet {

    GroupCourseOrderService groupCourseOrderService;

    @Override
    public void init() throws ServletException {
        groupCourseOrderService = new GroupCourseOrderServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request , response);
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
            case "/getOrder":
                getOrder(request , response);
                return;
            case "/check":
                check(request , response);
                return;
            default:
                if (pathInfo.startsWith("/getGroupInfo/")) {
                    forwardPath = getGroupInfo(request ,response ,result);
                }else {
                    forwardPath = "/";
                }
        }

        request.getRequestDispatcher(forwardPath).forward(request, response);
    }

    private String getGroupInfo(HttpServletRequest request , HttpServletResponse response , Integer gcsNo){


        GroupGourseScheduleService groupCourseScheduleService = new GroupCourseScheduleServiceImpl();
        GroupCourseSchedule groupCourseSchedule = groupCourseScheduleService.findByGcsNo(gcsNo);

        request.setAttribute("groupScheduleNo" , gcsNo);
        request.setAttribute("price" , groupCourseSchedule.getGcsPrice());


        String className = groupCourseSchedule.getGroupCourse().getClassType().getCtName() +" - "+ groupCourseSchedule.getGroupCourse().getSkill().getSkillName();
        request.setAttribute("className" , className);




        return "/frontend/group/registration.jsp";
    }

    private void getOrder(HttpServletRequest request , HttpServletResponse response) throws IOException {

        String groupClassStr = request.getParameter("groupClass");
        Integer groupClass = (groupClassStr == null ||  groupClassStr.length() == 0 ) ? null : Integer.valueOf(groupClassStr);
//        0:未付款 1:已付款 2:已退款 3.已取消
//        已取消，僅有使用匯款的人會有此狀態
//        到了截止日期的一天後且尚未匯款，排程器會改變狀態變成 < 已取消 >
        String statusStr = request.getParameter("selectStatus");
        Integer status = (statusStr == null ||  statusStr.length() == 0 ) ? null : Integer.valueOf(statusStr);

        String memNoStr = request.getParameter("memNo");
        String memNo = (memNoStr == null || memNoStr.trim().length() == 0) ? null : memNoStr;

        String page = request.getParameter("page");
        Integer currentPage = (page == null) ? 1 : Integer.parseInt(page);

        List<GroupCourseOrder> groupCourseOrderList = groupCourseOrderService.getAll(groupClass, status, memNo, currentPage);

        int pageTotal = groupCourseOrderService.getPageTotal(groupClass , status , memNo);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                        .setDateFormat("yyyy-MM-dd")
                                .create();

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("pageTotal" , pageTotal);
        jsonResponse.add("data" , gson.toJsonTree(groupCourseOrderList));

        System.out.println(groupCourseOrderList);


        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonResponse.toString());
    }

    synchronized private void check(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

//        取得所有資訊
        Member member = (Member) request.getSession().getAttribute("member");
        String smmpCount = request.getParameter("smmpCount");
        String email = request.getParameter("email");
        Integer actualAmount = Integer.valueOf(request.getParameter("actualAmount"));
        Integer payment = Integer.valueOf(request.getParameter("payment"));
        Integer groupScheduleNo = Integer.valueOf(request.getParameter("GroupScheduleNo"));
        GroupGourseScheduleService groupGourseScheduleService = new GroupCourseScheduleServiceImpl();
        GroupCourseSchedule groupCourseSchedule = groupGourseScheduleService.findByGcsNo(groupScheduleNo);

        List<String> errorMsgs = new LinkedList<>();

        request.setAttribute("errorMsgs", errorMsgs);

        Integer smmp = 0;
        if (smmpCount != null){
            smmp = Integer.valueOf(smmpCount);
            if (member.getMomoPoint() < smmp){
                errorMsgs.add("超出所擁有的毛毛幣");
            }
        }

        if (email == null || email.trim().length() == 0){
            errorMsgs.add("信箱請勿空白");
        } else if (!EmailValidator.isValidEmail(email)) {
            errorMsgs.add("信箱格式錯誤");
        }

//         參加報名時，判斷人數是否已達上限
        if (groupCourseSchedule.getRegCount() > groupCourseSchedule.getMaxLimit()){
            errorMsgs.add("人數報名已達上限");
        }

        if (!errorMsgs.isEmpty()){
            request.getRequestDispatcher("/groupOrder/getGroupInfo/1")
                    .forward(request, response);
            return;
        }

        Integer status = 0;
        if (payment == 1 || payment == 2){
            status = 1;
        }

        int orderNo = groupCourseOrderService.addOrder(member, groupCourseSchedule, payment, smmp, actualAmount, status);
        request.getSession().setAttribute("orderNo" , orderNo);
        response.sendRedirect(request.getContextPath()+"/frontend/group/orderPage.jsp");
    }

    private void getOneOrder(HttpServletRequest request , HttpServletResponse response) throws IOException {
        Integer orderNo = Integer.valueOf(request.getParameter("orderNo"));
        GroupCourseOrder order = groupCourseOrderService.getOneOrder(orderNo);


        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();

        String json = gson.toJson(order);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}