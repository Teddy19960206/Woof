package com.woof.groupcourseschedule.controller;

import com.alibaba.fastjson2.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourse.service.GroupCourseServiceImpl;
import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.groupcourseorder.service.GroupCourseOrderServiceImpl;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupcourseschedule.service.GroupGourseScheduleService;
import com.woof.groupscheduledetail.service.GroupScheduleDetailServiceImpl;
import com.woof.skill.service.SkillService;
import com.woof.skill.service.SkillServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerServiceImpl;
import com.woof.util.JedisUtil;
import com.woof.util.JsonIgnoreExclusionStrategy;
import com.woof.util.MailService;
import redis.clients.jedis.Jedis;


import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

@WebServlet("/schedule/*")
@MultipartConfig
public class GroupScheduleServlet extends HttpServlet {

    private GroupGourseScheduleService groupGourseScheduleService;

    @Override
    public void init() throws ServletException {
        groupGourseScheduleService = new GroupCourseScheduleServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

//       取得位址資訊
        String pathInfo = request.getPathInfo();

//       判斷若是 /edit/{:_id} 取得id
        int secondSlashIndex = pathInfo.indexOf('/', 2);
        Integer result = null;
        if (secondSlashIndex > 0) {
            result = Integer.valueOf(pathInfo.substring(secondSlashIndex + 1));
        }

        String forwardPath = "";

        switch (pathInfo) {
            case "/addpage":
//                進入新增頁面前，先撈取下拉是選項資料
                forwardPath = getSelectInfo(request, response , null);
                break;
            case "/addSchedule":
                addGroupSchedule(request, response);
                return;
            case "/getSchedule":
//                根據classType的選擇取得相對應的schedule資料
                getSchedule(request, response);
                return;
            case "/registration":
//                消費者點選報名時進行報名人數更新
                registration(request, response);
                return;
            case "/modified":
//                正式修改資料
                modified(request, response);
                return;
            case "/getListClass":
//                前台取得上架課程
                getListClass(request ,response);
                return;
            case "/getOffSechedule":
//                取得審核中的Schuedule
                getOffSechedule(request ,response);
                return;
            case "/addDelay":
//                延期、額外新增課程
                addDelay(request, response);
                return;
            case "/countInfo":
                countInfo(request ,response);
                return;
            case "/getNewsRedis":
                getNewsRedis(request , response);
                return;
            default:
                if (pathInfo.startsWith("/edit/")) {
//                   進入修改頁面前，先撈取下拉式選項資料
                    getSelectInfo(request, response , result);
//                   進入edit畫面根據取得的id去抓取要修改的資料
                    forwardPath = edit(request, response, result);
                } else {
//                    若是都不是以上位址，則預設取得全部資料，並轉回"/backend/course/schedule.jsp"
                    forwardPath = getAllSchedule(request, response);
                }
        }
        request.getRequestDispatcher(forwardPath).forward(request, response);
    }

    private String getAllSchedule(HttpServletRequest request, HttpServletResponse response) {

        List<GroupCourseSchedule> groupCourseScheduleList = groupGourseScheduleService.getAll();

        request.setAttribute("scheduleList", groupCourseScheduleList);

        return "/backend/course/schedule.jsp";
    }


    private void getSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String classTypeStr = request.getParameter("classType");
        Integer classType = (classTypeStr == null ||  classTypeStr.length() == 0 ) ? null : Integer.valueOf(classTypeStr);
        String statusStr = request.getParameter("status");
        Integer status = (statusStr == null || statusStr.length() == 0) ? null : Integer.valueOf(statusStr);
        String page = request.getParameter("page");

        Integer currentPage = (page == null) ? 1 : Integer.parseInt(page);

        List<GroupCourseSchedule> groupCourseScheduleSet  = groupGourseScheduleService.getAll(classType , status ,currentPage);

        int pageTotal = groupGourseScheduleService.getPageTotal(classType , status);

        Gson gson = new GsonBuilder()
                .setExclusionStrategies()
                .addSerializationExclusionStrategy(new JsonIgnoreExclusionStrategy(true))
                .addDeserializationExclusionStrategy(new JsonIgnoreExclusionStrategy(false))
                .setDateFormat("yyyy-MM-dd")
                .create();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pageTotal" , pageTotal);
        jsonObject.add("data" , gson.toJsonTree(groupCourseScheduleSet));

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonObject.toString());
    }

//   取得已下架的所有schedule
    private void getOffSechedule(HttpServletRequest request , HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        List<GroupCourseSchedule> offSchedule = groupGourseScheduleService.getOffSchedule();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String json = gson.toJson(offSchedule);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);

    }

    //    進入<新增>或<修改>頁面時，會先獲取select下拉式選單可選擇的資料，並到新增頁面
    private String getSelectInfo(HttpServletRequest request, HttpServletResponse response , Integer gcsNo) {

        List<GroupCourse> allGroupCourse = new GroupCourseServiceImpl().getAllGroupCourse();
        if (gcsNo != null){
            Integer skillNo = groupGourseScheduleService.findByGcsNo(gcsNo).getGroupCourse().getSkill().getSkillNo();
            SkillService skillService = new SkillServiceImpl();
            Set<Trainer> trainersBySkillNo = skillService.getTrainersBySkillNo(skillNo);

            request.setAttribute("trainers", trainersBySkillNo);
        }

        request.setAttribute("groupCourses", allGroupCourse);

        return "/backend/course/addSchedule.jsp";
    }

    //    進入修改頁面前，依照id撈取原先的資料
    private String edit(HttpServletRequest request, HttpServletResponse response, Integer id) {

        GroupCourseSchedule groupCourseSchedule = groupGourseScheduleService.findByGcsNo(id);

        request.setAttribute("schedule", groupCourseSchedule);

        return "/backend/course/editGroupSchedule.jsp";
    }

    //    消費者報名Schedule
    private void registration(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id = Integer.valueOf(request.getParameter("id"));
        groupGourseScheduleService.registrationSchedule(id);

        response.sendRedirect(request.getContextPath() + "/groupcourse/schedule?action=getAl");
    }

    //    修改Schedule
    private void modified(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        Long currentDate =  Calendar.getInstance().getTimeInMillis();
        Date startDate = null;
        Date endDate = null;
        Integer minLimit = null;
        Integer maxLimit = null;
        Integer price = null;
        String delayReason = null;
        GroupCourseSchedule byGcsNo = null;
        Integer regCount = null;

        List<String> errorMsgs = new LinkedList<>();
        request.setAttribute("errorMsgs" , errorMsgs);

        Integer scheduleNo = Integer.valueOf(request.getParameter("scheduleNo"));
        Integer content = Integer.valueOf(request.getParameter("groupCourse"));
        GroupCourse groupCourseByNo = new GroupCourseServiceImpl().findGroupCourseByNo(content);
        Trainer trainer = new TrainerServiceImpl().findTrainerByTrainerNo(Integer.valueOf(request.getParameter("trainer")));
        Integer status = Integer.valueOf(request.getParameter("status"));


//        驗證開始與結束日期
        String startDateStr = request.getParameter("startDate");
        if (startDateStr == null || startDateStr.trim().length() == 0) {
            errorMsgs.add("請選擇開始日期");
        } else {
            try {
                startDate = Date.valueOf(startDateStr);
                if (startDate.getTime() < currentDate) {
                    errorMsgs.add("請選擇大於今日的日期");
                }
            } catch (Exception e) {
                errorMsgs.add("開始日期格式錯誤");
            }
        }

        String endDateStr = request.getParameter("endDate");
        if (endDateStr == null || endDateStr.trim().length() == 0) {
            errorMsgs.add("請選擇結束日期");
        } else {
            try {
                endDate = Date.valueOf(endDateStr);
                if (endDate.getTime() < currentDate) {
                    errorMsgs.add("請選擇大於今日的日期");
                }
            } catch (Exception e) {
                errorMsgs.add("結束日期格式錯誤");
            }
        }

        if (startDate != null && endDate != null && startDate.getTime() > endDate.getTime()) {
            errorMsgs.add("開始日期不能大於結束日期");
        }

//        驗證報名上限人數與下限人數
        String minLimitStr = request.getParameter("minLimit");
        if (minLimitStr == null || minLimitStr.trim().length() == 0){
            errorMsgs.add("請輸入最少開課人數");
        }else{
            if (!minLimitStr.matches("\\d+")) {
                errorMsgs.add("最少開課人數應該是數字");
            } else {
                minLimit = Integer.valueOf(minLimitStr);
                if (minLimit > 100){
                    errorMsgs.add("最少開課人數不能超過100");
                }
            }
        }

        String maxLimitStr = request.getParameter("maxLimit");
        if (maxLimitStr == null || maxLimitStr.trim().length() == 0){
            errorMsgs.add("請輸入最多開課人數");
        }else{
            if (!maxLimitStr.matches("\\d+")) {
                errorMsgs.add("最多開課人數應該是數字");
            } else {
                maxLimit = Integer.valueOf(maxLimitStr);
                if (maxLimit > 100){
                    errorMsgs.add("最多開課人數不能超過100");
                }
            }
        }

        if (minLimit != null && maxLimit != null && minLimit > maxLimit){
            errorMsgs.add("最少開課人數不能大於最多開課人數");
        }

        String priceStr = request.getParameter("price");
        if (priceStr == null || priceStr.trim().length() == 0){
            errorMsgs.add("請輸入價格");
        }else{
            if (!priceStr.matches("\\d+")) {
                errorMsgs.add("價格應該是數字");
            } else {
                price = Integer.valueOf(priceStr);
            }
        }

        String relatedGcsNoStr = request.getParameter("relatedGcsNo");
        delayReason = request.getParameter("delayReason");

        if (relatedGcsNoStr != null && relatedGcsNoStr.trim().length() != 0){
//           獲取關聯的groupCourse物件，延期功能使用
            byGcsNo = groupGourseScheduleService.findByGcsNo(Integer.valueOf(relatedGcsNoStr));
            if (delayReason == null || delayReason.trim().length() == 0){
                errorMsgs.add("請輸入延期原因");
            }
        }else if (delayReason != null && delayReason.trim().length() != 0){
            if (relatedGcsNoStr == null || relatedGcsNoStr.trim().length() == 0){
                errorMsgs.add("請選擇關聯的延期課程");
            }
        }

//        驗證已報名人數
        String regCountStr = request.getParameter("regCount");

        if (regCountStr == null || regCountStr.trim().length() == 0){
            errorMsgs.add("已報名人數請勿空白");
        }else {
            regCount = Integer.valueOf(regCountStr);
        }

        if (!errorMsgs.isEmpty()){
            request.getRequestDispatcher("/schedule/edit/"+scheduleNo).forward(request,response);
            return;
        }


        try{
            int result = groupGourseScheduleService.updateSchedule(scheduleNo, groupCourseByNo, trainer, startDate, endDate, minLimit, maxLimit, regCount, price, status , delayReason ,byGcsNo);
            response.sendRedirect(request.getContextPath() + "/backend/course/schedule.jsp");
        }catch (Exception e){
            errorMsgs.add("修改失敗");
            request.getRequestDispatcher("/schedule/edit/"+scheduleNo).forward(request,response);
        }
    }

    //    新增 GroupSchedule 團體報名課程
    private void addGroupSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        GroupCourse groupCourse = null;
        Trainer trainer = null;
        Long currentDate =  Calendar.getInstance().getTimeInMillis();
        Date startDate = null;
        Date endDate = null;
        Integer minLimit = null;
        Integer maxLimit = null;
        Integer price = null;
        String delayReason = null;
        GroupCourseSchedule byGcsNo = null;


        List<String> errorMsgs = new LinkedList<>();
        request.setAttribute("errorMsgs" , errorMsgs);

//        取得groupCourse編號，並利用編號找到該物件
        String groupCourseStr = request.getParameter("groupCourse");
        if (groupCourseStr == null || groupCourseStr.trim().length() == 0){
            errorMsgs.add("請選擇課程");
        }else {
            groupCourse = new GroupCourseServiceImpl().findGroupCourseByNo(Integer.valueOf(groupCourseStr));
        }


//        取得 trainer 編號 ，並利用編號找到該物件
        String trainerStr = request.getParameter("trainer");
        if (trainerStr == null || groupCourseStr.trim().length() == 0){
            errorMsgs.add("請選擇訓練師");
        }else{
            trainer = new TrainerServiceImpl().findTrainerByTrainerNo(Integer.valueOf(trainerStr));
        }


//        驗證開始與結束日期
        String startDateStr = request.getParameter("startDate");
        if (startDateStr == null || startDateStr.trim().length() == 0) {
            errorMsgs.add("請選擇開始日期");
        } else {
            try {
                startDate = Date.valueOf(startDateStr);
                if (startDate.getTime() < currentDate) {
                    errorMsgs.add("請選擇大於今日的日期");
                }
            } catch (Exception e) {
                errorMsgs.add("開始日期格式錯誤");
            }
        }

        String endDateStr = request.getParameter("endDate");
        if (endDateStr == null || endDateStr.trim().length() == 0) {
            errorMsgs.add("請選擇結束日期");
        } else {
            try {
                endDate = Date.valueOf(endDateStr);
                if (endDate.getTime() < currentDate) {
                    errorMsgs.add("請選擇大於今日的日期");
                }
            } catch (Exception e) {
                errorMsgs.add("結束日期格式錯誤");
            }
        }

        if (startDate != null && endDate != null && startDate.getTime() > endDate.getTime()) {
            errorMsgs.add("開始日期不能大於結束日期");
        }

//        驗證報名上限人數與下限人數
        String minLimitStr = request.getParameter("minLimit");
        if (minLimitStr == null || minLimitStr.trim().length() == 0){
            errorMsgs.add("請輸入最少開課人數");
        }else{
            if (!minLimitStr.matches("\\d+")) {
                errorMsgs.add("最少開課人數應該是數字");
            } else {
                minLimit = Integer.valueOf(minLimitStr);
                if (minLimit > 100){
                    errorMsgs.add("最少開課人數不能超過100");
                }
            }
        }

        String maxLimitStr = request.getParameter("maxLimit");
        if (maxLimitStr == null || maxLimitStr.trim().length() == 0){
            errorMsgs.add("請輸入最多開課人數");
        }else{
            if (!maxLimitStr.matches("\\d+")) {
                errorMsgs.add("最多開課人數應該是數字");
            } else {
                maxLimit = Integer.valueOf(maxLimitStr);
                if (maxLimit > 100){
                    errorMsgs.add("最多開課人數不能超過100");
                }
            }
        }

        if (minLimit != null && maxLimit != null && minLimit > maxLimit){
            errorMsgs.add("最少開課人數不能大於最多開課人數");
        }

        String priceStr = request.getParameter("price");
        if (priceStr == null || priceStr.trim().length() == 0){
            errorMsgs.add("請輸入價格");
        }else{
            if (!priceStr.matches("\\d+")) {
                errorMsgs.add("價格應該是數字");
            } else {
                price = Integer.valueOf(priceStr);
            }
        }

//        String relatedGcsNoStr = request.getParameter("relatedGcsNo");
//        delayReason = request.getParameter("delayReason");
//
//        if (relatedGcsNoStr != null && relatedGcsNoStr.trim().length() != 0){
////           獲取關聯的groupCourse物件，延期功能使用
//            byGcsNo = groupGourseScheduleService.findByGcsNo(Integer.valueOf(relatedGcsNoStr));
//            if (delayReason == null || delayReason.trim().length() == 0){
//                errorMsgs.add("請輸入延期原因");
//            }
//        }else if (delayReason != null && delayReason.trim().length() != 0){
//            if (relatedGcsNoStr == null || relatedGcsNoStr.trim().length() == 0){
//                errorMsgs.add("請選擇關聯的延期課程");
//            }
//        }

        response.setContentType("application/json;charset=UTF-8");

//           若是填入有誤，則返回新增頁面，並顯示錯誤訊息
        if (!errorMsgs.isEmpty()){
            Gson gson = new Gson();
            String json = gson.toJson(errorMsgs);
            response.getWriter().write(json);
            return;
        }

        try{

//        同步新增多筆detail上課日期
            Set<Date> dates = new HashSet<>();
            String[] classDates = request.getParameter("classDate").split(",");

            for (String classDate : classDates){
                Date date = Date.valueOf(classDate);
                dates.add(date);
            }


            //        新增報名課程資訊
            GroupCourseSchedule groupCourseSchedule = groupGourseScheduleService.addSchedule(groupCourse, trainer, startDate, endDate, minLimit, maxLimit,0, price , 0, delayReason ,  byGcsNo);
            new GroupScheduleDetailServiceImpl().add(groupCourseSchedule , groupCourseSchedule.getTrainer() ,dates );

        }catch (Exception e){
            errorMsgs.add("新增檔期失敗");
            Gson gson = new Gson();
            String json = gson.toJson(errorMsgs);
            response.getWriter().write(json);

            return;
        }

//        成功回傳ok，做判斷

        response.getWriter().write("{ \"message\":\"新增成功\" }");
    }

    private void getListClass(HttpServletRequest request ,HttpServletResponse response){

//        1 :  幼犬班  2 : 成犬班
        Integer classType = Integer.valueOf(request.getParameter("classType"));

//        若是從前台發送 fetch 固定 status 為 1
        Integer status = Integer.valueOf(request.getParameter("status"));

//        status 狀態 1 為 上架狀態，代表可報名
        List<GroupCourseSchedule> listSchedule = groupGourseScheduleService.getListSchedule(classType, status);


        try {
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat("yyyy-MM-dd")
                    .create();

            String json = gson.toJson(listSchedule);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addDelay(HttpServletRequest request , HttpServletResponse response) throws IOException {

        Long currentDate =  Calendar.getInstance().getTimeInMillis();
        Date startDate = null;
        Date endDate = null;
        Integer id  = null;
        GroupCourseSchedule groupCourseSchedule = null;

        List<String> errorMsgs = new LinkedList<>();
        request.setAttribute("errorMsgs" , errorMsgs);

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().length() == 0){
            errorMsgs.add("沒有關聯的課程編號");
        }else{
            id = Integer.valueOf(idStr);
            groupCourseSchedule = new GroupCourseScheduleServiceImpl().findByGcsNo(id);
        }

        String reason = request.getParameter("reason");
        if (reason == null || reason.trim().length() == 0){
            errorMsgs.add("請填寫延期原因");
        }



//        驗證開始與結束日期
        String startDateStr = request.getParameter("startDate");
        if (startDateStr == null || startDateStr.trim().length() == 0) {
            errorMsgs.add("請選擇開始日期");
        } else {
            try {
                startDate = Date.valueOf(startDateStr);
                if (startDate.getTime() < currentDate) {
                    errorMsgs.add("請選擇大於今日的日期");
                }
            } catch (Exception e) {
                errorMsgs.add("開始日期格式錯誤");
            }
        }

        String endDateStr = request.getParameter("endDate");
        if (endDateStr == null || endDateStr.trim().length() == 0) {
            errorMsgs.add("請選擇結束日期");
        } else {
            try {
                endDate = Date.valueOf(endDateStr);
                if (endDate.getTime() < currentDate) {
                    errorMsgs.add("請選擇大於今日的日期");
                }
            } catch (Exception e) {
                errorMsgs.add("結束日期格式錯誤");
            }
        }

        if (startDate != null && endDate != null && startDate.getTime() > endDate.getTime()) {
            errorMsgs.add("開始日期不能大於結束日期");
        }


        Set<Date> dates = new HashSet<>();
        String[] classDates = request.getParameter("classDate").split(",");

        for (String classDate : classDates){
            Date date = Date.valueOf(classDate);
            dates.add(date);
        }


        response.setContentType("application/json;charset=UTF-8");
        if (!errorMsgs.isEmpty()){
            Gson gson = new Gson();
            String json = gson.toJson(errorMsgs);
            response.getWriter().write(json);
        }

        try {

//          新增新的schedule
            GroupCourseSchedule groupCourseScheduleNew = groupGourseScheduleService.addSchedule(
                    groupCourseSchedule.getGroupCourse(),
                    groupCourseSchedule.getTrainer(),
                    startDate,
                    endDate,
                    groupCourseSchedule.getMinLimit(),
                    groupCourseSchedule.getMaxLimit(),
                    groupCourseSchedule.getRegCount(),
                    groupCourseSchedule.getGcsPrice(),
                    1,                          //延期後，直接上架
                    reason,
                    groupCourseSchedule);

//         新增新的上課日期
            new GroupScheduleDetailServiceImpl().add(groupCourseScheduleNew , groupCourseSchedule.getTrainer() ,dates );


//         取得所有報名該課程的訂單
            List<GroupCourseOrder> orderBySchedule = new GroupCourseOrderServiceImpl().getOrderBySchedule(groupCourseSchedule.getGcsNo());
            for (GroupCourseOrder groupCourseOrder : orderBySchedule){

                new GroupCourseOrderServiceImpl().modifyOfGcoNo(groupCourseOrder , groupCourseScheduleNew);

                MailService mailService = new MailService();

                new Thread(()-> mailService.sendMail(groupCourseOrder.getMember().getMemEmail() ,
                        "課程延期" ,
                        MailService.groupOrderhtml( groupCourseOrder.getMember().getMemName()
                                ,groupCourseOrder.getGroupCourseSchedule().getGroupCourse().getClassType().getCtName()
                                ,dates,groupCourseOrder.getGroupCourseSchedule().getGroupCourse().getCourseContent()
                ))).start();

            }

//              更新舊課程狀態與報名人數
            groupGourseScheduleService.updateSchedule(
                    groupCourseSchedule.getGcsNo(),
                    groupCourseSchedule.getGroupCourse(),
                    groupCourseSchedule.getTrainer(),
                    groupCourseSchedule.getGcsStart(),
                    groupCourseSchedule.getGcsEnd(),
                    groupCourseSchedule.getMinLimit(),
                    groupCourseSchedule.getMaxLimit(),
                    0,
                    groupCourseSchedule.getGcsPrice(),
                    4,
                    groupCourseSchedule.getGcsDelayReason(),
                    groupCourseSchedule.getRelatedGcsNo());



            response.getWriter().write("{ \"message\":\"新增成功\" }");
        }catch (Exception e){
            e.printStackTrace();
            errorMsgs.add("異常");
            Gson gson = new Gson();
            String json = gson.toJson(errorMsgs);
            response.getWriter().write(json);
        }
    }
//    計算總共有幾筆資料
    private void countInfo(HttpServletRequest request , HttpServletResponse response) throws IOException {
        try(Jedis jedis = JedisUtil.getResource()){
            jedis.select(0);
            Long schedules = jedis.scard("schedules");

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(schedules.toString());
        }
    }
    private void getNewsRedis(HttpServletRequest request , HttpServletResponse response) throws IOException {

//        try (Jedis jedis = new Jedis()){
//            List<GroupCourseSchedule> all = groupGourseScheduleService.getAll();
//
//            Gson gson = new GsonBuilder()
//                    .excludeFieldsWithoutExposeAnnotation()
//                    .setDateFormat("yyyy-MM-dd")
//                    .create();
//
//
//            for (GroupCourseSchedule groupCourseSchedule : all){
//                jedis.sadd("schedules" , gson.toJson(groupCourseSchedule));
//            }
//        }




        List<Object> jsonObjects = new ArrayList<>();
        String json = null;
        try (Jedis jedis = JedisUtil.getResource()) {
            jedis.select(0);
            Set<String> schedules = jedis.smembers("schedules");

            for (String schedule : schedules){
                Object parse = JSON.parse(schedule);
                jsonObjects.add(parse);
            }


            Gson gson = new Gson();
            json = gson.toJson(jsonObjects);
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}


