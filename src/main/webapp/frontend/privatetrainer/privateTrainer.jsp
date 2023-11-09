<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>寵毛導師 Woof | 訓練師列表</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
    }

    h1 {
        color: #333;
        text-align: center;
    }

    .trainer {
        background-color: #fff;
        border: 1px solid #ccc;
        margin: 10px;
        padding: 15px;
        box-shadow: 0px 0px 10px #aaa;
    }

    img {
        display: block;
        margin: 0 auto;
        border: 2px solid #ccc;
        border-radius: 50%;
    }

    h2 {
        text-align: center;
        margin-top: 10px;
    }

    p {
        font-weight: bold;
        margin-top: 10px;
    }

    ul {
        list-style: none;
        padding: 0;
    }

    li {
        margin-top: 5px;
    }

    button {
        background-color: #333;
        color: #fff;
        border: none;
        padding: 5px 10px;
        cursor: pointer;
        margin-top: 10px;
    }

    button:hover {
        background-color: #555;
    }
</style>
</head>
<body>
    <h1>訓練師列表</h1>
<jsp:useBean id="trainerServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>
<jsp:useBean id="skillServer" scope="page" class="com.woof.skill.service.SkillServiceImpl"/>
<%--     <form method="POST" ACTION="${pageContext.request.contextPath}/trainer"> --%>
    <c:forEach var="trainer" items="${trainerServer.allTrainers}">
        <div class="trainer">
           <img src="${pageContext.request.contextPath}/DBPngReader?action=administrator&id=${trainer.administrator.adminNo}" style="width: 100px; height: 100px">
            <h2>${trainer.administrator.adminName}</h2>
            
            <p>專長:</p>
<%--             <p>${trainer.skills}</p> --%>
            <ul>          	
 				<c:forEach items="${trainer.skills}" var="trainerskill">
            			<li>${trainerskill.skillName}</li>
        		</c:forEach>
        	</ul>
            <p>評價:</p>
            <form method="POST" ACTION="${pageContext.request.contextPath}/commentreport">
            	<ul>            	
                	<c:forEach var="pta" items="${trainer.privateTrainingAppointmentForms}">
                   		<li>${pta.ptaComment}</li>            
                   		<input type="hidden" name="ptano" value="${pta.ptaNo}">
                   		<input type="hidden" name="comment" value="${pta.ptaComment}">
                    	<button>檢舉評論</button>
                	</c:forEach>
            	</ul>
            </form>
            
            <button>預約訓練師</button>
        </div>
    </c:forEach>
    <button>購買課堂</button>
<!--     </form> -->
</body>
</html>
