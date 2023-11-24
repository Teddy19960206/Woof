<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/meta.file"%>
    <title>寵毛導師 Woof | 團體報名訂單管理</title>
</head>

<body>
<%@ include file="body.jsp"%>
<style>
    .table th , .table td{
        border: 0px solid transparent;
        text-align: center;
        padding: 0;
        padding-top: 10px;
        padding-bottom: 10px;
    }
    table{
        width: 100%;
        table-layout: auto; /* 使列寬度更加彈性 */
        margin-left: auto;
        margin-right: auto;
    }
</style>
    <div class="col-12 col-md-8">
        <div class="card">
            <div class="card-body">
                <h3 class="card-title text-center p-2">團體課程訂單</h3>
                <table class="table table-bordered table-hover text-center align-content-center align-middle" id="show">
                    <thead>
                        <tr>
                            <th>訂單編號</th>
                            <th>課程班別</th>
                            <th>購買時間</th>
                            <th>付款方式</th>
                            <th>訂單狀態</th>
                            <th>訂單詳情</th>
                        </tr>

                    </thead>
                    <tbody>

                    <jsp:useBean id="all" class="com.woof.groupcourseorder.service.GroupCourseOrderServiceImpl" />
                    <c:forEach items="${all.getAll(null,null,sessionScope.member.memName,0)}" var="order">
                        <tr>
<%--                            <td class="truncated-text">${all.formatOrderId(order.gcoNo)}</td>--%>
<%--                            <td>${order.groupCourseSchedule.groupCourse.classType.ctName} : ${order.groupCourseSchedule.groupCourse.skill.skillName}</td>--%>
<%--                            <td>${order.gcoDate}</td>--%>
<%--                            &lt;%&ndash;  0:信用卡 1:匯款    &ndash;%&gt;--%>
<%--                            <td>${order.gcoPaymentMethod == 0 ? '信用卡' : order.gcoPaymentMethod == 1 ? '匯款' : "綠界"}</td>--%>
<%--                            &lt;%&ndash; 0:未付款 1:已付款 2:已退款 3.已取消 4. 已完成 5.退款申請中 &ndash;%&gt;--%>
<%--                            <td>${order.gcoStatus == 0 ? '未付款' : order.gcoStatus == 1 ? '已付款' : order.gcoStatus == 2 ? '已退款'--%>
<%--                                : order.gcoStatus == 3 ? '已取消' : order.gcoStatus == 4 ? '已完成' : '退款申請中'}</td>--%>
<%--                            <td><button type="button" class="btn btn-primary detail-button" data-id=${order.gcoNo}>詳情</button></td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div id="showBtn" class="text-center" style="display: none">
                    <button type="button" class="btn btn-danger" id="refundOrder">申請退款</button>
                    <button type="button" class="btn btn-primary" onclick="window.location.href=`${pageContext.request.contextPath}/frontend/member/login/groupOrder.jsp`">回上一頁</button>
                </div>
            </div>
        </div>
    </div>
<%@ include file="foot.jsp"%>

<script src="${pageContext.request.contextPath}/frontend/member/js/groupOrder.js"></script>
</body>
</html>
