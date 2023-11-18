package com.woof.skillslist.controller;


import com.woof.administrator.entity.Administrator;
import com.woof.skill.entity.Skill;
import com.woof.skill.service.SkillServiceImpl;
import com.woof.skillslist.service.SkillsListService;
import com.woof.skillslist.service.SkillsListServiceImpl;
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

@WebServlet("/SkillsList/*")
@MultipartConfig
public class SkillsListServlet extends HttpServlet {
    private SkillsListService skillsListService;

    @Override
    public void init() throws ServletException {
        skillsListService = new SkillsListServiceImpl();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         String pathInfo = request.getPathInfo();

         switch (pathInfo){
             case "/addSkillsList":
                 addSkillsList(request , response);
                 return;
             case "/deleteSkillsList":
                 deleteSkillsList(request ,response);
                 return;
             default:
         }

    }

    private void addSkillsList(HttpServletRequest request , HttpServletResponse response) throws IOException {

        Administrator administrator = (Administrator) request.getSession(false).getAttribute("administrator");
        Trainer trainer = new TrainerServiceImpl().getByAdmin(administrator.getAdminNo());

        Integer skillNo = Integer.valueOf(request.getParameter("skillNo"));

        skillsListService.TrainerAddSkill(trainer.getTrainerNo() , skillNo);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{ \"message\" : \"新增成功\" }");
    }

    private void deleteSkillsList(HttpServletRequest request , HttpServletResponse response) throws IOException {

        Administrator administrator = (Administrator) request.getSession(false).getAttribute("administrator");
        Trainer trainer = new TrainerServiceImpl().getByAdmin(administrator.getAdminNo());

        Integer skillNo = Integer.valueOf(request.getParameter("skillNo"));

        int result = skillsListService.deleteTrainerSkill(trainer.getTrainerNo(), skillNo);

        response.setContentType("application/json;charset=UTF-8");
        if (result == 1){
            response.getWriter().write("{ \"message\" : \"刪除成功\" }");
        }else{
            response.getWriter().write("{ \"message\" : \"刪除失敗\" }");
        }

    }
}
