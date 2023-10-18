package com.woof.groupcourse.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.classtype.entity.ClassType;
import com.woof.classtype.service.ClassTypeService;
import com.woof.classtype.service.ClassTypeServiceImpl;
import com.woof.groupcourse.entity.GroupCourse;
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

    private GroupCourseServiceImpl groupCourseService;

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
                    getSelectInfo(request,response);
                    forwardPath = "/groupcourse/addGroupCourse.jsp";
                    break;
                case "addgroup":
                    addGroupCourse(request,response);
                    return;
                case "modifiedPage":
                    getSelectInfo(request,response);
                    forwardPath = modify(request , response);
                    break;
                case "modified":
                    modifyGroupCourse(request,response);
                    return;
                case "getByClassType":
                    System.out.println(request.getRequestURI());
                    System.out.println("====================");
                    getGroupCourseByClassType(request, response);
                    return;
                default:
                    forwardPath = "/classtype/select_page.jsp";
            }
        }else{
            forwardPath = "/classtype/select_page.jsp";
        }
        request.getRequestDispatcher(forwardPath).forward(request,response);
    }

//    根據獲取的ClassType，去尋找所有有對應的GroupCourse資料
    private void getGroupCourseByClassType(HttpServletRequest request, HttpServletResponse response) throws IOException {



        request.setCharacterEncoding("UTF-8");

        String classtype = request.getParameter("classType");

        List<GroupCourse> groupCourseList = groupCourseService.getAllbyCtNo(Integer.valueOf(classtype));
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groupCourseList);
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(json);
    }

//    取得ClassType 與 Skill 所有資料 顯示在Select上
    private void getSelectInfo(HttpServletRequest request , HttpServletResponse response){

        ClassTypeService classTypeService = new ClassTypeServiceImpl();
        List<ClassType> allClassTypes = classTypeService.getAllClassTypes();
        SkillService skillService = new SkillServiceImpl();
        List<Skill> allSkill = skillService.getAllSkill();

        request.setAttribute("classTypes" , allClassTypes);
        request.setAttribute("skills", allSkill);

    }

//    新增GroupCourse資料
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

// 進入修改資料頁面時，先把資料讀取回來進行修改
    private String modify(HttpServletRequest request ,HttpServletResponse response){

        Integer id = Integer.valueOf(request.getParameter("id"));
        GroupCourse groupCourse = groupCourseService.findGroupCourseByNo(id);
        request.setAttribute("groupCourse" , groupCourse);


        return "/groupcourse/modifyGroupCourse.jsp";
    }

//  修改GroupCourse資料

    private void modifyGroupCourse(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {

        Integer gcNo = Integer.valueOf(request.getParameter("groupCourseNo"));

        SkillService skillService = new SkillServiceImpl();
        Integer skillNo = Integer.valueOf(request.getParameter("skill"));
        Skill skill = skillService.findBySkillNo(skillNo);

        ClassTypeService classTypeService = new ClassTypeServiceImpl();
        Integer ctNo = Integer.valueOf(request.getParameter("classType"));
        ClassType classTypeByNO = classTypeService.findClassTypeByNO(ctNo);


        byte[] bytes = null;

        Part filePart = request.getPart("photo");
        if (filePart != null && filePart.getSize() > 0){

            bytes = PartParsebyte.partToByteArray(filePart);

        }else{
            bytes = groupCourseService.getPhotoById(Integer.valueOf(request.getParameter("groupCourseNo")));
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
