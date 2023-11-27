<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 技能清單</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>

<div class="container p-5">
    <h1 align="center">專業技能清單</h1>
    <div id="showList"></div>
    <div class="text-center divBtn">
        <button type="button" class="btn btn-primary addSkill">新增專業技能</button>
    </div>
</div>


<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/trainer/js/skillList.js"></script>
</body>
</html>
