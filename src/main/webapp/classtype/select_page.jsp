<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>CLASS TYPE</title>
</head>
<body>
<jsp:useBean id="classTypeService" scope="page" class="com.woof.classtype.service.ClassTypeServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/classType">
    <select name="Type">
        <c:forEach var="classType" items="${classTypeService.allClassTypes}">
            <option  value="${classType.ctNo}">${classType.ctName}</option>
        </c:forEach>
    </select>
    <button type="submit">提交</button>
</form>
</body>
</html>