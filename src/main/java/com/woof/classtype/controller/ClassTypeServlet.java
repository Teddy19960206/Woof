package com.woof.classtype.controller;


import com.woof.classtype.entity.ClassType;
import com.woof.classtype.service.ClassTypeService;
import com.woof.classtype.service.ClassTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/classtype/*")
@MultipartConfig
public class ClassTypeServlet extends HttpServlet {

    private ClassTypeService classTypeService;


    @Override
    public void init() throws ServletException {
        classTypeService = new ClassTypeServiceImpl();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();

//       判斷若是 /edit/{:_id} 取得id
        int secondSlashIndex = pathInfo.indexOf('/', 2);
        Integer result = null;
        if (secondSlashIndex > 0) {
            result = Integer.valueOf(pathInfo.substring(secondSlashIndex + 1));
        }

        String forwardPath = "";

        switch (pathInfo) {
            case "/all":
//                獲取全部相關資料
                forwardPath = getAll(request, response);
                break;
            case "/addClassType":
//                新增ClassType
                addClassType(request, response);
                forwardPath = getAll(request, response);
                break;
            case "/modified":
//                正式修改資料
                modified(request, response);
                return;
            case "/delete":
//                刪除classType
                delete(request, response);
                return;
            default:
//                    若是都不是以上位址，則預設取得全部資料，並轉回"/backend/course/schedule.jsp"
                forwardPath = "/backend/course/classContent.jsp";

        }
        request.getRequestDispatcher(forwardPath).forward(request, response);
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    //      新增班別
    private void addClassType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String className = request.getParameter("className");
        classTypeService.addClassType(className);
    }

    //      修改班別名稱
    private void modified(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer classNo = Integer.valueOf(request.getParameter("ctNo"));
        String className = request.getParameter("ctName");

        classTypeService.updateClassType(classNo, className);

        response.setContentType("application/json");
        response.getWriter().print("{\"message\":\"更新成功\"}");
    }

    //      因為不用依照下拉式選單獲取相關資料，直接由 jsp 進到 servlet 獲取資料
//      取得全部classType資料
    private String getAll(HttpServletRequest request, HttpServletResponse response) {

        List<ClassType> allClassTypes = classTypeService.getAllClassTypes();

        request.setAttribute("classTypes", allClassTypes);

        return "/backend/course/classtype.jsp";
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer classNo = Integer.valueOf(request.getParameter("ctNo"));

        classTypeService.deleteClassType(classNo);

        response.setContentType("application/json");
        response.getWriter().print("{\"message\":\"刪除成功\"}");
    }
}
