<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/meta.file" %>
    <title>寵毛導師 Woof | 訓練師列表</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f7f7f7;
    }

    .container1 {
        width: 80%;
        margin: 0 auto;
        padding: 20px;
    }

    .trainer {
        background-color: #fff;
        border: 1px solid #ddd;
        margin-bottom: 20px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        display: flex;
    }

    .info {
        flex: 1;
        padding-right: 20px;
    }

    .info img {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        margin-bottom: 10px;
    }

    .comments {
        flex: 1;
        border-left: 1px solid #ddd;
        padding-left: 20px;
    }

    .comments ul {
        list-style: none;
        padding: 0;
    }

    .comments ul li {
        margin-bottom: 5px;
    }

    button {
        background-color: #3498db;
        color: #fff;
        border: none;
        padding: 8px 16px;
        cursor: pointer;
        border-radius: 3px;
        font-size: 14px;
        transition: background-color 0.3s;
    }

    button:hover {
        background-color: #2980b9;
    }

    .btn {
        display: block;
        width: 150px;
        margin: 20px auto;
    }

    .btn-back {
        background-color: #e74c3c;
    }

    .btn-back:hover {
        background-color: #c0392b;
    }

    /* 分頁的 CSS */
    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }

    .pagination a {
        padding: 6px 12px;
        margin: 0 3px;
        border: 1px solid #ddd;
        background-color: #f7f7f7;
        color: #333;
        text-decoration: none;
        border-radius: 3px;
    }

    .pagination a.active {
        background-color: #3498db;
        color: #fff;
    }

    .pagination a:hover {
        background-color: #ddd;
    }

    .btn-buyclass {
        background-color: #2ecc71;
    }

    .btn-buyclass:hover {
        background-color: #27ae60;
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
<%@ include file="/Header.file" %>
 
    <div class="container1">
    <h1>訓練師列表</h1>
    <c:forEach var="trainer" items="${trainers}">
        <div class="trainer">
            <div class="info">
                <img src="${pageContext.request.contextPath}/DBPngReader?action=administrator&id=${trainer.administrator.adminNo}" alt="${trainer.administrator.adminName}">
                <h2>${trainer.administrator.adminName}</h2>
                <p>專長:</p>
                <ul>
                    <c:forEach items="${trainer.skills}" var="trainerskill">
                        <li>${trainerskill.skillName}</li>
                    </c:forEach>
                </ul>
                <a href="${pageContext.request.contextPath}/frontend/privatetrainer/appointment.jsp?trainerNo=${trainer.trainerNo}">
                    <button type="button" class="btn btn-appointment">預約訓練師</button>
                </a>
            </div>
            <div class="comments">
                <p>評價:</p>
                <c:forEach var="pta" items="${trainer.privateTrainingAppointmentForms}" varStatus="loop">
                    <c:if test="${loop.index < 3}">
                        <ul>
                            <li>${pta.ptaComment}</li>
                            <form method="POST" action="${pageContext.request.contextPath}/commentreport?action=report" onsubmit="return commentReport()">
                                <input type="hidden" name="ptano" value="${pta.ptaNo}">
                                <input type="hidden" name="comment" value="${pta.ptaComment}">
                                <button>檢舉評論</button>
                            </form>
                        </ul>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
<%--     <p>currentPage = ${currentPage}</p> --%>
<%--     <p>TrainerPageQty = ${TrainerPageQty}</p> --%>
	<!-- 分頁連結 -->
	<div class="pagination">
		<c:if test="${currentPage > 1}">
    		<a href="${pageContext.request.contextPath}/trainer/getAll?page=1">至第一頁</a>&nbsp;
		</c:if>
		<c:if test="${currentPage - 1 != 0}">
    		<a href="${pageContext.request.contextPath}/trainer/getAll?page=${currentPage - 1}">上一頁</a>&nbsp;
		</c:if>
		<c:if test="${currentPage + 1 <= TrainerPageQty}">
    		<a href="${pageContext.request.contextPath}/trainer/getAll?page=${currentPage + 1}">下一頁</a>&nbsp;
		</c:if>
		<c:if test="${currentPage != TrainerPageQty}">
    		<a href="${pageContext.request.contextPath}/trainer/getAll?page=${TrainerPageQty}">至最後一頁</a>&nbsp;
		</c:if>
	</div>
	<!-- 購買課堂和返回按鈕 -->
	<div class="button-group">
		<button class="btn btn-buyclass" onclick="window.location='${pageContext.request.contextPath}/frontend/privatetrainer/buyClass.jsp'">購買課堂</button>
		<button class="btn btn-back" onclick="window.location='${pageContext.request.contextPath}/index.jsp'">返回</button>
	</div>	
<%@ include file="/Footer.file" %>

</body>
</html>
