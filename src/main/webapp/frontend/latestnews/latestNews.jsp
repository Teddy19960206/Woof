<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
  <%@ include file="/meta.file" %>
<meta charset="UTF-8">
<title>最新消息</title>
<!-- 引入 Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<style>

   .card {
  background-color: #f0f0f0;
}


 .fixed-size-image {
  width: 50%; /* 讓圖片寬度適應容器寬度 */
  height: auto; /* 高度自動調整以保持圖片比例 */
  object-fit: contain; /* 保持圖片完整顯示在容器內 */
}

	</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<%@ include file="/Header.file" %>
	<jsp:useBean id="latestNewsService" scope="page"
		class="com.woof.latestnews.service.LatestNewsServiceImpl" />
	<div class="container mt-5">

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
         class="fixed-size-image" alt="News Image"
        onerror="this.onerror=null; this.src='/woof/frontend/images/avatar24-01.png';">
        
</div>

				
				</div>
			</div>
		</c:forEach>
	</div>
	<%@ include file="/Footer.file" %>
</body>
</html>