<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>新增團體課程</h1>

<form action="${pageContext.request.contextPath}/groupcourse/addgroup" method="post" enctype="multipart/form-data">
    <label>技能名稱：</label>
    <select name="skill">
        <c:forEach items="${skills}" var="skill">
            <option value="${skill.skillNo}">${skill.skillName}</option>
        </c:forEach>
    </select>
    <br />

    <label>班別：</label>
    <select name="classType">
        <c:forEach items="${classTypes}" var="classType">
            <option value="${classType.ctNo}">${classType.ctName}</option>
        </c:forEach>
    </select>
    <br />

    <label>圖片：</label>
    <input type="file" name="photo" accept="image/*">
    <br />

    <label>課程內容：</label>
    <textarea name="content"></textarea>
    <br />

    <button type="submit">新增</button>
    <button type="button" onclick="history.back()">取消新增</button>
</form>

</body>
</html>
