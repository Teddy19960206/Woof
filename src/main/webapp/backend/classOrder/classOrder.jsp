<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 課堂訂單管理</title>
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
<%-- <jsp:useBean id="classOrderServer" scope="page" class="com.woof.classorder.service.ClassOrderServiceImpl"/> --%>
<div class="container py-3">
    <div class="row g-3 align-items-center">
        <form method="POST" action="${pageContext.request.contextPath}/classorder/getByMemNo" class="col-10">
            <div class="row">
                <!-- 會員帳號輸入框 -->
                <div class="col-4">
                    <label class="col-form-label">會員帳號</label>
                    <div class="col-12">
                        <input type="text" name="memNo" id="memNo" class="form-control" />
                    </div>
                </div>
                <!-- 查詢按鈕 -->
                <div class="col-6">
                    <label class="col-form-label">查詢</label>
                    <div class="col-12">
                        <button type="submit" id="button" class="btn btn-primary">提交</button>
                    </div>
                </div>
            </div>
        </form>
	</div>
</div>

<table border= 1 >
		<tr>
			<th>課堂訂單編號</th>
			<th>會員帳號</th>
			<th>剩餘課堂數</th>
			<th>購買課堂數</th>
			<th>付款方式</th>
			<th>折抵毛毛幣</th>
			<th>訂單時間</th>
			<th>訂單狀態</th>
			<th>實付金額</th>
			<th></th>
		</tr>

		<c:forEach var="classOrder"
			items="${classOrders}">

			<tr>
				<td>${classOrder.coNo}</td>
				<td>${classOrder.member.memNo}</td>
				<td>${classOrder.member.totalClass}</td>
				<td>${classOrder.coBc}</td>
				<td>${classOrder.coPaymentMethod}</td>
				<td>${classOrder.coSmmp}</td>
				<td>${classOrder.coTime}</td>
				<c:choose>
   					<c:when test="${classOrder.coStatus == 0}">
        				<td>未付款</td>
    				</c:when>
    				<c:when test="${classOrder.coStatus == 1}">
        				<td>已付款</td>
    				</c:when>
    				<c:when test="${classOrder.coStatus == 2}">
        				<td>取消訂單</td>
    				</c:when>
    				<c:otherwise>
        				<td>未知狀態</td>
    				</c:otherwise>
					</c:choose>
				<td>${classOrder.actualAmount}</td>
				<td>

					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/backend/classOrder/classOrderUpdate.jsp">
						<%
 							String coNo = request.getParameter("coNo");
	 						String memNo = request.getParameter("memNo");
	 						String coBc = request.getParameter("coBc");
	 						String coPaymentMethod = request.getParameter("coPaymentMethod");
	 						String coSmmp = request.getParameter("coSmmp");
	 						String coTime = request.getParameter("coTime");
	 						String coStatus = request.getParameter("coStatus");
	 						String actualAmount = request.getParameter("actualAmount");
						%> 
						<input type="hidden" name="coNo" value="${classOrder.coNo}">					
						<input type="hidden" name="memNo" value="${classOrder.member.memNo}">
						<input type="hidden" name="coBc" value="${classOrder.coBc}">
						<input type="hidden" name="coPaymentMethod" value="${classOrder.coPaymentMethod}">
						<input type="hidden" name="coSmmp" value="${classOrder.coSmmp}">
						<input type="hidden" name="coTime" value="${classOrder.coTime}">
						<input type="hidden" name="coStatus" value="${classOrder.coStatus}">
						<input type="hidden" name="actualAmount" value="${classOrder.actualAmount}">
						<button class="btn btn-success" type="submit">修改</button>

					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%-- 	<p>currentPage = ${currentPage}</p> --%>
<%-- 	<p>COPageQty = ${COPageQty}</p> --%>
	<div class="pagination">
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/classorder/getAll?page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/classorder/getAll?page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= COPageQty}">
		<a href="${pageContext.request.contextPath}/classorder/getAll?page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != COPageQty}">
		<a href="${pageContext.request.contextPath}/classorder/getAll?page=${COPageQty}">至最後一頁</a>&nbsp;
	</c:if>

    </div>
   

<%@ include file="/backend/backfoot.file" %>
</body>
</html>
