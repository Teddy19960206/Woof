<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
request.setCharacterEncoding("UTF-8");
%>
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
<h1>修改私人預約單</h1>

<form action="${pageContext.request.contextPath}/privatetrainingappointmentform/privateTrainingAppointmentForm" method="post" enctype="multipart/form-data">
   
    <label>私人訓練預約單編號：</label>
    <input type="text" name="ptaNo" value="${param.ptaNo}" readonly>
    <br />
    <label>會員名稱：</label>
    <select name="member">
        <c:forEach items="${members}" var="member">
            <option value="${member.memNo}"
            		<c:if test="${member.memName eq param.member}">selected</c:if>
            		>${member.memName}</option>
        </c:forEach>
    </select>
    <br />

    <label>訓練師名稱：</label>
    <select name="trainer">
        <c:forEach items="${trainers}" var="trainer">
            <option value="${trainer.trainerNo}"
            		<c:if test="${trainer.administrator.adminName eq param.trainer}">selected</c:if>
            		>${trainer.administrator.adminName}</option>
        </c:forEach>
    </select>
    <br />

    <label>課堂數：</label>
    <input type="text" name="number" value="${param.number}" required>
    <br />
	<input type="hidden" name="action" value="update">
    <button type="submit">修改</button>
    <button type="button" onclick="history.back()">取消修改</button>
</form>

</body>
</html>