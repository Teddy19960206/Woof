<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>所有會員資料</title>
<script type="text/javascript">
//表單點擊找出對應的function//
  function processUpdate(jsonData){
   window.location.href = " <%=request.getContextPath()%>/backend/member/updatemember.jsp?memNo=" + jsonData.memNo ;
  }
</script>
<style>
/* 基本樣式 */
table, th, td {
	width: 100%;
	/* 其他樣式 */
}

/* 小於或等於 600px 的屏幕 */
@media screen and (max-width: 600px) {
	table, th, td {
		width: auto;
		/* 其他調整，例如字體大小、間距等 */
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
	background-color: #FFA500; /* 橘色背景 */
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
	background-color: #FFF5E6; /* 淡橘色 */
}

tr:hover {
	background-color: #FFDAB5; /* 滑鼠懸停的顏色 */
}

.update-btn, .delete-btn {
	padding: 8px 15px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
}

.update-btn {
	background-color: #FF8C00; /* 深橘色 */
	color: white;
}

.update-btn:hover {
	background-color: #FFA07A; /* 滑鼠懸停的橘色 */
}

.delete-btn {
	background-color: #708090; /* 暗灰色 */
	color: white;
}

.delete-btn:hover {
	background-color: #778899; /* 滑鼠懸停的灰色 */
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
    <h3>所有會員資料 - listAllEmp.jsp</h3>
    <h4>
     <a href="/woof/backend/index.jsp">回首頁</a>
    </h4>
   </td>
  </tr>
 </table>

 <table>
  <tr>
   <th>編號</th>
   <th>姓名</th>
   <th>性別</th>
   <th>照片</th>
   <th>email</th>
   <th>密碼</th>
   <th>電話</th>
   <th>地址</th>
   <th>生日</th>
   <th>毛毛幣</th>
   <th>課堂數</th>
   <th>狀態</th>
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
      <input type="button" class="update-btn" value="修改" onclick="processUpdate({memNo:'${member.memNo}'});">
     </form>
    </td>
<!--     <td> -->
<!--      <FORM METHOD="POST" -->
<%--       ACTION="<%=request.getContextPath()%>/member.do" --%>
<!--       style="margin-bottom: 0px;"> -->
<!--       <input type="hidden" name="action" value="delete" > -->
<%--       <input type="hidden" name="memNo" value="${member.memNo}">  --%>
<!--       <button type="submit" class="delete-btn" >刪除</button> -->
<!--      </FORM> -->
<!--     </td> -->
   </tr>
  </c:forEach>
 </table>
</body>
 </html>