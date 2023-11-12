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
<style>
	
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f0f0;
      margin: 0;
      padding: 0;
    }

    .container {
      max-width: 200px;
      margin: 0 auto;
      padding: 20px;
      background-color: #ffffff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .button {
      display: inline-block;
      padding: 10px 20px;
      background-color: #3498db;
      color: #ffffff;
      border: none;
      border-radius: 5px;
      text-align: center;
      text-decoration: none;
      font-size: 16px;
      cursor: pointer;
      margin-bottom: 10px;
    }

    .button:hover {
      background-color: #2980b9;
    }

    .mydiv {
      border: 2px solid #3498db;
      border-radius: 10px;
      padding: 10px;
      margin-bottom: 20px;
      background-color: #fff;
    }

    .mydiv h5 {
      margin: 0;
    }
  </style>

</style>
<script type="text/javascript">
//表單點擊找出對應的function
  function processUpdate(jsonData){
	  window.location.href = " <%=request.getContextPath()%>/frontend/administrator/administratorUpdate.jsp?adminNo=" + jsonData.adminNo ;
  }
  function processUpdate2(jsonData){
	  window.location.href = " <%=request.getContextPath()%>/frontend/administrator/administratorUpdate2.jsp?adminNo=" + jsonData.adminNo ;
  }
  function processDelete(jsonData){
 	 $.ajax({
     //指定http參數傳輸格式為POST
     type : "POST",
     //ajax請求配置
     data : jsonData,
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
<input type="button" class='button' id="btnAdd" value="新增" onclick="processAdd();" > 
	<c:forEach var="administrator" items="${administratorService.allAdministrators}">
	<div class="mydiv">
		<h5>${administrator.adminName}</h5>
	</div>
     		<td><input type="button" value="修改" onclick="processUpdate({adminNo:'${administrator.adminNo}'});"></td> 
     		<td><input type="button" value="修改2" onclick="processUpdate2({adminNo:'${administrator.adminNo}'});"></td> 
     		<td><input type="button" value="刪除" onclick="processDelete({ADMIN_NO:'${administrator.adminNo}'});"></td>	
    </c:forEach>
</body>
</html>



