<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='${pageContext.request.contextPath}/webutil/js/index.global.min.js'></script>
<link href="${pageContext.request.contextPath}/webutil/css/bootstrap.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/webutil/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>
<%@ include file="/backend/backhead.file" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/backend/course/css/calendar.css" />
<title>寵毛導師 Woof | 檔期團課課程查詢</title>
</head>
<body>

<%@ include file="/backend/backbody.file" %>

<div>
  <h2 class="text-center py-3">檔期團課課程查詢</h2>
</div>
<div id='calendar'></div>
<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/getDetailByDate.js"></script>
</body>
</html>
