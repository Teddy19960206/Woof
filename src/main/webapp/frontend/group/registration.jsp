<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/meta.file" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/group/css/registration.css"/>
    <title>寵毛導師 Woof | 結帳頁面</title>
</head>
<body>
<%@ include file="/Header.file" %>

<div class="container">
    <div class="row">
        <div class="col-md-5 col-lg-4">
            <form class="bg-white p-5 rounded-3">
                <div class="mb-3 ">
                    <label for="member" class="form-label">會員帳號</label>
                    <input type="text" class="form-control-plaintext text-center" id="member" value="member1" readonly />
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">會員姓名</label>
                    <input type="text" class="form-control-plaintext text-center" id="name" value="蕭皓鴻">
                </div>
                <div class="mb-3">
                    <label for="smmp" class="form-label" >毛毛幣</label>
                    <input type="text" class="form-control-plaintext text-center" id="smmp" value="10點">
                </div>
            </form>
        </div>
        <div class="col-md-7 col-lg-8">
            <form class="bg-white p-5 rounded-3">
                <div class="mb-4">
                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1">
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>

    </div>
</div>
<%--            <img src="${pageContext.request.contextPath}/webutil/images/registration.jpg" class="img-fluid" />--%>

<%@ include file="/Footer.file" %>
</body>
</html>
