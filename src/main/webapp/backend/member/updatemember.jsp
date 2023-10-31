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

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>�Ҧ����u��� - updatemember.jsp</title>
<script>
$(document).ready(function(){
    $.ajax({
        //���whttp�Ѽƶǿ�榡��POST
        type : "POST",

        data : {memNo : <%=request.getParameter("memNo")%>},
        //�ШD�ؼЪ�url
        url : "<%=request.getContextPath()%>/member.do?action=query",
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
										$('#memPhoto').attr('src', jsonObj.photoBase64);
										$('#memEmail').val(jsonObj.memEmail);
										$('#memPassword').val(
												jsonObj.memPassword);
										$('#memTel').val(jsonObj.memTel);
										$('#memAddress')
												.val(jsonObj.memAddress);
										$('#totalClass')
												.val(jsonObj.totalClass);
										$('#momoPoint').val(jsonObj.momoPoint);
// 										$('#memBd').val(jsonObj.memBd);
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

						//����榡
						$("#memBd").datepicker({dateFormat : 'yy-mm-dd'});
					})
</script>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

<style>
#preview {
	border: 1px solid lightgray;
	display: inline-block;
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
<body bgcolor='white'>
	<!--request.getContextPath()�ʺA�ڸ��|�Aaction=update�����switch(action)��update-->

	<table id="table-1">
		<tr>
			<td>
				<h3>�|����Ƨ�s - updatemember.jsp</h3>
				<h4>
					<a href="selectmember.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>��Ƨ�s:</h3>
	<form method="post"
		action="${pageContext.request.contextPath}/member.do"
		enctype="multipart/form-data" accept-charset="UTF-8">
		<table>
			<input type="hidden" name="memNo" id="member"
				value='<%=request.getParameter("memNo")%>'>
			<%-- <input type="hidden" name="memNo" value="${memNo}"/> --%>
			<tr>
				<td>�|���b��:</td>
				<td><input type="TEXT" name="memNo" id="memNo" size="45"/></td>

			</tr>
			<tr>
			<tr>
				<td>�|���m�W:</td>
				<td><input type="TEXT" name="memName" id="memName" size="45" /></td>

			</tr>
			<tr>
				<td>�ʧO:</td>
				<td><input type="radio" name="memGender" value="M" checked>�k
					<input type="radio" name="memGender" value="F">�k</td>

			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><input type="file" name="memPhoto" accept="image/*"
					id="p_file">
					<div id="preview">
						<span class="text"> <img id="photo" class="preview_img" 
							src="${pageContext.request.contextPath}/DBPngReader?action=member&id=${member.memNo}"
							onerror="this.style.display='none'" />
						</span>
					</div></td>
			</tr>
			<tr>
				<td>email:</td>
				<td><input type="email" name="memEmail" id="memEmail"
					placeholder="XXX@gmail.com" size="45"></td>

			</tr>
			<tr>
				<td>�K�X:</td>
				<td><input type="TEXT" name="memPassword" id="memPassword"
					size="45" /></td>

			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="tel" name="memTel" id="memTel" size="45" /></td>

			</tr>
			<tr>
				<td>�a�}:</td>
				<td><input type="TEXT" name="memAddress" id="memAddress"
					size="45" /></td>

			</tr>
			<tr>
				<td>�ͤ�:</td>
				<td><input type="TEXT" name="memBd" id="memBd" size="45" /></td>

			</tr>
			<tr>
				<td>����:</td>
				<td><input type="number" name="momoPoint" id="momoPoint"
					size="45" /></td>

			</tr>
			<tr>
				<td>�`���:</td>
				<td><input type="number" name="totalClass" id="totalClass"
					size="45" /></td>

			</tr>
			<tr>
				<td>���A:</td>
				<td><input type="radio" name="memStatus" value="0">���v<input
					type="radio" name="memStatus" value="1" checked>���`</td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update">
		<button type="submit">�e�X</button>
		<button type="button" onclick="history.back()">������s</button>
		<button type="button" name="delete" id="delete" data-id="${member.memNo}">�R���Ϥ�</button>
	</form>
<script src="${pageContext.request.contextPath}/backend/member/js/updatemember.js"></script>
</body>
</html>