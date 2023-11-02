package com.woof.groupcourseorder.controller;

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
import java.sql.Date;


@WebServlet("/groupOrder/*")
@MultipartConfig
public class GroupCourseOrderServlet extends HttpServlet {

    GroupCourseOrderService dao;

    @Override
    public void init() throws ServletException {
        dao = new GroupCourseOrderServiceImpl();
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
            case "/getOrderByDate":
                getOrderByDate(request , response);
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

    private void getOrderByDate(HttpServletRequest request , HttpServletResponse response){

    }

}