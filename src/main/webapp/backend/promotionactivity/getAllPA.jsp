<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>Promotion Activity</title>
</head>
<body>

<table border=1>
<tr>
<th>促銷活動編號</th>
<th>活動名稱</th>
<th>折扣數</th>
<th>活動內容</th>
<th>活動開始日期</th>
<th>活動結束日期</th>
<th>活動狀態</th>
</tr>


<c:forEach var="promotionActivity" items="${promotionActivityList}">
<tr>
<td>${promotionActivity.paNo}</td>
<td>${promotionActivity.paName}</td>
<td>${promotionActivity.paDiscount}</td>
<td>${promotionActivity.paContent}</td>
<td>${promotionActivity.paStart}</td>
<td>${promotionActivity.paEnd}</td>
<td>${promotionActivity.paStatus}</td>
</tr>
</c:forEach>

<input type="button" value="返回" onclick="history.back()">


<%-- <jsp:useBean id="promotionActivityServer" scope="page" class="com.woof.promotionactivity.service.PromotionActivityServiceImpl"/> --%>
<%-- <form method="POST" ACTION="${pageContext.request.contextPath}/promotionactivity"> --%>
<!--     <select name="Type"> -->
<%--         <c:forEach var="promotionActivity" items="${promotionActivityServer.allPromotionActivity}"> --%>
<%--             <option  value="${promotionActivity.paNo} ${promotionActivity.paDiscount} ${promotionActivity.paContent} ${promotionActivity.paStart} ${promotionActivity.paEnd} ${promotionActivity.paStatus}">${promotionActivity.paName}</option> --%>
<%--         </c:forEach> --%>
<!--     </select> -->
<!--     <button type="submit">提交</button> -->
<!-- </form> -->


</body>
</html>