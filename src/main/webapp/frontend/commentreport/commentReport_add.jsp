<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>新增評論檢舉</title>
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

    select, input[type="date"] {
        width: 100%;
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
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
<h1>新增不授課日期</h1>

<form action="${pageContext.request.contextPath}/commentreport" method="post" enctype="multipart/form-data">
    <label>會員名稱</label>
    <select name="memNo">
        <c:forEach items="${members}" var="member">
            <option value="${member.memNo}">${member.memName}</option>
        </c:forEach>
    </select>
    <br />
    <label>訓練師名稱</label>
    <select name="trainerNo">
        <c:forEach items="${trainers}" var="trainer">
            <option value="${trainer.trainerNo}">${trainer.administrator.adminName}</option>
        </c:forEach>
    </select>
    <br />
    <label>私人訓練預約單編號</label>
    <select name="ptaNo">
        <c:forEach items="${PTAs}" var="PTA">
            <option value="${PTA.ptaNo}">${PTA.ptaNo}</option>
        </c:forEach>
    </select>
    <br />
        <label>被檢舉的內容：</label>
        <input type="text" name="crContext" required>
    <br />
    <label>處理狀態：</label> 
		<select name="crStatus">
			<option value="0" selected>待處理</option>
			<option value="1">檢舉通過</option>
			<option value="2">檢舉未通過</option>
		</select>
	<br />
	
    
	<input type="hidden" name="action" value="add">
    <button type="submit">新增</button>
    <button type="button" onclick="history.back()">取消新增</button>
</form>
</body>
</html>