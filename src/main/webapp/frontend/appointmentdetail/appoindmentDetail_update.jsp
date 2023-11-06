<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Title</title>
<style>
/* 在这里添加你的 CSS 样式 */
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
	<h1>修改私人預約單明細</h1>

	<form action="${pageContext.request.contextPath}/appointmentdetail"
		method="post" enctype="multipart/form-data">
		<input	type="hidden" name="adNo"	value="${param.adNo}">
		<input	type="hidden" name="ptaNo"	value="${param.ptaNo}">
		<label for="datepick">選擇預約日期:</label>
		<input type="date" id="datePicker" name="date" value="${param.appTime}">
		<br />
		<label>預約狀態：</label> 
		<select name="appStatus">
   		<option value="0" ${param.appStatus == '0' ? 'selected' : ''}>接受</option>
		<option value="1" ${param.appStatus == '1' ? 'selected' : ''}>取消</option>
		</select>
		 <br /> 
	
			
		<button type="submit" name="action"
			value="update">修改</button>
		<button type="button" onclick="history.back()">取消修改</button>
	</form>
</body>
</html>