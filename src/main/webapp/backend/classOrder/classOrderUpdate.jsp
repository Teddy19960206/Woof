<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<%@ include file="/backend/backhead.file" %>
<title>寵毛導師 Woof | 課堂訂單管理</title>
<style>

	body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }

     h2 {
         color: #333;
         margin: 20px;
         text-align: center;
     } 

    form {
        background-color: #fff;
        border-radius: 5px;
        padding: 20px;
        width: 400px;
        margin: 0 auto;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    label {
        display: block;
        margin-bottom: 5px;
    }

    input[type="text"],
    select {
        width: calc(100% - 12px);
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    select {
        appearance: none;
        -webkit-appearance: none;
        -moz-appearance: none;
        background: url('data:image/svg+xml;utf8,<svg fill="#666" width="12" height="12" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M7.41 8.59L12 13.17l4.59-4.58L18 10l-6 6-6-6 1.41-1.41z"/></svg>') no-repeat right 8px center;
    }

    button {
        padding: 10px 20px;
        margin-top: 10px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .cancel {
        background-color: #dc3545;
        margin-left: 10px;
    }

    button:hover,
    .cancel:hover {
        opacity: 0.8;
    }
</style>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
	<h2>修改訂單狀態</h2>

	<form action="${pageContext.request.contextPath}/classorder/update"
		method="post" enctype="application/x-www-form-urlencoded">
		<label>課堂訂單編號:</label>
		<input	type="text" name="coNo"	value="${param.coNo}" readonly>
		<label>會員帳號:</label>
		<input	type="text" name="memNo" value="${param.memNo}" readonly>
		<label>購買堂數:</label>
		<input	type="text" name="coBc" value="${param.coBc}" readonly>
		<input	type="hidden" name="coPaymentMethod" value="${param.coPaymentMethod}">
		<input	type="hidden" name="coSmmp" value="${param.coSmmp}">
		<input	type="hidden" name="coTime" value="${param.coTime}">
		<label>實付金額:</label>
		<input	type="text" name="actualAmount" value="${param.actualAmount}">
		<label>訂單狀態:</label>
		<select name="coStatus">
   		<option value="0" ${param.coStatus == '0' ? 'selected' : ''}>未付款</option>
		<option value="1" ${param.coStatus == '1' ? 'selected' : ''}>已付款</option>
		<option value="2" ${param.coStatus == '2' ? 'selected' : ''}>取消訂單</option>
		</select>
		 <br /> 
		
		<button type="submit">修改</button>
		<button type="button" class="cancel" onclick="history.back()">取消修改</button>
	</form>
	
<%@ include file="/backend/backfoot.file" %>
</body>
</html>