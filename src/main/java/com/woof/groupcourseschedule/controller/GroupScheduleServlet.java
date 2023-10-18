package com.woof.groupcourseschedule.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupcourseschedule.service.GroupGourseScheduleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/groupcourse/schedule")
public class GroupScheduleServlet extends HttpServlet {

    private GroupGourseScheduleService groupGourseScheduleService;
    @Override
    public void init() throws ServletException {
        groupGourseScheduleService = new GroupCourseScheduleServiceImpl();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String forwardPath = "";
        switch (action){
            case "schedule":
                getScheduleByCtNo(request , response);
                return;
            default:
                forwardPath = getAllSchedule(request ,response);
        }
        request.getRequestDispatcher(forwardPath).forward(request,response);
    }

    private String getAllSchedule(HttpServletRequest request , HttpServletResponse response){

        List<GroupCourseSchedule> groupCourseScheduleList = groupGourseScheduleService.getAll();

        request.setAttribute("scheduleList" ,groupCourseScheduleList );

        return "/groupcourse/schedule.jsp";
    }


    private void getScheduleByCtNo(HttpServletRequest request , HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Integer classtypeNo = Integer.valueOf(request.getParameter("classType"));

        List<GroupCourseSchedule> groupCourseScheduleSet = groupGourseScheduleService.getGroupScheduleByCtNo(classtypeNo);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groupCourseScheduleSet);
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(json);

    }

}


