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


@WebServlet("/groupcourse/*")
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
            case "/addpage":
//                預先載入可選擇的選項
                forwardPath = getSelectInfo(request,response);
                break;
            case "/addgroup":
//                正式增加GroupCourse資料
                addGroupCourse(request,response);
                return;
            case "/modified" :
//               正式修改資料
                modified(request,response);
                return;
            case "/getGroupCourse":
//               根據ClassType取得對應的GroupCourse
                getGroupCourse(request, response);
                return;
            default:
//                進入edit畫面根據取得的id去抓取要修改的資料
                if (pathInfo.startsWith("/edit/")) {
                    getSelectInfo(request,response);
                    forwardPath = edit(request , response , result);
                } else {
//                    若是都不是以上位址，則預設取得全部資料，並轉回"/classtype/classContent.jsp"
                    forwardPath = "/classtype/classContent.jsp";
                }
        }
        request.getRequestDispatcher(forwardPath).forward(request,response);
    }

//    根據獲取的ClassType，去尋找所有有對應的GroupCourse資料
    private void getGroupCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {



        request.setCharacterEncoding("UTF-8");

        String classtype = request.getParameter("classType");

        List<GroupCourse> groupCourseList = null;

        if (classtype != null){
            if ("0".equals(classtype)){
                groupCourseList = groupCourseService.getAllGroupCourse();
            }else{
                groupCourseList = groupCourseService.getAllbyCtNo(Integer.valueOf(classtype));
            }
        }else{
//            異常判斷
        }

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groupCourseList);
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(json);
    }

//    取得ClassType 與 Skill 所有資料 顯示在Select上
    private String getSelectInfo(HttpServletRequest request , HttpServletResponse response){

        ClassTypeService classTypeService = new ClassTypeServiceImpl();
        List<ClassType> allClassTypes = classTypeService.getAllClassTypes();


        SkillService skillService = new SkillServiceImpl();
        List<Skill> allSkill = skillService.getAllSkill();

        request.setAttribute("classTypes" , allClassTypes);
        request.setAttribute("skills", allSkill);

        return "/backend/course/addGroupCourse.jsp";
    }

//    新增GroupCourse資料
    private void addGroupCourse(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        Integer skillNo = Integer.valueOf(request.getParameter("skill"));
        SkillService skillService = new SkillServiceImpl();
        Skill skill = skillService.findBySkillNo(skillNo);


        ClassTypeService classTypeService = new ClassTypeServiceImpl();
        Integer ctNo = Integer.valueOf(request.getParameter("classType"));
        ClassType classTypeByNO = classTypeService.findClassTypeByNo(ctNo);

        Part filePart = request.getPart("photo");
        byte[] bytes = PartParsebyte.partToByteArray(filePart);
        String content = request.getParameter("content");


        int result = groupCourseService.addGroupCourse(skill, classTypeByNO, bytes, content);

        if ( result == 1){
            System.out.println("新增成功");
        }else{
            System.out.println("新增失敗");
        }

        response.sendRedirect(request.getContextPath()+"/backend/course/classContent.jsp");

    }

// 進入修改資料頁面時，先把資料讀取回來進行修改
    private String edit(HttpServletRequest request ,HttpServletResponse response , Integer id){

        GroupCourse groupCourse = groupCourseService.findGroupCourseByNo(id);
        request.setAttribute("groupCourse" , groupCourse);


        return "/backend/course/editGroupCourse.jsp";
    }

//  修改GroupCourse資料

    private void modified(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {

        Integer gcNo = Integer.valueOf(request.getParameter("groupCourseNo"));

        SkillService skillService = new SkillServiceImpl();
        Integer skillNo = Integer.valueOf(request.getParameter("skill"));
        Skill skill = skillService.findBySkillNo(skillNo);

        ClassTypeService classTypeService = new ClassTypeServiceImpl();
        Integer ctNo = Integer.valueOf(request.getParameter("classType"));
        ClassType classTypeByNO = classTypeService.findClassTypeByNo(ctNo);


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

        response.sendRedirect(request.getContextPath()+"/backend/course/classContent.jsp");

    }


}
