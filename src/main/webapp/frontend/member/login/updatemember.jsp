<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
<script
	src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>更新會員資料</title>
<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/webutil/icons/happy_1.png" />
<script>
	// 日期選擇器初始化
	$(function() {
		$("#memBd").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
<script>
    function togglePasswordVisibility(inputId, toggleIcon) {
        var input = document.getElementById(inputId);
        if (input.type === "password") {
            input.type = "text";
            toggleIcon.classList.add('fa-eye');
            toggleIcon.classList.remove('fa-eye-slash');
        } else {
            input.type = "password";
            toggleIcon.classList.remove('fa-eye');
            toggleIcon.classList.add('fa-eye-slash');
        }
    }
</script>
<script>
	$(function() {
		$("#twzipcode").twzipcode({
			zipcodeIntoDistrict : true,
			css : [ "city form-control", "town form-control" ],
			countyName : "city",
			districtName : "town"
		});
		$('select[name=city],select[name=town]').change(
				function() {
					$('#memAddress').val(
							$('select[name=city]').val() + ' '
									+ $('select[name=town]').val());
				});
	});
</script>
<script>
function confirmUpdate() {
    // 使用 SweetAlert 顯示確認對話框
    Swal.fire({
        title: '您確定要修改這些資料嗎？',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '是的，我要修改！',
        cancelButtonText: '取消'
    }).then((result) => {
        // 如果用戶確認，則提交表單
        if (result.isConfirmed) {
            document.getElementById("update").submit(); 
        }
    });
    // 阻止表單的預設提交行為
    return false;
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
      /*     $('#memPassword').val(jsonObj.memPassword); */
          $('#memTel').val(jsonObj.memTel);
          $('#memAddress')
            .val(jsonObj.memAddress);
          $('#totalClass')
            .val(jsonObj.totalClass);
          $('#momoPoint').val(jsonObj.momoPoint);
          console.log($('#memBd'));
          $('#memBd').val(jsonObj.memBd);
          $('#memStatus').val(jsonObj.memStatus);
         },
         //Ajax失敗後要執行的function，此例為印出錯誤訊息
         error : function(xhr, ajaxOptions,
           thrownError) {
          alert("哇 錯了");
         }
        });
     })
</script>
<script src="https://kit.fontawesome.com/3f37e88a3b.js"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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

.btn-third {
	background-color: #f0f0f0; /* 淡紅色按鈕 */
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

.btn-third:hover, .btn-third:focus {
	background-color: #e0e0e0; /* 按鈕懸停淡紅色 */
	color: red;
}

.fa-camera {
	font-size: 24px; /* 設定圖示大小，您可以根據需要調整這個值 */
}

.img-box {
	position: absolute;
	bottom: 0px;
	right: -30px;
	cursor: pointer;
}

.photo-container {
	position: relative;
	/* 其他必要的樣式 */
}
</style>
<style>
.twzipcode .form-control {
	display: inline-block;
	width: auto;
	margin-right: 10px;
}
</style>
<style>
/* 密碼表情符號 */
.form__input:valid+.icon::after {
	font-size: 24px;
	content: '😃';
}

.form__input:invalid+.icon::after {
	font-size: 24px;
	content: '😳';
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
			<a href="/woof/index.jsp"
				style="position: absolute; top: 10px; right: 20px;"><i
				class="fa-solid fa-house"></i></a>

			<form method="post"
				action="${pageContext.request.contextPath}/member1.do"
				enctype="multipart/form-data" accept-charset="UTF-8"
				onsubmit="return confirmUpdate()" id="update">
				<div class="form-group">
					<label for="memNo">會員帳號:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memNo"
								id="memNo" size="51" readonly
								style="background-color: #e9ecef; border: 1px solid #ced4da;" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="memName"><span class="required">*</span>會員姓名:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memName"
								id="memName" size="51" required />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memName}</small>
				</div>
				<div class="form-group">
					<label>性別:</label>
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
					<label>會員相片:</label>
					<div>
						<div class="form-check form-check-inline">
							<!-- 預覽圖片區塊 -->
							<div id="preview" style="margin-bottom: 10px;">
								<img id="photo" class="preview_img"
									onerror="this.style.display='none'" /> <span class="text"></span>
							</div>
							<label for="p_file" class="img-box"> <i
								class="fa-solid fa-camera"></i>
							</label>
							<!-- 檔案上傳輸入框 -->
							<input class="form-check-input" type="file" name="memPhoto"
								accept="image/*" id="p_file" style="display: none;" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="memEmail">會員信箱:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="email" name="memEmail"
								id="memEmail" placeholder="XXX@gmail.com" size="51" readonly
								style="background-color: #e9ecef; border: 1px solid #ced4da;" />
						</div>
					</div>
					<%-- <small class="error-msg">${errorMsgs.memEmail}</small> --%>
				</div>
				<div class="form-group">
					<label for="memPassword">密碼(需大於六個字):</label>
					<div style="position: relative;">
						<input class="form__input" pattern=".{6,}" type="password"
							name="memPassword" id="memPassword" size="51" /> <i
							class="fa fa-eye-slash"
							style="position: absolute; right: 120px; top: 6px; cursor: pointer;"
							onclick="togglePasswordVisibility('memPassword', this)"
							id="togglePassword"></i>
						<!-- 	<span class="icon" style="position: absolute; right: 10; top: 6px;"></span> -->
					</div>
					<small class="error-msg">${errorMsgs.memPassword}</small>
				</div>
				<div class="form-group">
					<label for="memTel"><span class="required">*</span>會員電話:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="tel" name="memTel"
								id="memTel" size="51" required />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memTel}</small>
				</div>
				<div class="form-group">
					<label><span class="required">*</span>地址:</label>
					<div class="form-check form-check-inline">
						<div id="twzipcode" class="twzipcode"></div>
						<input class="form-check-input" type="text" name="memAddress"
							id="memAddress" placeholder="詳細地址（如街道、門牌號等" size="30" required />
					</div>
					<small class="error-msg">${errorMsgs.memAddress}</small>
				</div>
				<div class="form-group">
					<label for="memBd"><span class="required">*</span>生日:<span>(西元-月-日)</span></label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="Text" name="memBd"
								id="memBd" size="51" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="momoPoint">毛毛幣:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="momoPoint"
								id="momoPoint" size="51" readonly
								style="background-color: #e9ecef; border: 1px solid #ced4da;" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="totalClass">總堂數:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="totalClass"
								id="totalClass" size="51" readonly
								style="background-color: #e9ecef; border: 1px solid #ced4da;" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="memStatus">狀態:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="memStatus"
								id="memStatus" size="51" readonly
								style="background-color: #e9ecef; border: 1px solid #ced4da;" />
						</div>
					</div>
				</div>
				<br>
				<div>
					<input type="hidden" name="action" value="update">
					<button type="submit" class="btn btn-primary btn-custom mr-2">修改</button>
					<button type="button" onclick="history.back()"
						class="btn btn-secondary btn-custom mr-2">取消修改</button>
					<button type="button" class="btn btn-third btn-custom"
						onclick="document.getElementById('deleteForm').submit();">刪除圖片</button>
				</div>
			</form>
			<!-- 隱藏的刪除圖片表單 -->
			<form id="deleteForm" method="post"
				action="${pageContext.request.contextPath}/member1.do"
				style="display: none;">
				<input type="hidden" name="action" value="deletephoto"> <input
					type="hidden" name="memNo" value="${member.memNo}">
			</form>
		</div>
	</div>
	<!-- Bootstrap JavaScript -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/frontend/member/js/updatemember.js"></script>
</body>
</html>