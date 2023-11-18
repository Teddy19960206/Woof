<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 訓練師技能管理</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
<jsp:useBean id="skillService" scope="page" class="com.woof.skill.service.SkillServiceImpl"/>

<div class="container py-2">

    <h2 align="center">專業技能清單</h2>
    <table class="table table-hover text-center align-middle border">
        <thead class="table-light">
            <tr>
                <th>技能編號</th>
                <th>技能名稱</th>
                <th>編輯</th>
                <th>確定修改</th>
                <th>刪除</th>
            </tr>
        </thead>
        <tbody class="table-group-divider">
            <c:forEach items="${skillService.allSkill}" var="skill">
                <tr>
                    <td>${skill.skillNo}</td>
                    <td>${skill.skillName}</td>
                    <td><button class="btn btn-info edit-button">編輯</button></td>
                    <td><button class="btn btn-primary modify-button" disabled>確定修改</button></td>
                    <td><button class="btn btn-danger delete-button">刪除</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <button type="button" id="addSkill" class="btn btn-primary">新增技能</button>
</div>
<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/member/js/trainerSkill.js"></script>
</body>
</html>
