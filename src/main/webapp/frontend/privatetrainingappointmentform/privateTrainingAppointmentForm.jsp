<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>PrivateTrainingAppointmentForm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        select {
            width: 200px;
            padding: 10px;
        }
        button {
            background-color: #0074d9;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
        h1 {
            color: #333;
        }
    </style>
</head>
<body>
<jsp:useBean id="privateTrainingAppointmentFormServer" scope="page" class="com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/privatetrainingappointmentform">
    <select name="ptaNo">
        <c:forEach var="privateTrainingAppointmentForm" items="${privateTrainingAppointmentFormServer.allPrivateTrainingAppointmentForms}">
            <option  value="${privateTrainingAppointmentForm.ptaNo}">${privateTrainingAppointmentForm.ptaNo}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="action" value="getone">
    <button type="submit">提交</button>
    <br>
    <h1>私人訓練預約單</h1>
    <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettoadd">新增頁面</a>
    <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettoupdate">更新頁面</a>
<%--     <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettoupdelete">刪除頁面</a> --%>
    <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getall">查詢全部</a>
    <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettoselect">單一查詢</a>

    <br><br>
</form>
</body>
</html>