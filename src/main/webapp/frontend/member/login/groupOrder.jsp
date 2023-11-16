<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/meta.file"%>
    <title>寵毛導師 Woof | 團體報名訂單管理</title>
</head>
<style>
    body {
        background-color: #fff4e5; /* 淺橘色背景 */
    }

    .card {
        border: none;
        border-radius: 20px;
        background-color: #f8f9fa;
    }

    .card-header{
        border: none;
        border-radius: 20px;
        background-color:SandyBrown;
    }

    .list-group-item {
        padding: 10px 15px;
        border: none;
        border-bottom: 1px solid #ddd;
        background-color: #f8f9fa;
        cursor: pointer;
    }

    .list-group-item:hover {
        background-color: #e2e6ea; /* 輕微改變背景色 */
    }

    .list-group-item a {
        color: #007bff; /* 連結的顏色 */
        text-decoration: none; /* 移除底線 */
    }

    .list-group-item a:hover {
        color: #0056b3; /* 懸停時的顏色 */
    }
    /* 子選單樣式 */
    #orderManagementOptions, #courseManagementOptions ,#shopManagementOptions {
        padding-left: 0px; /* 增加左側內距 */
        background-color: #f0f0f0; /* 與主選單區分的背景色 */
    }
    .left-icon {
        text-align: left; /* 使內容靠右對齊 */
        margin-right: 20px; /* 右邊距，可依需求調整 */
    }

    th , td{
        text-align: center;
    }

    .truncated-text {
        max-width: 100px;  /* 或其他適當的寬度 */
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: pre-wrap;
    }
</style>
</style>
<body>
<script type="text/javascript">
    function toggleOrderManagement() {
        var orderoptions = document.getElementById('orderManagementOptions');
        toggleDisplay(orderoptions);
    }

    function toggleCourseManagement() {
        var courseOptions = document.getElementById('courseManagementOptions');
        var shopOrderOptions = document.getElementById('shopManagementOptions');
        toggleDisplay(courseOptions);
    }
    function toggleShopOrderManagement() {
        var shopOrderOptions = document.getElementById('shopManagementOptions');
        var courseOptions = document.getElementById('courseManagementOptions');
        toggleDisplay(shopOrderOptions);
    }

    function toggleDisplay(element) {
        if (element.style.display === 'none') {
            element.style.display = 'block';
        } else {
            element.style.display = 'none';
        }
    }
</script>
<script src="https://kit.fontawesome.com/3f37e88a3b.js" crossorigin="anonymous"></script>
<%@ include file="/Header.file"%>

<div class="container mt-5">
    <div class="row">
        <div class="col-12 col-md-4">
            <!-- 左側欄內容（導航欄） -->
            <div class="card">
                <div class="card-header">
                    <!-- 再次使用 sessionScope 來獲取用戶名 -->
                    您好，
                    <c:out value="${member.memName}！" />
                    ૮˙ﻌ˙ა
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <form method="post"
                              action="${pageContext.request.contextPath}/member1.do"
                              style="margin-bottom: 0px;">
                            <input type="hidden" name="action" value="update"> <input
                                type="hidden" name="memNo" value="${member.memNo}"> <a
                                onclick="processUpdate({memNo:'${member.memNo}'});"
                                style="cursor: pointer;">修改基本資料</a>
                        </form>
                    </li>
                    <li class="list-group-item"><a
                            onclick="toggleOrderManagement()" style="cursor: pointer;">訂單管理</a>
                        <div class="left-icon">
                            <ul id="orderManagementOptions" style="display: none;">
                                <li class="list-group-item"><a
                                        onclick="toggleCourseManagement()" style="cursor: pointer;">課程管理</a>
                                    <ul id="courseManagementOptions" style="display: none;">
                                        <li class="list-group-item"><a href="#">私人訓練師</a></li>
                                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/frontend/member/login/groupOrder.jsp">團體報名訂單管理</a></li>
                                    </ul></li>
                                <li class="list-group-item"><a
                                        onclick="toggleShopOrderManagement()" style="cursor: pointer;">商城訂單查詢</a>
                                    <ul id="shopManagementOptions" style="display: none;">
                                        <li class="list-group-item"><a href="#">訂單查詢</a></li>
                                        <li class="list-group-item"><a href="#">訂單追蹤</a></li>
                                        <li class="list-group-item"><a href="#">商品退貨</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-12 col-md-8">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title text-center p-2">團體課程訂單</h3>
                    <table class="table table-bordered table-hover text-center" id="show">
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
                        <c:forEach items="${all.getOrdersbyMember(sessionScope.member.memNo)}" var="order">
                            <tr>
                                <td class="truncated-text">${all.formatOrderId(order.gcoNo)}</td>
                                <td>${order.groupCourseSchedule.groupCourse.classType.ctName} : ${order.groupCourseSchedule.groupCourse.skill.skillName}</td>
                                <td>${order.gcoDate}</td>
                                <%--  0:信用卡 1:匯款    --%>
                                <td>${order.gcoPaymentMethod == 0 ? '信用卡' : order.gcoPaymentMethod == 1 ? '匯款' : "綠界"}</td>
                                <%-- 0:未付款 1:已付款 2:已退款 3.已取消 4. 已完成 5.退款申請中 --%>
                                <td>${order.gcoStatus == 0 ? '未付款' : order.gcoStatus == 1 ? '已付款' : order.gcoStatus == 2 ? '已退款'
                                    : order.gcoStatus == 3 ? '已取消' : order.gcoStatus == 4 ? '已完成' : '退款申請中'}</td>
                                <td><button type="button" class="btn btn-primary detail-button" data-id=${order.gcoNo}>詳情</button></td>
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

    </div>
</div>

<script src="${pageContext.request.contextPath}/frontend/member/js/groupOrder.js"></script>
</body>
</html>
