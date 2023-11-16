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
<title>��s�|�����</title>
<script>
	// �����ܾ���l��
	$(function() {
		$("#memBd").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
<script>
    function confirmUpdate() {
        return confirm('�z�T�w�n�ק�o�Ǹ�ƶܡH');
    }
</script>
<script>
$(document).ready(function(){
    $.ajax({
        ///���whttp�Ѽƶǿ�榡��POST
        type : "POST",

        data : {memNo : "<%=request.getParameter("memNo")%>"},
        ///�ШD�ؼЪ�url
        url : "<%=request.getContextPath()%>/member1.do?action=query",
         //Ajax���\����檺function�Aresponse���^�Ǫ���
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
         //Ajax���ѫ�n���檺function�A���Ҭ��L�X���~�T��
         error : function(xhr, ajaxOptions,
           thrownError) {
          alert("�z ���F");
         }
        });
     })
</script>
<script src="https://kit.fontawesome.com/3f37e88a3b.js" crossorigin="anonymous"></script>
<style>
body {
	background-color: #fff4e5; /* �L���I�� */
}

.container {
	padding-top: 40px;
	max-width: 600px;
}

.custom-card {
	background: #ffffff; /* �զ�d���I�� */
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
	margin-bottom: 20px;
}

.custom-header {
	background: #ffa726; /* �����D�� */
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
	border: 1px solid #ffa726; /* ������ */
}

.btn-custom {
	border-radius: 5px;
	padding: 10px 30px;
}

.btn-primary {
	background-color: #ffa726; /* �����s */
	border: none;
}

.btn-secondary {
	background-color: #f0f0f0; /* �L�Ǧ���s */
	color: #333;
	border: none;
}

.btn-primary:hover, .btn-primary:focus {
	background-color: #fb8c00; /* ���s�a����� */
	border: none;
}

.btn-secondary:hover, .btn-secondary:focus {
	background-color: #e0e0e0; /* ���s�a���L�Ǧ� */
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
	<!--request.getContextPath()�ʺA�ڸ��|�Aaction=update�����switch(action)��update-->

	<div class="container">
		<div class="custom-card">
			<div class="custom-header text-center">
				<h3>�򥻸��</h3>
			</div>
				<a href="/woof/index.jsp" style="position: absolute; top: 10px; right: 20px;"><i class="fa-solid fa-house"></i></a>

			<form method="post"
				action="${pageContext.request.contextPath}/member1.do"
				enctype="multipart/form-data" accept-charset="UTF-8"
				onsubmit="return confirmUpdate()">
				<div class="form-group">
					<label for="memNo">�|���b��:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memNo"
								id="memNo" size="45" />
						</div>
					</div>
<%-- 					<small class="error-msg">${errorMsgs.memNo}</small> --%>
				</div>
				<div class="form-group">
					<label for="memName">�|���m�W:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memName"
								id="memName" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memName}</small>
				</div>
				<div class="form-group">
					<label for="memGender">�ʧO:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memGender"
								id="memGenderM" value="M" checked> <label
								class="form-check-label" for="memGenderM">�k</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memGender"
								id="memGenderF" value="F"> <label
								class="form-check-label" for="memGenderF">�k</label>
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memGender}</small>
				</div>
				<div class="form-group">
					<label for="memPhoto">�|���ۤ�:</label>
					<div>
						<div class="form-check form-check-inline">
							<!-- �w���Ϥ��϶� -->
							<div id="preview" style="margin-bottom: 10px;">
								<img id="photo" class="preview_img" onerror="this.style.display='none'" /> 
								<span class="text"></span>
							</div>
							<!-- �ɮפW�ǿ�J�� -->
							<input class="form-check-input" type="file" name="memPhoto"
								accept="image/*" id="p_file" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="memEmail">�|���H�c:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="email" name="memEmail"
								id="memEmail" placeholder="XXX@gmail.com" size="45" />
						</div>
					</div>
<%-- 					<small class="error-msg">${errorMsgs.memEmail}</small> --%>
				</div>
				<div class="form-group">
					<label for="memPassword">�K�X:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memPassword"
								id="memPassword" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memPassword}</small>
				</div>
				<div class="form-group">
					<label for="memTel">�|���q��:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="tel" name="memTel"
								id="memTel" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memTel}</small>
				</div>
				<div class="form-group">
					<label for="memAddress">�a�}:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="Text" name="memAddress"
								id="memAddress" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memAddress}</small>
				</div>
				<div class="form-group">
					<label for="memBd">�ͤ�:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="Text" name="memBd"
								id="memBd" size="45" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="momoPoint">����:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="momoPoint"
								id="momoPoint" size="45" readonly />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="totalClass">�`���:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="totalClass"
								id="totalClass" size="45" readonly />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="memStatus">���A:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memStatus"
								id="memStatus" value="0" disabled> <label
								class="form-check-label" for="memStatus">���v</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memStatus"
								id="memStatus" value="1" disabled> <label
								class="form-check-label" for="memStatus">���`</label>
						</div>
					</div>
				</div>

				<br> <input type="hidden" name="action" value="update">
				<button type="submit">�e�X</button>
				<button type="button" onclick="history.back()">�����ק�</button>
				<button type="button" name="delete" id="delete"
					data-id="${member.memNo}">�R���Ϥ�</button>
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
