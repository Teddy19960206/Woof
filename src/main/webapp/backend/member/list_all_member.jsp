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
.delete-btn {
    width: 50px; /* or whatever width you want */
}
.update-btn {
    width: 50px; /* or whatever width you want */
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