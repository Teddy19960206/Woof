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
<jsp:useBean id="trainerServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>
<jsp:useBean id="skillServer" scope="page" class="com.woof.skill.service.SkillServiceImpl"/>
    <form method="POST" ACTION="${pageContext.request.contextPath}/trainer">
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
            <ul>
                <c:forEach var="comment" items="${trainer.privateTrainingAppointmentForms}">
                    <li>${comment.ptaComment}</li>
                    <button>檢舉評論</button>
                </c:forEach>
            </ul>
            
            
            <button>預約訓練師</button>
        </div>
    </c:forEach>
    <button>購買課堂</button>
    </form>
</body>
</html>
