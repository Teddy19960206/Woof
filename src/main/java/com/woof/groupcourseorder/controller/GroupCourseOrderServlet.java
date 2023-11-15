package com.woof.groupcourseorder.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.groupcourseorder.service.GroupCourseOrderService;
import com.woof.groupcourseorder.service.GroupCourseOrderServiceImpl;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupcourseschedule.service.GroupGourseScheduleService;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.groupscheduledetail.service.GroupScheduleDetailService;
import com.woof.groupscheduledetail.service.GroupScheduleDetailServiceImpl;
import com.woof.member.entity.Member;
import com.woof.util.*;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;


@WebServlet("/groupOrder/*")
@MultipartConfig
public class GroupCourseOrderServlet extends HttpServlet {

    GroupCourseOrderService groupCourseOrderService;

    public static AllInOne all;

    @Override
    public void init() throws ServletException {
        groupCourseOrderService = new GroupCourseOrderServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request , response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();

        int secondSlashIndex = pathInfo.indexOf('/', 2);
        Integer result = null;
        if (secondSlashIndex > 0){
            result = Integer.valueOf(pathInfo.substring(secondSlashIndex + 1));
        }

        String forwardPath = "";
        switch (pathInfo){
            case "/getOneOrder":
//               取得單筆訂單
                getOneOrder(request , response);
                return;
            case "/getOrder":
//               取得訂單列表(複合查詢 + 分頁)
                getOrder(request , response);
                return;
            case "/check":
//                報名
                check(request , response);
                return;
            case "/refund":
//                退款
                refund(request , response);
                return;
            case "/refundAllBySchedule":
//                該課程全部退款
                refundAllBySchedule(request ,response);
                return;
            case "/modify":
//                修改訂單
                modify(request, response);
                return;
            default:
                if (pathInfo.startsWith("/getGroupInfo/")) {
                    forwardPath = getGroupInfo(request ,response ,result);
                } else if (pathInfo.startsWith("/ecpay/")) {
                    ecpay(request ,response , result);
                    return;
                } else {
                    forwardPath = "/index.jsp";
                }
        }

        request.getRequestDispatcher(forwardPath).forward(request, response);
    }

    private String getGroupInfo(HttpServletRequest request , HttpServletResponse response , Integer gcsNo){


        GroupGourseScheduleService groupCourseScheduleService = new GroupCourseScheduleServiceImpl();
        GroupCourseSchedule groupCourseSchedule = groupCourseScheduleService.findByGcsNo(gcsNo);

        request.setAttribute("groupScheduleNo" , gcsNo);
        request.setAttribute("price" , groupCourseSchedule.getGcsPrice());


        String className = groupCourseSchedule.getGroupCourse().getClassType().getCtName() +" - "+ groupCourseSchedule.getGroupCourse().getSkill().getSkillName();
        request.setAttribute("className" , className);

        return "/frontend/group/registration.jsp";
    }

    private void getOrder(HttpServletRequest request , HttpServletResponse response) throws IOException {

        String groupClassStr = request.getParameter("groupClass");
        Integer groupClass = (groupClassStr == null ||  groupClassStr.length() == 0 ) ? null : Integer.valueOf(groupClassStr);
//        0:未付款 1:已付款 2:已退款 3.已取消 4.已完成
//        已取消，僅有使用匯款的人會有此狀態
//        到了截止日期的一天後且尚未匯款，排程器會改變狀態變成 < 已取消 >
        String statusStr = request.getParameter("selectStatus");
        Integer status = (statusStr == null ||  statusStr.length() == 0 ) ? null : Integer.valueOf(statusStr);

        String memNoStr = request.getParameter("memNo");
        String memNo = (memNoStr == null || memNoStr.trim().length() == 0) ? null : memNoStr;

        String page = request.getParameter("page");
        Integer currentPage = (page == null) ? 1 : Integer.parseInt(page);

        List<GroupCourseOrder> groupCourseOrderList = groupCourseOrderService.getAll(groupClass, status, memNo, currentPage);

        int pageTotal = groupCourseOrderService.getPageTotal(groupClass , status , memNo);


        Gson gson = new GsonBuilder()
                .setExclusionStrategies()
                .addSerializationExclusionStrategy(new JsonIgnoreExclusionStrategy(true))
                .addDeserializationExclusionStrategy(new JsonIgnoreExclusionStrategy(false))
                .setDateFormat("yyyy-MM-dd")
                .create();

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("pageTotal" , pageTotal);
        jsonResponse.add("data" , gson.toJsonTree(groupCourseOrderList));


        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonResponse.toString());
    }

    synchronized private void check(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {

//        取得所有資訊
        Member member = (Member) request.getSession(false).getAttribute("member");
        String smmpCount = request.getParameter("smmpCount");
        String email = request.getParameter("email");
        Integer actualAmount = Integer.valueOf(request.getParameter("actualAmount"));
        Integer payment = Integer.valueOf(request.getParameter("payment"));

        Integer groupScheduleNo = Integer.valueOf(request.getParameter("GroupScheduleNo"));

        GroupGourseScheduleService groupGourseScheduleService = new GroupCourseScheduleServiceImpl();
        GroupCourseSchedule groupCourseSchedule = groupGourseScheduleService.findByGcsNo(groupScheduleNo);

        List<String> errorMsgs = new LinkedList<>();

        request.setAttribute("errorMsgs", errorMsgs);

        Integer smmp = 0;
        if (smmpCount != null){
            smmp = Integer.valueOf(smmpCount);
            if (member.getMomoPoint() < smmp){
                errorMsgs.add("超出所擁有的毛毛幣");
            }
        }

        if (email == null || email.trim().length() == 0){
            errorMsgs.add("信箱請勿空白");
        } else if (!EmailValidator.isValidEmail(email)) {
            errorMsgs.add("信箱格式錯誤");
        }

//         參加報名時，判斷人數是否已達上限
        if (groupCourseSchedule.getRegCount() >= groupCourseSchedule.getMaxLimit()){
            errorMsgs.add("人數報名已達上限");
        }else {
//            增加報名人數
            groupGourseScheduleService.registrationSchedule(groupScheduleNo);
        }

        if (!errorMsgs.isEmpty()){
            request.getRequestDispatcher("/groupOrder/getGroupInfo/1")
                    .forward(request, response);
            return;
        }

//        使用信用卡0 預設為已付款1
//        使用匯款 1 預設為未付款0
//        使用綠界 2 預設為已付款1
        Integer status = 0;
        if (payment == 0){
            status = 1;
        }



        Integer orderNo = null;
        try{
//          新增Order
            orderNo = groupCourseOrderService.addOrder(member, groupCourseSchedule, payment, smmp, actualAmount, status);


            if (payment == 2){
                // 獲取協議（http或https）
                String scheme = request.getScheme();
                // 獲取主機名
                String host = request.getServerName();
                // 獲取端口號
                int port = request.getServerPort();

                // 構建完整的URL
                String fullURL = scheme + "://" + host + ":" + port;



                all = new AllInOne("");

                AioCheckOutOneTime obj = new AioCheckOutOneTime();
                obj.setMerchantTradeNo(orderNo.toString()); // 該位數為上限
                obj.setMerchantTradeDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                obj.setTotalAmount(actualAmount.toString());
                obj.setTradeDesc("寵毛導師：團體課程報名");
                obj.setItemName(groupCourseSchedule.getGroupCourse().getSkill().getSkillName() + groupCourseSchedule.getGroupCourse().getSkill().getSkillName());
                obj.setReturnURL(fullURL+request.getContextPath()+"/index.jsp");
                obj.setNeedExtraPaidInfo("N");
                obj.setRedeem("N");

//                要重新更新訂單狀態
                obj.setClientBackURL(fullURL+request.getContextPath()+"/groupOrder/ecpay/"+orderNo);

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(all.aioCheckOut(obj , null));
                return;
            }


//          取得上課日期
            List<GroupScheduleDetail> details = new GroupScheduleDetailServiceImpl().getByGroupSchedule(groupCourseSchedule.getGcsNo());
            Set<Date> dates = new HashSet<>();
            for (GroupScheduleDetail detail : details){
                dates.add(detail.getClassDate());
            }


            MailService mailService = new MailService();

            new Thread(() -> mailService.sendMail("trick95710@gmail.com" ,
                    "報名成功" ,
                    MailService.groupOrderhtml(member.getMemName() ,                            // 報名人姓名
                            groupCourseSchedule.getGroupCourse().getClassType().getCtName(),    // 班級名稱
                            dates,                                                              // 上課日期
                            groupCourseSchedule.getGroupCourse().getCourseContent()))).start(); // 課程內容



        }catch (Exception e){
            e.printStackTrace();
            AppLogger.getLogger().log(Level.ALL, "發生例外，新增失敗：" + e);
        }
        request.getSession().setAttribute("orderNo" , orderNo);
        response.sendRedirect(request.getContextPath()+"/frontend/group/orderPage.jsp");
    }

    private void getOneOrder(HttpServletRequest request , HttpServletResponse response) throws IOException {
        Integer orderNo = Integer.valueOf(request.getParameter("orderNo"));
        GroupCourseOrder order = groupCourseOrderService.getOneOrder(orderNo);


        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();

        String json = gson.toJson(order);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    private void refund(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        Integer id= null;
        List<String> errorMsgs = new ArrayList<>();

        if (idStr == null || idStr.trim().length() == 0){
            errorMsgs.add("沒有取得訂單編號");
        }else{
            id = Integer.valueOf(idStr);
        }

        response.setContentType("application/json;charset=UTF-8");

        if (!errorMsgs.isEmpty()){
            Gson gson = new Gson();
            String json = gson.toJson(errorMsgs);
            response.getWriter().write(json);
            return;
        }
//        變更狀態成已退款
        groupCourseOrderService.refund(id);

        response.getWriter().write("{ \"message\" : \"更新成功\"}");
    }

    private void modify(HttpServletRequest request , HttpServletResponse response) throws IOException {


        List<String> errorMsgs = new ArrayList<>();
        Integer id = null;
        Integer status = null;

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().length() == 0){
            errorMsgs.add("取得不到訂單編號");
        }else{
            id = Integer.valueOf(idStr);
        }

        String statusStr = request.getParameter("status");
        if (statusStr == null || statusStr.trim().length() == 0){
            errorMsgs.add("取得不到訂單狀態");
        }else{
            status = Integer.valueOf(statusStr);
        }


        response.setContentType("application/json;charset=UTF-8");
        Gson gson = new Gson();
        String json = null;
        if (!errorMsgs.isEmpty()){
            json = gson.toJson(errorMsgs);
            response.getWriter().write(json);
            return;
        }

        try{
            groupCourseOrderService.modify(id , status);
            response.getWriter().write("{ \"message\" : \"更新成功\"}");
            
        }catch (Exception e){
            errorMsgs.add("更新失敗");
            json = gson.toJson(errorMsgs);
            response.getWriter().write(json);
        }
    }

    public void ecpay(HttpServletRequest request ,HttpServletResponse response ,Integer gcoNo) throws IOException {

        groupCourseOrderService.modify(gcoNo, 1);

        MailService mailService = new MailService();

        GroupCourseOrder order = groupCourseOrderService.getOneOrder(gcoNo);

        Member member = (Member) request.getSession().getAttribute("member");

        List<GroupScheduleDetail> details = new GroupScheduleDetailServiceImpl().getByGroupSchedule(order.getGroupCourseSchedule().getGcsNo());
        Set<Date> dates = new HashSet<>();
        for (GroupScheduleDetail detail : details){
            dates.add(detail.getClassDate());
        }

        new Thread(() -> mailService.sendMail("trick95710@gmail.com" ,
                "報名成功" ,
                MailService.groupOrderhtml(member.getMemName() ,                            // 報名人姓名
                        order.getGroupCourseSchedule().getGroupCourse().getClassType().getCtName(),    // 班級名稱
                        dates,                                                              // 上課日期
                        order.getGroupCourseSchedule().getGroupCourse().getCourseContent()))).start(); // 課程內容

        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    private void refundAllBySchedule(HttpServletRequest request , HttpServletResponse response) throws IOException {
        List<String> errorMsgs = new ArrayList<>();
        Integer gcsNo = null;
//        改變 status to 已退款 (2)
        String gcsNoStr = request.getParameter("gcsNo");

        if (gcsNoStr == null || gcsNoStr.trim().length() == 0){
            errorMsgs.add("沒有該課程編號");
        }else{
            gcsNo = Integer.valueOf(gcsNoStr);
        }

        response.setContentType("application/json;charset=UTF-8");

        try(Jedis jedis = JedisUtil.getResource()){
            groupCourseOrderService.modifyAllOrderByGcsNo(gcsNo);
            Long deleteCount = jedis.hdel("schedules" , gcsNoStr);

//            將該課程狀態改變成 已取消(3)
            new GroupCourseScheduleServiceImpl().updateStatus(3 , gcsNo);
            response.getWriter().write("{ \"message\" : \"全部已退款\"  }");

        }catch (Exception e){

            response.getWriter().write("{ \"退款失敗\"}");
        }
    }
}