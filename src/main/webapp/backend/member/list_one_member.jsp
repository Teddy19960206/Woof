<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="/backend/backhead.file"%>
<title>會員資料</title>
<script type="text/javascript">
  function confirmChangeStatus(form) {
	    Swal.fire({
	        title: '您確定要更改狀態嗎？',
	        icon: 'warning',
	        showCancelButton: true,
	        confirmButtonColor: '#3085d6',
	        cancelButtonColor: '#d33',
	        confirmButtonText: '我要更改！',
	        cancelButtonText: '取消'
	    }).then((result) => {
	        if (result.isConfirmed) {
	            form.submit();
	        }
	    });
	    // 阻止表單的預設提交
	    return false;
	}
</script>
<style>
/* 基本樣式，適用於大屏幕 */
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
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
    width: 60%;
    border-collapse: collapse;
    margin-top: 5px;
    margin-bottom: 5px;
    margin-left: auto;  /* 左邊距設為自動 */
    margin-right: auto; /* 右邊距設為自動 */
    background-color: white;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}

th {
	background-color: #FFA500; /* 橘色背景 */
	color: white;
	padding: 5px;
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

.status-label {
	padding: .25em .6em;
	font-size: 75%;
	font-weight: bold;
	line-height: 2;
	text-align: center;
	white-space: nowrap;
	vertical-align: baseline;
	border-radius: .25rem;
	display: inline-block;
}

.status-normal {
	background-color: #28a745; /* 綠色背景 */
	color: white;
}

.status-suspended {
	background-color: #dc3545; /* 紅色背景 */
	color: white;
}

.status-nonvalid {
	background-color: gray; /* 藍色背景 */
	color: white;
}
</style>
<script src="https://kit.fontawesome.com/3f37e88a3b.js"crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/backend/backbody.file"%>
	<jsp:useBean id="memberService" scope="page"
		class="com.woof.member.service.MemberServiceImpl" />
	<div>
		<a type="button" onclick="history.back()"
			style="position: absolute; top: 20px; right: 20px;"><i
			class="fa-solid fa-arrow-rotate-left"></i></a>
	</div>
<table>
	<tr>
        <th></th>
        <th colspan="2" style="height: 60px;">會員資料</th> 
    </tr>
    <tr>
        <th>編號</th>
        <td>${member.memNo}</td>
    </tr>
    <tr>
        <th>姓名</th>
        <td>${member.memName}</td>
    </tr>
    <tr>
        <th>性別</th>
        <td>
            <c:choose>
                <c:when test="${member.memGender == 'M'}">
                    <span>男</span>
                </c:when>
                <c:when test="${member.memGender == 'F'}">
                    <span>女</span>
                </c:when>
                <c:otherwise>
                    <span>未設定</span>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <th>照片</th>
        <td>
            <img src="${pageContext.request.contextPath}/DBPngReader?action=member&id=${member.memNo}" 
                onerror="this.onerror=null; this.src='/woof/backend/member/jpg/dog.jpg';" 
                style="width: 100px; height: 100px;border-radius: 50%;">
        </td>
    </tr>
    <tr>
        <th>email</th>
        <td>${member.memEmail}</td>
    </tr>
    <tr>
        <th>電話</th>
        <td>${member.memTel}</td>
    </tr>
    <tr>
        <th>地址</th>
        <td>${member.memAddress}</td>
    </tr>
    <tr>
        <th>生日</th>
        <td>${member.memBd}</td>
    </tr>
    <tr>
        <th>毛毛幣</th>
        <td>${member.momoPoint}</td>
    </tr>
    <tr>
        <th>課堂數</th>
        <td>${member.totalClass}</td>
    </tr>
    <tr>
        <th>狀態</th>
        <td>
            <c:choose>
                <c:when test="${member.memStatus == 0}">
                    <span class="status-label status-suspended">停權</span>
                </c:when>
                <c:when test="${member.memStatus == 1}">
                    <span class="status-label status-normal">正常</span>
                </c:when>
                <c:when test="${member.memStatus == 2}">
                    <span class="status-label status-nonvalid">未驗證</span>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <th></th>
        <td>
            <form method="post" action="${pageContext.request.contextPath}/member.do" 
                onsubmit="return confirmChangeStatus(this);" style="margin-bottom: 0px;">
                <input type="hidden" name="action" value="changestatus">
                <input type="hidden" name="memNo" value="${member.memNo}">
                <input type="submit" class="update-btn" value="修改狀態">
            </form>
        </td>
    </tr>
</table>
	<%@ include file="/backend/backfoot.file"%>
</body>
</html>