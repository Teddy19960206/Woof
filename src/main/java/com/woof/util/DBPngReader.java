package com.woof.util;


import com.woof.AppService;
import com.woof.groupcourse.service.GroupCourseServiceImpl;
import com.woof.member.service.MemberServiceImpl;
import com.woof.administrator.service.AdministratorServiceImpl;

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
            case "administrator":
            	appService = new AdministratorServiceImpl();
            	break;
        }

        String id = request.getParameter("id").trim();
        byte[] picture = null;
        try {
            picture = appService.getPhotoById(id);
            response.setContentType("image/png");
            ServletOutputStream out = response.getOutputStream();
            out.write(picture);
        }catch (NullPointerException e){
            System.out.println("該會員沒有圖片");
        }

    }
}
