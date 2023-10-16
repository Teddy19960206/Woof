<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>Appointment Detail</title>
</head>
<body>
<jsp:useBean id="appointmentDetailServer" scope="page" class="com.woof.appointmentdetail.service.AppointmentDetailServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/appointmentdetail">
    <select name="Type">
        <c:forEach var="appointmentDetail" items="${appointmentDetailServer.allAppointmentDetails}">
            <option  value="${appointmentDetail.ptaNo} ${appointmentDetail.appTime} ${appointmentDetail.appStatus} ${appointmentDetail.appVenue}">${appointmentDetail.adNo}</option>
        </c:forEach>
    </select>
    <button type="submit">提交</button>
</form>
</body>
</html>