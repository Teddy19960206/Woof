<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<!-- 日期的套版 -->
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet"> 
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
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
    
    .button-container {
    display: flex;
    justify-content: center; /* 這將使按鈕在容器中水平置中 */
    padding: 10px 0; /* 添加一些間隔 */
}

/* 調整按鈕的外邊距以增加按鈕之間的空間 */
button[type="submit"],
input[type="button"] {
    margin: 0 10px; /* 左右邊距設為10px */
}
      .photo-section {
    display: flex;
    flex-direction: column; /* 子元素垂直排列 */
    align-items: center; /* 子元素在交叉軸上置中，對於 column 方向的 flex 容器，這意味著水平置中 */
    margin-top: 20px; /* 可以調整適合您的佈局 */
}
  </style>
<meta charset="UTF-8">
<title>寵毛導師 Woof | 訓練師修改</title>
<script>
	$(document).ready(function(){
	 	 $.ajax({
	 	     //指定http參數傳輸格式為POST
	 	     type : "POST",
	 	     data : {ADMIN_NO : '<%= request.getParameter("adminNo")%>'},
	 	     //請求目標的url
	 	     url : "<%=request.getContextPath()%>/administrator.do?action=query",
	 	     //Ajax成功後執行的function，response為回傳的值
	 	     success : function(data) {
	 	    	 var jsonObj = JSON.parse(data);
	 	    	 $('#ADMIN_NO').val(jsonObj.adminNo);
	 	    	 $('#ADMIN_NAME').val(jsonObj.adminName);
	 	    	 $('input[name=ADMIN_GENDER][value='+jsonObj.adminGender+']').prop('checked',true);
	 	    	 $('#ADMIN_EMAIL').val(jsonObj.adminEmail);
	 	    	/* $('#ADMIN_PASSWORD').val(jsonObj.adminPassword);*/
	 	    	 $('#ADMIN_TEL').val(jsonObj.adminTel);
	 	    	 $('#ADMIN_ADDRESS').val(jsonObj.adminAddress);
	 	    	 $('#ADMIN_BD').val(jsonObj.adminBd);
	 	    	 $('#EMERGENCY_CONTACTNAME').val(jsonObj.emergencyContactName);
	 	    	 $('#EMERGENCY_CONTACTEL').val(jsonObj.emergencyContactel);
	 	    	 $('#ADMIN_HD').val(jsonObj.adminHd);
	 	    	 $('input[name=ADMIN_STATUS][value='+jsonObj.adminStatus+']').prop('checked',true);
	 	    	 $('#myphoto').attr('src', "${pageContext.request.contextPath}/DBPngReader?action=administrator&id="+jsonObj.adminNo);
	 	     },
	 	     //Ajax失敗後要執行的function，此例為印出錯誤訊息
	 	     error : function(xhr, ajaxOptions, thrownError) {
	 	      alert("哇 錯了");
	 	     }
	 	});	
	 	  $("#ADMIN_PHOTO").change(function() {
	 	        if (this.files && this.files[0]) {
	 	            var reader = new FileReader();
	 	            reader.onload = function(e) {
	 	                $('#myphoto').attr('src', e.target.result);
	 	            }
	 	            reader.readAsDataURL(this.files[0]);
	 	        }
	 	    });
	 
	    
	 	 //日期格式

	 	 $("#ADMIN_BD").datepicker({ dateFormat: 'yy-mm-dd' });
	 	 $("#ADMIN_HD").datepicker({ dateFormat: 'yy-mm-dd' });
	})
	

</script>
</head>
<body>
<!--                               request.getContextPath()動態根路徑，action=update找到後端switch(action)的update-->
	<form method="post" action="<%=request.getContextPath()%>/administrator.do?action=update2" enctype="multipart/form-data">
		<input type="file" style="display:none;" id="ADMIN_PHOTO" name="ADMIN_PHOTO"> 
		<table>
		<tr>
			<th>管理員帳號</th>
			<td>
				<input type="text" name="ADMIN_NO" id="ADMIN_NO" value="<%= request.getParameter("adminNo")%>" readonly >
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
				<input type="text" name="ADMIN_NAME" id="ADMIN_NAME" required >
			</td>
			<td><small class="error-msg" style="color: red;">${errorMsgs.ADMIN_NAME}</small></td>
		</tr>
		<tr>
			<th>管理員性別</th>
			<td>
				<input type="radio" name="ADMIN_GENDER" value="1" >男
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
			<td><small class="error-msg" style="color: red;">${errorMsgs.ADMIN_TEL}</small></td>
		</tr>
		<tr>
			<th>管理員地址</th>
			<td>
				<input type="text" name="ADMIN_ADDRESS" id="ADMIN_ADDRESS">
			</td>
			<td><small class="error-msg" style="color: red;">${errorMsgs.ADMIN_ADDRESS}</small></td>
		</tr>
		<tr>
			<th>管理員生日</th>
			<td>
				<input type="text" name="ADMIN_BD" id="ADMIN_BD">
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
	
		</table>
<!-- 	顯示大頭貼的DIV -->
<!-- 	<img> -->
 <div class="photo-section">
 <div>個人大頭貼</div>
  <img src="#" id="myphoto" name="myphoto" accept="image/*" style="width: 200px; height: 200px">
<input type="file" id="ADMIN_PHOTO" name="ADMIN_PHOTO" style="display:none;" accept="image/*">
</div>
<div class="button-container">
    <input type="button" value="修改照片" onclick="$('#ADMIN_PHOTO').click();" >
    <button type="submit">送出</button>
    <input type="button" onclick="window.location.href='<%=request.getContextPath()%>/frontend/administrator/administratorcenter.jsp'" value="取消">
</div>
	</form>




</body>
</html>