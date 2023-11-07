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
.no-data-message {
    color: #c9302c;
    font-weight: bold;
    margin-top: 10px;
    font-size: 18px;
}
</style>
</head>
<body>
	<c:choose>
        <c:when test="${not empty members}">
	<table border=1>
		<tr>
			<th>私人訓練預約單編號</th>
			<th>會員名稱</th>
			<th>訓練師名稱</th>
			<th>預約堂數</th>
			<th></th>
		</tr>
		<c:set var="memNo" value="" />
 		<c:forEach var="pta" 
 			items="${members}"> 
		<c:set var="memNo" value="${pta.member.memNo}" />
			<tr>
				<td>${pta.ptaNo}</td>
				<td>${pta.member.memName}</td>
				<td>${pta.trainer.administrator.adminName}</td>
				<td>${pta.ptaClass}</td>
				<td>

					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/privatetrainingappointmentform?action=comment">
						<%
						String ptaNo = request.getParameter("ptaNo");
						String member = request.getParameter("member");
						String trainer = request.getParameter("trainer");
						String number = request.getParameter("number");
 						String comment = request.getParameter("comment");
 						String commentTime = request.getParameter("commenttime");
 						String commentUpTime = request.getParameter("commentuptime");
						%>
						<input type="hidden" name="action" value="comment">
						<input type="hidden" name="ptaNo" value="${pta.ptaNo}">					
						<input type="hidden" name="member" value="${pta.member.memNo}">
						<input type="hidden" name="trainer" value="${pta.trainer.trainerNo}">
						<input type="hidden" name="number" value="${pta.ptaClass}">
						<input type="hidden" name="comment" value="${pta.ptaComment}">
						<input type="hidden" name="commenttime" value="${pta.commentTime}">
						<input type="hidden" name="commentuptime" value="${pta.commentUpTime}">
						<button class="btn btn-comment" type="submit">我要評論</button>
						
					</FORM>
				</td>
 		</c:forEach> 

	</table>
<%-- <p>memNo: ${memNo}</p> --%>
<%-- <p>currentPage: ${currentPage}</p> --%>
<%-- <p>PTAPageQty2: ${PTAPageQty2}</p> --%>
	<div class="pagination-container" >
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=commentbymember&page=1&memNo=${memNo}">至第一頁</a>&nbsp;
    </c:if>
    <c:if test="${currentPage - 1 != 0}">
        <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=commentbymember&page=${currentPage - 1}&memNo=${memNo}">上一頁</a>&nbsp;
    </c:if>
    <c:if test="${currentPage + 1 <= PTAPageQty2}">
        <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=commentbymember&page=${currentPage + 1}&memNo=${memNo}">下一頁</a>&nbsp;
    </c:if>
    <c:if test="${currentPage != PTAPageQty2}">
        <a href="${pageContext.request.contextPath}/privatetrainingappointmentform?action=commentbymember&page=${PTAPageQty2}&memNo=${memNo}">至最後一頁</a>&nbsp;
    </c:if>
	</div>
	
	</c:when>
       <c:otherwise>
    		<p class="no-data-message">查無資料</p>
		</c:otherwise>
    </c:choose>
	
	<button class="btn btn-danger" onclick="history.back()">返回</button>



</body>
</html>