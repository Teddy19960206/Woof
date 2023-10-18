<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<meta charset="UTF-8">
<title>administrator</title>
</head>
<body>
<jsp:useBean id="trainerService" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/trainer">
    <select name="Trainer">
            <c:forEach var="trainer" items="${trainerService.allTrainers}">
             <option value="${trainer.experience}">${trainer.trainerNo}</option> --%>
            </c:forEach>
    </select>
    <button type="submit">提交</button>
</form>
</body>
</html>









<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %> --%>


<!-- <script> -->

<!-- </script> -->
<!-- <html> -->

<!-- <body> -->
<%-- <jsp:useBean id="trainerService" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/> --%>
<%-- <%-- action="${pageContext.request.contextPath}/administrator?conmain=main" --%> 
<!-- <form id="editForm" method="POST" > -->
<!--     <select name="TRAINER_NO" id="TRAINER_NO"> -->
<%--         <c:forEach var="trainer" items="${trainerService.allTrainer}"> --%>
<%--             <option value="${trainer.experience}">${trainer.trainerNo}</option> --%>
<%--         </c:forEach> --%>
<!--     </select> -->
<!--     <input type="button" id="test" name="test" onclick="processSave();" value="表單送出"> -->
<!-- <!--     <button type="submit" id="button">提交</button> --> 
<!-- </form> -->
<!-- </body> -->
<!-- </html> -->