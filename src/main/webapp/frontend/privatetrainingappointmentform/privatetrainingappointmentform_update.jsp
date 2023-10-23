<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>修改私人預約單</h1>

<form action="${pageContext.request.contextPath}/privatetrainingappointmentform/privatetrainingappointmentform" method="post" enctype="multipart/form-data">
    <label>私人訓練預約單編號：</label>
    <select name="ptaNo">
        <c:forEach items="${privateTrainingAppointmentForms}" var="privateTrainingAppointmentForm">
            <option value="${privateTrainingAppointmentForm.ptaNo}">${privateTrainingAppointmentForm.ptaNo}</option>
        </c:forEach>
    </select>
    <br />
    <label>成員名稱：</label>
    <select name="member">
        <c:forEach items="${privateTrainingAppointmentForms}" var="privateTrainingAppointmentForm">
            <option value="${privateTrainingAppointmentForm.member.memNo}">${privateTrainingAppointmentForm.member.memName}</option>
        </c:forEach>
    </select>
    <br />

    <label>訓練師編號：</label>
    <select name="trainer">
        <c:forEach items="${privateTrainingAppointmentForms}" var="privateTrainingAppointmentForm">
            <option value="${privateTrainingAppointmentForm.trainer.trainerNo}">${privateTrainingAppointmentForm.trainer.trainerNo}</option>
        </c:forEach>
    </select>
    <br />

    <label>課堂數：</label>
    <input type="text" name="number" required>
    <br />
	<input type="hidden" name="action" value="update">
    <button type="submit">修改</button>
    <button type="button" onclick="history.back()">取消修改</button>
</form>

</body>
</html>