<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
<title>單一查詢</title>
<style>
    body, p, h1, a {
        margin: 0;
        padding: 0;
    }

    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        text-align: center;
    }

    h1 {
        background-color: #3498db;
        color: #fff;
        padding: 20px;
    }

    a {
        text-decoration: none;
        color: #3498db;
        margin: 10px;
        display: inline-block;
        border: 1px solid #3498db;
        padding: 5px 10px;
        border-radius: 5px;
    }

    a:hover {
        background-color: #3498db;
        color: #fff;
    }

    /* Center the links */
    body > a {
        display: block;
        margin: 10px auto;
        max-width: 200px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    table, th, td {
        border: 1px solid #3498db;
    }

    th, td {
        padding: 10px;
        text-align: center;
    }

    th {
        background-color: #3498db;
        color: #fff;
    }

    .btn {
        background-color: #3498db;
        color: #fff;
        padding: 5px 10px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .btn:hover {
        background-color: #2960a5;
    }

    .btn-success {
        background-color: #4CAF50;
    }

    .btn-success:hover {
        background-color: #45a049;
    }

    .btn-back {
        background-color: #c9302c;
    }

    .btn-back:hover {
        background-color: #ac2925;
    }
    
    .btn-delete {
        background-color: #c9302c;
    }

	.btn-delete:hover {
        background-color: #ac2925;
    }
    
    .successMessage {
        color: #4CAF50;
    }

    .errorMessage {
        color: #c9302c;
    }
    .pagination-container {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }

    .pagination-container a {
        text-decoration: none;
        color: #3498db;
        margin: 10px;
        display: inline-block;
        border: 1px solid #3498db;
        padding: 5px 10px;
        border-radius: 5px;
    }
</style>
</head>
<body>
	
	<table border=1>
		<tr>
			<th>訓練師不授課日程編號</th>
			<th>訓練師名稱</th>
			<th>不授課日期</th>
			<th></th>
			<th></th>
			
		</tr>
		<c:set var="trainerNo" value="" />
		<c:forEach var="NTS"
			items="${trainers}">
		<c:set var="trainerNo" value="${NTS.trainer.trainerNo}" />
			<tr>
				<td>${NTS.ntsNo}</td>
				<td>${NTS.trainer.administrator.adminName}</td>
				<td>${NTS.ntsDate}</td>
				<td>

					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/nontrainingschedule?action=gettoupdate">
						<%
						String ntsNo = request.getParameter("ntsNo");
						String trainer = request.getParameter("trainer");
						String ntsDate = request.getParameter("ntsDate");
						%>
						<input type="hidden" name="action" value="gettoupdate">
						<input type="hidden" name="ntsNo" value="${NTS.ntsNo}">					
						<input type="hidden" name="trainer" value="${NTS.trainer.administrator.adminName}">
						<input type="hidden" name="ntsDate" value="${NTS.ntsDate}">
						<button class="btn btn-success" type="submit">修改</button>

					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/nontrainingschedule?action=delete">
						<input type="hidden" name="ntsNo" value="${NTS.ntsNo}">
						<button class="btn btn-delete" type="submit">刪除</button>

					</FORM>

				</td>
			</tr>
		</c:forEach>

	</table>
<%-- <p>trainerNo: ${trainerNo}</p> --%>
<%-- <p>currentPage: ${currentPage}</p> --%>
<%-- <p>NTSPageQty2: ${NTSPageQty2}</p> --%>
	<div class="pagination-container" >
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/nontrainingschedule?action=getbytrainer&page=1&trainerNo=${trainerNo}">至第一頁</a>&nbsp;
    </c:if>
    <c:if test="${currentPage - 1 != 0}">
        <a href="${pageContext.request.contextPath}/nontrainingschedule?action=getbytrainer&page=${currentPage - 1}&trainerNo=${trainerNo}">上一頁</a>&nbsp;
    </c:if>
    <c:if test="${currentPage + 1 <= NTSPageQty2}">
        <a href="${pageContext.request.contextPath}/nontrainingschedule?action=getbytrainer&page=${currentPage + 1}&trainerNo=${trainerNo}">下一頁</a>&nbsp;
    </c:if>
    <c:if test="${currentPage != NTSPageQty2}">
        <a href="${pageContext.request.contextPath}/nontrainingschedule?action=getbytrainer&page=${NTSPageQty2}&trainerNo=${trainerNo}">至最後一頁</a>&nbsp;
    </c:if>
</div>


	<button class="btn btn-back" onclick="window.location='${pageContext.request.contextPath}/frontend/nontrainingschedule/nonTrainingSchedule.jsp'">返回</button>


</body>
</html>