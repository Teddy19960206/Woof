<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>刪除</title>

</head>
<body>

	<%@ include file="/backend/backbody.file"%>

    <div class="container">
        <h1>刪除FAQ畫面</h1>
        <form method="post" action="${pageContext.request.contextPath}/faq?action=deletefaq">
            <div class="form-group">
                <label for="faqNo">FAQ編號：</label>
                <input type="text" class="form-control" id="faqNo" name="faqNo">
            </div>
            <button class="btn btn-danger" type="submit">確定刪除</button>
            <a class="btn btn-secondary" href="javascript:history.back()">取消刪除</a>
        </form>
        <a class="btn btn-secondary" href="javascript:history.back()">返回</a>
    </div>

	<%@ include file="/backend/backfoot.file"%>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
