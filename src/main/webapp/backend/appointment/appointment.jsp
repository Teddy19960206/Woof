<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 私人預約管理</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
<div class="container py-2" id="allPage">
    <h1 align="center">私人預約單管理</h1>
    <div class="my-2 mx-auto">
        <div class="row">
            <div class="col-3">
            <label class="col-form-label">訓練師名稱</label>
                <div>
                    <select name="trainer" id="trainerNo" class="form-select">
                    </select>
                </div>
            </div>
    		<div class="col-3">
                <label class="col-form-label">會員帳號</label>
                <div>
                    <input type="text" name="memNo" id="memNo" class="form-control"/>
                </div>
            </div>
            <div class="col-3">
                <label class="col-form-label">開始查詢</label>
                <button type="button" id="button" class="btn btn-primary d-block">提交</button>
            </div>
        </div>
    </div>
</div>

<%@ include file="/backend/backfoot.file" %>
</body>
</html>
