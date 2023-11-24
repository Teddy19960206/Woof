<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="/backend/backhead.file"%>
<title>寵毛導師 Woof | 管理員資料</title>
<style>
.table-wrapper table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px; /* 調整與上方元素的間距 */
}
.table-wrapper {
  margin-top: 5px; /* 或者您想要的任何距離 */
}
.table-wrapper th,
.table-wrapper td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

/* 表格標題的樣式 */
.table-wrapper th {
  background-color: #f9f9f9;
  font-weight: bold;
}

/* 圖片的樣式，避免太大 */
.table-wrapper .img-thumbnail {
  width: 100px; /* 或者其他適合的大小 */
  height: auto; /* 保持圖片比例 */
  border: 1px solid #ddd;
  border-radius: 4px;
}

/* 狀態標籤的基本樣式 */
.status-label {
  padding: 2px 5px;
  color: white;
  border-radius: 4px;
  font-size: 0.9em;
}

/* 特定狀態的標籤顏色 */
.status-normal {
  background-color: #28a745;
}

.status-suspended {
  background-color: #dc3545;
}

.status-nonvalid {
  background-color: #6c757d;
}
.table-wrapper {
  margin-top: 30px; /* 頂部間距 */
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 輕微的陰影效果 */
  background-color: #fff; /* 純白背景色 */
  border-radius: 6px; /* 圓角邊框 */
  overflow: hidden; /* 圓角效果 */
}

/* 表格內容樣式 */
.table-wrapper table {
  width: 100%;
  border-collapse: collapse;
}

.table-wrapper th,
.table-wrapper td {
  border-bottom: 1px solid #e1e1e1; /* 底部邊框顏色 */
  padding: 12px 15px; /* 內邊距 */
}

/* 表格標題樣式 */
.table-wrapper th {
  background-color: #f5cb5c; /* 標題背景色，淡金黃色 */
  color: #fff; /* 標題文字顏色 */
  font-weight: bold;
  text-transform: uppercase; /* 大寫字母 */
}

/* 圖片樣式 */
.table-wrapper .img-thumbnail {
  width: 100px; /* 圖片寬度 */
  height: auto; /* 自動高度 */
  border-radius: 4px; /* 圓角邊框 */
}

/* 狀態標籤樣式 */
.status-label {
  padding: 3px 8px;
  border-radius: 12px; /* 圓角效果 */
  font-size: 0.8em;
  font-weight: bold;
}

/* 狀態標籤顏色 */
.status-normal {
  background-color: #2a9d8f; /* 在職 - 暗青色 */
}

.status-suspended {
  background-color: #e76f51; /* 停權 - 暗橙色 */
}

.status-nonvalid {
  background-color: #264653; /* 離職 - 暗藍色 */
}

/* 添加懸停效果到每一行 */
.table-wrapper tr:hover {
  background-color: #f8f2e3; /* 懸停時的背景色 */
}



/* 修改按鈕的樣式 */
.update-button {
margin-top: 5px; /* 或者您想要的任何距離 */
 border-radius: 10px; /* 這會給按鈕四個角添加圓角 */
  background-color: #f5cb5c;
  color: white;
  font-family: 'Noto Sans TC', sans-serif;
  font-weight: 700;
  border: none;
  border-radius: 4px;
  padding: 8px 15px;
  cursor: pointer;
  text-transform: uppercase;
  transition: background-color 0.2s ease-in-out; /* 平滑過渡效果 */
}

/* 修改按鈕被點擊時的背景顏色 */
.update-button:active {
  background-color: #e2b357; /* 按鈕被點擊時的顏色 */
}
.button2 {
   margin-left: 10px; /* 新增左邊距 */
margin-top: 5px; /* 或者您想要的任何距離 */
 border-radius: 10px; /* 這會給按鈕四個角添加圓角 */
  background-color: #f5cb5c;
  color: white;
  font-family: 'Noto Sans TC', sans-serif;
  font-weight: 700;
  border: none;
  border-radius: 4px;
  padding: 8px 15px;
  cursor: pointer;
  text-transform: uppercase;
  transition: background-color 0.2s ease-in-out; /* 平滑過渡效果 */
}

/* 修改按鈕被點擊時的背景顏色 */
.button2:active {
  background-color: #e2b357; /* 按鈕被點擊時的顏色 */
}
</style>


<script src="https://kit.fontawesome.com/3f37e88a3b.js"crossorigin="anonymous"></script>
<script type="text/javascript">
function processUpdate(jsonData){
	  window.location.href = " <%=request.getContextPath()%>/frontend/administrator/administratorUpdate.jsp?adminNo=" + jsonData.adminNo ;
}

</script>
</head>
<body>
	<%@ include file="/backend/backbody.file"%>
	<jsp:useBean id="administratorService" scope="page"
		class="com.woof.administrator.service.AdministratorServiceImpl" />
	 <div class="table-wrapper">
	<table>
		<button value="修改" onclick="processUpdate({adminNo:'${administrator.adminNo}'});" class="update-button">修改</button>
<button value="取消" onclick="window.history.back();" class="button2">取消</button>
		<tr>
		  <th scope="row">編號</th>
		  <td>${administrator.adminNo}</td>
	   </tr>
	   <tr>
		  <th scope="row">姓名</th>
		  <td>${administrator.adminName}</td>
	   </tr>
	   <tr>
		  <th scope="row">性別</th>
		  <td><c:choose>
					<c:when test="${administrator.adminGender == '1'}">
						<span>男</span>
					</c:when>
					<c:when test="${administrator.adminGender == '0'}">
						<span>女</span>
					</c:when>
					<c:otherwise>
						<span>未設定</span>
					</c:otherwise>
				</c:choose></td>
	   </tr>
	  <tr>
			<th scope="row">照片</th>
			<td><img
			src="${pageContext.request.contextPath}/DBPngReader?action=administrator&id=${administrator.adminNo}" 
			class="img-thumbnail" style="width: 100px; height: 100px">
		</td>
	</tr>
	    <tr>
		  <th scope="row">信箱</th>
		  <td>${administrator.adminEmail}</td>
	   </tr>
	   <tr>
		  <th scope="row">電話</th>
		  <td>${administrator.adminTel}</td>
	   </tr>
	    <tr>
		  <th scope="row">地址</th>
		  <td>${administrator.adminAddress}</td>
	   </tr>
	    <tr>
		  <th scope="row">生日</th>
		  <td>${administrator.adminBd}</td>
	   </tr>
	     <tr>
		  <th scope="row">聯絡人姓名</th>
		  <td>${administrator.emergencyContactName}</td>
	   </tr>
	    <tr>
		  <th scope="row">聯絡人電話</th>
		  <td>${administrator.emergencyContactel}</td>
	   </tr>
	    <tr>
		  <th scope="row">到職日</th>
		  <td>${administrator.adminHd}</td>
	   </tr>
	   <tr>
		  <th scope="row">離職日</th>
		  <td>${administrator.adminRd}</td>
	   </tr>
	   <tr>
		  <th scope="row">帳號狀態</th>
		  <td><c:choose>
					<c:when test="${administrator.adminStatus == 2}">
						<span class="status-label status-suspended">停權</span>
					</c:when>
					<c:when test="${administrator.adminStatus == 1}">
						<span class="status-label status-normal">在職</span>
					</c:when>
					<c:when test="${administrator.adminStatus == 0}">
						<span class="status-label status-nonvalid">離職</span>
					</c:when>
				</c:choose></td>
				
	   </tr>
	    <tr>
		  <th scope="row">驗證碼狀態</th>
		  <td><c:choose>
					<c:when test="${administrator.adminVerifyStatus == 0}">
						<span class="status-label status-suspended">未驗證信箱</span>
					</c:when>
					<c:when test="${administrator.adminVerifyStatus == 1}">
						<span class="status-label status-normal">已驗證信箱</span>
					</c:when>
				</c:choose></td>
	   </tr>
	     <tr>
		  <th scope="row">權限</th>
		 			<td><c:choose>
					<c:when test="${administrator.adminFuncName == 0}">
						<span class="status-label status-suspended">無功能</span>
					</c:when>
					<c:when test="${administrator.adminFuncName == 1}">
						<span class="status-label status-normal">管理員</span>
					</c:when>
					<c:when test="${administrator.adminFuncName == 2}">
						<span class="status-label status-normal">訓練師</span>
					</c:when>
				</c:choose></td>
	   </tr>

	</table>
</div>
		<%@ include file="/backend/backfoot.file"%>
</body>
</html>