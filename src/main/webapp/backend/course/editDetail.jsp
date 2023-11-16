<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 修改上課資訊</title>
    <script src='${pageContext.request.contextPath}/webutil/js/index.global.min.js'></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backend/course/css/calendar.css" />
</head>
<body>
<%@ include file="/backend/backbody.file" %>
<input type="hidden" id="detailNo" value="${param.id}">
<input type="hidden" id="trainer" value="${param.trainer}">

<h1 align="center" class="py-5">修改上課資訊</h1>
<div id='calendar' class="p-3"></div>

<div class="container" id="next">
    <div class="row d-flex justify-content-center">
        <div class="col-auto">
            <button type="button" class="btn btn-primary" onclick="change()">下一步</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn btn-secondary" onclick="window.location.href=`${pageContext.request.contextPath}/backend/course/schedule.jsp`">取消修改</button>
        </div>
    </div>
</div>

<div class="container" id="confirm" style="display: none; position: relative; top: 20vh">

    <div class="row d-flex justify-content-center">
        <h2 class="text-center">請選擇該日期上課訓練師</h2>
        <div id="showSelect" class="col-auto">

        </div>
        <div class="col-auto">
            <button type="button" class="btn btn-primary modify-button">確認修改</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn btn-secondary" onclick="window.location.href=`${pageContext.request.contextPath}/backend/course/schedule.jsp`">取消修改</button>
        </div>
    </div>
</div>

<div style="display: none">
    <div id="year"></div>
    <div id="month"></div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">確定修改該日期嗎?</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center">
                <div id="showDate"></div>
                <div id="hideDate" style="display:none"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="myModal.hide();">取消</button>
                <button type="button" class="btn btn-primary" id="reserveBtn">確定修改</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/editDetail.js"></script>
</body>
</html>
