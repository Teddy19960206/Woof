package com.woof.groupcourse.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
                getSelectInfo(request,response);
                forwardPath = "/backend/course/addGroupCourse.jsp";
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
            case "/getAll":
                getAll(request , response);
                return;
            case "/getUpStatusCourse":
                getUpStatusCourse(request , response);
                return;
            default:
//                進入edit畫面根據取得的id去抓取要修改的資料
                if (pathInfo.startsWith("/edit/")) {
                    getSelectInfo(request,response);
                    forwardPath = edit(request , response , result);
                } else if (pathInfo.startsWith("/delete/")) {
                    delete(request , response , result);
                    return;
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

        String classTypeStr = request.getParameter("classType");
        Integer classType = (classTypeStr.length() == 0 ) ? null : Integer.valueOf(classTypeStr);
        String statusStr = request.getParameter("status");
        Integer status = (statusStr.length() == 0) ? null : Integer.valueOf(statusStr);
        String page = request.getParameter("page");

        int currentPage = (page == null) ? 1 : Integer.parseInt(page);

        List<GroupCourse> groupCourseList = groupCourseService.getAllGroupCourse(classType , status ,currentPage);


        int pageTotal = groupCourseService.getPageTotal(classType , status);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("pageTotal", pageTotal);
        jsonResponse.add("data", gson.toJsonTree(groupCourseList));

        response.setContentType("application/json;charset=UTF-8");

//        System.out.println(pageTotal);
        response.getWriter().write(jsonResponse.toString());
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
            bytes = groupCourseService.getPhotoById(request.getParameter("groupCourseNo"));
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

    private void delete(HttpServletRequest request , HttpServletResponse response , Integer id) throws IOException {
        groupCourseService.deletePhoto(id);

        response.getWriter().write("ok");
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<GroupCourse> allGroupCourse = groupCourseService.getAllGroupCourse();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();

        String json = gson.toJson(allGroupCourse);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    private void getUpStatusCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<GroupCourse> allGroupCourse = groupCourseService.getUpStatusCourse();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();

        String json = gson.toJson(allGroupCourse);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}
