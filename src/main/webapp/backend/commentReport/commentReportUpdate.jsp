<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<%@ include file="/backend/backhead.file" %>
<title>寵毛導師 Woof | 評論檢舉處理</title>
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
    /* 為 input 和 textarea 元素指定相同的寬度 */
	input[type="text"],
	textarea {
    	width: 100%; /* 設定元素的寬度為其父元素的100% */
    	box-sizing: border-box; /* 讓元素的寬度包括內邊距和邊框 */
	}
	textarea {
    	direction: ltr; /* 設定文字從左到右排列 */
	}
</style>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
	<div class="container py-3">
	<h2>修改評論檢舉狀態</h2>

	<form action="${pageContext.request.contextPath}/commentreport?action=update"
		method="post" enctype="application/x-www-form-urlencoded">
		<label>評論檢舉編號:</label>
		<input	type="text" name="crNo"	value="${param.crNo}" readonly>
		<label>撰寫評論的帳號:</label>
		<input	type="text" name="memNo" value="${param.memNo}" readonly>
		<label>被檢舉的內容:</label>
		<textarea name="crContent" rows="4" cols="40" readonly>
			${param.crContext}
		</textarea>
		<label>留言時間:</label>
		<input	type="text" name="commentTime" value="${param.commentTime}" readonly>
		<c:if test="${not empty param.commentUpTime}">
			<label>留言編輯時間:</label>
			<input	type="text" name="commentUpTime" value="${param.commentUpTime}" readonly>
		</c:if>
		<label>檢舉時間:</label>
		<input	type="text" name="crDate" value="${param.crDate}" readonly>
		<label>處理狀態:</label>
		<select name="crStatus">
   		<option value="0" ${param.crStatus == '0' ? 'selected' : ''}>待處理</option>
		<option value="1" ${param.crStatus == '1' ? 'selected' : ''}>檢舉通過</option>
		<option value="2" ${param.crStatus == '2' ? 'selected' : ''}>檢舉未通過</option>
		</select>
		 <br /> 
		
		<button type="submit">確定</button>
		<button type="button" class="cancel" onclick="history.back()">返回</button>
	</form>
</div>
<%@ include file="/backend/backfoot.file" %>
</body>
</html>