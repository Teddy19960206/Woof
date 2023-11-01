<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>AppointmentDetail</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
}

table {
	width: 100%;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid #ccc;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #0074d9;
	color: white;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:nth-child(odd) {
	background-color: #ffffff;
}

.btn-success {
	background-color: #4CAF50;
	color: white;
	padding: 5px 10px;
	border: none;
	cursor: pointer;
}

.btn-back {
	background-color: #f44336;
	color: white;
	padding: 5px 10px;
	border: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<table border=1>
		<tr>
			<th>預約明細編號</th>
			<th>私人訓練預約單編號</th>
			<th>預約時間</th>
			<th>預約狀態</th>
			<th>預約地點</th>
			<th></th>

		</tr>

		<c:forEach var="AD" items="${appointmentDetails}">

			<tr>
				<td>${AD.adNo}</td>
				<td>${AD.privateTrainingAppointmentForm.ptaNo}</td>
				<td>${AD.appTime}</td>
				<td>${AD.appStatus}</td>
				<td>${AD.appVenue}</td>
				<td>

					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/appointment?action=gettoupdate2">
						<%
						String ptaNo = request.getParameter("ptaNo");
						String member = request.getParameter("member");
						String trainer = request.getParameter("trainer");
						String number = request.getParameter("number");
						%>
						<input type="hidden" name="action" value="gettoupdate"> <input
							type="hidden" name="ptaNo"
							value="${privateTrainingAppointmentForm.ptaNo}"> <input
							type="hidden" name="member"
							value="${privateTrainingAppointmentForm.member.memName}">
						<input type="hidden" name="trainer"
							value="${privateTrainingAppointmentForm.trainer.administrator.adminName}">
						<input type="hidden" name="number"
							value="${privateTrainingAppointmentForm.ptaClass}">
						<button class="btn btn-success" type="submit">修改</button>

					</FORM>
				</td>

			</tr>
		</c:forEach>

	</table>
<!-- 	<button class="btn btn-new" -->
<!-- 		name="action" value="gettoadd">新增</button> -->
	<button class="btn btn-back"
		onclick="history.back()">返回</button>

</body>
</html>