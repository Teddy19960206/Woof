package com.woof.classtype.controller;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String classtype = req.getParameter("Type");


        Set<GroupCourse> groupCourseByCtNo = classTypeService.getGroupCourseByCtNo(Integer.valueOf(classtype));
        req.setAttribute("groupCourseByCtNo", groupCourseByCtNo);
        req.getRequestDispatcher("groupcourse/showgroup.jsp").forward(req, resp);


    }
	

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }

}
