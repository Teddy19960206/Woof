<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/meta.file" %>
    <script src='${pageContext.request.contextPath}/webutil/js/index.global.min.js'></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backend/course/css/calendar.css" />
    <title>寵毛導師 Woof | 預約訓練師</title>
    <style>
        div#calendar {

            padding: 0;
            font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
            font-size: 14px;
        }

        #calendar {
            max-height: 75vh;
            width: auto;
            margin: 0px;
            margin-top:10px;
            padding-right: 30px;
            z-index: -1;
        }

        a {
            text-decoration: none;
            color: black;
        }
        div#showDate{
            font-size: 30px;
        }
        .fc-event, .event-title {
            padding: 0 1px;
            white-space: normal;
        }
    </style>
</head>
<body>
<%@ include file="/Header.file" %>

<div>
    <h2 class="text-center py-3">預約訓練師</h2>
    <h2 class="text-center py-3">您所擁有的課堂數：<span id="totalClass" class="text-primary">${sessionScope.member.totalClass}</span></h2>
    <h2 class="text-center">已預約：<span id="reserveClass" class="text-danger">0</span>堂</h2>
</div>
<div class="container my-5">
    <div id='calendar'></div>
</div>

<div class="container">
    <div class="col-auto text-center mx-auto my-5">
        <button type="button" class="btn btn-primary" id="confirm">確認預約</button>
        <button type="button" class="btn btn-secondary" onclick="window.location.href = `${pageContext.request.contextPath}/trainer/getAll`">回上頁</button>
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
                <form>
                <button type="button" class="btn btn-primary" id="reserveBtn">確定預約</button>
            	</form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/Footer.file" %>
<script src="${pageContext.request.contextPath}/frontend/privatetrainer/js/appointment.js"></script>
</body>
</html>
