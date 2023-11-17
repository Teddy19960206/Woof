<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>

    <script src='${pageContext.request.contextPath}/webutil/js/index.global.min.js'></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backend/course/css/calendar.css" />

    <title>寵毛導師 Woof | 新增日期</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>

<h1 class="pt-3">新增日期</h1>

<div id='calendar'></div>

<div class="container my-2">
    <div class="col-auto mx-auto text-center">
        <button type="button" class="btn btn-primary" id="confirm">確定新增</button>
        <button type="button" class="btn btn-secondary" onclick="window.location.href= `${pageContext.request.contextPath}/backend/course/schedule.jsp`">取消新增</button>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">確定預約該日期嗎?</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center">
                <div id="showDate"></div>
                <div id="hideDate" style="display:none"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="myModal.hide();">取消</button>
                <button type="button" class="btn btn-primary" id="reserveBtn">確定預約</button>
            </div>
        </div>
    </div>
</div>


<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/addDetail.js"></script>
</body>
</html>
