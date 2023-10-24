<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>新增</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
        }
        .container {
            max-width: 800px;
        }
        table {
            width: 100%;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>新增FAQ畫面</h1>

        <form action="${pageContext.request.contextPath}/faq/addfaq" method="post">

            <table class="table table-bordered">
                <tr>
                    <th>FAQ編號</th>
                    <th>類別</th>
                    <th>標題</th>
                    <th>內容</th>
                </tr>

                <tr>
                    <td>${result.faqNo}</td>
                    <td>${result.faqClass}</td>
                    <td>${result.faqTitle}</td>
                    <td>${result.faqContent}</td>
                </tr>
            </table>
            <a class="btn btn-secondary" href="javascript:history.back()">返回</a>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
