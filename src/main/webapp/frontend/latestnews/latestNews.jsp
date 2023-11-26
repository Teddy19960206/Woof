<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
  <%@ include file="/meta.file" %>
<meta charset="UTF-8">
<title>寵毛導師 Woof | 最新消息</title>
<!-- 引入 Bootstrap CSS -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
	<style>

   .card {
  background-color: #f0f0f0;
}


 .fixed-size-image {
  width: 50%; /* 讓圖片寬度適應容器寬度 */
  height: auto; /* 高度自動調整以保持圖片比例 */
  object-fit: contain; /* 保持圖片完整顯示在容器內 */
}
.footer-container > * {
  max-width: 100%;
  box-sizing: border-box;
}
.footer-container::after {
  content: "";
  display: table;
  clear: both;
}
body, html {
  margin: 0;
  padding: 0;
}
.footer-container {
  padding: 1rem 0;
  background-color: #f8f9fa; /* 設置一個背景色以便於觀察 */
  border-top: 1px solid #e7e7e7; /* 如果需要，可以添加一個邊框 */
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
	<div class="container mt-5" >

		<c:forEach var="latestNews" items="${latestNewsService.allLatestNews}" varStatus="loop">
		
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
		
<!-- 添加翻頁按鈕 -->
<!-- 分頁導航區域 -->
<c:if test="${fn:length(latestNewsService.allLatestNews) > 2}">
  <div class="container mt-3" >
    <nav aria-label="Page navigation">
      <ul class="pagination justify-content-center">
        <li class="page-item">
          <a class="page-link" href="#" id="previousPage">上一頁</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#" id="firstPage">回到首頁</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#" id="nextPage">下一頁</a>
        </li>
      </ul>
    </nav>
  </div>
</c:if>

</div>
  <%@ include file="/Footer.file" %>




<!-- ...後續的 HTML 略... -->

</body>
<!-- JavaScript -->
<script type="text/javascript">
  $(document).ready(function() {
    var currentPage = 0;
    var pageSize = 2; // 每頁顯示的資料筆數
    var totalItems = ${fn:length(latestNewsService.allLatestNews)}; // 總資料數

    function updatePage() {
      $('.card').hide().slice(currentPage * pageSize, (currentPage + 1) * pageSize).show();
    }

    function scrollToTop() {
      window.scrollTo(0, 0);
    }

    $('#previousPage').on('click', function(event) {
      event.preventDefault();
      if (currentPage > 0) {
        currentPage--;
        updatePage();
        scrollToTop();
      }
    });

    $('#nextPage').on('click', function(event) {
      event.preventDefault();
      if ((currentPage + 1) * pageSize < totalItems) {
        currentPage++;
        updatePage();
        scrollToTop();
      }
    });

    $('#firstPage').on('click', function(event) {
      event.preventDefault();
      currentPage = 0;
      updatePage();
      scrollToTop();
    });

    updatePage();
  });
</script>
</html>