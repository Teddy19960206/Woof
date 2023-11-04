<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/SkillsList/addSkillsList">
    <select name="skill">
        <c:forEach items="${notExistSkill}" var="skill">
            <option value="${skill.skillNo}">${skill.skillName}</option>
        </c:forEach>
    </select>
    <button type="submit">新增技能</button>
</form>
</body>
</html>
