package com.woof.classtype.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.classtype.service.ClassTypeService;
import com.woof.classtype.service.ClassTypeServiceImpl;
import com.woof.groupcourse.entity.GroupCourse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet("/classType")
public class ClassTypeServlet extends HttpServlet {
	
	private ClassTypeService classTypeService;


	
	@Override
	public void init() throws ServletException{
		classTypeService = new ClassTypeServiceImpl();
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
	

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String classtype = request.getParameter("classType");

        Set<GroupCourse> groupCourseByCtNo = classTypeService.getGroupCourseByCtNo(Integer.valueOf(classtype));
//        req.setAttribute("groupCourseByCtNo", groupCourseByCtNo);
//        req.getRequestDispatcher("groupcourse/showgroup.jsp").forward(req, resp);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groupCourseByCtNo);
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(json);

        request.setAttribute("xxx" , groupCourseByCtNo);
        request.getRequestDispatcher("/xxxWeb.jsp").forward(request,response);

        request.getAttribute("xxx");

    }

}
