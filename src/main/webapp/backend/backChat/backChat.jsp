<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@ include file="/backend/backhead.file" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backend/backChat/css/backChat.css">
    <title>寵毛導師 Woof | 客服管理</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>


<div class="container py-5">
  <div class="row clearfix">
    <div class="col-lg-12">
      <div class="card chat-app">
        <div id="plist" class="people-list">
<%--          顯示會員  --%>
          <ul class="list-unstyled chat-list mt-2 mb-0" id="chat-list">

<%--            會員列表  --%>

          </ul>
        </div>
<%--             --%>


<%--          聊天室內容  --%>
        <div class="chat">
<%--          聊天室header --%>
          <div class="chat-header clearfix" id="chat-header">
            <div class="row">
              <div class="col-lg-6">
                <a href="javascript:void(0);" data-toggle="modal" data-target="#view_info">
                  <img src="${pageContext.request.contextPath}/webutil/icons/happy_1.png" alt="avatar">
                </a>
<%--                about --%>
                <div class="chat-about">
                  <h6 class="m-b-0">尚未選擇會員</h6>
                  <small>選擇後可發話</small>
                </div>

              </div>
            </div>
          </div>
<%--           聊天室訊息框 --%>
          <div class="chat-history" id="chat-history">
            <ul class="m-b-0" id="input-history">

<%--                聊天框  --%>

            </ul>
          </div>


<%--        聊天室 發送框 --%>
          <div class="chat-message clearfix">
            <div class="input-group mb-0">

              <input type="text" id="inputText" class="form-control" placeholder="請輸入文字" disabled>

              <div class="input-group-prepend">
                <span class="input-group-text"><i class="fa fa-send"></i></span>
              </div>
            </div>
          </div>


        </div>
      </div>
    </div>
  </div>
</div>

<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/backChat/js/backChat.js"></script>
</body>
</html>
