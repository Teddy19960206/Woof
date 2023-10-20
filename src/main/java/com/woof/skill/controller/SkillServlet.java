package com.woof.skill.controller;

import com.woof.skill.entity.Skill;
import com.woof.skill.service.SkillService;
import com.woof.skill.service.SkillServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
                forwardPath = getAllSkill(request,response);
                break;
            case "/modified":
//                修改skill
                modified(request,response);
                return;
            case "/delete":
//                刪除skill
                delete(request, response);
                return;
            case "/addSkill":
                addSkill(request ,response);
                forwardPath = getAllSkill(request, response);
                break;
            default:
                    forwardPath = getAllSkill(request, response);
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

        response.setContentType("application/json");
        response.getWriter().print("{\"message\":\"刪除成功\"}");

    }

    private void addSkill(HttpServletRequest request , HttpServletResponse response){
        String skillName = request.getParameter("skillName");
        skillService.addSkill(skillName);
    }

}
