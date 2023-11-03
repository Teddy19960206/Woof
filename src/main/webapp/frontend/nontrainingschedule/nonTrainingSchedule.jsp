<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>訓練師不授課日程</title>
    <style>
      body, p, h1, a {
    margin: 0;
    padding: 0;
}

body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    text-align: center;
}

h1 {
    background-color: #3498db;
    color: #fff;
    padding: 20px;
}

a {
    text-decoration: none;
    color: #3498db;
    margin: 10px;
    display: inline-block;
    border: 1px solid #3498db;
    padding: 5px 10px;
    border-radius: 5px;
}

a:hover {
    background-color: #3498db;
    color: #fff;
}

/* Center the links */
body > a {
    display: block;
    margin: 10px auto;
    max-width: 200px;
}
    </style>
</head>
<body>
	 
    <h1>訓練師不授課日程</h1>
    <a href="${pageContext.request.contextPath}/nontrainingschedule?action=gettoadd">新增頁面</a>
    <a href="${pageContext.request.contextPath}/nontrainingschedule?action=getall">查詢全部</a>
    <a href="${pageContext.request.contextPath}/nontrainingschedule?action=gettoselect">單一查詢</a>
	<jsp:useBean id="trainerServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>
	<form method="POST" ACTION="${pageContext.request.contextPath}/trainer">
    <select name="trainerNo">
        <c:forEach var="trainer" items="${trainerServer.allTrainers}">
            <option  value="${trainer.trainerNo}">${trainer.administrator.adminName}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="action" value="getone">
    <button type="submit">提交</button>
    <br>
    <br><br>
</body>
</html>