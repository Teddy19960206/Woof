<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.woof.member.*"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<!-- ������M�� -->
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
<title>�|����Ʒs�W - addmember.jsp</title>
<script>
	// �����ܾ���l��
	$(function() {
		$("#memBd").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
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
</head>
<body>
	<div class="container">
		<div class="custom-card">
			<div class="custom-header text-center">
				<h3>�|����Ʒs�W</h3>
			</div>
			<a href="login.jsp" class="btn btn-light btn-sm"
				style="position: absolute; top: 10px; right: 20px;">�^����</a>
			<form method="post"
				action="${pageContext.request.contextPath}/member.do"
				enctype="multipart/form-data" accept-charset="UTF-8">
				<!-- ��L���椸�� -->

				<div class="form-group">
					<label for="memNo">�|���b��:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memNo"
								id="memNo" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<div class="form-group">
					<label for="memName">�|���m�W:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memName"
								id="memName" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<div class="form-group">
					<label for="memGender">�ʧO:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memGender"
								id="memGenderM" value="M"> <label
								class="form-check-label" for="memGenderM">�k</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memGender"
								id="memGenderF" value="F" checked> <label
								class="form-check-label" for="memGenderF">�k</label>
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<div class="form-group">
					<label for="memPhoto">�|���ۤ�:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="file" name="memPhoto"
								accept="image/*" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<div class="form-group">
					<label for="memEmail">�|���H�c:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="email" name="memEmail"
								id="memEmail" placeholder="XXX@gmail.com" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<div class="form-group">
					<label for="memPassword">�K�X:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="TEXT" name="memPassword"
								id="memPassword" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<div class="form-group">
					<label for="memTel">�|���q��:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="tel" name="memTel"
								id="memTel" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<div class="form-group">
					<label for="memAddress">�a�}:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="Text" name="memAddress"
								id="memAddress" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<div class="form-group">
					<label for="memBd">�ͤ�:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="Text" name="memBd"
								id="memBd" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<div class="form-group">
					<label for="momoPoint">�|������:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="momoPoint"
								id="momoPoint" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<div class="form-group">
					<label for="totalClass">�`���:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="number" name="totalClass"
								id="totalClass" size="45" />
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>

				<div class="form-group">
					<label for="memStatus">���A:</label>
					<div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memStatus"
								id="memStatus0" value="0"> <label
								class="form-check-label" for="memStatus0">���v</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="memStatus"
								id="memStatus1" value="1" checked> <label
								class="form-check-label" for="memStatus1">���`</label>
						</div>
					</div>
					<small class="error-msg">${errorMsgs.memStatus}</small>
				</div>
				<input type="hidden" name="action" value="add">
				<button type="submit" class="btn btn-primary btn-custom">�s�W</button>
				<button type="button" class="btn btn-secondary btn-custom"
					onclick="history.back()">����</button>
			</form>
		</div>
	</div>

	<!-- Bootstrap JavaScript -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>