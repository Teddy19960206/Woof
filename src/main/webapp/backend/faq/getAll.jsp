<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>GetAllFAQ</title>
</head>
<body>

<table border=1>
<tr>
<th>FAQ編號</th>
<th>類別</th>
<th>標題</th>
<th>內容</th>
</tr>


<c:forEach var="all" items="${all}">
<tr>
<td>${all.faqNo}</td>
<td>${all.faqClass}</td>
<td>${all.faqTitle}</td>
<td>${all.faqContent}</td>
</tr>
</c:forEach>

<input type="button" value="返回" onclick="history.back()" />

</table>
</body>
</html>