<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/meta.file" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/css/groupSchedule.css">
    <title>寵物訓練師 | 團體課程資訊</title>
</head>
<body>
<%@ include file="/Header.file" %>

    <div class="d-flex justify-content-center my-5">
        <img src="${pageContext.request.contextPath}/frontend/images/groupPhoto.jpg" class="img-fluid background">
    </div>


<div class="container mt-5">
    <div class="row">
        <div data-aos="zoom-in" data-aos-duration="1500">
            <h1 class="h5 text-center">通過我們的團體課程，讓您的犬幼犬學會基本命令，改善行為問題，並促進與其他狗狗和人的社交</h1>
        </div>


        <div class="text-center">
            <button type="button" class="m-5 myBtn">成犬班</button>
            <button type="button" class="m-5 myBtn">幼犬班</button>
        </div>
    </div>
</div>

<%@ include file="/Footer.file" %>
</body>
</html>
