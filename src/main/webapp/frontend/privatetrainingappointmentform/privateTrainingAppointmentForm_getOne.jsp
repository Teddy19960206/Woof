<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<title>GetOne</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/privatetrainingappointmentform/privatetrainingappointmentform" method="post" enctype="multipart/form-data">
	<table border=1>
		<tr>
			<th>私人訓練預約單編號</th>
			<th>會員編號</th>
			<th>訓練師編號</th>
			<th>預約堂數</th>
		</tr>

<%-- 		<c:forEach var="privateTrainingAppointmentForm" items="${privateTrainingAppointmentForms}"> --%>
		
			<tr>
				<td>${pta.ptaNo}</td>
				<td>${pta.member.memNo}</td>
				<td>${pta.trainer.trainerNo}</td>
				<td>${pta.ptaClass}</td>
<!-- 				<td> -->

<!-- 					<FORM METHOD="post" -->
<%-- 						action="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettoupdate"> --%>
<!-- 						<button class="btn btn-success" type="submit">修改</button> -->
		
<!-- 					</FORM> -->
<!-- 				</td>  -->
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						action="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettodelete"> --%>
<!-- 						<button class="btn btn-danger" type="submit">刪除</button> -->

<!-- 					</FORM> -->

<!-- 				</td> -->
			</tr>
<%-- 		</c:forEach> --%>
		
	</table>
	
	
	<input type="button" value="返回" onclick="history.back()" >

</form>
</body>
</html>