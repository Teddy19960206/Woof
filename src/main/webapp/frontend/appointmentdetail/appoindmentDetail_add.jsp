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
	<h1>新增私人預約單明細</h1>

	<form action="${pageContext.request.contextPath}/appointmentdetail"
		method="post" enctype="multipart/form-data">
		<input	type="hidden" name="ptaNo"	value="${ptaNo}">
		<label for="datetimepick">選擇預約時間:</label>
		 <input type="datetime-local" id="dateTimePicker">
		<br />
		<label>預約狀態：</label> 
		<select name="appstatus">
			<option value="0" selected>待審核</option>
			<option value="1">拒絕</option>
			<option value="2">接受</option>
			<option value="3">完成</option>
			<option value="4">取消</option>
		</select>
		 <br /> 
		 <label>預約地點：</label>
		<input type="text" id="addressInput" placeholder="請輸入地址">
		<br />
			
		<button type="submit" name="action"
			value="add">新增</button>
		<button type="button" onclick="history.back()">取消新增</button>
	</form>
</body>
</html>