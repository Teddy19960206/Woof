<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/meta.file" %>
    <title>寵毛導師 Woof | 課堂數訂單</title>
</head>
<style>
    input{
        text-align: center;
    }
</style>
<body>
<%@ include file="/Header.file" %>
<div class="container shadow my-3 col-5">
    <div class="">

        <h1 align="center" class="p-3">購買成功</h1>
        <div class="col-12 text-center">
            <h3>詳細訂單明細</h3>
        </div>
        <div class="col-12 my-3">
            <div class="row">
                <label class="col-5 text-center">課堂訂單編號</label>
                <div class="col-5">
                    <input type="text" class="form-control" id="orderNo" value="${orderNo}" readonly>
                </div>

            </div>

            <div class="row my-3">
                <label class="col-5 text-center">會員帳號</label>
                <div class="col-5">
                    <input type="text" class="form-control" id="memNo" readonly>
                </div>

            </div>

            <div class="row my-3">
                <label class="col-5 text-center">會員姓名</label>
                <div class="col-5">
                    <input type="text" class="form-control" id="memName" readonly>
                </div>

            </div>


            <div class="row my-3">
                <label class="col-5 text-center">課程數量</label>
                <div class="col-5">
                    <input type="text" class="form-control" id="groupScheduleName" readonly>
                </div>

            </div>

            <div class="row my-3">
                <label class="col-5 text-center">訂單成立時間</label>
                <div class="col-5">
                    <input type="text" class="form-control" id="gcoDate" readonly>
                </div>

            </div>

            <div class="row my-3">
                <label class="col-5 text-center">付款方式</label>
                <div class="col-5">
                    <input type="text" class="form-control" id="method" readonly>
                </div>

            </div>

            <div class="row my-3">
                <label class="col-5 text-center">折抵毛毛幣</label>
                <div class="col-5">
                    <input type="text" class="form-control" id="smmp" readonly>
                </div>

            </div>

            <div class="row my-3">
                <label class="col-5 text-center">實付金額</label>
                <div class="col-5">
                    <input type="text" class="form-control" id="actualAmount" readonly>
                </div>

            </div>

            <div class="row my-3">
                <label class="col-5 text-center">訂單狀態</label>
                <div class="col-5">
                    <input type="text" class="form-control" id="status" readonly>
                </div>

            </div>
            <div id="show" style="display: none">
                <div class="col-12 text-center">
                    <p>匯款帳號</p>
                    <p>銀行：合作金庫 (006)</p>
                    <p>匯款帳號：3333-333-333333</p>
                    <p>付款金額：<font class="text-danger">2300</font></p>
                </div>
            </div>

            <div class="text-center p-3">
                <button class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/index.html'">返回首頁</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="/Footer.file" %>
<script src="${pageContext.request.contextPath}/frontend/privatetrainer/js/orderPage.js"></script>
</body>
</html>
