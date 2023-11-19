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
          <ul class="list-unstyled chat-list mt-2 mb-0">
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="avatar">
              <div class="about">
                <div class="name">Vincent Porter</div>
                <div class="status"> <i class="fa fa-circle offline"></i> left 7 mins ago </div>
              </div>
            </li>
            <li class="clearfix active">
              <img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="avatar">
              <div class="about">
                <div class="name">Aiden Chavez</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt="avatar">
              <div class="about">
                <div class="name">Mike Thomas</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="avatar">
              <div class="about">
                <div class="name">Christian Kelly</div>
                <div class="status"> <i class="fa fa-circle offline"></i> left 10 hours ago </div>
              </div>
            </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
              <div class="about">
                <div class="name">Monica Ward</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt="avatar">
              <div class="about">
                <div class="name">Dean Henry</div>
                <div class="status"> <i class="fa fa-circle offline"></i> offline since Oct 28 </div>
              </div>
            </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
              <div class="about">
                <div class="name">Monica Ward</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
              <div class="about">
                <div class="name">Monica Ward</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
              <div class="about">
                <div class="name">Monica Ward</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li>

            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
              <div class="about">
                <div class="name">Monica Ward</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li>
            <li class="clearfix">
            <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
            <div class="about">
              <div class="name">Monica Ward</div>
              <div class="status"> <i class="fa fa-circle online"></i> online </div>
            </div>
          </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
              <div class="about">
                <div class="name">Monica Ward</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
              <div class="about">
                <div class="name">Monica Ward</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
              <div class="about">
                <div class="name">Monica Ward</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li><li class="clearfix">
            <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
            <div class="about">
              <div class="name">Monica Ward</div>
              <div class="status"> <i class="fa fa-circle online"></i> online </div>
            </div>
          </li><li class="clearfix">
            <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
            <div class="about">
              <div class="name">Monica Ward</div>
              <div class="status"> <i class="fa fa-circle online"></i> online </div>
            </div>
          </li>
            <li class="clearfix">
              <img src="https://bootdey.com/img/Content/avatar/avatar8.png" alt="avatar">
              <div class="about">
                <div class="name">Monica Ward</div>
                <div class="status"> <i class="fa fa-circle online"></i> online </div>
              </div>
            </li>

          </ul>
        </div>
<%--             --%>


<%--          聊天室內容  --%>
        <div class="chat">
<%--          聊天室header --%>
          <div class="chat-header clearfix">
            <div class="row">
              <div class="col-lg-6">
                <a href="javascript:void(0);" data-toggle="modal" data-target="#view_info">
                  <img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="avatar">
                </a>
                <div class="chat-about">
                  <h6 class="m-b-0">Aiden Chavez</h6>
                  <small>Last seen: 2 hours ago</small>
                </div>
              </div>
            </div>
          </div>
<%--           聊天室訊息框 --%>
          <div class="chat-history">
            <ul class="m-b-0">
              <li class="clearfix">
                <div class="message-data text-right">
                  <span class="message-data-time">10:10 AM, Today</span>
                  <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="avatar">
                </div>
                <div class="message other-message float-right"> Hi Aiden, how are you? How is the project coming along? </div>
              </li>

              <li class="clearfix">
                <div class="message-data">
                  <span class="message-data-time">10:12 AM, Today</span>
                </div>
                <div class="message my-message">Are we meeting today?</div>
              </li>


              <li class="clearfix">
                <div class="message-data">
                  <span class="message-data-time">10:15 AM, Today</span>
                </div>
                <div class="message my-message">Project has been already finished and I have results to show you.</div>
              </li>
              <li class="clearfix">
                <div class="message-data">
                  <span class="message-data-time">10:15 AM, Today</span>
                </div>
                <div class="message my-message">Project has been already finished and I have results to show you.</div>
              </li>
              <li class="clearfix">
                <div class="message-data">
                  <span class="message-data-time">10:15 AM, Today</span>
                </div>
                <div class="message my-message">Project has been already finished and I have results to show you.</div>
              </li>
              <li class="clearfix">
                <div class="message-data">
                  <span class="message-data-time">10:15 AM, Today</span>
                </div>
                <div class="message my-message">Project has been already finished and I have results to show you.</div>
              </li>
              <li class="clearfix">
                <div class="message-data">
                  <span class="message-data-time">10:15 AM, Today</span>
                </div>
                <div class="message my-message">Project has been already finished and I have results to show you.</div>
              </li>

            </ul>
          </div>


<%--        聊天室 發送框 --%>
          <div class="chat-message clearfix">
            <div class="input-group mb-0">

              <input type="text" class="form-control" placeholder="請輸入文字">

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
