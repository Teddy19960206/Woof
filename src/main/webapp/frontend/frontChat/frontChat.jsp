<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${!empty sessionScope.member}">
<link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/frontChat/css/frontChat.css" />

<div class="chat-box">
    <div class="header">
        <div class="avatar-wrapper avatar-big">
            <img
                    src="https://znews-photo.zadn.vn/w660/Uploaded/pnbcuhbatgunb/2020_03_23/i13863960814_1.jpg"
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

<%--        <!-- 左邊 -->--%>
<%--        <div class="message message-left">--%>
<%--            <div class="avatar-wrapper avatar-small"></div>--%>
<%--            <small>客服</small>--%>
<%--            <div class="bubble bubble-light">Hey anhat!</div>--%>
<%--        </div>--%>

<%--        <!-- 右邊 -->--%>
<%--        <div class="message message-right">--%>
<%--            <small>member1</small>--%>
<%--            <div class="bubble bubble-dark">--%>
<%--                what is going on?aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa--%>
<%--            </div>--%>
<%--        </div>--%>
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