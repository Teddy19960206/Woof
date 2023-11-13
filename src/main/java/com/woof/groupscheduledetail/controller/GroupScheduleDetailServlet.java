package com.woof.groupscheduledetail.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.administrator.entity.Administrator;
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
import java.util.List;
import java.util.Set;
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
            case "/getDetailByDate":
//              取得某年某月的detail所有資料
                getDetailByDate(request , response);
                return;
            case "/getTrainerDetail":
//                取得訓練師課表
                getTrainerDetail(request , response);
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

        SkillService skillService = new SkillServiceImpl();
        Set<Trainer> trainersBySkillNo = skillService.getTrainersBySkillNo(skillNo);

        System.out.println(trainersBySkillNo);

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
        Date date = Date.valueOf(request.getParameter("classDate"));

        TrainerService trainerService = new TrainerServiceImpl();
        Trainer trainerByTrainerNo = trainerService.findTrainerByTrainerNo(trainerNo);

        groupScheduleDetailService.modify(gcsdNo , trainerByTrainerNo , date);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("ok");
    }

    private void getTrainerClass(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String trainerId = request.getParameter("trainerId");
        String json = null	;

        if (trainerId != null && trainerId.length() != 0) {
            Integer Id = Integer.valueOf(trainerId);
            List<Object[]> byTrainer = groupScheduleDetailService.getByTrainer(Id);
            System.out.println(byTrainer);
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            json = gson.toJson(byTrainer);
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    private void getDetailByDate(HttpServletRequest request , HttpServletResponse response) throws IOException {

        Integer year = Integer.valueOf(request.getParameter("year"));
        Integer month = Integer.valueOf(request.getParameter("month"));
        List<GroupScheduleDetail> detailByDate = groupScheduleDetailService.getDetailByDate(year, month);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String json = gson.toJson(detailByDate);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);

    }

    private void getTrainerDetail(HttpServletRequest request , HttpServletResponse response) throws IOException {

        Administrator administrator = (Administrator) request.getSession(false).getAttribute("Administrator");
        TrainerService trainerService = new TrainerServiceImpl();
        Integer trainerNo = trainerService.getByAdmin(administrator.getAdminNo()).getTrainerNo();

        Integer year = Integer.valueOf(request.getParameter("year"));
        Integer month = Integer.valueOf(request.getParameter("month"));

        List<GroupScheduleDetail> detailByDate = groupScheduleDetailService.getDetailByDate(year, month ,trainerNo);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String json = gson.toJson(detailByDate);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}
