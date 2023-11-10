<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>



    <title>訂單</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
        }
        .container {
            max-width: 800px;
        }
        .form-group {
            margin-bottom: 20px;
        }
    </style>
    
</head>
<body>

 

    <div class="container">
        <h1>搜尋全部訂單</h1>
        <form method="Post">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/shoporder?action=getAll">查詢全部訂單</a>
        </form>

        <h1>單一查詢訂單</h1>
       
       
       
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
