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
	<h1>查詢私人預約單</h1>

	<form
		action="${pageContext.request.contextPath}/privatetrainingappointmentform/privateTrainingAppointmentForm"
		method="post" enctype="multipart/form-data">
		<label>私人訓練預約單編號：</label> <select name="ptaNo">
			<!--     	<option value="0">-- 顯示全部 --</option> -->
			<c:forEach items="${privateTrainingAppointmentForms}"
				var="privateTrainingAppointmentForm">
				<option value="${privateTrainingAppointmentForm.ptaNo}">${privateTrainingAppointmentForm.ptaNo}</option>
			</c:forEach>
		</select> 
		<br />
		<button type="submit" name="action" value="getone">用預約單編號查詢</button>
		<br /> 
		<br /> 
		<label>會員名稱：</label> 
		<select name="memNo">
			<c:forEach items="${members}" var="member">
				<option value="${member.memNo}">${member.memName}</option>
			</c:forEach>
		</select> <br />
		<button type="submit" name="action" value="getbymemno">用會員查詢</button>
		<br /> <br />
		 <label>訓練師名稱：</label> 
		 <select name="trainerNo">
			<c:forEach items="${trainers}" var="trainer">
				<option value="${trainer.trainerNo}">${trainer.administrator.adminName}</option>
			</c:forEach>
		</select> <br />
		<button type="submit" name="action" value="getbytrainerno">用訓練師查詢</button>
		<br /> 
		<br />



		<button type="button" onclick="history.back()">取消查詢</button>
	</form>
</body>
</html>