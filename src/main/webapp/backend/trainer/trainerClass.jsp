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

<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/trainer/js/trainerClass.js"></script>
</body>
</html>
