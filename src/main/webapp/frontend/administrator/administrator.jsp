<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script
  src="https://code.jquery.com/jquery-3.7.1.js"
  integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
  crossorigin="anonymous"></script>

<html>
<head>
<meta charset="UTF-8">
<title>administrator</title>
<script type="text/javascript">
//表單點擊找出對應的function
  function processUpdate(adminNo){
	  window.location.href= "<%=request.getContextPath()%>/frontend/administrator/administratorUpdate.jsp?adminNo="+ adminNo ;
  }
  function processDelete(adminNo){
 	 $.ajax({
     //指定http參數傳輸格式為POST
     type : "POST",
     //ajax請求配置
     data : {ADMIN_NO : adminNo},
     //請求目標的url
     url : "<%=request.getContextPath()%>/administrator.do?action=del",
     //Ajax成功後執行的function，response為回傳的值
     success : function(data) {
    	 alert('刪除成功');
    	 window.location.reload();
     },
     //Ajax失敗後要執行的function，此例為印出錯誤訊息
     error : function(xhr, ajaxOptions, thrownError) {
      alert("哇 錯了");
     }
    });
  }
  //跳轉到add.jsp頁面
  function processAdd() {
	  window.location.href= "<%=request.getContextPath()%>/frontend/administrator/administratorAdd.jsp"
  }
</script>
</head>
<body>

<jsp:useBean id="administratorService" scope="page" class="com.woof.administrator.service.AdministratorServiceImpl"/>
<input type="button" id="btnAdd" value="新增" onclick="processAdd();" > 
<form method="POST">
	<table>
		<th>編輯</th>
		<th>刪除</th>
		<th>寵物訓練師名稱</th>
		<th>寵物訓練師編號</th>
		<th>電話</th>
	<c:forEach var="administrator" items="${administratorService.allAdministrators}">
    	<tr>
    		<td><input type="button" value="修改" onclick="processUpdate(${administrator.adminNo});"></td>
    		<td><input type="button" value="刪除" onclick="processDelete(${administrator.adminNo});"></td>	
    		<td>${administrator.adminName}</td>
    		<td>${administrator.adminNo}</td>
    		<td>${administrator.adminTel}</td>
    	<tr>
    </c:forEach>
	</table>
</form>
</body>
</html>



