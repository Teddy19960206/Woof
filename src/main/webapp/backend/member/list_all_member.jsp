<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="/backend/backhead.file" %>
<title>所有會員資料</title>
<script type="text/javascript">
//表單點擊找出對應的function//
  function processUpdate(jsonData){
   window.location.href = " <%=request.getContextPath()%>/backend/member/updatemember.jsp?memNo=" + jsonData.memNo ;
  }
 //確認刪除提示框
  function confirmDelete(memNo) {
    var confirmAction = confirm("您確定要刪除編號為 " + memNo + " 的會員資料嗎？");
    if (confirmAction) {
        // 執行刪除操作，例如發送表單或請求
        window.location.href = "<%=request.getContextPath()%>/member.do?action=delete&memNo=" + memNo;
    } else {
        // 取消操作
        console.log("取消刪除");
    }
}
</script>
<style>
/* 基本樣式，適用於大屏幕 */
body {
    font-family: Arial, sans-serif;
}
/* 小於或等於 768px 的屏幕（如平板） */
@media screen and (max-width: 768px) {
    table, th, td {
        width: auto; /* 讓表格寬度自動調整 */
        font-size: 14px; /* 調整字體大小 */
    }

    .btn {
        padding: 10px; /* 調整按鈕大小 */
    }

    /* 其他需要調整的樣式 */
}

/* 小於或等於 480px 的屏幕（如手機） */
@media screen and (max-width: 480px) {
    table, th, td {
        font-size: 12px; /* 進一步減小字體大小 */
    }

    .btn {
        padding: 8px; /* 進一步縮小按鈕 */
    }

    /* 可以考慮隱藏某些不必要的列或元素 */
    .hide-on-small {
        display: none;
    }

    /* 其他針對小屏幕的調整 */
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
	background-color: red; 
	color: white;
}

.update-btn:hover {
	background-color: pink;
}
.suspended {
    color: red; /* 停權的文字顏色 */
}

.active {
    color: blue; /* 正常狀態的文字顏色 */
    }
</style>
<script src="https://kit.fontawesome.com/3f37e88a3b.js" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
<jsp:useBean id="memberService" scope="page"
   class="com.woof.member.service.MemberServiceImpl" />
 <div>
 <a href="/woof/backend/index.jsp"style="position: absolute; top: 20px; right: 20px;"><i class="fa-solid fa-house"></i></a>
 </div>
 <table>
  <tr>
   <th>編號</th>
   <th>姓名</th>
   <th>性別</th>
   <th>照片</th>
   <th>email</th>
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
    <td><img src="${pageContext.request.contextPath}/DBPngReader?action=member&id=${member.memNo}" onerror="this.onerror=null; this.src='/woof/backend/member/jpg/dog.jpg';" style="width: 100px; height: 100px"></td>
    <td>${member.memEmail}</td>
    <td>${member.memTel}</td>
    <td>${member.memAddress}</td>
    <td>${member.memBd}</td>
    <td>${member.momoPoint}</td>
    <td>${member.totalClass}</td>
    <td>
       <c:choose>
            <c:when test="${member.memStatus == 0}">
                <span class="suspended">停權</span>
            </c:when>
            <c:when test="${member.memStatus == 1}">
                <span class="active">正常</span>
            </c:when>
        </c:choose>
	</td>
    
<!--     <td> -->
<!--      <form method="post" -->
<%--       action="${pageContext.request.contextPath}/member.do" --%>
<!--       style="margin-bottom: 0px;"> -->
<!--       <input type="hidden" name="action" value="update">  -->
<%--       <input type="hidden" name="memNo" value="${member.memNo}">  --%>
<%--       <input type="button" class="update-btn" value="修改" onclick="processUpdate({memNo:'${member.memNo}'});"> --%>
<!--      </form> -->
<!--     </td> -->
    <td> 
 	 <form method="post"
      action="${pageContext.request.contextPath}/member.do"
      style="margin-bottom: 0px;">
      <input type="hidden" name="action" value="changestatus"> 
      <input type="hidden" name="memNo" value="${member.memNo}"> 
      <input type="submit" class="update-btn" value="修改狀態">
     </form>
     </td>
   </tr>
  </c:forEach>
 </table>
 <%@ include file="/backend/backfoot.file" %>
</body>
 </html>