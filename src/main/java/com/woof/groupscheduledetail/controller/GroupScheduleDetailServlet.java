package com.woof.groupscheduledetail.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.groupscheduledetail.service.GroupScheduleDetailService;
import com.woof.groupscheduledetail.service.GroupScheduleDetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/scheduleDetail/*")
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

            default:
//                進入detail畫面根據取得的id去抓取要修改的資料
                if (pathInfo.startsWith("/detail/")) {
                    getdetail(request , response , result);
                    return;
                } else {
//                    若是都不是以上位址，則預設取得全部資料，並轉回"/classtype/classContent.jsp"
                    forwardPath = "/classtype/classContent.jsp";
                }
        }
        request.getRequestDispatcher(forwardPath).forward(request,response);
    }

    private void getdetail(HttpServletRequest request , HttpServletResponse response , Integer gcsNo) throws IOException {
        GroupScheduleDetailService groupScheduleDetailService1 = new GroupScheduleDetailServiceImpl();
        List<GroupScheduleDetail> byGroupSchedule = groupScheduleDetailService1.getByGroupSchedule(gcsNo);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(byGroupSchedule);
        response.setContentType("application/json;charset=UTF-8");
        System.out.println(json);
        response.getWriter().write(json);
    }
}
