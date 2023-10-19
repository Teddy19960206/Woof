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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
	

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }


    private void getGroupCourse(HttpServletRequest request , HttpServletResponse response) throws IOException {

    }

    private void getGroupSchedule(HttpServletRequest request ,HttpServletResponse response) throws IOException{

    }
}
