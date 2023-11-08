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
</head>
<body>

<%@ include file="/backend/backbody.file" %>


<div id='calendar' style="width: 100%"></div>

<div class="modal fade" id="myModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">確定預約該日期嗎?</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body text-center">
        <div id="showDate"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id="reserveBtn">確定預約</button>
      </div>
    </div>
  </div>
</div>
<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/getDetailByDate.js"></script>
</body>
</html>
