<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.woof.member.*"%>
<html>
<head>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>更新會員資料</title>
<script>
	// 日期選擇器初始化
	$(function() {
		$("#memBd").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
<script>
    function confirmUpdate() {
        return confirm('您確定要修改這些資料嗎？');
    }
</script>
<script>
$(document).ready(function(){
    $.ajax({
        ///指定http參數傳輸格式為POST
        type : "POST",

        data : {memNo : "<%=request.getParameter("memNo")%>"},
        ///請求目標的url
        url : "<%=request.getContextPath()%>/member1.do?action=query",
         //Ajax成功後執行的function，response為回傳的值
         success : function(data) {
          var jsonObj = JSON.parse(data);
          console.log(jsonObj)
          $('#memNo').val(jsonObj.memNo);
          $('#memName').val(jsonObj.memName);
          $(
            'input[name=memGender][value='
              + jsonObj.memGender
              + ']').prop('checked',
            true);
          $('#photo').attr('src', "${pageContext.request.contextPath}/DBPngReader?action=member&id="+jsonObj.memNo);
          $('#memEmail').val(jsonObj.memEmail);
          $('#memPassword').val(
            jsonObj.memPassword);
          $('#memTel').val(jsonObj.memTel);
          $('#memAddress')
            .val(jsonObj.memAddress);
          $('#totalClass')
            .val(jsonObj.totalClass);
          $('#momoPoint').val(jsonObj.momoPoint);
          console.log($('#memBd'));
          $('#memBd').val(jsonObj.memBd);
          $(
            'input[name=memStatus][value='
              + jsonObj.memStatus
              + ']').prop('checked',
            true); 
         },
         //Ajax失敗後要執行的function，此例為印出錯誤訊息
         error : function(xhr, ajaxOptions,
           thrownError) {
          alert("哇 錯了");
         }
        });
     })
</script>
<script src="https://kit.fontawesome.com/3f37e88a3b.js" crossorigin="anonymous"></script>
<style>
body {
	background-color: #fff4e5; /* 淺橘色背景 */
}

.container {
	padding-top: 40px;
	max-width: 600px;
}

.custom-card {
	background: #ffffff; /* 白色卡片背景 */
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
	margin-bottom: 20px;
}

.custom-header {
	background: #ffa726; /* 橘色標題欄 */
	color: #ffffff;
	padding: 10px 20px;
	border-radius: 10px 10px 0 0;
	margin: -20px -20px 20px -20px;
}

.form-group label {
	font-weight: 600;
}

.error-msg {
	color: #cc0000;
}

.form-control {
	border-radius: 5px;
	border: 1px solid #ffa726; /* 橘色邊框 */
}

.btn-custom {
	border-radius: 5px;
	padding: 10px 30px;
}

.btn-primary {
	background-color: #ffa726; /* 橘色按鈕 */
	border: none;
}

.btn-secondary {
	background-color: #f0f0f0; /* 淺灰色按鈕 */
	color: #333;
	border: none;
}

.btn-primary:hover, .btn-primary:focus {
	background-color: #fb8c00; /* 按鈕懸停橘色 */
	border: none;
}

.btn-secondary:hover, .btn-secondary:focus {
	background-color: #e0e0e0; /* 按鈕懸停淺灰色 */
	color: #333;
}
</style>

<style>
  #preview {
   border: 1px solid lightgray;
   display: inline-block;/*  */
   width: 300px;
   min-height: 400px;
   position: relative;
  }
 
 #preview span.text {
  position: absolute;
  display: inline-block;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  z-index: -1;
  color: lightgray;
 }
 
 #preview img.preview_img {
  width: 100%;
 } 
</style>
</head>
<body>
	<!--request.getContextPath()動態根路徑，action=update找到後端switch(action)的update-->

	<div class="container">
		<div class="custom-card">
			<div class="custom-header text-center">
				<h3>基本資料</h3>
			</div>
				<a href="/woof/index.jsp" style="position: absolute; top: 10px; right: 20px;"><i class="fa-solid fa-house"></i></a>

			<form method="post"
				action="${pageContext.request.contextPath}/member1.do"
				enctype="multipart/form-data" accept-charset="UTF-8"
				onsubmit="return confirmUpdate()">
				<div class="form-group">
					<label for="memNo">會員帳號:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memNo"
								id="memNo" size="45" />
						</div>
					</div>
<%-- 					<small class="error-msg">${errorMsgs.memNo}</small> --%>
				</div>
				<div class="form-group">
					<label for="memName">會員姓名:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memName"
								id="memName" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memName}</small>
				</div>
				<div class="form-group">
					<label for="memGender">性別:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memGender"
								id="memGenderM" value="M" checked> <label
								class="form-check-label" for="memGenderM">男</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memGender"
								id="memGenderF" value="F"> <label
								class="form-check-label" for="memGenderF">女</label>
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memGender}</small>
				</div>
				<div class="form-group">
					<label for="memPhoto">會員相片:</label>
					<div>
						<div class="form-check form-check-inline">
							<!-- 預覽圖片區塊 -->
							<div id="preview" style="margin-bottom: 10px;">
								<img id="photo" class="preview_img" onerror="this.style.display='none'" /> 
								<span class="text"></span>
							</div>
							<!-- 檔案上傳輸入框 -->
							<input class="form-check-input" type="file" name="memPhoto"
								accept="image/*" id="p_file" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="memEmail">會員信箱:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="email" name="memEmail"
								id="memEmail" placeholder="XXX@gmail.com" size="45" />
						</div>
					</div>
<%-- 					<small class="error-msg">${errorMsgs.memEmail}</small> --%>
				</div>
				<div class="form-group">
					<label for="memPassword">密碼:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memPassword"
								id="memPassword" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memPassword}</small>
				</div>
				<div class="form-group">
					<label for="memTel">會員電話:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="tel" name="memTel"
								id="memTel" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memTel}</small>
				</div>
				<div class="form-group">
					<label for="memAddress">地址:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="Text" name="memAddress"
								id="memAddress" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memAddress}</small>
				</div>
				<div class="form-group">
					<label for="memBd">生日:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="Text" name="memBd"
								id="memBd" size="45" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="momoPoint">毛毛幣:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="momoPoint"
								id="momoPoint" size="45" readonly />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="totalClass">總堂數:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="totalClass"
								id="totalClass" size="45" readonly />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="memStatus">狀態:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memStatus"
								id="memStatus" value="0" disabled> <label
								class="form-check-label" for="memStatus">停權</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memStatus"
								id="memStatus" value="1" disabled> <label
								class="form-check-label" for="memStatus">正常</label>
						</div>
					</div>
				</div>

				<br> <input type="hidden" name="action" value="update">
				<button type="submit">送出</button>
				<button type="button" onclick="history.back()">取消修改</button>
				<button type="button" name="delete" id="delete"
					data-id="${member.memNo}">刪除圖片</button>
			</form>
		</div>
	</div>
	<!-- Bootstrap JavaScript -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/backend/member/js/updatemember.js"></script>
</body>
</html>
