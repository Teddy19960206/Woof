<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 評論檢舉處理</title>
    <style>
    	 table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .btn {
            padding: 5px 10px;
            background-color: #428bca;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #357ebd;
        }
        .pagination {
		  margin-top: 20px;
		  text-align: center;
		}
		
		.pagination a {
		  color: #428bca;
		  padding: 5px 10px;
		  text-decoration: none;
		  border: 1px solid #428bca;
		  border-radius: 3px;
		  margin: 0 5px;
		}
		
		.pagination a:hover {
		  background-color: #428bca;
		  color: #fff;
		  transition: all 0.3s ease;
		}
        
    </style>
</head>
<body>
<%@ include file="/backend/backbody.file" %>

<div class="container py-4">
    <div class="row">
	<table>
        <thead>
            <tr>
                <th>評論檢舉編號</th>
                <th>私人訓練預約單編號</th>
                <th>撰寫評論的帳號</th>
                <th>被檢舉的內容</th>
                <th>檢舉時間</th>
                <th>處理狀態</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="commentReport" items="${commentReports}">
                <tr>
                    <td>${commentReport.crNo}</td>
                    <td>${commentReport.privateTrainingAppointmentForm.ptaNo}</td>
                    <td>${commentReport.privateTrainingAppointmentForm.member.memNo}</td>
                    <td class="truncated-text">${commentReport.crContext}</td>
                    <td>${commentReport.crDate}</td>
                    <c:choose>
    					<c:when test="${commentReport.crStatus == 0}">
        					<td>待處理</td>
    					</c:when>
    					<c:when test="${commentReport.crStatus == 1}">
        					<td>檢舉通過</td>
    					</c:when>
    					<c:when test="${commentReport.crStatus == 2}">
        					<td>檢舉未通過</td>
    					</c:when>
					</c:choose>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/backend/commentReport/commentReportUpdate.jsp">
                            <input type="hidden" name="crNo" value="${commentReport.crNo}">
                            <input type="hidden" name="memNo" value="${commentReport.privateTrainingAppointmentForm.member.memNo}">
                            <input type="hidden" name="commentTime" value="${commentReport.privateTrainingAppointmentForm.commentTime}">
                            <input type="hidden" name="commentUpTime" value="${commentReport.privateTrainingAppointmentForm.commentUpTime}">
                            <input type="hidden" name="crContext" value="${commentReport.crContext}">
                            <input type="hidden" name="crDate" value="${commentReport.crDate}">
                            <input type="hidden" name="crStatus" value="${commentReport.crStatus}">
                            <button class="btn btn-detail" type="submit">查看內容</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
</div>
<%-- 	<p>currentPage = ${currentPage}</p> --%>
<%-- 	<p>CRPageQty = ${CRPageQty}</p> --%>
	<div class="pagination">
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/commentreport?action=getAll&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/commentreport?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= CRPageQty}">
		<a href="${pageContext.request.contextPath}/commentreport?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != CRPageQty}">
		<a href="${pageContext.request.contextPath}/commentreport?action=getAll&page=${CRPageQty}">至最後一頁</a>&nbsp;
	</c:if>

    </div>
   

<%@ include file="/backend/backfoot.file" %>
</body>
</html>
