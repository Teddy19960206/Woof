<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %> --%>
<!DOCTYPE html>
<html>
<head>
<%-- <%@ include file="/meta.file" %> --%>
<!--     <meta charset="UTF-8"> -->
    <title>寵毛導師 Woof | 訓練師列表</title>
    <style>
	body {
    background-color: #FAD02E;
    font-family: Arial, sans-serif;
}

.container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    background-color: #FFF;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

h1 {
    color: #FF5733;
}

.trainer {
    border: 1px solid #FF5733;
    padding: 10px;
    margin: 20px 0;
}

.trainer img {
    max-width: 100px;
    max-height: 100px;
    border-radius: 50%;
    margin-right: 10px;
}

.trainer h2 {
    color: #333;
}

ul {
    list-style-type: disc;
    padding-left: 20px;
}

button {
    background-color: #FF5733;
    color: #FFF;
    border: none;
    padding: 5px 10px;
    cursor: pointer;
}

button:hover {
    background-color: #FF7F55;
}

.btn-buyclass, .btn-back {
    display: block;
    margin: 20px auto;
    text-align: center;
}

footer {
    text-align: center;
    color: #333;
    margin-top: 20px;
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
 
<jsp:useBean id="trainerServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>

    <div class="container">
        <h1>訓練師列表</h1>
        <c:forEach var="trainer" items="${trainerServer.allTrainers}">
            <div class="trainer">
                <img src="${pageContext.request.contextPath}/DBPngReader?action=administrator&id=${trainer.administrator.adminNo}" alt="${trainer.administrator.adminName}">
                <h2>${trainer.administrator.adminName}</h2>
                <p>專長:</p>
                <ul>
                    <c:forEach items="${trainer.skills}" var="trainerskill">
                        <li>${trainerskill.skillName}</li>
                    </c:forEach>
                </ul>
                <p>評價:</p>
                <form method="POST" action="${pageContext.request.contextPath}/commentreport" onsubmit="return commentReport()">
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
    </div>

    <button class="btn btn-buyclass" onclick="window.location='${pageContext.request.contextPath}/frontend/privatetrainer/buyClass.jsp'">購買課堂</button>
    <button class="btn btn-back" onclick="window.location='${pageContext.request.contextPath}/index.jsp'">返回</button>

<%-- <%@ include file="/Footer.file" %> --%>

</body>
</html>
