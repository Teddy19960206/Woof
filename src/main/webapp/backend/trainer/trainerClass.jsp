<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <%@ include file="/backend/backhead.file" %>
   <script src='${pageContext.request.contextPath}/webutil/js/index.global.min.js'></script>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/backend/course/css/calendar.css" />
   <title>寵毛導師 Woof | 訓練師課程查詢</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>

<div id="calendar" class="py-2">
</div>
<div style="display: none">
   <div id="year"></div>
   <div id="month"></div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" aria-hidden="true">
   <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title">確定將該日期設定為非授課嗎?</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
         </div>
         <div class="modal-body text-center">
            <div id="showDate"></div>
            <div id="hideDate" style="display:none"></div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="myModal.hide();">取消</button>
            <button type="button" class="btn btn-primary" id="reserveBtn">是，確定</button>
         </div>
      </div>
   </div>
</div>

<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/trainer/js/trainerClass.js"></script>
</body>
</html>
