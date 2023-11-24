<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<!-- 日期的套版 -->
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet"> 
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<meta charset="UTF-8">
<title>寵毛導師 Woof | 管理員修改</title>
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
 	 	    	 /*  $('#ADMIN_PASSWORD').val(jsonObj.adminPassword);*/
	 	    	 $('#ADMIN_TEL').val(jsonObj.adminTel);
	 	    	 $('#ADMIN_ADDRESS').val(jsonObj.adminAddress);
	 	    	 $('#ADMIN_BD').val(jsonObj.adminBd);
	 	    	 $('#EMERGENCY_CONTACTNAME').val(jsonObj.emergencyContactName);
	 	    	 $('#EMERGENCY_CONTACTEL').val(jsonObj.emergencyContactel);
	 	    	 $('#ADMIN_HD').val(jsonObj.adminHd);
	 	    	 $('#ADMIN_RD').val(jsonObj.adminRd);
	 	    	 $('input[name=ADMIN_STATUS][value='+jsonObj.adminStatus+']').prop('checked',true);
	 	    	 $('#ADMIN_VERIFY_STATUS').val(jsonObj.adminVerifyStatus);
	 	    	$('input[name=ADMIN_FUNC_NAME][value='+jsonObj.adminFuncName+']').prop('checked',true);
	 	    	  $('#myphoto').attr('src', "${pageContext.request.contextPath}/DBPngReader?action=administrator&id="+jsonObj.adminNo);
	 	     },
	 	     //Ajax失敗後要執行的function，此例為印出錯誤訊息
	 	     error : function(xhr, ajaxOptions, thrownError) {
	 	      alert("哇 錯了");
	 	     }
	 	});	
	 	// 圖片
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
	 	 $("#ADMIN_HD").datepicker({ dateFormat: 'yy-mm-dd' });
	 	 $("#ADMIN_RD").datepicker({ dateFormat: 'yy-mm-dd' });
	 	 $("#ADMIN_BD").datepicker({ dateFormat: 'yy-mm-dd' });
	})
	
</script>
</head>
<body>
<!--                               request.getContextPath()動態根路徑，action=update找到後端switch(action)的update-->
	<form method="post" action="<%=request.getContextPath()%>/administrator.do?action=update"  enctype="multipart/form-data">
		<table>
		<tr>
			<th>管理員帳號</th>
			<td>
				<input type="text" name="ADMIN_NO" id="ADMIN_NO" value="<%= request.getParameter("adminNo")%>" readonly>
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
				<input type="text" name="ADMIN_ADDRESS" id="ADMIN_ADDRESS">
			</td>
			<td><small class="error-msg" style="color: red;" >${errorMsgs.ADMIN_ADDRESS}</small></td>
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
		<tr>
			<th>管理員到職日</th>
			<td>
				<input type="text" name="ADMIN_HD" id="ADMIN_HD">
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
				<input type="radio" name="ADMIN_STATUS" value="1" >在職
				<input type="radio" name="ADMIN_STATUS" value="2">停職
			</td>
		</tr>
		<tr>
			<th>管理員驗證狀態</th>
			<td>
				<input type="text" name="ADMIN_VERIFY_STATUS" id="ADMIN_VERIFY_STATUS">
			</td>
		</tr>
		<tr>
			<th>管理員帳號狀態</th>
			<td>
				<input type="radio" name="ADMIN_FUNC_NAME" value="0" >無功能
				<input type="radio" name="ADMIN_FUNC_NAME" value="1" >管理員
				<input type="radio" name="ADMIN_FUNC_NAME" value="2">訓練師
			</td>
		</tr>
		
		</table>
  <div>個人大頭貼</div>
   <img src="#" id="myphoto" name="myphoto" accept="image/*" style="width: 200px; height: 200px">
<input type="file" id="ADMIN_PHOTO" name="ADMIN_PHOTO"  accept="image/*">
<input type="button" value="修改照片" onclick="$('#ADMIN_PHOTO').click();" >
		
		<button type="submit">送出</button>
                    <!-- 		取消後跳轉回去首頁 -->
		<input  type="button" onclick="window.location.href='<%=request.getContextPath()%>/frontend/administrator/administrator.jsp'" value="取消">
	</form>
</body>
</html>