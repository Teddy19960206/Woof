<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/meta.file" %>
    <title>寵毛導師 Woof | 訓練師列表</title>
    <style>



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
<%@ include file="/Header.file" %>
 
<%-- <jsp:useBean id="trainerServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/> --%>

    <div class="container1">
        <h1>訓練師列表</h1>
        <c:forEach var="trainer" items="${trainers}">
        <form method="POST" action="${pageContext.request.contextPath}/commentreport" onsubmit="return commentReport()">
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
<c:forEach var="pta" items="${trainer.privateTrainingAppointmentForms}" varStatus="loop">
    <c:if test="${loop.index < 3}">
        <ul>
            <li>${pta.ptaComment}</li>
            <input type="hidden" name="ptano" value="${pta.ptaNo}">
            <input type="hidden" name="comment" value="${pta.ptaComment}">
            <input type="hidden" name="action" value="report">
            <button>檢舉評論</button>
        </ul>
    </c:if>
</c:forEach>
                <a href="${pageContext.request.contextPath}/frontend/privatetrainer/appointment.jsp?trainerNo=${trainer.trainerNo}"><button type="button">預約訓練師</button></a>
            </div>
        </form>
        </c:forEach>
    </div>
    <p>currentPage = ${currentPage}</p>
    <p>TrainerPageQty = ${TrainerPageQty}</p>
	<!-- 分頁連結 -->
	<c:if test="${currentPage > 1}">
    	<a href="${pageContext.request.contextPath}/trainer?action=getAll&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
    	<a href="${pageContext.request.contextPath}/trainer?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= TrainerPageQty}">
    	<a href="${pageContext.request.contextPath}/trainer?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != TrainerPageQty}">
    	<a href="${pageContext.request.contextPath}/trainer?action=getAll&page=${TrainerPageQty}">至最後一頁</a>&nbsp;
	</c:if>

<!-- 購買課堂和返回按鈕 -->
<button class="btn btn-buyclass" onclick="window.location='${pageContext.request.contextPath}/frontend/privatetrainer/buyClass.jsp'">購買課堂</button>
<button class="btn btn-back" onclick="window.location='${pageContext.request.contextPath}/index.jsp'">返回</button>
	
<%@ include file="/Footer.file" %>

</body>
</html>
