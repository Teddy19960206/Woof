package com.woof.groupcourseschedule.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.classtype.entity.ClassType;
import com.woof.classtype.service.ClassTypeService;
import com.woof.classtype.service.ClassTypeServiceImpl;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourse.service.GroupCourseService;
import com.woof.groupcourse.service.GroupCourseServiceImpl;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupcourseschedule.service.GroupGourseScheduleService;
import com.woof.skill.entity.Skill;
import com.woof.skill.service.SkillService;
import com.woof.skill.service.SkillServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;

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

        String pathInfo = request.getPathInfo();

        int secondSlashIndex = pathInfo.indexOf('/', 2);
        Integer result = null;

        if (secondSlashIndex > 0){
            result = Integer.valueOf(pathInfo.substring(secondSlashIndex + 1));
        }
        String forwardPath = "";

        switch (pathInfo){
            case "/getSchedule":
                getSchedule(request , response);
                return;
            case "/registration":
                registration(request , response);
                return;
            default:
                if (pathInfo.startsWith("/edit/")) {
                    getSelectInfo(request,response);
                    forwardPath = edit(request ,response , result);
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


    private void getSchedule(HttpServletRequest request , HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String classType = request.getParameter("classType");

        List<GroupCourseSchedule> groupCourseScheduleSet = null;
        if (classType != null){
            if ("0".equals(classType)){
                groupCourseScheduleSet = groupGourseScheduleService.getAll();
            }else{
                groupCourseScheduleSet = groupGourseScheduleService.getGroupScheduleByCtNo(Integer.valueOf(classType));
            }
        }else{
            //異常判斷
        }


        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groupCourseScheduleSet);
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(json);

    }

    private String getSelectInfo(HttpServletRequest request , HttpServletResponse response){

        GroupCourseService groupCourseService = new GroupCourseServiceImpl();
        List<GroupCourse> allGroupCourse = groupCourseService.getAllGroupCourse();

        TrainerService trainerService = new TrainerServiceImpl();
        List<Trainer> allTrainers = trainerService.getAllTrainers();


        request.setAttribute("groupCourses" , allGroupCourse);
        request.setAttribute("trainers", allTrainers);

        return "/backend/course/addGroupCourse.jsp";
    }

    private String edit(HttpServletRequest request , HttpServletResponse response , Integer id){

        GroupCourseSchedule groupCourseSchedule = groupGourseScheduleService.findByGcsNo(id);

        request.setAttribute("schedule" , groupCourseSchedule);


        return "/groupcourse/editGroupSchedule.jsp";
    }

    private void registration(HttpServletRequest request , HttpServletResponse response) throws IOException {

        Integer id = Integer.valueOf(request.getParameter("id"));
        groupGourseScheduleService.registrationSchedule(id);

        response.sendRedirect(request.getContextPath()+"/groupcourse/schedule?action=getAl");
    }
}


