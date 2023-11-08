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

        select {
            padding: 5px;
            border: 1px solid #3498db;
            border-radius: 5px;
        }

        button {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #2960a5;
        }

        /* Style for form container */
        .form-container {
            text-align: center;
            margin-top: 20px;
        }
        #datePicker {
            padding: 5px;
            border: 1px solid #3498db;
            border-radius: 5px;
        }
    </style>
</head>
<body>
	 
    <h1>評論檢舉</h1>
    <a href="${pageContext.request.contextPath}/commentreport?action=gettoadd">新增頁面</a>
    <a href="${pageContext.request.contextPath}/commentreport?action=getall">查詢全部</a>
    
    <h2>會員查詢</h2>
	<jsp:useBean id="memberServer" scope="page" class="com.woof.member.service.MemberServiceImpl"/>
	<form method="POST" ACTION="${pageContext.request.contextPath}/commentreport">
    <select name="memNo">
        <c:forEach var="member" items="${memberServer.allMembers}">
            <option  value="${member.memNo}">${member.memName}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="action" value="getbymember">
    <button type="submit">提交</button>
    </form>
    <h2>訓練師查詢</h2>
	<jsp:useBean id="trainerServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>
	<form method="POST" ACTION="${pageContext.request.contextPath}/commentreport">
    <select name="trainerNo">
        <c:forEach var="trainer" items="${trainerServer.allTrainers}">
            <option  value="${trainer.trainerNo}">${trainer.administrator.adminName}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="action" value="getbytrainer">
    <button type="submit">提交</button>
    </form>
    
    <h2>私人訓練預約單編號查詢</h2>
	<jsp:useBean id="PTAServer" scope="page" class="com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl"/>
	<form method="POST" ACTION="${pageContext.request.contextPath}/commentreport">
    <select name="ptaNo">
        <c:forEach var="pta" items="${PTAServer.allPrivateTrainingAppointmentForms}">
            <option  value="${pta.ptaNo}">${pta.ptaNo}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="action" value="getbyptano">
    <button type="submit">提交</button>
    </form>
    
    <br>
    <br><br>
</body>
</html>