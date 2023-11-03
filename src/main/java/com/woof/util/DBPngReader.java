package com.woof.util;


import com.woof.AppService;
import com.woof.groupcourse.service.GroupCourseServiceImpl;
import com.woof.member.service.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DBPngReader")
public class DBPngReader extends HttpServlet {

    private AppService appService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action){
            case "groupCourse":
                appService = new GroupCourseServiceImpl();
                break;
            case "member":
                appService = new MemberServiceImpl();
                break;
        }
        String id = request.getParameter("id").trim();

        byte[] picture = appService.getPhotoById(id);
        

        response.setContentType("image/png");
        ServletOutputStream out = response.getOutputStream();
        out.write(picture);
        
        
        
    }
    
    
}
