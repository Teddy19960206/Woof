<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${!empty sessionScope.member}">
<link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/frontChat/css/frontChat.css" />

<div class="chat-box">
    <div class="header">
        <div class="avatar-wrapper avatar-big">
            <img
                    src="${pageContext.request.contextPath}/webutil/icons/happy_1.png"
                    alt="avatar"
                    class="chat-img"
            />
        </div>
        <span class="name">客服</span>
        <span class="options">
          <i class="fas fa-ellipsis-h"></i>
        </span>
    </div>
    <div class="chat-room chat-none" id="chat-room">

<%--        聊天框  --%>
    </div>

    <div class="type-area chat-none">
        <div class="input-wrapper">
            <input type="text" id="inputText" placeholder="請輸入文字" />
        </div>
        <button class="button-send">傳送</button>
    </div>
</div>
<div id="member" style="display: none">${member.memNo}</div>

<script src="${pageContext.request.contextPath}/frontend/frontChat/js/frontChat.js"></script>

</c:if>