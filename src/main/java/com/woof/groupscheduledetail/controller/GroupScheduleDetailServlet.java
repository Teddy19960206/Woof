package com.woof.groupscheduledetail.controller;

import com.woof.groupscheduledetail.service.GroupScheduleDetailService;
import com.woof.groupscheduledetail.service.GroupScheduleDetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/scheduleDetail")
@MultipartConfig
public class GroupScheduleDetailServlet extends HttpServlet {

    private GroupScheduleDetailService groupScheduleDetailService;
    @Override
    public void init() throws ServletException {
        groupScheduleDetailService = new GroupScheduleDetailServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request , response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

//       取得位址資訊
        String pathInfo = request.getPathInfo();

//       判斷若是 /edit/{:_id} 取得id
        int secondSlashIndex = pathInfo.indexOf('/', 2);
        Integer result = null;
        if (secondSlashIndex > 0){
            result = Integer.valueOf(pathInfo.substring(secondSlashIndex + 1));
        }

        String forwardPath = "";

        switch (pathInfo){

        }
        request.getRequestDispatcher(forwardPath).forward(request,response);
    }


}
