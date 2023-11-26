<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="com.woof.latestnews.entity.*"%>
<%
LatestNews ln = (LatestNews) request.getAttribute("latestNews");
%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
<meta charset="UTF-8">
<title>�d��ɮv Woof | �̷s�����s�W</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<style>
    body {
        padding-top: 20px;
    }
    .container {
        max-width: 600px;
    }
    .error-msg {
        font-size: 0.9em;
    }
    .form-group {
        margin-bottom: 10px;
    }
</style>
</head>
<body>
<div class="container">
    <form method="post" action="<%=request.getContextPath()%>/latestNews.do" accept-charset="UTF-8" enctype="multipart/form-data">
        <div class="form-group">
            <label for="LN_PHOTO">�̷s�����Ϥ�</label>
            <input type="file" class="form-control-file" name="LN_PHOTO">
        </div>
        <div class="form-group">
            <label for="LN_TITLE">�������D</label>
            <input type="text" class="form-control" name="LN_TITLE" id="LN_TITLE">
            <small style="color: red;" class="error-msg">${errorMsgs.LN_TITLE}</small>
        </div>
        <div class="form-group">
            <label for="LN_CONTENT">�������e</label>
            <textarea class="form-control" name="LN_CONTENT" id="LN_CONTENT" rows="5" placeholder="�п�J�������e"></textarea>
            <small style="color: red;" class="error-msg">${errorMsgs.LN_CONTENT}</small>
        </div>
        <input type="hidden" name="action" value="add">
        <button type="submit" class="btn btn-primary">�e�X</button>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='<%=request.getContextPath()%>/backend/latestnews/latestNews.jsp'">����</button>
    </form>
</div>
<!-- Bootstrap JavaScript and dependencies -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
