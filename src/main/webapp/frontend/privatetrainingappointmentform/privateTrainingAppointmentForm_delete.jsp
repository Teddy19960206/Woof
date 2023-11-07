<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        h1 {
            color: #0074d9;
        }
        label {
            font-weight: bold;
        }
        select, input {
            padding: 5px;
            margin: 5px;
        }
        button {
            background-color: #0074d9;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>刪除私人預約單</h1>

<form action="${pageContext.request.contextPath}/privatetrainingappointmentform/privateTrainingAppointmentForm" method="post" enctype="multipart/form-data">
     <label>私人訓練預約單編號：</label>
    <select name="ptaNo">
        <c:forEach items="${privateTrainingAppointmentForms}" var="privateTrainingAppointmentForm">
            <option value="${privateTrainingAppointmentForm.ptaNo}">${privateTrainingAppointmentForm.ptaNo}</option>
        </c:forEach>
    </select>
    
    <br />
	<input type="hidden" name="action" value="delete">
    <button type="submit">刪除</button>
    <button type="button" onclick="history.back()">取消刪除</button>
</form>
</body>
</html>