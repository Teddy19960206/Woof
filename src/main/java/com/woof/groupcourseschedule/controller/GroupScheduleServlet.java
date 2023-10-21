package com.woof.groupcourseschedule.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourse.service.GroupCourseService;
import com.woof.groupcourse.service.GroupCourseServiceImpl;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupcourseschedule.service.GroupGourseScheduleService;
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
import java.sql.Date;
import java.util.List;
import java.util.Set;

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

//       取得位址資訊
        String pathInfo = request.getPathInfo();

//       判斷若是 /edit/{:_id} 取得id
        int secondSlashIndex = pathInfo.indexOf('/', 2);
        Integer result = null;
        if (secondSlashIndex > 0) {
            result = Integer.valueOf(pathInfo.substring(secondSlashIndex + 1));
        }

        String forwardPath = "";

        switch (pathInfo) {
            case "/addpage":
//                進入新增頁面前，先撈取下拉是選項資料
                forwardPath = getSelectInfo(request, response , null);
                break;
            case "/addSchedule":
                addGroupSchedule(request, response);
                return;
            case "/getSchedule":
//                根據classType的選擇取得相對應的schedule資料
                getSchedule(request, response);
                return;
            case "/registration":
//                消費者點選報名時進行報名人數更新
                registration(request, response);
                return;
            case "/modified":
//                正式修改資料
                modified(request, response);
                return;
            default:
                if (pathInfo.startsWith("/edit/")) {
//                   進入修改頁面前，先撈取下拉式選項資料
                    getSelectInfo(request, response , result);
//                   進入edit畫面根據取得的id去抓取要修改的資料
                    forwardPath = edit(request, response, result);
                } else {
//                    若是都不是以上位址，則預設取得全部資料，並轉回"/backend/course/schedule.jsp"
                    forwardPath = getAllSchedule(request, response);
                }
        }
        request.getRequestDispatcher(forwardPath).forward(request, response);
    }

    private String getAllSchedule(HttpServletRequest request, HttpServletResponse response) {

        List<GroupCourseSchedule> groupCourseScheduleList = groupGourseScheduleService.getAll();

        request.setAttribute("scheduleList", groupCourseScheduleList);

        return "/backend/course/schedule.jsp";
    }


    private void getSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String classType = request.getParameter("classType");

        List<GroupCourseSchedule> groupCourseScheduleSet = null;
        if (classType != null) {
            if ("0".equals(classType)) {
                groupCourseScheduleSet = groupGourseScheduleService.getAll();
            } else {
                groupCourseScheduleSet = groupGourseScheduleService.getGroupScheduleByCtNo(Integer.valueOf(classType));
            }
        } else {
            //異常判斷
        }


        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groupCourseScheduleSet);
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(json);

    }

    //    進入<新增>或<修改>頁面時，會先獲取select下拉式選單可選擇的資料，並到新增頁面
    private String getSelectInfo(HttpServletRequest request, HttpServletResponse response , Integer gcsNo) {

        GroupCourseService groupCourseService = new GroupCourseServiceImpl();
        List<GroupCourse> allGroupCourse = groupCourseService.getAllGroupCourse();
        if (gcsNo != null){
            Integer skillNo = groupGourseScheduleService.findByGcsNo(gcsNo).getGroupCourse().getSkill().getSkillNo();
            SkillService skillService = new SkillServiceImpl();
            Set<Trainer> trainersBySkillNo = skillService.getTrainersBySkillNo(skillNo);

            request.setAttribute("trainers", trainersBySkillNo);
        }

        request.setAttribute("groupCourses", allGroupCourse);

        return "/backend/course/addSchedule.jsp";
    }

    //    進入修改頁面前，依照id撈取原先的資料
    private String edit(HttpServletRequest request, HttpServletResponse response, Integer id) {

        GroupCourseSchedule groupCourseSchedule = groupGourseScheduleService.findByGcsNo(id);

        request.setAttribute("schedule", groupCourseSchedule);

        return "/backend/course/editGroupSchedule.jsp";
    }

    //    消費者報名Schedule
    private void registration(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id = Integer.valueOf(request.getParameter("id"));
        groupGourseScheduleService.registrationSchedule(id);

        response.sendRedirect(request.getContextPath() + "/groupcourse/schedule?action=getAl");
    }

    //    修改Schedule
    private void modified(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer scheduleNo = Integer.valueOf(request.getParameter("scheduleNo"));

        Integer content = Integer.valueOf(request.getParameter("skill"));
        GroupCourseService groupCourseService = new GroupCourseServiceImpl();
        GroupCourse groupCourseByNo = groupCourseService.findGroupCourseByNo(content);

        Integer trainer = Integer.valueOf(request.getParameter("trainer"));
        TrainerService trainerService = new TrainerServiceImpl();
        Trainer trainerByTrainerNo = trainerService.findTrainerByTrainerNo(trainer);

        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        Integer minLimit = Integer.valueOf(request.getParameter("minLimit"));
        Integer maxLimit = Integer.valueOf(request.getParameter("maxLimit"));
        Integer count = Integer.valueOf(request.getParameter("count"));
        Integer price = Integer.valueOf(request.getParameter("price"));
        Integer status = Integer.valueOf(request.getParameter("status"));


        int result = groupGourseScheduleService.updateSchedule(scheduleNo, groupCourseByNo, trainerByTrainerNo, startDate, endDate, minLimit, maxLimit, count, price, status);

        if (result == 1) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失敗");
        }

        response.sendRedirect(request.getContextPath() + "/backend/course/schedule.jsp");
    }

    //    新增 GroupSchedule 團體報名課程
    private void addGroupSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer groupCourse = Integer.valueOf(request.getParameter("groupCourse"));
        GroupCourseService groupCourseService = new GroupCourseServiceImpl();
        GroupCourse groupCourseByNo = groupCourseService.findGroupCourseByNo(groupCourse);

        Integer trainer = Integer.valueOf(request.getParameter("trainer"));
        TrainerService trainerService = new TrainerServiceImpl();
        Trainer trainerByTrainerNo = trainerService.findTrainerByTrainerNo(trainer);

        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        Integer minLimit = Integer.valueOf(request.getParameter("minLimit"));
        Integer maxLimit = Integer.valueOf(request.getParameter("maxLimit"));
        Integer price = Integer.valueOf(request.getParameter("price"));

        int result = groupGourseScheduleService.addSchedule(groupCourseByNo, trainerByTrainerNo, startDate, endDate, minLimit, maxLimit, price);

        if (result == 1) {
            System.out.println("新增成功");
        } else {
            System.out.println("新增失敗");
        }

        response.sendRedirect(request.getContextPath() + "/backend/course/schedule.jsp");
    }

}


