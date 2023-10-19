<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>Promotion Activity</title>
</head>
<body>
<jsp:useBean id="promotionActivityServer" scope="page" class="com.woof.promotionactivity.service.PromotionActivityServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/promotionactivity">
    <select name="Type">
        <c:forEach var="promotionActivity" items="${promotionActivityServer.allPromotionActivity}">
            <option  value="${promotionActivity.paNo} ${promotionActivity.paDiscount} ${promotionActivity.paContent} ${promotionActivity.paStart} ${promotionActivity.paEnd} ${promotionActivity.paStatus}">${promotionActivity.paName}</option>
        </c:forEach>
    </select>
    <button type="submit">提交</button>
</form>
</body>
</html>