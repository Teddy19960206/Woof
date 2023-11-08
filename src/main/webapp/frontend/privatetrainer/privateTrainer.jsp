<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>訓練師列表</title>
</head>
<body>
    <h1>訓練師列表</h1>
<jsp:useBean id="privateTrainingAppointmentFormServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>
    <form method="POST" ACTION="${pageContext.request.contextPath}/trainer">
    <c:forEach var="trainer" items="${allTrainers}">
        <div class="trainer">
<%--             <img src="${trainer.photo}" alt="${trainer.name}的照片" width="100" height="100"> --%>
            <h2>${trainer.administrator.adminName}</h2>
<%--             <p>專長: ${}</p> --%>
<%--             <p>評價: ${trainer.rating}</p> --%>
            <button>檢舉評論</button>
            <button>購買課堂</button>
            <button>預約私人訓練師</button>
        </div>
    </c:forEach>
    </form>
    <script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/backend/employee/js/trainer.js"></script>
</body>
</html>
