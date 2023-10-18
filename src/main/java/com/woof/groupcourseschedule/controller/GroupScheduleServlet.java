package com.woof.groupcourseschedule.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupcourseschedule.service.GroupGourseScheduleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/schedule/*")
@MultipartConfig
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

        String classType = request.getParameter("classType");

        String pathInfo = request.getPathInfo();

        int secondSlashIndex = pathInfo.indexOf('/', 2);
        Integer result = null;

        if (secondSlashIndex > 0){
            result = Integer.valueOf(pathInfo.substring(secondSlashIndex + 1));
        }
        String forwardPath = "";

        switch (pathInfo){
            case "/getSchedule":
                getScheduleByCtNo(request , response);
                return;
            case "/registration":
                registration(request , response);
                return;
            default:
                if (pathInfo.startsWith("/edit/")) {
                    forwardPath = getScheduleByGcsNo(request ,response , result);
                } else {
                    forwardPath = getAllSchedule(request ,response);
                }
        }
        request.getRequestDispatcher(forwardPath).forward(request,response);
    }

    private String getAllSchedule(HttpServletRequest request , HttpServletResponse response){

        List<GroupCourseSchedule> groupCourseScheduleList = groupGourseScheduleService.getAll();

        request.setAttribute("scheduleList" ,groupCourseScheduleList );

        return "/backend/course/schedule.jsp";
    }


    private void getScheduleByCtNo(HttpServletRequest request , HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String classType = request.getParameter("classType");

        List<GroupCourseSchedule> groupCourseScheduleSet = groupGourseScheduleService.getGroupScheduleByCtNo(Integer.valueOf(classType));

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groupCourseScheduleSet);
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(json);

    }

    private String getScheduleByGcsNo(HttpServletRequest request , HttpServletResponse response , Integer result){

        Integer id = Integer.valueOf(request.getParameter("id"));

        GroupCourseSchedule groupCourseSchedule = groupGourseScheduleService.findByGcsNo(id);

        request.setAttribute("schedule" , groupCourseSchedule);


        return "/groupcourse/modifyGroupSchedule.jsp";
    }

    private void registration(HttpServletRequest request , HttpServletResponse response) throws IOException {

        Integer id = Integer.valueOf(request.getParameter("id"));
        groupGourseScheduleService.registrationSchedule(id);

        response.sendRedirect(request.getContextPath()+"/groupcourse/schedule?action=getAl");
    }
}


