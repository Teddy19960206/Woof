<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/backend/backhead.file"%>
<meta charset="UTF-8">
<title>寵毛導師 Woof | 最新消息管理</title>
<!-- 引入 Bootstrap CSS -->

	<style>


.card {
  background-color: #f0f0f0;
}



.fixed-size-image {
  width: 70%; /* 图片宽度自适应容器宽度 */
  height: auto; /* 图片高度自动调整 */
  object-fit: contain; /* 确保图片完整显示，不裁切 */
}
	</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script type="text/javascript">
	// 你的 JavaScript 函數
		function processUpdate(jsonData){
		window.location.href="<%=request.getContextPath()%>/backend/latestnews/latestNewsUpdate.jsp?lnNo="+jsonData.lnNo;
	}
	function processDelete(jsonData){
		$.ajax({
			type:"POST",
			data:jsonData,
			url:"<%=request.getContextPath()%>/latestNews.do?action=del",
					success:function(data){
						alert('刪除成功');
						window.location.reload();
					},
					error: function(xhr,ajaxOptions,thrownError){
						alert("哇 錯了");
					}
		});
	}
	function processAdd(){
		window.location.href="<%=request.getContextPath()%>/backend/latestnews/latestNewsAdd.jsp"
	}
</script>
</head>
<body>
<%@ include file="/backend/backbody.file"%>
<div class="container mt-5"> <!-- mt-5 表示 margin-top: 3rem -->
	<jsp:useBean id="latestNewsService" scope="page"
		class="com.woof.latestnews.service.LatestNewsServiceImpl" />
	<div class="container mt-5">
		<input type="button" class="btn btn-primary mb-3" id="btnadd"
			value="新增" onclick="processAdd();">
		<c:forEach var="latestNews" items="${latestNewsService.allLatestNews}">
			<div class="card mb-3">
				<div class="card-header">消息編號: ${latestNews.lnNo}</div>
				<div class="card-body">
					<h5 class="card-title">消息標題: ${latestNews.lnTitle}</h5>
					<p class="card-text">消息內容: ${latestNews.lnContent}</p>
					<p class="card-text">
						<small class="text-muted">消息時間: ${latestNews.lnTime}</small>
					</p>
					<div class="image-container mb-3">
						<!-- 確保圖片的 Base64 編碼是正確的 -->
						<img src="data:image/jpeg;base64,${latestNews.imgStr1}"
    class="fixed-size-image" alt="News Image">
					</div>
					<input type="button" class="btn btn-secondary" value="修改"
						onclick="processUpdate({lnNo:'${latestNews.lnNo}'});"> <input
						type="button" class="btn btn-danger" value="刪除"
						onclick="processDelete({LN_NO:'${latestNews.lnNo}'});">
				</div>
			</div>
		</c:forEach>
	</div>
	</div>
		<%@ include file="/backend/backfoot.file"%>
</body>
</html>