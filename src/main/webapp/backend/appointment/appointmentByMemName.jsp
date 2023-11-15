<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 私人預約管理</title>
    <style>
    body {
    font-family: Arial, sans-serif;
    background-color: #ffffff; /* 白色背景 */
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

.btn-back {
    background-color: #f44336;
    color: white;
    padding: 5px 10px;
    border: none;
    cursor: pointer;
}
.pagination {
    margin-top: 20px;
    text-align: center;
}

.pagination a {
    color: #0074d9;
    padding: 8px 16px;
    text-decoration: none;
}

.pagination a:hover {
    background-color: #0074d9;
    color: white;
}

.pagination .active {
    background-color: #0074d9;
    color: white;
}

    </style>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
<%-- <p>${param.memName}</p> --%>
<%-- <p>PTAFPageQty4 = ${PTAPageQty4}</p> --%>
<jsp:useBean id="trainerServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>
<div class="container py-3" >
	<div class="row">
		<form method="POST" ACTION="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getpta">
        	<div class="col-3 select">
	        	<label for="trainerSelect" class="col-form-label">訓練師名稱：</label>
	          	<select name="trainerNo" class="form-select">
	        		<option value="0">顯示全部</option>
	        		<c:forEach var="trainer" items="${trainerServer.allTrainers}">
	            		<option  value="${trainer.trainerNo}">${trainer.administrator.adminName}</option>
	        		</c:forEach>
	    		</select>
    		</div>
            <div class="col-3">
                <label class="col-form-label">會員名稱</label>
                <input type="text" name="memName" id="memName" class="form-control"/>
            </div>
            <div class="col-3">
                <label class="col-form-label">開始查詢</label>
                <button type="submit" id="button" class="btn btn-primary d-block">提交</button>
			</div>
		</form>

<table border= 1 >
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
						<input type="hidden" name="ptaNo" value="${privateTrainingAppointmentForm.ptaNo}">
						<button class="btn btn-in" type="submit">查看明細</button>

					</FORM>

				</td>
			</tr>
		</c:forEach>

	</table>
<%-- 	<p>memName = ${param.memName}</p> --%>
	<p>currentPage = ${currentPage}</p>
	<p>PTAFPageQty4 = ${PTAPageQty4}</p>
	<div class="pagination">
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getbymemname&page=1&memName=${param.memName}">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getbymemname&page=${currentPage - 1}&memName=${param.memName}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= PTAFPageQty4}">
		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getbymemname&page=${currentPage + 1}&memName=${param.memName}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != PTAFPageQty4}">
		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getbymemname&page=${PTAFPageQty4}&memName=${param.memName}">至最後一頁</a>&nbsp;
	</c:if>

	<button class="btn btn-back" onclick="window.location='${pageContext.request.contextPath}/backend/index.jsp'">返回</button>
    </div>
   </div>
</div>

<%@ include file="/backend/backfoot.file" %>
</body>
</html>
