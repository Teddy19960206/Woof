<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/meta.file"%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/ae360af17e.js"
	crossorigin="anonymous"></script>
<script
	src="<%=request.getContextPath()%>/webutil/js/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath()%>/webutil/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/cartlist/css/finishorder.css">
<title>寵毛導師 Woof | 訂單完成</title>

</head>

<body>
	<script>
        let orderSuccess = <%= request.getAttribute("orderSuccess") != null ? request.getAttribute("orderSuccess") : false %>;
    </script>
	<%@ include file="/Header.file"%>

	<div class="container mb-5 bg-white p-5 rounded-4 shadow">
		<div id="orderSuccessIcon" style="display: none; text-align: center;">
			<h2>訂單完成</h2>
		</div>


	</div>
	<%@ include file="/Footer.file"%>
	<script src="<%=request.getContextPath()%>/frontend/cartlist/js/finishorder.js"></script>
</body>

</html>