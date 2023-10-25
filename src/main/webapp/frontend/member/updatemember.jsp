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
        data : {MemNO : <%=request.getParameter("memNo")%>},
        //�ШD�ؼЪ�url
        url : "<%=request.getContextPath()%>
	/member?action=query",
					//Ajax���\����檺function�Aresponse���^�Ǫ���
					success : function(data) {
						var jsonObj = JSON.parse(data);
						console.log(jsonObj)
						$('#MEM_NAME').val(jsonObj.memName);
						$(
								'input[name=MEM_GENDER][value='
										+ jsonObj.memGender + ']').prop(
								'checked', true);
						$('#MEM_EMAIL').val(jsonObj.memEmail);
						$('#MEM_PASSWORD').val(jsonObj.memPassword);
						$('#MEM_TEL').val(jsonObj.memTel);
						$('#MEM_ADDRESS').val(jsonObj.memAddress);
						$('#MEM_TOTALCLASS').val(jsonObj.memTotalclass);
						$('#MEM_MOMOPOINT').val(jsonObj.momoPoint);
						$('#MEM_BD').val(jsonObj.memBd);
						$(
								'input[name=MEM_STATUS][value='
										+ jsonObj.memStatus + ']').prop(
								'checked', true);
					},
					//Ajax���ѫ�n���檺function�A���Ҭ��L�X���~�T��
					error : function(xhr, ajaxOptions, thrownError) {
						alert("�z ���F");
					}
				});

				//����榡
				$("#memBd").datepicker({
					dateFormat : 'yy-mm-dd'
				});
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

</head>
<body bgcolor='white'>
<!--request.getContextPath()�ʺA�ڸ��|�Aaction=update�����switch(action)��update-->
 <form method="post" action="<%=request.getContextPath()%>/member.do?action=update">
  <input type="hidden" name="member" id="member" value='<%= request.getParameter("MEM_NO")%>'>
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
		<table>
			<tr>
				<td>�|���m�W:</td>
				<td><input type="TEXT" name="memname" id="memname" size="45" /></td>
				<td>${errorMsgs.memName}</td>
			</tr>
			<tr>
				<td>�ʧO:</td>
				<td><input type="radio" name="memGender" value="1" checked>�k
					<input type="radio" name="memGender" value="0">�k</td>
				<td>${errorMsgs.memGender}</td>
			</tr>
			<tr>
				<td>email:</td>
				<td><input type="TEXT" name="memEmail" id="memEmail"
					placeholder="XXX@gmail.com" size="45"></td>
				<td>${errorMsgs.memEmail}</td>
			</tr>
			<tr>
				<td>�K�X:</td>
				<td><input type="TEXT" name="memPassword" id="memPassword"
					size="45" /></td>
				<td>${errorMsgs.memPassword}</td>
			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="TEXT" name="memTel" id="memTel" size="45" /></td>
				<td>${errorMsgs.memTel}</td>
			</tr>
			<tr>
				<td>�a�}:</td>
				<td><input type="TEXT" name="memAdress" id="memAdress"
					size="45" /></td>
				<td>${errorMsgs.memAdress}</td>
			</tr>
			<tr>
				<td>�ͤ�:</td>
				<td><input type="TEXT" name="memBd" id="memBd" size="45" /></td>
				<td>${errorMsgs.memBd}</td>
			</tr>
			<tr>
				<td>����:</td>
				<td><input type="TEXT" name="momoPoint" id="momoPoint"
					size="45" /></td>
				<td>${errorMsgs.momoPoint}</td>
			</tr>
			<tr>
				<td>�`���:</td>
				<td><input type="TEXT" name="totalClass" id="totalClass"
					size="45" /></td>
				<td>${errorMsgs.totalClass}</td>
			</tr>
			<tr>
				<td>���A:</td>
				<td><input type="radio" name="memStatus" value="0">���v <input
					type="radio" name="memStatus" value="1" checked>���`</td>
				<td>${errorMsgs.memStatus}</td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�s�W">
		<!--   �I�����������^�h���� -->
  <input  type="button" onclick="window.location.href='<%=request.getContextPath()%>/frontend/member/selectmember.jsp'" value="����">
	</form>
</body>
</html>