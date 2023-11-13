<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 私人預約管理</title>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
<jsp:useBean id="memberServer" scope="page" class="com.woof.member.service.MemberServiceImpl"/>
<jsp:useBean id="trainerServer" scope="page" class="com.woof.trainer.service.TrainerServiceImpl"/>
<div class="container py-2" id="allPage">
    <h1 align="center">私人預約單管理</h1>
    <div class="my-2 mx-auto">
        <div class="row">
            <div class="col-3">
                <label class="col-form-label">會員名稱</label>
                <div>
                    <select name="member" id="member" class="form-select">
                    	<c:forEach var="member" items="${memberServer.allMembers}">
            				<option  value="${member.memNo}">${member.memName}</option>
        				</c:forEach>
                    </select>
              </div>
            </div>
            <div class="col-3">
            <label class="col-form-label">訓練師名稱</label>
                <div>
                    <select name="trainer" id="trainer" class="form-select">
                        <c:forEach var="trainer" items="${trainerServer.allTrainers}">
            				<option  value="${trainer.trainerNo}">${trainer.administrator.adminName}</option>
        				</c:forEach>
                    </select>
                </div>
            </div>
    
            <div class="col-3">
                <label class="col-form-label">開始查詢</label>
                <button type="button" id="button" class="btn btn-primary d-block">提交</button>
            </div>
        </div>
    </div>
        <table class="table table-hover text-center align-middle border">
            <thead class="table-light">
                <tr>
                    <th>預約單編號</th>
                    <th>會員姓名</th>
                    <th>訓練師姓名</th>
                    <th>預約堂數</th>
                    <th>評論</th>
                    <th>評論時間</th>
                    <th>評論修改時間</th>
                    <th>查看明細</th>
                </tr>
            </thead>
            <tbody class="table-group-divider">
                <tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<FORM METHOD="post"
							action="${pageContext.request.contextPath}/privatetrainingappointmentform?action=gettoupdate2">
							<%
							String ptaNo = request.getParameter("ptaNo");
							String member = request.getParameter("member");
							String trainer = request.getParameter("trainer");
							String number = request.getParameter("number");
							%>
							<input type="hidden" name="action" value="gettoupdate">
							<input type="hidden" name="ptaNo" value="${pta.ptaNo}">					
							<input type="hidden" name="member" value="${pta.member.memName}">
							<input type="hidden" name="trainer" value="${pta.trainer.administrator.adminName}">
							<input type="hidden" name="number" value="${pta.ptaClass}">
							<button class="btn btn-success" type="submit">查看明細</button>
						</FORM>
					</td>
				</tr>
            </tbody>
        </table>
</div>

<%@ include file="/backend/backfoot.file" %>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="${pageContext.request.contextPath}/backend/appointment/js/appointment.js"></script>
</body>
</html>
