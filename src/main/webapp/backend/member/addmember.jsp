<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.woof.member.*"%>
<html>
<head>
<!-- 日期的套版 -->
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員資料新增</title>
<script>
	//日期格式//
	$(function() {
		$("#memBd").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
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
/* 紅色星號 */
.required {
    color: red;
}
</style>
<style>
#preview {
	border: 1px solid lightgray;
	display: inline-block; /*  */
	width: 200px;
	min-height: 250px;
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
/* 紅色星號 */
.required {
    color: red;
</style>
<style>
/* 密碼表情符號 */
.form__input:valid + .icon::after {
  content: '😃';
}
.form__input:invalid + .icon::after {
  content: '😳';
}
</style>
</head>
<div class="container">
		<div class="custom-card">
			<div class="custom-header text-center">
				<h3>會員資料新增</h3>
			</div>
			 <a href="/woof/backend/index.jsp"style="position: absolute; top: 10px; right: 20px;"><i class="fa-solid fa-house"></i></a>
			<form method="post"
		action="${pageContext.request.contextPath}/member.do" enctype="multipart/form-data" accept-charset="UTF-8">
				<!-- 其他表單元素 -->
					<div class="form-group">
					<label for="memNo"><span class="required">*</span>會員帳號:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memNo"
								id="memNo" size="45"  required  />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memNo}</small>
				</div>
				<div class="form-group">
					<label for="memName"><span class="required">*</span>會員姓名:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memName"
								id="memName" size="45"  required  />
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
								<img id="photo" class="preview_img" src="#" alt="會員相片預覽"
									onerror="this.style.display='none'" /> <span class="text">
								</span>
							</div>
							<!-- 檔案上傳輸入框 -->
							<input class="form-check-input" type="file" name="memPhoto"
								accept="image/*" id="p_file" />
						</div>
						</div>
						</div>
				<div class="form-group">
					<label for="memEmail"><span class="required">*</span>會員信箱:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="email" name="memEmail"
								id="memEmail" placeholder="XXX@gmail.com" size="45"  required  />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memEmail}</small>
				</div>
				<div class="form-group">
					<label for="memPassword"><span class="required">*</span>密碼(需大於六個字):</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form__input"  pattern=".{6,}" type="TEXT" name="memPassword"
								id="memPassword" size="45" required /><span class="icon"></span>
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memPassword}</small>
				</div>
				<div class="form-group">
					<label for="memTel"><span class="required">*</span>會員電話:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="tel" name="memTel"
								id="memTel" size="45"  required  />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memTel}</small>
				</div>
				<div class="form-group">
					<label for="memAddress"><span class="required">*</span>地址:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="Text" name="memAddress"
								id="memAddress" size="45"  required  />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memAddress}</small>
				</div>
				<div class="form-group">
					<label for="memBd">生日:<span>(西元-月-日)</span></label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="Text" name="memBd"
								id="memBd" size="45" />
						</div>
					</div>
				</div>			
				<div class="form-group">
					<label for="momoPoint"><span class="required">*</span>毛毛幣:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="momoPoint"
								id="momoPoint" size="45"  required />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="totalClass"><span class="required">*</span>總堂數:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="totalClass"
								id="totalClass" size="45"  required />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="memStatus">狀態:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memStatus"
								id="memStatus" value="1"checked> <label
								class="form-check-label" for="memStatus">正常</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memStatus"
								id="memStatus" value="0"> <label
								class="form-check-label" for="memStatus">停權</label>
						</div>
					</div>
				</div>
		<input type="hidden" name="action" value="add">
		<button type="submit" class="btn btn-primary btn-custom">新增</button>
		<button type="button" onclick="history.back()" class="btn btn-secondary btn-custom">取消新增</button>
	</form>
		</div>
	</div>
	<!-- Bootstrap JavaScript -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/backend/member/js/updatemember.js"></script>
</body>
</html>