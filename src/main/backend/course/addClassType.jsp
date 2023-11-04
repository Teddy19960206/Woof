<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增班別</title>
</head>
<body>
<h1>新增班別</h1>
<form action="${pageContext.request.contextPath}/classtype/addClassType" method="post">
    <label for="className">班別名稱：</label>
    <input type="text" name="className" id="className"/>
    <br/>

    <button type="submit">確定新增</button>
    <button type="button" onclick="history.back()">取消新增</button>
</form>
</body>
</html>
