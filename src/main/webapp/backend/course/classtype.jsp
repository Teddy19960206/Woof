<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>查看班別名稱列表</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
<div class="container py-3">
    <h1 align="center" class="my-3">查看班別名稱列表</h1>

    <table class="table">
        <thead>
            <tr>
                <th>班別編號</th>
                <th>班別名稱</th>
                <th>編輯</th>
                <th>確認修改</th>
                <th>刪除</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${classTypes}" var="classType">
            <tr>
                <td>${classType.ctNo}</td>
                <td>${classType.ctName}</td>
                <td><button type="button" class="edit-button btn btn-info">編輯</button></td>
                <td><button type="button" data-id="${classType.ctNo}" class="modify-button btn btn-primary" disabled>確認修改</button></td>
                <td><button type="button" data-id="${classType.ctNo}" class="delete-button btn btn-danger">刪除</button></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="col-12 text-center">
        <a href="${pageContext.request.contextPath}/backend/course/addClassType.jsp"><button class="btn btn-primary">新增班別</button></a>
        <a href="${pageContext.request.contextPath}/groupcourse/"><button class="btn btn-secondary">返回</button></a>
    </div>
</div>
<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/editClassType.js"></script>
</body>
</html>
