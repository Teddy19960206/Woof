<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
    <title>Title</title>
    <style>
    body, p, h1, a {
        margin: 0;
        padding: 0;
    }

    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        text-align: center;
    }

    h1 {
        background-color: #3498db;
        color: #fff;
        padding: 20px;
    }

    form {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
    }

    label {
        display: block;
        margin-top: 10px;
    }

    input[type="text"], select, input[type="date"] {
        width: 100%;
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    select {
        height: 30px;
    }

    button {
        background-color: #3498db;
        color: #fff;
        border: none;
        border-radius: 5px;
        padding: 5px 10px;
        cursor: pointer;
        margin-top: 10px;
    }

    button[type="button"] {
        background-color: #c9302c;
    }

    button:hover {
        background-color: #2960a5;
    }

    button[type="button"]:hover {
        background-color: #ac2925;
    }
</style>
</head>
<body>
<h1>更新</h1>

<form action="${pageContext.request.contextPath}/nontrainingschedule" method="post" enctype="multipart/form-data">
   
    <label>欲修改的訓練師不授課日程編號：</label>
    <input type="text" name="ntsNo" value="${param.ntsNo}" readonly>
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

    <label for="datePicker">不授課日期：</label>
        <input type="date" id="datePicker" name="selectedDate" value="${param.ntsDate}">
    <br />


	<input type="hidden" name="action" value="update">
    <button type="submit">修改</button>
    <button type="button" onclick="history.back()">取消修改</button>
</form>

</body>
</html>