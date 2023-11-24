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
<div id="memberName" style="display: none">${sessionScope.member.memName}</div>
<div class="col-12 col-md-8">
    <div class="card">
        <div class="card-body">
            <h3 class="card-title text-center p-2">團體課程訂單</h3>
            <div id="show"></div>
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
