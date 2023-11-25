<%@ page import="com.woof.member.entity.Member" %>
<%@ page import="com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl" %>
<%@ page import="com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%

 Member member = (Member) request.getSession(false).getAttribute("member");

 PrivateTrainingAppointmentFormServiceImpl p = new PrivateTrainingAppointmentFormServiceImpl();
 List<PrivateTrainingAppointmentForm> appointmentByMemNo = p.getAppointmentByMemNo(member.getMemNo());

 request.setAttribute("all" , appointmentByMemNo);
 
 List<Integer> ptaNoList = new ArrayList<>();

 for (PrivateTrainingAppointmentForm appointment : appointmentByMemNo) {
     Integer ptaNo = appointment.getPtaNo();
     ptaNoList.add(ptaNo);
 }

 List<Integer> canCommentList = new ArrayList<>();
 
 for (Integer ptaNo : ptaNoList){
	 boolean canComment = p.canComment(ptaNo);
	 canCommentList.add(canComment ? 1 : 0);
 }
Map<PrivateTrainingAppointmentForm, Integer> canCommentMap = new LinkedHashMap<>(); // 使用LinkedHashMap來保持順序

for (int i = 0; i < appointmentByMemNo.size(); i++) {
  canCommentMap.put(appointmentByMemNo.get(i), canCommentList.get(i));
}
request.setAttribute("canCommentMap", canCommentMap); 

// System.out.println(canCommentMap);
%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/meta.file"%>
<title>寵毛導師 Woof | 預約單管理</title>
<script type="text/javascript">
//表單點擊找出對應的function//
  function processUpdate(jsonData){
   window.location.href = " <%=request.getContextPath()%>/frontend/member/login/updatemember.jsp?memNo=" + jsonData.memNo ;
  }
</script>

<style>
body {
 background-color: #FFF3E0;
}

.card {
 border: none;
 border-radius: 20px;
 background-color: #f8f9fa;
}

.card-header{
 border: none;
 border-radius: 20px;
 background-color:SandyBrown;
}

.profile-img {
 width: 100px;
 height: 100px;
 border-radius: 50%;
}

.icon-btn {
 font-size: 1.5rem;
 color: #495057;
}

.icon-btn:hover {
 color: #0056b3;
}

.update-btn {
 width: 50px; /* or whatever width you want */
}

.list-group-item {
    padding: 10px 15px;
    border: none;
    border-bottom: 1px solid #ddd;
    background-color: #f8f9fa;
    cursor: pointer;
}

.list-group-item:hover {
    background-color: #e2e6ea; /* 輕微改變背景色 */
}

.list-group-item a {
    color: #007bff; /* 連結的顏色 */
    text-decoration: none; /* 移除底線 */
}

.list-group-item a:hover {
    color: #0056b3; /* 懸停時的顏色 */
}


.table {
  width: 100%;
  border-collapse: collapse;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.table th,
.table td {
  padding: 12px;
  text-align: center;
  border-bottom: 1px solid #dee2e6;
}

.table thead th {
  background-color: #007bff;
  color: white;
}

/* 我要評論按鈕 */
.btn-comment {
  padding: 8px 16px;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: bold;
  color: #fff;
  background-color: #28a745;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

.btn-comment:hover {
  background-color: #218838;
}

/* 查看明細按鈕 */
.btn-in {
  padding: 8px 16px;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: bold;
  color: #fff;
  background-color: #ffc107;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

.btn-in:hover {
  background-color: #e0a800;
}

/* 預約單編號欄位 */
.truncated-text {
  max-width: 120px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}


.btn-cannotcomment {
  background-color: gray;
  color: white;
  padding: 8px 16px;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: bold;
  color: #fff;
}
</style>
</head>
<body>
<%@ include file="body.jsp" %>
   <div class="col-12 col-md-8">
    <div class="card">
        <div class="card-body">
            <h3 class="card-title text-center p-2">預約單管理</h3>
            <table class="table table-bordered table-hover text-center align-content-center align-middle" id="show">
                        <thead>
                            <tr>
                                <th>預約單編號</th>
                                <th>訓練師名稱</th>
                                <th>預約堂數</th>
                                <th>查看明細</th>
                                <th>我要評論</th>
                            </tr>

                        </thead>
                        <tbody>
                        <c:forEach items="${all}" var="pta">
    						<tr>
        						<td class="truncated-text">${pta.ptaNo}</td>
        						<td>${pta.trainer.administrator.adminName}</td>
                                <td>${pta.ptaClass}</td>
                                <td>
                                	<FORM METHOD="post" action="${pageContext.request.contextPath}/appointmentdetail?action=getdetail3">
          								<input type="hidden" name="ptaNo" value="${pta.ptaNo}">
          								<input type="hidden" name="memNo" value="${pta.member.memNo}">
          								<input type="hidden" name="trainerNo" value="${pta.trainer.trainerNo}">
          								<button class="btn btn-in" type="submit">查看明細</button>
         							</FORM>
        						</td>
						        <td>
						            <FORM METHOD="post" action="${pageContext.request.contextPath}/frontend/member/login/comment.jsp">
						                <input type="hidden" name="ptaNo" value="${pta.ptaNo}">     
						                <input type="hidden" name="commentTime" value="${pta.commentTime}">
						                <input type="hidden" name="commentUpTime" value="${pta.commentUpTime}">
						                <input type="hidden" name="ptaComment" value="${pta.ptaComment}">
						                <c:choose>
						                    <c:when test="${canCommentMap[pta] == 1}">
						                        <button class="btn btn-comment" type="submit">我要評論</button>
						                    </c:when>
						                    <c:otherwise>
						                        <button class="btn btn-cannotcomment" type="submit" style="" disabled>無法評論</button>
						                    </c:otherwise>
						                </c:choose>
						            </FORM>
						        </td>
						    </tr>
						</c:forEach>
                        </tbody>
                    </table>
     			</div>
    		</div>
   		</div>
<%@ include file="foot.jsp" %>

<%-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>--%>
<%-- <script--%>
<%--  src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>--%>
<%-- <script--%>
<%--  src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
</body>
</html>