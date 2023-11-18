package com.woof.skill.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.administrator.entity.Administrator;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourse.service.GroupCourseService;
import com.woof.groupcourse.service.GroupCourseServiceImpl;
import com.woof.skill.entity.Skill;
import com.woof.skill.service.SkillService;
import com.woof.skill.service.SkillServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/skill/*")
@MultipartConfig
public class SkillServlet extends HttpServlet {

    private SkillService skillService;

    @Override
    public void init() throws ServletException{
        skillService = new SkillServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();

        int secondSlashIndex = pathInfo.indexOf('/' , 2);
        Integer result  = null;
        if (secondSlashIndex > 0){
            result = Integer.valueOf(pathInfo.substring(secondSlashIndex +1));
        }
        String forwardPath = "";

        switch (pathInfo){
            case "/allSkill":
//                取得所有skill
                getAllSkill(request,response);
                return;
            case "/modified":
//                修改skill
                modified(request,response);
                return;
            case "/delete":
//                刪除skill
                delete(request, response);
                return;
            case "/addSkill":
//                新增skill
                addSkill(request ,response);
                return;
            case "/getNotExistSKill":
                getNotExistSKill(request, response);
                return;
            default:
                if (pathInfo.startsWith("/getTrainersBySkill/")){
                    getTrainers(request , response , result);
                    return;
                }else {
                    forwardPath = getAllSkill(request, response);
                }
        }
        request.getRequestDispatcher(forwardPath).forward(request,response);
    }

    private String getAllSkill(HttpServletRequest request ,HttpServletResponse response){
        List<Skill> allSkill = skillService.getAllSkill();
        request.setAttribute("skills" , allSkill);

        return "/backend/employee/skill.jsp";
    }

    private void modified(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer skillNo = Integer.valueOf(request.getParameter("skillNo"));
        String skillName = request.getParameter("skillName");

        skillService.updateSkill(skillNo , skillName);

        response.setContentType("application/json");
        response.getWriter().print("{\"message\":\"更新成功\"}");
    }

    private void delete(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        Integer skillNo = Integer.valueOf(request.getParameter("skillNo"));

        skillService.deleteSkill(skillNo);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print("{\"message\":\"刪除成功\"}");

    }

    private void addSkill(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String skillName = request.getParameter("skillName");
        skillService.addSkill(skillName);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print("{\"message\":\"新增成功\"}");
    }


//    由 skill 往 trainer撈取所有有相對應的資料，以測試成功
//    取得groupCourseNo 尋找該物件資料，並抓到SkillNo去尋找擁有該skill的所有trainers
    private void getTrainers(HttpServletRequest request , HttpServletResponse response , Integer gcNo) throws IOException {
//        取得groupCourse物件
        GroupCourseService groupCourseService = new GroupCourseServiceImpl();
        GroupCourse groupCourse = groupCourseService.findGroupCourseByNo(gcNo);
//        找到該物件的skillNo
        Integer skillNo = groupCourse.getSkill().getSkillNo();

//        利用groupCourse的skillNo去尋找擁有該skill的所有tr
        Set<Trainer> trainerBySkillNo = skillService.getTrainersBySkillNo(skillNo);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(trainerBySkillNo);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);

    }

    private void getNotExistSKill(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {

        Administrator administrator = (Administrator) request.getSession(false).getAttribute("administrator");
        Trainer trainer = new TrainerServiceImpl().getByAdmin(administrator.getAdminNo());
        List<Skill> trainerNotExistsSkill = skillService.getTrainerNotExistsSkill(trainer.getTrainerNo());

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(trainerNotExistsSkill);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);

    }
}
