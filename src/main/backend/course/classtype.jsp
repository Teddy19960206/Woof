<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>查看班別名稱列表</title>
</head>
<body>
<h1>查看班別名稱列表</h1>

<table border="1">
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
            <td><button type="button" class="edit-button">編輯</button></td>
            <td><button type="button" data-id="${classType.ctNo}" class="modify-button" disabled>確認修改</button></td>
            <td><button type="button" data-id="${classType.ctNo}" class="delete-button">刪除</button></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/backend/course/addClassType.jsp"><button>新增班別</button></a>
<a href="${pageContext.request.contextPath}/groupcourse/"><button>返回</button></a>

<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/backend/course/js/editClassType.js"></script>
</body>
</html>
