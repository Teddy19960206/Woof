<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>skill</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>技能編號</th>
        <th>技能名稱</th>
        <th>編輯</th>
        <th>確認修改</th>
        <th>刪除</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${skills}" var="skill">
        <tr>
            <td>${skill.skillNo}</td>
            <td>${skill.skillName}</td>
            <td><button type="button" class="edit-button">編輯</button></td>
            <td><button type="button" data-id="${skill.skillNo}" class="modify-button" disabled>確認修改</button></td>
            <td><button type="button" data-id="${skill.skillNo}" class="delete-button">刪除</button></td>
        </tr>
    </c:forEach>
    </tbody>
    <a href="${pageContext.request.contextPath}/backend/employee/addSkill.jsp"><button>新增技能</button></a>
<a href="${pageContext.request.contextPath}/backend/employee/trainer.jsp"><button>返回</button></a>

<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/backend/employee/js/editSkill.js"></script>
</table>
</body>
</html>
