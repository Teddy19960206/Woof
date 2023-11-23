<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 輪播管理</title>
  <style>
    #preview {
      border: 1px solid lightgray;
      display: inline-block;
      width: 300px;
      min-height: 400px;
      position: relative;
    }
    #preview span.text {
      position: absolute;
      display: inline-block;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      z-index: -1;
      color: lightgray;
    }
    #preview img.preview_img {
      width: 100%;
      text-align: center;
    }
    .uploadImage{
      background-color: #0d6efd;
      border-radius: 10px;
      padding: 8px 15px;
      color: white;
      /*width: 100px;*/
    }
    select{
      text-align: center;
    }
    select > option{
      text-align: center;
    }
    label{
      font-size: 20px;
    }

  </style>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
<div class="container mx-auto text-center p-3">
  <h1>輪播管理</h1>
  <div class="showCarousel"></div>
</div>

<%@ include file="/backend/backfoot.file" %>
  <script src="${pageContext.request.contextPath}/backend/carousel/js/carousel.js"></script>
</body>
</html>
