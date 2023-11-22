<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.woof.administrator.entity.*" %>
<%
	Administrator admin = (Administrator) request.getAttribute("admin");
%>
<html>
<head>
<!-- 日期的套版 -->
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet"> 
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
<meta charset="UTF-8">
<title>administrator</title>
<script>
      //日期格式
    $(function() {
    	 $("#ADMIN_BD").datepicker({ dateFormat: 'yy-mm-dd' });
    	 $("#ADMIN_RD").datepicker({ dateFormat: 'yy-mm-dd' });
        $("#ADMIN_HD").datepicker({ dateFormat: 'yy-mm-dd' });
        $("#twzipcode").twzipcode({
        	zipcodeIntoDistrict: true, // 郵遞區號自動顯示在區別選單中
        	css: ["city form-control", "town form-control"], // 自訂 "城市"、"地別" class 名稱 
        	countyName: "city", // 自訂城市 select 標籤的 name 值
        	districtName: "town" // 自訂區別 select 標籤的 name 值
       	});
        $('select[name=city],select[name=town]').change(function(){
        	$('#ADMIN_ADDRESS').val('');
        	$('#ADMIN_ADDRESS').val($('select[name=city]').val()+$('select[name=town]').val());
        })
    });
</script>
<style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f0f0;
      margin: 0;
      padding: 0;
    }

    form {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
      background-color: #fff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    th, td {
      padding: 10px;
      border-bottom: 1px solid #ccc;
    }

    th {
      text-align: right;
      font-weight: bold;
    }

    td {
      text-align: left;
    }
   

    input[type="text"],
    input[type="password"],
    input[type="file"] {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    input[type="radio"] {
      margin-right: 10px;
    }

    button[type="submit"],
    input[type="button"] {
      display: inline-block;
      padding: 10px 20px;
      background-color: #3498db;
      color: #fff;
      border: none;
      border-radius: 5px;
      text-align: center;
      text-decoration: none;
      font-size: 16px;
      cursor: pointer;
      margin-top: 10px;
    }

    button[type="submit"]:hover,
    input[type="button"]:hover {
      background-color: #2980b9;
    }
  </style>
</head>
<body>
<!-- request.getContextPath()動態根路徑，action=add找到後端switch(action)的add-->
	<form method="post" action="<%=request.getContextPath()%>/administrator.do" accept-charset="UTF-8" enctype="multipart/form-data">
		<table>
		<tr>
			<th>管理員帳號</th>
			<td>
				<input type="text" name="ADMIN_NO" id="ADMIN_NO" >
			</td>
			<td><small class="error-msg" style="color: red;">${errorMsgs.ADMIN_NO}</small></td>
		</tr>
		<tr>
			<th>管理員密碼</th>
			<td>
				<input type="password" name="ADMIN_PASSWORD" id="ADMIN_PASSWORD" >
			</td>
			<td><small class="error-msg" style="color: red;">${errorMsgs.ADMIN_PASSWORD}</small></td>
		</tr>
		<tr>
			<th>管理員名字</th>
			<td>
				<input type="text" name="ADMIN_NAME" id="ADMIN_NAME"  >
				<td><small class="error-msg" style="color: red;">${errorMsgs.ADMIN_NAME}</small></td>
			</td>
		</tr>
		<tr>
			<th>管理員性別</th>
			<td>
				<input type="radio" name="ADMIN_GENDER" value="1" checked>男
				<input type="radio" name="ADMIN_GENDER" value="0">女
			</td>
			<td><small class="error-msg" style="color: red;">${errorMsgs.ADMIN_GENDER}</small></td>
		</tr>
		<tr>
			<th>管理員信箱</th>
			<td>
				<input type="text" name="ADMIN_EMAIL" id="ADMIN_EMAIL" placeholder="XXX@gmail.com">
			</td>
			<td><small class="error-msg" style="color: red;">${errorMsgs.ADMIN_EMAIL}</small></td>
		</tr>
		<tr>
			<th>管理員電話</th>
			<td>
				<input type="text" name="ADMIN_TEL" id="ADMIN_TEL">
			</td>
			<td><small class="error-msg"  style="color: red;">${errorMsgs.ADMIN_TEL}</small></td>
		</tr>
		<tr>
			<th>管理員地址</th>
			<td>
				<div id="twzipcode"></div>
				<input type="text" id="ADMIN_ADDRESS" name="ADMIN_ADDRESS">
			</td>
				<td><small class="error-msg" style="color: red;" >${errorMsgs.ADMIN_ADDRESS}</small></td>
		</tr>
		<tr>
			<th>管理員生日</th>
			<td>
				<input type="text" id="ADMIN_BD" name="ADMIN_BD">
			</td>
		</tr>
		<tr>
			<th>管理員緊急聯絡人</th>
			<td>
				<input type="text" name="EMERGENCY_CONTACTNAME" id="EMERGENCY_CONTACTNAME">
			</td>
				<td><small class="error-msg" style="color: red;">${errorMsgs.EMERGENCY_CONTACTNAME}</small></td>
		</tr>
		<tr>
			<th>管理員緊急聯絡人電話</th>
			<td>
				<input type="text" name="EMERGENCY_CONTACTEL" id="EMERGENCY_CONTACTEL">
			</td>
				<td><small class="error-msg" style="color: red;">${errorMsgs.EMERGENCY_CONTACTEL}</small></td>
		</tr>
		<tr>
			<th>管理員到職日</th>
			<td>
				<input type="text" name="ADMIN_HD" id="ADMIN_HD" required >
			</td>
		</tr>
		<tr>
			<th>管理員離職日</th>
			<td>
				<input type="text" name="ADMIN_RD" id="ADMIN_RD">
			</td>
		</tr>
		<tr>
			<th>管理員帳號狀態</th>
			<td>
				<input type="radio" name="ADMIN_STATUS" value="0">離職
				<input type="radio" name="ADMIN_STATUS" value="1" checked>在職
				<input type="radio" name="ADMIN_STATUS" value="2">停職
			</td>
		</tr>
		<tr>
			<th>管理員大頭貼</th>
			<td>
				<input type="file" name="ADMIN_PHOTO" >
			</td>
		</tr>
		<tr>
			<th>管理員帳號權限</th>
			<td>
				<input type="radio" name="ADMIN_FUNC_NAME" value="0">無功能
				<input type="radio" name="ADMIN_FUNC_NAME" value="1" checked>管理員
				<input type="radio" name="ADMIN_FUNC_NAME" value="2">訓練師
			</td>
		</tr>
		</table>
        
		<input type="hidden" name="action" value="add">
		<button type="submit">送出</button>
		<!-- 		點擊取消後跳轉回去首頁 -->
		<input  type="button" onclick="window.location.href='<%=request.getContextPath()%>/frontend/administrator/administrator.jsp'" value="取消">
	</form>
</body>
</html>