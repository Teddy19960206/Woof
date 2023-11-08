<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>我要評論</title>
    <style>
    
    </style>
</head>
<body>
	 
    <h1>選擇會員編號</h1>
    <jsp:useBean id="memberServer" scope="page" class="com.woof.member.service.MemberServiceImpl"/>
	<form method="POST" ACTION="${pageContext.request.contextPath}/privatetrainingappointmentform">
    <select name="memNo">
        <c:forEach var="member" items="${memberServer.allMembers}">
            <option  value="${member.memNo}">${member.memName}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="action" value="commentbymember">
    <button type="submit">提交</button>
    </form>
    
</body>
</html>