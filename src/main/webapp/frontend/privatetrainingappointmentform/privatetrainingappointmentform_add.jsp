<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>新增私人預約單</h1>

<form action="privatetrainingappointmentform?action=add" method="post" enctype="multipart/form-data">
    <label>成員名稱：</label>
    <select name="member">
        <c:forEach items="${members}" var="member">
            <option value="${member.memNo}">${member.memName}</option>
        </c:forEach>
    </select>
    <br />

    <label>訓練師名稱：</label>
    <select name="trainer">
        <c:forEach items="${trainer}" var="trainer">
            <option value="${trainer.trainerNo}">${trainer.trainerName}</option>
        </c:forEach>
    </select>
    <br />

    <label>課堂數：</label>
    <input type="text" name="number">
    <br />

    <button type="submit">新增</button>
    <button type="button" onclick="history.back()">取消新增</button>
</form>

</body>
</html>