<%--
  Created by IntelliJ IDEA.
  User: trick
  Date: 2023/10/20
  Time: 下午 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增技能</title>
</head>
<body>
<h1>新增技能</h1>
<form action="${pageContext.request.contextPath}/skill/addSkill" method="post">
  <label for="skillName">技能名稱：</label>
  <input type="text" name="skillName" id="skillName"/>
  <br/>

  <button type="submit">確定新增</button>
  <button type="button" onclick="history.back()">取消新增</button>
</form>
</body>
</html>
