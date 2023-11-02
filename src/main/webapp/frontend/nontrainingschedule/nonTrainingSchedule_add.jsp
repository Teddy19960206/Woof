<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <style>
       
    </style>
</head>
<body>
<h1>新增不授課日期</h1>

<form action="${pageContext.request.contextPath}/nontrainingschedule" method="post" enctype="multipart/form-data">

    <label>訓練師名稱：</label>
    <select name="trainer">
        <c:forEach items="${trainers}" var="trainer">
            <option value="${trainer.trainerNo}">${trainer.administrator.adminName}</option>
        </c:forEach>
    </select>
    <br />
        <label for="datePicker">選擇不授課日期：</label>
        <input type="date" id="datePicker" name="selectedDate">
    <br />
	<input type="hidden" name="action" value="add">
    <button type="submit">新增</button>
    <button type="button" onclick="history.back()">取消新增</button>
</form>
</body>
</html>