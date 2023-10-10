package com.woof.classtype.controller;

import com.woof.classtype.service.ClassTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        ClassTypeService classTypeService = new ClassTypeService();
        classTypeService.addClassType("123");
        System.out.println(111);
    }
}
