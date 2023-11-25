<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 私人預約管理</title>
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
        margin-top: 20px;
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

    .butn {
        background-color: #0074d9;
        color: white;
        padding: 5px 10px;
        border: none;
        cursor: pointer;
        margin-right: 5px;
    }

    .btn-delete {
        background-color: #f44336;
    }

    .button-container {
        margin-top: 20px;
    }
    </style>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
<%-- <p>trainerNo = ${param.trainerNo}</p> --%>
<%-- <p>trainer = ${param.trainer}</p> --%>
<%-- <p>memNo = ${param.memNo}</p> --%>
<%-- <p>member = ${param.member}</p> --%>
<div class="container py-3" >
<div class="col">
<table border=1>
		<tr>
			<th>預約明細編號</th>
			<th>私人訓練預約單編號</th>
			<th>預約時間</th>
			<th>預約狀態</th>
			<th></th>
<!-- 			<th></th> -->
		
		</tr>
		<c:set var="ptaNo" value="" />
		<c:forEach var="AD" items="${appointmentDetails}">
		<c:set var="ptaNo" value="${AD.privateTrainingAppointmentForm.ptaNo}" />
			<tr>
				<td>${AD.adNo}</td>
				<td>${AD.privateTrainingAppointmentForm.ptaNo}</td>
				<td>${AD.appTime}</td>
				
				
				<td>
					<span class="status-text">
						<c:choose>
                			<c:when test="${AD.appStatus == 0}">接受</c:when>
                			<c:when test="${AD.appStatus == 1}">取消</c:when>
                			<c:when test="${AD.appStatus == 2}">已完成</c:when>
            			</c:choose>
            		</span>
            	</td>
				<td>
				 
				 
					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/appointmentdetail?action=gettoupdate">
						<%
						String adNo = request.getParameter("adNo");
						String ptaNo = request.getParameter("ptaNo");
						String appTime = request.getParameter("appTime");
						String appStatus = request.getParameter("appStatus");
						String memNo = request.getParameter("memNo");
						String trainerNo = request.getParameter("trainerNo");
						%>
						<input type="hidden" name="adNo" value="${AD.adNo}"> 
						<input type="hidden" name="ptaNo" value="${AD.privateTrainingAppointmentForm.ptaNo}">
						<input type="hidden" name="appTime"	value="${AD.appTime}">
						<input type="hidden" name="appStatus" value="${AD.appStatus}">
						<input type="hidden" name="memNo" value="${AD.privateTrainingAppointmentForm.member.memNo}">
						<input type="hidden" name="trainerNo" value="${AD.privateTrainingAppointmentForm.trainer.trainerNo}">
						<button class="butn btn-success" type="submit">修改</button>

					</FORM>
				</td>
<!-- 				<td> -->
<%-- 					<FORM METHOD="post" action="${pageContext.request.contextPath}/appointmentdetail?action=delete" enctype="application/x-www-form-urlencoded"> --%>
<%-- 						<% --%>
<!-- 						adNo = request.getParameter("adNo"); -->
<!-- 						ptaNo = request.getParameter("ptaNo"); -->
<!-- 						memNo = request.getParameter("memNo"); -->
<!-- 						trainerNo = request.getParameter("trainerNo"); -->
<!-- 						%> -->
<%-- 						<input type="hidden" name="adNo" value="${AD.adNo}"> --%>
<%-- 						<input type="hidden" name="ptaNo" value="${AD.privateTrainingAppointmentForm.ptaNo}"> --%>
<%-- 						<input type="hidden" name="memNo" value="${param.memNo}"> --%>
<%-- 						<input type="hidden" name="trainerNo" value="${param.trainerNo}"> --%>
<!-- 						<button class="btn btn-delete" type="submit">刪除</button> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>
		</c:forEach>
	</table>
	
	<div class="button-container">
    <FORM METHOD="post" action="${pageContext.request.contextPath}/appointmentdetail?action=gettoadd">
        <input	type="hidden" name="ptaNo"	value="${param.ptaNo}">
        <input	type="hidden" name="trainerNo" value="${param.trainerNo}">
        <input	type="hidden" name="memNo"	value="${param.memNo}">
        <button class="butn btn-new" type="submit">新增</button>
<%--         <p>ptaNo = ${param.ptaNo}</p> --%>
    </FORM>
    <FORM METHOD="post" action="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getall">
    <button class="butn btn-back" type="submit">返回</button>
    </FORM>
	</div>
</div>
</div>
<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/appointment/js/appointmentDetail.js"></script>
</body>
</html>
