
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>訓練師專區</title>
</head>
<body>

<div id="showTrainers"></div>
<div id="showTrianerSkills"></div>
<div id="addSkill"></div>

<a href="${pageContext.request.contextPath}/skill/getNotExistSKill"><button>新增訓練師技能</button></a>
<a href="${pageContext.request.contextPath}/skill/allSkill"><button type="button">技能列表</button></a>
<a href="${pageContext.request.contextPath}/backend/"><button type="button">回到後台頁面</button></a>
<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/backend/employee/js/trainer.js"></script>
</body>
</html>
