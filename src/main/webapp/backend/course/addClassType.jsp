<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 新增班別</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
<div class="position-absolute top-50 start-50 translate-middle">
    <div class="container" align="center">
        <div class="row">
            <div class="col-auto">
                <h1>新增班別</h1>
                <form action="${pageContext.request.contextPath}/classtype/addClassType" method="post">
                    <label for="className">班別名稱：</label>
                    <input type="text" name="className" id="className" class="form-control"/>
                    <br/>

                    <button type="submit" class="btn btn-primary">確定新增</button>
                    <button type="button" onclick="history.back()" class="btn btn-secondary">取消新增</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/backend/backfoot.file" %>
</body>
</html>
