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
        background-color: #f5f5f5;
    }

    table {
        border-collapse: collapse;
        width: 80%;
        margin: 20px auto;
        background-color: #fff;
    }

    th, td {
        padding: 10px;
        text-align: center;
    }

    th {
        background-color: #333;
        color: #fff;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #ddd;
    }

    button {
        padding: 5px 10px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        cursor: pointer;
    }

    .btn-new {
        background-color: #007bff;
    }

    .btn-back {
        background-color: #f00;
    }

    button:hover {
        background-color: #45a049;
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
						action="${pageContext.request.contextPath}/appointmentdetail?action=gettoupdate">
						<%
						String adNo = request.getParameter("adNo");
						String ptaNo = request.getParameter("ptaNo");
						String appTime = request.getParameter("appTime");
						String appStatus = request.getParameter("appStatus");
						String appVenue = request.getParameter("appVenue");
						%>
						<input type="hidden" name="action" value="gettoupdate"> 
						<input type="hidden" name="adNo" value="${AD.adNo}"> 
						<input type="hidden" name="ptaNo" value="${AD.privateTrainingAppointmentForm.ptaNo}">
						<input type="hidden" name="appTime"	value="${AD.appTime}">
						<input type="hidden" name="appStatus" value="${AD.appStatus}">
						<input type="hidden" name="appVenue" value="${AD.appVenue}">
						<button class="btn btn-success" type="submit">修改</button>

					</FORM>
				</td>

			</tr>
		</c:forEach>

	</table>
	<div class="button-container">
    <FORM METHOD="post" action="${pageContext.request.contextPath}/appointmentdetail?action=gettoadd">
        <input	type="hidden" name="ptaNo"	value="${AD.privateTrainingAppointmentForm.ptaNo}">
        <button class="btn btn-new" name="action" value="gettoadd">新增</button>
    </FORM>
    <button class="btn btn-back" onclick="history.back()">返回</button>
	</div>

</body>
</html>