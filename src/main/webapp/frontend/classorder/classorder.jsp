<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>ClassOrder</title>
</head>
<body>
<jsp:useBean id="classOrderServer" scope="page" class="com.woof.classorder.service.ClassOrderServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/classorder">
    <select name="Type">
        <c:forEach var="classOrder" items="${classOrderServer.allClassOrders}">
            <option  value="${classOrder.member.memNo} ${classOrder.coBc} ${classOrder.coPaymentMethod} ${classOrder.coSmmp} ${classOrder.coTime} ${classOrder.coStatus} ${classOrder.coCt} ${classOrder.actualAmount}">${classOrder.coNo}</option>
        </c:forEach>
    </select>
    <button type="submit">提交</button>
</form>
</body>
</html>