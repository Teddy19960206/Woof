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
	<h2>修改私人預約單明細</h2>

	<form action="${pageContext.request.contextPath}/appointmentdetail"
		method="post" enctype="multipart/form-data">
		<input	type="hidden" name="memNo"	value="${param.memNo}">
		<input	type="hidden" name="trainerNo"	value="${param.trainerNo}">
		<input	type="hidden" name="adNo"	value="${param.adNo}">
		<input	type="hidden" name="ptaNo"	value="${param.ptaNo}">
		<label for="datepick">預約日期:</label>
		<input type="date" id="datePicker" name="date" value="${param.appTime}" readonly>
		<br />
		<label>預約狀態：</label> 
		<select name="appStatus">
   		<option value="0" ${param.appStatus == '0' ? 'selected' : ''}>接受</option>
		<option value="1" ${param.appStatus == '1' ? 'selected' : ''}>取消</option>
		<option value="2" ${param.appStatus == '2' ? 'selected' : ''}>已完成</option>
		</select>
		 <br /> 
	
			
		<button type="submit" name="action"
			value="update">修改</button>
		<button type="button" class="cancel" onclick="history.back()">取消修改</button>
	</form>
<%@ include file="/backend/backfoot.file" %>
</body>
</html>