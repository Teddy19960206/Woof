<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>�Ҧ��|�����</title>
<script type="text/javascript">
//����I����X������function//
  function processUpdate(jsonData){
   window.location.href = " <%=request.getContextPath()%>/backend/member/updatemember.jsp?memNo=" + jsonData.memNo ;
  }
</script>
<style>
/* �򥻼˦� */
table, th, td {
	width: 100%;
	/* ��L�˦� */
}

/* �p��ε��� 600px ���̹� */
@media screen and (max-width: 600px) {
	table, th, td {
		width: auto;
		/* ��L�վ�A�Ҧp�r��j�p�B���Z�� */
	}
}

body {
	background-color: #f4f4f4;
	font-family: Arial, sans-serif;
}

h3, h4 {
	color: #333;
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 5px;
	margin-bottom: 5px;
	background-color: white;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}

th {
	background-color: #FFA500; /* ���I�� */
	color: white;
	padding: 15px;
	text-align: center;
	font-size: 16px;
}

td {
	padding: 15px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
	background-color: #FFF5E6; /* �H��� */
}

tr:hover {
	background-color: #FFDAB5; /* �ƹ��a�����C�� */
}

.update-btn, .delete-btn {
	padding: 8px 15px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
}

.update-btn {
	background-color: #FF8C00; /* �`��� */
	color: white;
}

.update-btn:hover {
	background-color: #FFA07A; /* �ƹ��a������� */
}

.delete-btn {
	background-color: #708090; /* �t�Ǧ� */
	color: white;
}

.delete-btn:hover {
	background-color: #778899; /* �ƹ��a�����Ǧ� */
}
</style>

</head>
<body bgcolor='white'>
<jsp:useBean id="memberService" scope="page"
   class="com.woof.member.service.MemberServiceImpl" />
 <h4></h4>
 <table>
  <tr>
   <td>
    <h3>�Ҧ��|����� - listAllEmp.jsp</h3>
    <h4>
     <a href="/woof/backend/index.jsp">�^����</a>
    </h4>
   </td>
  </tr>
 </table>

 <table>
  <tr>
   <th>�s��</th>
   <th>�m�W</th>
   <th>�ʧO</th>
   <th>�Ӥ�</th>
   <th>email</th>
   <th>�K�X</th>
   <th>�q��</th>
   <th>�a�}</th>
   <th>�ͤ�</th>
   <th>����</th>
   <th>�Ұ��</th>
   <th>���A</th>
   <th>   </th>
  </tr>
  <c:forEach var="member" items="${memberService.allMembers}">
   <tr>
    <td>${member.memNo}</td>
    <td>${member.memName}</td>
    <td>${member.memGender}</td>
    <td><img src="${pageContext.request.contextPath}/DBPngReader?action=member&id=${member.memNo}" style="width: 100px; height: 100px"></td>
    <td>${member.memEmail}</td>
    <td>${member.memPassword}</td>
    <td>${member.memTel}</td>
    <td>${member.memAddress}</td>
    <td>${member.memBd}</td>
    <td>${member.momoPoint}</td>
    <td>${member.totalClass}</td>
    <td>${member.memStatus}</td>
    <td>

     <form method="post"
      action="${pageContext.request.contextPath}/member.do"
      style="margin-bottom: 0px;">
      <input type="hidden" name="action" value="update"> 
      <input type="hidden" name="memNo" value="${member.memNo}"> 
      <input type="button" class="update-btn" value="�ק�" onclick="processUpdate({memNo:'${member.memNo}'});">
     </form>
    </td>
<!--     <td> -->
<!--      <FORM METHOD="POST" -->
<%--       ACTION="<%=request.getContextPath()%>/member.do" --%>
<!--       style="margin-bottom: 0px;"> -->
<!--       <input type="hidden" name="action" value="delete" > -->
<%--       <input type="hidden" name="memNo" value="${member.memNo}">  --%>
<!--       <button type="submit" class="delete-btn" >�R��</button> -->
<!--      </FORM> -->
<!--     </td> -->
   </tr>
  </c:forEach>
 </table>
</body>
 </html>