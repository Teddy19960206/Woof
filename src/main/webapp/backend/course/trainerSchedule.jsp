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
<style>

  body {

    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-height: 75vh;
    width: auto;
    margin: 20px;
    padding-right: 30px;
    z-index: -1;
  }

  a {
    text-decoration: none;
    color: black;
  }
  div#showDate{
    font-size: 30px;
  }
  .fc-event, .event-title {
    padding: 0 1px;
    white-space: normal;
  }
</style>
</head>
<body>

<%@ include file="/backend/backbody.file" %>


<div id='calendar' style="width: 100%"></div>

<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">確定預約該日期嗎?</h5>
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
