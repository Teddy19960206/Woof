<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	
	/* 標題 */
	h1 {
    	text-align: center;
    	margin-bottom: 30px;
	}
	
    .container1 {
        width: 80%;
        margin: 0 auto;
        padding: 20px;
    }

    /* 整個訓練師項目 */
	.trainer {
    	display: flex;
    	align-items: center;
    	border: 1px solid #ddd;
    	background-color: #fff;
    	margin-bottom: 20px;
    	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    	padding: 20px;

	}

    .info {
        flex: 1;
        padding-right: 20px;
    }
	
	/* 訓練師名稱 */
	.info h2 {
    	margin-top: 0;
    	font-size: 24px;
    	text-align: center;
	}
	
	/* 專長列表 */
	.info ul {
   		list-style: none;
    	padding: 0;
	}
	
	.info ul li {
    	font-size: 18px;
    	margin-bottom: 5px;
	}
    div.img-div{
        width: 300px;
        height: 300px;
    }
	
    .info img {
        width: 100%;
        height: 100%;
        border-radius: 50%;
        margin-bottom: 10px;
    }

    .comments {
        /*flex: 1;*/
        /*border-left: 1px solid #ddd;*/
        /*padding-left: 20px;*/
    }

    /* 評價列表 */
	.comments ul {
    	list-style: none;
    	padding: 0;
	}

    .comments ul li {
    	font-size: 16px;
    	margin-bottom: 8px;
    	border-bottom: 1px solid #ddd;
   		padding-bottom: 8px;
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

    /* 分頁連結 */
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
    
    .btn-appointment {
    	background-color: #2ecc71;
    	color: #fff;
    	border: none;
    	padding: 10px 20px;
    	cursor: pointer;
    	border-radius: 5px;
    	font-size: 16px;
    	transition: background-color 0.3s;
	}

	/* 移除連結底線 */
	a {
    	text-decoration: none;
	}
	
	.btn-appointment:hover {
    	background-color: #27ae60;
	}
	
	/* 按鈕組 */
	.button-group {
  	 	display: flex;
    	justify-content: center;
    	margin-top: 30px;
	}
/* 	============================== */
	.comment-wrapper {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

.comment {
    flex: 0 0 70%; /* 調整評論區域的寬度 */
}

.report-comment {
    flex: 0 0 28%; /* 調整檢舉評論區域的寬度 */
    text-align: right; /* 右對齊 */
}

.comment ul {
    list-style: none;
    padding: 0;
}

.comment li {
    margin-bottom: 5px;
}

.report-comment button {
    padding: 5px 10px;
    border: none;
    border-radius: 5px;
    background-color: #FF6347; /* 按鈕背景色 */
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s;
}

.report-comment button:hover {
    background-color: #DC143C; /* 滑鼠懸停時的背景色 */
}
.comment-meta {
    font-size: 12px;
    color: #666;
    margin-bottom: 5px;
}

.comment-time {
    font-weight: bold;
    margin-right: 5px;
}

.comment-content {
    margin-top: 5px;
}
h1 {
    text-align: center;
    margin-bottom: 30px;
    margin-left: -60px; /* 負的左邊距 */
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
 
    <div class="container container1">
    <h1>訓練師列表</h1>
    <c:forEach var="trainer" items="${trainers}">
        <div class="trainer row">
            <div class="col-lg-6 col-12 info">
                <div class="col-auto mx-auto text-center">
                    <h2>${trainer.administrator.adminName}</h2>
                    <div class="img-div mx-auto">
                    <c:choose>
					    <c:when test="${empty trainer.administrator.adminPhoto}">
					        <img class="img-fluid" src="${pageContext.request.contextPath}/frontend/images/avatar24-01.png" alt="${trainer.administrator.adminName}">
					    </c:when>
					    <c:otherwise>
					        <img class="img-fluid" src="${pageContext.request.contextPath}/DBPngReader?action=administrator&id=${trainer.administrator.adminNo}" alt="${trainer.administrator.adminName}">
					    </c:otherwise>
					</c:choose>
                    </div>
                    <p>專長:</p>
                    <ul>
                        <c:forEach items="${trainer.skills}" var="trainerskill">
                            <li>${trainerskill.skillName}</li>
                        </c:forEach>
                    </ul>
                    <a href="${pageContext.request.contextPath}/frontend/privatetrainer/appointment.jsp?trainerNo=${trainer.trainerNo}">
                        <buttonf type="button" class="btn btn-appointment">預約訓練師</buttonf>
                    </a>
                </div>
            </div>
            <div class="col-lg-6 col-12 comments text-center">
                <div class="row">
                     <h1>評論:</h1>
                </div>
                <div class="row">
    <c:forEach var="pta" items="${trainer.privateTrainingAppointmentForms}" varStatus="loop">
    <c:if test="${loop.index < 3}">
        <div class="comment-wrapper">
            <div class="comment">
                <ul>
                    <li class="comment-meta">${pta.member.memName} - <span class="comment-time">${pta.commentTime}</span></li>
                    <li class="comment-content">${pta.ptaComment}</li>
                </ul>
            </div>
            <div class="report-comment">
                <form method="POST" action="${pageContext.request.contextPath}/commentreport?action=report" onsubmit="return commentReport()">
                    <input type="hidden" name="ptano" value="${pta.ptaNo}">
                    <input type="hidden" name="comment" value="${pta.ptaComment}">
                    <button>檢舉評論</button>
                </form>
            </div>
        </div>
    </c:if>
</c:forEach>
</div>
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
