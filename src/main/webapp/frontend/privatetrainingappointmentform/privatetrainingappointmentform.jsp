<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>PrivateTrainingAppointmentForm</title>
</head>
<body>
<jsp:useBean id="privateTrainingAppointmentFormServer" scope="page" class="com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/privatetrainingappointmentform">
    <select name="Type">
        <c:forEach var="privateTrainingAppointmentForm" items="${privateTrainingAppointmentFormServer.allPrivateTrainingAppointmentForms}">
            <option  value="${privateTrainingAppointmentForm.member.memNo} ${privateTrainingAppointmentForm.trainer.trainerNo} ${privateTrainingAppointmentForm.ptaClass}">${privateTrainingAppointmentForm.ptaNo}</option>
        </c:forEach>
    </select>
    <button type="submit">提交</button>
    <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettoadd">新增頁面</a>
    <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettoupdate">更新頁面</a>

    <br><br>
</form>
</body>
</html>