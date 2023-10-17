<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>CommentReport</title>
</head>
<body>
<jsp:useBean id="commentReportServer" scope="page" class="com.woof.commentreport.service.CommentReportServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/commentreport">
    <select name="Type">
        <c:forEach var="commentReport" items="${commentReportServer.allCommentReports}">
            <option  value="${commentReport.member.memNo} ${commentReport.trainer.trainerNo} ${commentReport.privateTrainingAppointmentForm.ptaNo} ${commentReport.crContext} ${commentReport.crStatus} ${commentReport.crDate}">${commentReport.crNo}</option>
        </c:forEach>
    </select>
    <button type="submit">提交</button>
</form>
</body>
</html>