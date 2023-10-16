package com.woof.groupcourse.controller;

import com.woof.classtype.entity.ClassType;
import com.woof.classtype.service.ClassTypeService;
import com.woof.classtype.service.ClassTypeServiceImpl;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourse.service.GroupCourseService;
import com.woof.groupcourse.service.GroupCourseServiceImpl;
import com.woof.skill.entity.Skill;
import com.woof.skill.service.SkillService;
import com.woof.skill.service.SkillServiceImpl;
import com.woof.util.PartParsebyte;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;


@WebServlet("/groupcourse")
@MultipartConfig
public class GroupCourseServlet extends HttpServlet {

    private GroupCourseService groupCourseService;

    @Override
    public void init() throws ServletException {
        groupCourseService = new GroupCourseServiceImpl();
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
        if (action != null){
            switch (action){
                case "addpage":
                    getAllSelect(request,response);
                    forwardPath = "/groupcourse/addGroupCourse.jsp";
                    break;
                case "addgroup":
                    addGroupCourse(request,response);
                    return;
                case "modifiedPage":
                    getAllSelect(request,response);
                    forwardPath = modify(request , response);
                    break;
                case "modified":
                    modifyGroupCourse(request,response);
                    return;
                default:
                    forwardPath = "/classtype/select_page.jsp";
            }
        }else{
            forwardPath = "/classtype/select_page.jsp";
        }
        request.getRequestDispatcher(forwardPath).forward(request,response);
    }

    private void getAllSelect(HttpServletRequest request , HttpServletResponse response){

        ClassTypeService classTypeService = new ClassTypeServiceImpl();
        List<ClassType> allClassTypes = classTypeService.getAllClassTypes();
        SkillService skillService = new SkillServiceImpl();
        List<Skill> allSkill = skillService.getAllSkill();

        request.setAttribute("classTypes" , allClassTypes);
        request.setAttribute("skills", allSkill);

    }

    private void addGroupCourse(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        Integer skillNo = Integer.valueOf(request.getParameter("skill"));
        SkillService skillService = new SkillServiceImpl();
        Skill skill = skillService.findBySkillNo(skillNo);


        ClassTypeService classTypeService = new ClassTypeServiceImpl();
        Integer ctNo = Integer.valueOf(request.getParameter("classType"));
        ClassType classTypeByNO = classTypeService.findClassTypeByNO(ctNo);

        Part filePart = request.getPart("photo");
        byte[] bytes = PartParsebyte.partToByteArray(filePart);
        String content = request.getParameter("content");


        int result = groupCourseService.addGroupCourse(skill, classTypeByNO, bytes, content);

        if ( result == 1){
            System.out.println("新增成功");
        }else{
            System.out.println("新增失敗");
        }

        response.sendRedirect(request.getServletContext().getContextPath()+"/classtype/select_page.jsp");

    }


    private String modify(HttpServletRequest request ,HttpServletResponse response){

        Integer id = Integer.valueOf(request.getParameter("id"));
        GroupCourse groupCourse = groupCourseService.findGroupCourseByNo(id);
        request.setAttribute("groupCourse" , groupCourse);


        return "/groupcourse/modify.jsp";
    }

    private void modifyGroupCourse(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {

        Integer gcNo = Integer.valueOf(request.getParameter("groupCourseNo"));
//
//        SkillService skillService = new SkillServiceImpl();
//        Integer skillNo = Integer.valueOf(request.getParameter("skill"));
//        Skill skill = skillService.findBySkillNo(skillNo);
            Skill skill =new Skill();
            skill.setSkillNo(1);
            skill.setSkillName("攻擊行為矯正");

        ClassTypeService classTypeService = new ClassTypeServiceImpl();
        Integer ctNo = Integer.valueOf(request.getParameter("classType"));
        ClassType classTypeByNO = classTypeService.findClassTypeByNO(ctNo);


        byte[] bytes = null;
        if (request.getPart("photo") != null){
            Part filePart = request.getPart("photo");
            bytes = PartParsebyte.partToByteArray(filePart);

        }

        String content = request.getParameter("content");

        Integer status = Integer.valueOf(request.getParameter("status"));


        int result = groupCourseService.modify(gcNo, skill, classTypeByNO, bytes, content,status);

        if ( result == 1){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失敗");
        }

        response.sendRedirect(request.getServletContext().getContextPath()+"/classtype/select_page.jsp");

    }


}
