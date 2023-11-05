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

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

            case "/registration":
                registration(request , response);
                return;
            case "/getOrder":
                getOrder(request , response);
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

//        有session後，就可以刪除了
        MemberService memberService = new MemberServiceImpl();
        Member member = memberService.findMemberByNo("member1");
        HttpSession session = request.getSession();
        session.setAttribute("member" , member);


        GroupGourseScheduleService groupCourseScheduleService = new GroupCourseScheduleServiceImpl();
        GroupCourseSchedule groupCourseSchedule = groupCourseScheduleService.findByGcsNo(gcsNo);

        request.setAttribute("groupScheduleNo" , gcsNo);
        request.setAttribute("price" , groupCourseSchedule.getGcsPrice());


        String className = groupCourseSchedule.getGroupCourse().getClassType().getCtName() +" - "+ groupCourseSchedule.getGroupCourse().getSkill().getSkillName();
        request.setAttribute("className" , className);




        return "/frontend/group/registration.jsp";
    }

    synchronized private void registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer groupScheduleNo = Integer.valueOf(request.getParameter("GroupScheduleNo"));
        GroupGourseScheduleService groupGourseScheduleService = new GroupCourseScheduleServiceImpl();
        GroupCourseSchedule groupCourseSchedule = groupGourseScheduleService.findByGcsNo(groupScheduleNo);


//         參加報名時，判斷人數是否已達上限
        if (groupCourseSchedule.getRegCount() > groupCourseSchedule.getMaxLimit()){
          request.getRequestDispatcher("/frontend/group/groupSchedule.jsp").forward(request , response);
        }

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");


        Integer payment = Integer.valueOf(request.getParameter("payment"));

        Integer smmp = null;
        String smmpCount = request.getParameter("smmpCount");
        if (smmpCount != null){
            smmp = Integer.valueOf(smmpCount);
        }

//        dao.addOrder(member ,  groupCourseSchedule , payment , smmp , );

        response.sendRedirect(request.getContextPath()+"/index.html");
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
}