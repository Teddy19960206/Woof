<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>

    <script src='${pageContext.request.contextPath}/webutil/js/index.global.min.js'></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backend/course/css/calendar.css" />
    <title>寵毛導師 Woof | 延期</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>

<div id="show">

    <div class="position-absolute top-50 start-50 translate-middle">

        <div class="col-6 my-3">
            <label for="startDate">開始報名時間：<span id="startDateErr" style="color: red; display: none">錯誤</span></label>
            <input type="date" name="startDate" id="startDate" value="${schedule.gcsStart}" style="width: 500px" class="form-control"/>
        </div>
        <div class="col-6 my-3">
            <label for="endDate">結束報名時間：<span id="endDateErr" style="color: red; display: none">錯誤</span></label>
            <input type="date" name="endDate" id="endDate" value="${schedule.gcsEnd}" style="width: 500px" class="form-control"/>
        </div>

        <div class="col-12 mx-auto my-3">
            <label class="h2 text-center mx-auto">請輸入延期原因：<span id="reasonErr" style="color: red; display: none">錯誤</span></label>
        </div>

        <input type="text" id="reason" class="form-control" style="width: 500px"/>
        <div class="text-center my-5">
            <button type="button" id="next" class="btn btn-primary text-center">下一步</button>
        </div>
    </div>
</div>

<div id="calendarStr" class="h2 p-3 text-center text-primary" style="display: none; font-weight: 900">請選擇日期</div>
<div id='calendar'></div>
<div id="submitBtn" style="display: none; text-align: center" class="mt-5"><button type="button" id="add" class="btn btn-primary">送出</button></div>

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
<script src="${pageContext.request.contextPath}/backend/course/js/delaySchedule.js"></script>
</body>
</html>
