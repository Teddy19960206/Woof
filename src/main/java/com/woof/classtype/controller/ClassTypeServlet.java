package com.woof.classtype.controller;


import com.woof.classtype.service.ClassTypeService;
import com.woof.classtype.service.ClassTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/classtype/*")
public class ClassTypeServlet extends HttpServlet {
	
	private ClassTypeService classTypeService;


	
	@Override
	public void init() throws ServletException{
		classTypeService = new ClassTypeServiceImpl();
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String action = request.getParameter("action");
//        String forwardPath ="";
//        if (action != null){
//            switch (action){
//                case "get":
//                    break;
//                default:
//                    forwardPath = "/classtype/classContent.jsp";
//            }
//        }else {
//            forwardPath = "/classtype/classContent.jsp";
//        }
//
//        request.getRequestDispatcher(forwardPath).forward(request,response);

    }
	

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String groupCourse = request.getParameter("action");
        switch (groupCourse){
            case "groupCourse":
                getGroupCourse(request,response);
                break;
            case "groupSchedule":
//                getGroupSchedule(request,response);
                break;
            default:
                break;
        }

    }


    private void getGroupCourse(HttpServletRequest request , HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        String classtype = request.getParameter("classType");

//        Set<GroupCourse> groupCourseByCtNo = classTypeService.getGroupCourseByCtNo(Integer.valueOf(classtype));
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//        String json = gson.toJson(groupCourseByCtNo);
//        response.setContentType("application/json;charset=UTF-8");

//        response.getWriter().write(json);
    }

    private void getGroupSchedule(HttpServletRequest request ,HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        String classtype = request.getParameter("classType");

//        classTypeService.getGroupCourseByCtNo(Integer.valueOf(classtype));
    }
}
