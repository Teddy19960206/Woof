package com.woof.util;


import com.woof.groupcourse.service.GroupCourseService;
import com.woof.groupcourse.service.GroupCourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;

@WebServlet("/DBPngReader")
public class DBPngReader extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("image/png");
        ServletOutputStream out = response.getOutputStream();

        String id = request.getParameter("id").trim();

        GroupCourseService groupCourseService = new GroupCourseServiceImpl();
        byte[] picture = groupCourseService.getPictureById(Integer.valueOf(id));

        out.write(picture);


    }
}
