<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 課堂檔期訂單管理</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>

<div class="container" id="allPage">

    <h1 align="center">課堂檔期訂單管理</h1>
    <div class="">
        <div class="row">
            <label class="col-form-label col-5 d-flex justify-content-end">團體課程</label>
            <div class="col-2">
                <select name="class" id="groupClass" class="form-select">
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <label class="col-form-label col-5 d-flex justify-content-end ">狀態</label>
            <div class="col-2">
                <select name="status" id="selectStatus" class="form-select">
                    <option value="">顯示全部</option>
                    <option value="0">未付款</option>
                    <option value="1">已付款</option>
                    <option value="2">已退款</option>
                    <option value="3">已取消</option>
                    <option value="4">已完成</option>
                    <option value="5">退款申請中</option>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <label class="col-form-label col-5 d-flex justify-content-end ">會員帳號</label>
            <div class="col-2">
                <input type="text" name="memNo" id="memNo" class="form-control"/>
            </div>
        </div>

        <div class="row ">
            <div class="col-12 text-center m-3 ">
                <button type="button" id="button" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>


    <div id="show"></div>
</div>

<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/order.js"></script>
</body>
</html>
