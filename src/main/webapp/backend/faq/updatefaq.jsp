<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>


<!DOCTYPE html>
<html>
<head>
    <title>修改</title>

<%@ include file="/backend/backhead.file"%>
    
</head>
<body>
	<%@ include file="/backend/backbody.file"%>


    <div class="container">
        <h1>修改FAQ畫面</h1>
        <form method="post" action="${pageContext.request.contextPath}/faq?action=updatefaq">
            <div class="form-group">
                <label for="faqNo">FAQ編號：</label>
            	<input type="text" class="form-control" id="faqNo" name="faqNo" value="${param.faqNo}" readonly>
            </div>
            <div class="form-group">
                <label for="faqClass">FAQ類別：</label>
                <input type="text" class="form-control" id="faqClass" name="faqClass" value="${param.faqClass}">
            </div>
            <div class="form-group">
                <label for="faqTitle">FAQ標題：</label>
                <input type="text" class="form-control" id="faqTitle" name="faqTitle" value="${param.faqTitle}">
            </div>
            <div class="form-group">
                <label for="faqContent">FAQ內容：</label>
                <input type="text" class="form-control" id="faqContent" name="faqContent" value="${param.faqContent}">
            </div>
            <button class="btn btn-success" type="submit">確定修改</button>
            <a class="btn btn-secondary" href="javascript:history.back()">取消修改</a>
        </form>
        <a class="btn btn-secondary" href="javascript:history.back()">返回</a>
    </div>

	<%@ include file="/backend/backfoot.file"%>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
