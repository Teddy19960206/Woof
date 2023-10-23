<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.woof.member.*"%>

<html>
<head>
<!-- ������M�� -->
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>���u��Ʒs�W - addEmp.jsp</title>
<script>
      //����榡
    $(function() {
        $("#memBd").datepicker({ dateFormat: 'yy-mm-dd' });
    });
</script>
<style>
table#table-1 {
	width: 450px;
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
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>�|����Ʒs�W - addEmp.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="selectmember.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<%-- <c:if test="${not empty errorMsgs}"> --%>
	<!-- 	<font style="color:red">�Эץ��H�U���~:</font> -->
	<!-- 	<ul> -->
	<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
	<%-- 			<li style="color:red">${message.value}</li> --%>
	<%-- 		</c:forEach> --%>
	<!-- 	</ul> -->
	<%-- </c:if> --%>

	<form method="POST" ACTION="${pageContext.request.contextPath}/member">
		<table>
			<tr>
				<td>�|���m�W:</td>
				<td><input type="TEXT" name="memname" id="memname"
					size="45" /></td>
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
				<td><input type="TEXT" name="memEmail"
					id="memEmail" placeholder="XXX@gmail.com" size="45"></td>
				<td>${errorMsgs.memEmail}</td>
			</tr>
			<tr>
				<td>�K�X:</td>
				<td><input type="TEXT" name="memPassword"
					id="memPassword" size="45" /></td>
				<td>${errorMsgs.memPassword}</td>
			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="TEXT" name="memTel" id="memTel"
					size="45" /></td>
				<td>${errorMsgs.memTel}</td>
			</tr>
			<tr>
				<td>�a�}:</td>
				<td><input type="TEXT" name="memAdress"
					id="memAdress" size="45" /></td>
				<td>${errorMsgs.memAdress}</td>
			</tr>
			<tr>
				<td>�ͤ�:</td>
				<td><input type="TEXT" name="memBd" id="memBd"
					size="45" /></td>
				<td>${errorMsgs.memBd}</td>
			</tr>
			<tr>
				<td>����:</td>
				<td><input type="TEXT" name="momoPoint"
					id="momoPoint" size="45" /></td>
				<td>${errorMsgs.momoPoint}</td>
			</tr>
			<tr>
				<td>�`���:</td>
				<td><input type="TEXT" name="totalClass"
					id="totalClass" size="45" /></td>
				<td>${errorMsgs.totalClass}</td>
			</tr>
			<tr>
				<td>���A:</td>
				<td><input type="radio" name="memStatus" value="0">���v
					<input type="radio" name="memStatus" value="1" checked>���`</td>
				<td>${errorMsgs.memStatus}</td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�s�W">
		<button type="button" onclick="history.back()">�����s�W</button>
	</form>
</body>


<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<%
java.sql.Date hiredate = null;
try {
	hiredate = java.sql.Date.valueOf(request.getParameter("hiredate").trim());
} catch (Exception e) {
	hiredate = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>