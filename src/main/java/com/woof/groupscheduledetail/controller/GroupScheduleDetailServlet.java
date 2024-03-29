package com.woof.groupscheduledetail.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.administrator.entity.Administrator;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.groupscheduledetail.service.GroupScheduleDetailService;
import com.woof.groupscheduledetail.service.GroupScheduleDetailServiceImpl;
import com.woof.skill.service.SkillService;
import com.woof.skill.service.SkillServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/scheduleDetail/*")
@MultipartConfig
public class GroupScheduleDetailServlet extends HttpServlet {

    private GroupScheduleDetailService groupScheduleDetailService;
    @Override
    public void init() throws ServletException {
        groupScheduleDetailService = new GroupScheduleDetailServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request , response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

//       取得位址資訊
        String pathInfo = request.getPathInfo();

//       判斷若是 /edit/{:_id} 取得id
        int secondSlashIndex = pathInfo.indexOf('/', 2);
        Integer result = null;
        if (secondSlashIndex > 0){
            result = Integer.valueOf(pathInfo.substring(secondSlashIndex + 1));
        }

        String forwardPath = "";

        switch (pathInfo){
            case "/delete":
                delete(request,response);
                return;
            case "/edit":
                edit(request, response);
                return;
            case "/modify":
                modify(request , response);
                return;
            case "/getClassDate":
//               取得訓練師的所有上課時程
                getTrainerClass(request, response);
                return;
            case "/getDetails":
                getDetails(request ,response);
                return;
            case "/addAll":
                addAll(request ,response);
                return;
            default:
//                進入detail畫面根據取得的id去抓取要修改的資料
                if (pathInfo.startsWith("/detail/")) {
                    getdetail(request , response , result);
                    return;
                } else {
//                    若是都不是以上位址，則預設取得全部資料，並轉回"/classtype/classContent.jsp"
                    forwardPath = "/classtype/classContent.jsp";
                }
        }
        request.getRequestDispatcher(forwardPath).forward(request,response);
    }

    private void getdetail(HttpServletRequest request , HttpServletResponse response , Integer gcsNo) throws IOException {

        List<GroupScheduleDetail> groupScheduleDetails = new GroupScheduleDetailServiceImpl().getByGroupSchedule(gcsNo);


        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String json = gson.toJson(groupScheduleDetails);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    private void delete(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String detail = request.getParameter("Detail");
        groupScheduleDetailService.delete(Integer.valueOf(detail));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{ \"message\" :  \"刪除成功\" }");
    }

    private void edit(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String detail = request.getParameter("Detail");

        GroupScheduleDetail groupScheduleDetail = groupScheduleDetailService.findByGcsd(Integer.valueOf(detail));

        Integer skillNo = groupScheduleDetail.getGroupCourseSchedule().getGroupCourse().getSkill().getSkillNo();

        Set<Trainer> trainersBySkillNo = new SkillServiceImpl().getTrainersBySkillNo(skillNo);


        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String json = gson.toJson(trainersBySkillNo);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);

    }
    private void modify(HttpServletRequest request , HttpServletResponse response) throws IOException {
        Integer gcsdNo = Integer.valueOf(request.getParameter("gcsdNo"));
        Integer trainerNo = Integer.valueOf(request.getParameter("trainerNo"));
        Date date = null;

        Trainer trainerByTrainerNo = new TrainerServiceImpl().findTrainerByTrainerNo(trainerNo);

        String classDateStr = request.getParameter("classDate");


        if (classDateStr == null || "undefined".equals(classDateStr) || classDateStr.trim().length() == 0){
            date = groupScheduleDetailService.findByGcsd(gcsdNo).getClassDate();
        }else{
            date = Date.valueOf(classDateStr);
        }

        groupScheduleDetailService.modify(gcsdNo , trainerByTrainerNo , date);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"message\" : \"更新成功\" }");
    }

    private void getTrainerClass(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String trainerId = request.getParameter("trainerId");
        String json = null	;

        if (trainerId != null && trainerId.length() != 0) {
            Integer Id = Integer.valueOf(trainerId);
            List<Object[]> byTrainer = groupScheduleDetailService.getByTrainer(Id);
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            json = gson.toJson(byTrainer);
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    private void getDetails(HttpServletRequest request , HttpServletResponse response) throws IOException {
        Integer year = Integer.valueOf(request.getParameter("year"));
        Integer month = Integer.valueOf(request.getParameter("month"));
        String trainerNoStr = request.getParameter("applyTrainerFilter");
        String applyStatusFilterStr = request.getParameter("applyStatusFilter");
        Integer trainerNo = null;
        Boolean applyStatusFilter = false;

        if ("true".equals(trainerNoStr)){
            Administrator administrator = (Administrator) request.getSession(false).getAttribute("administrator");
            TrainerService trainerService = new TrainerServiceImpl();
            trainerNo = trainerService.getByAdmin(administrator.getAdminNo()).getTrainerNo();
        }

        if ("true".equals(applyStatusFilterStr)){
            applyStatusFilter = true;
        }

        List<GroupScheduleDetail> dates = groupScheduleDetailService.getByDate(year, month, trainerNo, applyStatusFilter);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String json = gson.toJson(dates);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);

    }

    private void addAll(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        Integer scheduleNo = Integer.valueOf(request.getParameter("scheduleNo"));
        GroupCourseSchedule schedule = new GroupCourseScheduleServiceImpl().findByGcsNo(scheduleNo);


        Set<Date> dates = Arrays.stream(request.getParameter("classDates").split(","))
                .map(Date::valueOf)
                .collect(Collectors.toSet());

        groupScheduleDetailService.add(schedule , schedule.getTrainer() , dates);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"message\" : \"新增成功\"}");
    }
}
