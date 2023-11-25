<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <%@ include file="/meta.file" %>
    <title>寵毛導師 Woof | 檔期訂單</title>
</head>
<style>
    input.form-control{
        text-align: center;
        border: 0 transparent;
        width: auto;
        margin-left: auto;
        margin-right: auto;
        font-size: 20px;
        margin-bottom: 20px;
    }
    label{
        color: #e7770d;
        font-size: 24px;
    }

</style>
<body>
<%@ include file="/Header.file" %>
<div class="container shadow my-3 col-5 p-0" style="min-height: 70%">
    <div class="">
        <h1 align="center" class="p-3">報名成功</h1>
        <div class="mx-auto text-center">
            <img src="${pageContext.request.contextPath}/webutil/icons/success.svg" style="width: 100px" class="img-fluid" />
        </div>
        <div class="col-12 text-center">
            <h1>詳細訂單明細</h1>
        </div>

        <div class="text-center">
            <label class="">訂單編號</label>

            <input type="hidden" id="orderNo" value="${orderNo}"/>
            <input type="text" class="form-control" id="orderNoOriginal" readonly>


            <label class="text-center">會員編號</label>
            <input type="text" class="form-control" id="memNo" readonly>


            <label class="text-center">會員姓名</label>
            <input type="text" class="form-control" id="memName" readonly>



            <label class="text-center">團體課程名稱</label>
            <input type="text" class="form-control" id="groupScheduleName" readonly>


            <label class="text-center">訂單成立時間</label>
            <input type="text" class="form-control" id="gcoDate" readonly>


            <label class="text-center">付款方式</label>
            <input type="text" class="form-control" id="method" readonly>

            <label class="text-center">總金額</label>
            <input type="text" class="form-control" id="total" readonly>

            <label class="text-center">折抵毛毛幣</label>
            <input type="text" class="form-control" id="smmp" readonly>

            <label class="text-center">實付金額</label>
            <input type="text" class="form-control" id="actualAmount" readonly>

            <label class="text-center">訂單狀態</label>
            <input type="text" class="form-control" id="status" readonly>
        </div>


        <div id="show" style="display: none">
            <div class="col-12 text-center">
                <p>匯款帳號</p>
                <p>銀行：合作金庫 (006)</p>
                <p>匯款帳號：3333-333-333333</p>
                <p>付款金額：<font class="text-danger" id="payText"></font></p>
            </div>
        </div>

        <div class="text-center p-3">
            <button class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">返回首頁</button>
        </div>

        </div>
    </div>
</div>
<%@ include file="/Footer.file" %>
<script src="${pageContext.request.contextPath}/frontend/group/js/orderPage.js"></script>
</body>
</html>
