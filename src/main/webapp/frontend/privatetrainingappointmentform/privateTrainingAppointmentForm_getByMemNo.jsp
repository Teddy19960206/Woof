<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
<title>GetAll</title>
<style>
/* 在这里添加你的 CSS 样式 */
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
.btn-in {
	background-color: orange;
	color: white;
	padding: 5px 10px;
	border: none;
	cursor: pointer;
}
.btn-danger {
	background-color: #f44336;
	color: white;
	padding: 5px 10px;
	border: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<%-- <form action="${pageContext.request.contextPath}/privatetrainingappointmentform/privateTrainingAppointmentForm" method="post" enctype="multipart/form-data"> --%>
	<table border=1>
		<tr>
			<th>私人訓練預約單編號</th>
			<th>會員名稱</th>
			<th>訓練師名稱</th>
			<th>預約堂數</th>
			<th></th>
			<th></th>
		</tr>

 		<c:forEach var="pta" 
 			items="${members}"> 

			<tr>
				<td>${pta.ptaNo}</td>
				<td>${pta.member.memName}</td>
				<td>${pta.trainer.administrator.adminName}</td>
				<td>${pta.ptaClass}</td>
				<td>

					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettoupdate2">
						<%
						String ptaNo = request.getParameter("ptaNo");
						String member = request.getParameter("member");
						String trainer = request.getParameter("trainer");
						String number = request.getParameter("number");
						%>
						<input type="hidden" name="action" value="gettoupdate">
						<input type="hidden" name="ptaNo" value="${pta.ptaNo}">					
						<input type="hidden" name="member" value="${pta.member.memName}">
						<input type="hidden" name="trainer" value="${pta.trainer.administrator.adminName}">
						<input type="hidden" name="number" value="${pta.ptaClass}">
						<button class="btn btn-success" type="submit">修改</button>

					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/appointmentdetail?action=getdetail">
						<input type="hidden" name="ptaNo" value="${pta.ptaNo}">
						<button class="btn btn-in" type="submit">查看明細</button>

					</FORM>

				</td>
 		</c:forEach> 

	</table>
<%-- 	<c:if test="${currentPage > 1}"> --%>
<%-- 		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getall&page=1">至第一頁</a>&nbsp; --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${currentPage - 1 != 0}"> --%>
<%-- 		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getall&page=${currentPage - 1}">上一頁</a>&nbsp; --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${currentPage + 1 <= PTAFPageQty}"> --%>
<%-- 		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getall&page=${currentPage + 1}">下一頁</a>&nbsp; --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${currentPage != PTAFPageQty}"> --%>
<%-- 		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getall&page=${PTAFPageQty}">至最後一頁</a>&nbsp; --%>
<%-- 	</c:if> --%>

<%-- 	<input type="hidden" value="返回" onclick="${pageContext.request.contextPath}/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp"> --%>
	<button class="btn btn-danger" onclick="history.back()">返回</button>


<!-- 	</form> -->
</body>
</html>