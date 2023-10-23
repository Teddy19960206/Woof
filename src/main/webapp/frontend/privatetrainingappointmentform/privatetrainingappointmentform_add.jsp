<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <style>
        /* 在这里添加你的 CSS 样式 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        h1 {
            color: #0074d9;
        }
        label {
            font-weight: bold;
        }
        select, input {
            padding: 5px;
            margin: 5px;
        }
        button {
            background-color: #0074d9;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>新增私人預約單</h1>

<form action="${pageContext.request.contextPath}/privatetrainingappointmentform/privatetrainingappointmentform" method="post" enctype="multipart/form-data">
    <label>成員名稱：</label>
    <select name="member">
        <c:forEach items="${members}" var="member">
            <option value="${member.memNo}">${member.memName}</option>
        </c:forEach>
    </select>
    <br />

    <label>訓練師編號：</label>
    <select name="trainer">
        <c:forEach items="${trainers}" var="trainer">
            <option value="${trainer.trainerNo}">${trainer.trainerNo}</option>
        </c:forEach>
    </select>
    <br />

    <label>課堂數：</label>
    <input type="text" name="number" required>
    <br />
	<input type="hidden" name="action" value="add">
    <button type="submit">新增</button>
    <button type="button" onclick="history.back()">取消新增</button>
</form>

</body>
</html>