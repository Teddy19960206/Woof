<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <script src='${pageContext.request.contextPath}/webutil/js/index.global.min.js'></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backend/course/css/calendar.css" />
    <title>寵毛導師 Woof | 私人訓練師課表查詢</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>

<div class="container-fluid py-3">
    <div class="row g-3 align-items-center">
        <div class="col-auto">
            <label for="trainers" class="col-form-label">訓練師：</label>
        </div>
        <div class="col-auto">
            <jsp:useBean id="trainers" class="com.woof.trainer.service.TrainerServiceImpl" />
            <select name="trainer" id="trainers" class="form-select">
                <c:forEach items="${trainers.allTrainers}" var="trainer">
                    <option value="${trainer.trainerNo}">${trainer.administrator.adminName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>


<div id='calendar'></div>

<div id="recordDate" style="display: none">
    <div id="recordYear"></div>
    <div id="recordMonth"></div>
</div>

<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/privateTrainer/js/allSchedule.js"></script>
</body>
</html>
