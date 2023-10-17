<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>NonTrainingSchedule</title>
</head>
<body>
<jsp:useBean id="nonTrainingScheduleServer" scope="page" class="com.woof.nontrainingschedule.service.NonTrainingScheduleServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/nontrainingschedule">
    <select name="Type">
        <c:forEach var="nonTrainingSchedule" items="${nonTrainingScheduleServer.allNonTrainingSchedules}">
            <option  value="${nonTrainingSchedule.trainerNo} ${nonTrainingSchedule.ntsDate}">${nonTrainingSchedule.ntsNo}</option>
        </c:forEach>
    </select>
    <button type="submit">提交</button>
</form>
</body>
</html>