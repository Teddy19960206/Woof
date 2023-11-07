<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	div.mydiv { 
	    margin : 20px ;
	    border-style:solid ;
	    height : 150px;
	    border-radius:10px;
	}
	
	div.mydiv_photo { 
	    display: inline-block;
	    margin : 5px ;
	    border-style:solid ;
	    height : 90%;
	    width : 20%;
	    border-radius:10px;
	    vertical-align: top; /* Align the top of the image with the top of the container */
	}
	
	div.mydiv_content {
	    display: inline-block; 
	    margin : 5px 3% ;
	    border-style:solid ;
	    height : 90%;
	    width : 70%;
	    border-radius:10px;
	}
	
	div.mydiv_photo img {
	    width: 100%; /* 使图像宽度填充容器 */
	    height: 100%; /* 使图像高度填充容器 */
	    object-fit: cover; /* 保持图像比例，剪裁以填充容器 */
	}
</style>
<script type="text/javascript">
//表單點擊找出對應的function
  function processUpdate(jsonData){
	  window.location.href = " <%=request.getContextPath()%>/frontend/administrator/administratorUpdate.jsp?adminNo=" + jsonData.adminNo ;
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
		<div class="mydiv_photo">
			<img src="data:image/jpeg;base64,${administrator.imgStr}">
		</div>
		<div class="mydiv_content">
            名字 : ${administrator.adminName}
            <br>
            Email: ${administrator.adminEmail}
            <br>
            地址:  ${administrator.adminAddress}
        </div>
	</div>
<%--     		<td><input type="button" value="修改" onclick="processUpdate({adminNo:'${administrator.adminNo}'});"></td> --%>
<%--     		<td><input type="button" value="刪除" onclick="processDelete({ADMIN_NO:'${administrator.adminNo}'});"></td>	 --%>
    </c:forEach>
</body>
</html>



