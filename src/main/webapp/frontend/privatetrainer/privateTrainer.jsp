<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %> --%>
<!DOCTYPE html>
<html>
<head>
<%-- <%@ include file="/meta.file" %> --%>
    <meta charset="UTF-8">
    <title>寵毛導師 Woof | 訓練師列表</title>
    <style>
    input{
        text-align: center;
    }
</style>
 <script type="text/javascript">
        // 當頁面加載完成後執行
        window.onload = function() {
            // 從URL參數中取得result
            var urlParams = new URLSearchParams(window.location.search);
            var result = urlParams.get('result');
            if(result === 'success'){
                alert('檢舉成功');
            } else if(result === 'fail'){
                alert('檢舉失敗');
            }
        };
    </script>
</head>
<body>
<%-- <%@ include file="/Header.file" %> --%>
    <h1>訓練師列表</h1>
<jsp:useBean id="trainerServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>
<jsp:useBean id="skillServer" scope="page" class="com.woof.skill.service.SkillServiceImpl"/>
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
            <form method="POST" ACTION="${pageContext.request.contextPath}/commentreport" onsubmit="return commentReport()">
            	<ul>            	
                	<c:forEach var="pta" items="${trainer.privateTrainingAppointmentForms}">
                   		<li>${pta.ptaComment}</li>            
                   		<input type="hidden" name="ptano" value="${pta.ptaNo}">
                   		<input type="hidden" name="comment" value="${pta.ptaComment}">
                   		<input type="hidden" name="action" value="report">                		
                    	<button>檢舉評論</button>
                	</c:forEach>
            	</ul>
            </form>
            <button>預約訓練師</button>
        </div>
    </c:forEach>
   
    <button class="btn btn-buyclass" onclick="window.location='${pageContext.request.contextPath}/frontend/privatetrainer/buyclass.jsp'">購買課堂</button>
    <button class="btn btn-back" onclick="window.location='${pageContext.request.contextPath}/index.html'">返回</button>
<%-- <%@ include file="/Footer.file" %> --%>
</body>
</html>
