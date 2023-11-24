<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${!empty sessionScope.member}">
<link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/frontChat/css/frontChat.css" />
<style>
  #chat-icon {
    position: fixed;
    bottom: 50px;
    right: 50px;
    width: 75px;
    height: 75px;
    border-radius: 50%;
    text-align: center;
    line-height: 50px;
    cursor: pointer;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
    transition: transform 1s ease;
 }
 #chat-icon img {
    max-width: 100%; /* 確保圖片不會超過容器寬度 */
    max-height: 100%; /* 確保圖片不會超過容器高度 */
    border-radius: 50%; /* 圓形圖片 */
    object-fit: cover; /* 保持圖片的比例 */
}
</style>
<script>
$(document).ready(function() {
    $('#chat-icon').click(function() {
        $('#chat-box').toggle(); // 切換顯示和隱藏
    });
});
</script>
<!-- 聊天室圖標 -->
<div id="chat-icon" class="chat-icon">
    <img src="${pageContext.request.contextPath}/webutil/icons/6.jpg" />
</div>
<div class="chat-box" id="chat-box" style="display: none;">
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