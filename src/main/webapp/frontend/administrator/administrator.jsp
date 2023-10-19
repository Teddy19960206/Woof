<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<meta charset="UTF-8">
<title>administrator</title>
</head>
<body>
<jsp:useBean id="administratorService" scope="page" class="com.woof.administrator.service.AdministratorServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/administrator">
    <select name="Administrator">
            <c:forEach var="administrator" items="${administratorService.allAdministrators}">
              <option   value="${administrator.adminName}${administrator.adminGender}${administrator.adminPhoto}${administrator.adminEmail}${administrator.adminPassword}${administrator.adminTel}${administrator.adminAddress}${administrator.adminBd}${administrator.emergencyContactName}${administrator.emergencyContactel}${administrator.adminHd}${administrator.adminRd}${administrator.adminStatus}">${administrator.adminNo}</option>  
            </c:forEach>
    </select>
    <button type="submit">提交</button>
</form>
</body>
</html>


<!-- <html> -->

<!-- <body> -->
<%-- <jsp:useBean id="administratorService" scope="page" class="com.woof.administrator.service.AdministratorServiceImpl"/> --%>
<%-- action="${pageContext.request.contextPath}/administrator?conmain=main" --%>
<%-- <form  method="POST" action="${pageContext.request.contextPath}/administrator" > --%>
<!--     <select name="administrator" > -->
<%--         <c:forEach var="administrator" items="${administratorService.allAdministrator}"> --%>
<%--             <option value="${administrator.adminNo}">${administrator.adminName}${administrator.adminGender}${administrator.adminPhoto}${administrator.adminEmail}${administrator.adminPassword}${administrator.adminTel}${administrator.adminAddress}${administrator.adminBd}${administrator.emergencyContactName}${administrator.emergencyContactel}${administrator.adminHd}${administrator.adminRd}${administrator.adminStatus}</option> --%>
<%--         </c:forEach>  --%>
<!--     </select> -->
<!--     <input type="button" id="test" name="test" onclick="processSave();" value="表單送出"> -->
<!--   <button type="submit" >提交</button> -->
<!-- </form> -->
<!-- </body> -->
<!-- </html> -->
