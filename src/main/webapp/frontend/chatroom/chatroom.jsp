<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>ChatRoom</title>
</head>
<body>
<jsp:useBean id="chatroomService" scope="page" class="com.woof.chatroom.service.ChatroomServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/chatroom">
    <select name="Chatroom">
            <c:forEach var="chatroom" items="${chatroomService.allChatrooms}">
                <option  value="${chatroom.roomNo} ${chatroom.chatMsgDirect} ${chatroom.chatMsgTime} ${chatroom.chatMsgContext} ${chatroom.chatMsgPhoto}">${chatroom.memNo}</option>
            </c:forEach>
    </select>
    <button type="submit">提交</button>
</form>
</body>
</html>
