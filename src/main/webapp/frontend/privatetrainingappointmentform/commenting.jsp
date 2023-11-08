<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>我要評論</title>
    <style>
    	body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #2960a5;
        }
        .btn-back {
            background-color: #ccc;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn-back:hover {
            background-color: #999;
        }
        .btn-report {
            background-color: #e74c3c;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn-report:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
	<form method="POST" action="${pageContext.request.contextPath}/privatetrainingappointmentform?action=updatecomment">
        <h2>評論內容</h2>
        <textarea name="comment" rows="5" placeholder="請輸入評論內容" required>${param.comment}</textarea>
        <input type="hidden" name="ptaNo" value="${param.ptaNo}">					
		<input type="hidden" name="member" value="${param.member}">
		<input type="hidden" name="trainer" value="${param.trainer}">
		<input type="hidden" name="number" value="${param.number}">
		<input type="hidden" name="commenttime" value="${param.commentTime}">
		<input type="hidden" name="commentuptime" value="${param.commentUpTime}">
        <button type="submit">提交評論</button>
    </form>
<%--     <p>ptaNo = ${param.ptaNo}</p> --%>
<%--     <p>member = ${param.member}</p> --%>
<%--     <p>trainer = ${param.trainer}</p> --%>
<%--     <p>number = ${param.number}</p> --%>
<%--     <p>comment = ${param.comment}</p> --%>
<%--     <p>commentTime = ${param.commentTime}</p> --%>
<%--     <p>commentUpTime = ${param.commentUpTime}</p> --%>
    
    	<button class="btn-report" onclick="reportComment()">檢舉</button>
    <button class="btn btn-back" onclick="history.back()">返回</button>
</body>
</html>