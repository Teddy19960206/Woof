<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<jsp:useBean id="trainerServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>
<%-- <p>trainerNo = ${param.trainerNo}</p> --%>
<%-- <p>memNo = ${param.memNo}</p> --%>
<form method="POST" ACTION="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getboth" class="row g-3 align-items-center">
	<div class="container py-3">
    	<div class="row">
            <!-- 下拉式選單 -->
            <div class="col-3">
                <label for="trainerSelect" class="col-form-label">訓練師名稱</label>
                <div class="col-12">
                <select name="trainerNo" class="form-select">
                    <option value="0">顯示全部</option>
                    <c:forEach var="trainer" items="${trainerServer.allTrainers}">
                        <option value="${trainer.trainerNo}">${trainer.administrator.adminName}</option>
                    </c:forEach>
                </select>
                </div>
            </div>
            <!-- 會員帳號輸入框 -->
            <div class="col-3">
                <label class="col-form-label">會員帳號</label>
                <div class="col-12">
                <input type="text" name="memNo" id="memNo" class="form-control" />
            	</div>
            </div>
            <!-- 查詢按鈕 -->
            <div class="col-3">
                <label class="col-form-label">開始查詢</label>
                <div class="col-12">
                <button type="submit" id="button" class="btn btn-primary">提交</button>
            	</div>
            </div>
   		 </div>
	</div>
</form>

<table border= 1 >
		<tr>
			<th>私人訓練預約單編號</th>
			<th>會員帳號</th>
			<th>訓練師名稱</th>
			<th>預約堂數</th>
<!-- 			<th></th> -->
			<th></th>
		</tr>

		<c:forEach var="privateTrainingAppointmentForm"
			items="${privateTrainingAppointmentForms}">

			<tr>
				<td>${privateTrainingAppointmentForm.ptaNo}</td>
				<td>${privateTrainingAppointmentForm.member.memNo}</td>
				<td>${privateTrainingAppointmentForm.trainer.administrator.adminName}</td>
				<td>${privateTrainingAppointmentForm.ptaClass}</td>
<!-- 				<td> -->

<!-- 					<FORM METHOD="post" -->
<%-- 						action="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettoupdate2"> --%>
<%-- 						<% --%>
<!-- 						String ptaNo = request.getParameter("ptaNo"); -->
<!-- 						String member = request.getParameter("member"); -->
<!-- 						String trainer = request.getParameter("trainer"); -->
<!-- 						String number = request.getParameter("number"); -->
<!-- 						%> -->
<!-- 						<input type="hidden" name="action" value="gettoupdate"> -->
<%-- 						<input type="hidden" name="ptaNo" value="${privateTrainingAppointmentForm.ptaNo}">					 --%>
<%-- 						<input type="hidden" name="member" value="${privateTrainingAppointmentForm.member.memName}"> --%>
<%-- 						<input type="hidden" name="trainer" value="${privateTrainingAppointmentForm.trainer.administrator.adminName}"> --%>
<%-- 						<input type="hidden" name="number" value="${privateTrainingAppointmentForm.ptaClass}"> --%>
<!-- 						<button class="btn btn-success" type="submit">修改</button> -->

<!-- 					</FORM> -->
<!-- 				</td> -->
				<td>
					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/appointmentdetail?action=getdetail">
						<input type="hidden" name="ptaNo" value="${privateTrainingAppointmentForm.ptaNo}">
						<input type="hidden" name="memNo" value="${privateTrainingAppointmentForm.member.memNo}">
						<input type="hidden" name="trainerNo" value="${privateTrainingAppointmentForm.trainer.trainerNo}">
						<button class="btn btn-in" type="submit">查看明細</button>

					</FORM>

				</td>
			</tr>
		</c:forEach>

	</table>
<%-- 	<p>trainerNo = ${param.trainerNo}</p> --%>
<%-- 	<p>memNo = ${param.memNo}</p> --%>
	<div class="pagination">
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getboth&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getboth&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= PTAFPageQty}">
		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getboth&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != PTAFPageQty}">
		<a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=getboth&page=${PTAFPageQty}">至最後一頁</a>&nbsp;
	</c:if>

<%-- 	<button class="btn btn-back" onclick="window.location='${pageContext.request.contextPath}/backend/index.jsp'">返回</button> --%>
    </div>
   

<%@ include file="/backend/backfoot.file" %>
</body>
</html>
