package com.woof.skillslist.controller;


import com.woof.skill.entity.Skill;
import com.woof.skill.service.SkillServiceImpl;
import com.woof.skillslist.service.SkillsListService;
import com.woof.skillslist.service.SkillsListServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SkillsList/*")
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
             default:
         }

    }

    private void addSkillsList(HttpServletRequest request , HttpServletResponse response){

//        若有member session 則可刪除
        Trainer trainer1 = new TrainerServiceImpl().findTrainerByTrainerNo(1);
        HttpSession session = request.getSession();
        session.setAttribute("trainer" , trainer1);


        Trainer trainer = (Trainer) session.getAttribute("trainer");
        Integer skillNo = Integer.valueOf(request.getParameter("skill"));
        Skill skill = new SkillServiceImpl().findBySkillNo(skillNo);

        skillsListService.TrainerAddSkill(trainer.getTrainerNo() , skill.getSkillNo());

    }
}
