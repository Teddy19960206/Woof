<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/meta.file"%>
    <title>寵毛導師 Woof | 團體報名訂單管理</title>
</head>
<style>
    .table {
        border-collapse: collapse; /* 確保邊框合併 */
    }

    .table th, .table td {
        border: 1px solid #ddd; /* 加強邊框 */
        padding: 8px; /* 增加內距 */
        text-align: left; /* 左對齊文本 */
    }

    .table th {
        background-color: #f8f9fa; /* 標題欄的背景色 */
        color: #333; /* 標題欄的文本顏色 */
    }

    .table tr:nth-child(even) {
        background-color: #f2f2f2; /* 每兩行使用不同的背景色 */
    }

    .table tr:hover {
        background-color: #ddd; /* 懸停時改變背景色 */
    }
</style>
<style>
    body {
        background-color: #FFF3E0;
    }

    .card {
        border: none;
        border-radius: 20px;
        background-color: #f8f9fa;
    }

    .card-header {
        border: none;
        border-radius: 20px;
        background-color: SandyBrown;
    }

    .profile-img {
        width: 100px;
        height: 100px;
        border-radius: 50%;
    }

    .icon-btn {
        font-size: 1.5rem;
        color: #495057;
    }

    .icon-btn:hover {
        color: #0056b3;
    }

    .update-btn {
        width: 50px; /* or whatever width you want */
    }
    /* 子選單樣式 */
    #orderManagementOptions, #courseManagementOptions,
    #shopManagementOptions {
        padding-left: 0px; /* 增加左側內距 */
        background-color: orange; /* 與主選單區分的背景色 */
    }

    .left-icon {
        text-align: left; /* 使內容靠右對齊 */
        margin-right: 20px; /* 右邊距，可依需求調整 */
    }

    .custom-divider {
        margin-top: 0px; /* 上方間隔 */
        margin-bottom: 0px; /* 下方間隔 */
        border: 0; /* 移除邊框 */
        border-top: 1px solid #ccc; /* 設置頂部邊框，可調整顏色和粗細 */
    }

    a {
        color: inherit; /* 使超連結的顏色與其父元素相同 */
        text-decoration: none; /* 去除底線 */
    }
    /* 當按鈕被點擊或懸停時的顏色變化 */
    .accordion-button:not(.collapsed) {
        background-color: #FFAA77; /* 按鈕展開時的背景色，這裡使用了稍微深一點的橘色 */
        color: #333;
    }

    .accordion-button:hover {
        background-color: #FFBB88; /* 按鈕懸停時的背景色 */
        color: #333;
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
            <div class="accordion" id="accordionExample">
                <!-- 用戶資訊項目 -->
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button"
                                data-bs-toggle="collapse" data-bs-target="#collapseUserInfo"
                                aria-expanded="true" aria-controls="collapseUserInfo">
                            您好，
                            <c:out value="${member.memName}！" />
                        </button>
                    </h2>
                    <div id="collapseUserInfo"
                         class="accordion-collapse collapse show"
                         data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <form method="post"
                                  action="${pageContext.request.contextPath}/member1.do"
                                  style="margin-bottom: 0px;">
                                <input type="hidden" name="action" value="update"> <input
                                    type="hidden" name="memNo" value="${member.memNo}"> <a
                                    onclick="processUpdate({memNo:'${member.memNo}'});"
                                    style="cursor: pointer;"> <i class="fa-solid fa-user"></i>&nbsp;修改基本資料
                            </a>
                            </form>
                        </div>
                    </div>
                    <hr class="custom-divider">
                    <div id="collapseUserInfo"
                         class="accordion-collapse collapse show"
                         data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <form method="post"
                                  action="${pageContext.request.contextPath}/member1.do"
                                  style="margin-bottom: 0px;">
                                <input type="hidden" name="action" value="delete"> <input
                                    type="hidden" name="memNo" value="${member.memNo}"> <a
                                    onclick="confirmDelete('${member.memNo}')"
                                    style="cursor: pointer;"> <i class="fa-solid fa-trash"></i>&nbsp;註銷會員
                            </a>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- 課程管理項目 -->
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button collapsed" type="button"
                                data-bs-toggle="collapse"
                                data-bs-target="#collapseCourseManagement" aria-expanded="false"
                                aria-controls="collapseCourseManagement">
                            <i class="fa-solid fa-school"></i>&nbsp;課程管理
                        </button>
                    </h2>
                    <div id="collapseCourseManagement"
                         class="accordion-collapse collapse"
                         data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <!-- 私人訓練師項目 -->
                            <div class="accordion-item">
                                <h2 class="accordion-header">
                                    <button class="accordion-button collapsed" type="button"
                                            data-bs-toggle="collapse"
                                            data-bs-target="#collapsePersonalTrainerOptions"
                                            aria-expanded="false"
                                            aria-controls="collapsePersonalTrainerOptions">
                                        </i> 私人訓練師
                                    </button>
                                </h2>
                                <div id="collapsePersonalTrainerOptions"
                                     class="accordion-collapse collapse"
                                     data-bs-parent="#collapseCourseManagement">
                                    <div class="accordion-body">
                                        <a href="${pageContext.request.contextPath}/frontend/member/login/classOrder.jsp">課程訂單管理</a>
                                    </div>
                                    <hr class="custom-divider">
                                    <div class="accordion-body">
                                        <a href="${pageContext.request.contextPath}/frontend/member/login/appointment.jsp">預約單管理</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr class="custom-divider">
                        <div class="accordion-body">
                            <a
                                    href="${pageContext.request.contextPath}/frontend/member/login/groupOrder.jsp">團體報名訂單管理</a>
                        </div>
                    </div>
                </div>

                <!-- 商城訂單查詢項目 -->
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button collapsed" type="button"
                                data-bs-toggle="collapse"
                                data-bs-target="#collapseShopOrderManagement"
                                aria-expanded="false" aria-controls="collapseShopOrderManagement">
                            <i class="fa-solid fa-shop"></i>&nbsp;商城訂單查詢
                        </button>
                    </h2>
                    <div id="collapseShopOrderManagement"
                         class="accordion-collapse collapse"
                         data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <a href="#">訂單查詢</a>
                        </div>
                    </div>
                    <hr class="custom-divider">
                    <!-- 新增分隔線，並加入自訂類別 -->
                    <div id="collapseShopOrderManagement"
                         class="accordion-collapse collapse"
                         data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <a href="#">訂單追蹤</a>
                        </div>
                    </div>
                    <hr class="custom-divider">
                    <!-- 新增分隔線，並加入自訂類別 -->
                    <div id="collapseShopOrderManagement"
                         class="accordion-collapse collapse"
                         data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <a href="#">商品退貨</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
