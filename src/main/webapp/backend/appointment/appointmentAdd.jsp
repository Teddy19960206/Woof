<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<%@ include file="/backend/backhead.file" %>
<title>寵毛導師 Woof | 私人預約管理</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

h2 {
    color: #333;
    text-align: center;
}

form {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    background: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
    display: block;
    margin-bottom: 5px;
}

input[type="date"],
select {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

button {
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-right: 10px;
}

button[type="submit"] {
    background-color: #007bff;
    color: white;
}

.cancel {
    background-color: #dc3545;
    color: white;
}

</style>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
	<h2>新增私人預約單明細</h2>
<%-- 	<p>memberNo = ${param.memNo}</p> --%>
<%-- 	<p>trainerNo = ${param.trainerNo}</p> --%>
	<form action="${pageContext.request.contextPath}/appointmentdetail"
		method="post" enctype="application/x-www-form-urlencoded">
		<input	type="hidden" name="ptaNo"	value="${ptaNo}">
		
		<label for="datepick">選擇預約日期:</label>
		<input type="date" id="datePicker" name="date" required>
		<br />
		<label>預約狀態：</label> 
		<select name="appStatus">
			<option value="0" selected>接受</option>
			<option value="1">取消</option>
		</select>
		 <br />
		<%
		String memNo = request.getParameter("memberNo");
		String trainerNo = request.getParameter("trainerNo");
		%> 
		<input	type="hidden" name="memNo"	value="${param.memNo}">	
		<input	type="hidden" name="trainerNo" value="${param.trainerNo}">	
		<button type="submit" name="action"
			value="add">新增</button>
		<button type="button" class="cancel" onclick="history.back()">取消新增</button>
	</form>
	<%@ include file="/backend/backfoot.file" %>
</body>
</html>