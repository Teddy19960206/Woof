package com.woof.classtype.controller;

import com.woof.classtype.service.ClassTypeService;
import com.woof.classtype.service.ClassTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/classType")
public class ClassTypeServlet extends HttpServlet {
	
	private ClassTypeService classTypeService;
	
	@Override
	public void init() throws ServletException{
		classTypeService = new ClassTypeServiceImpl();
	}
	

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        
        System.out.println(classTypeService.getAllClassTypes());
        
    }
    
}
