<%-- <%@ page language="java"  pageEncoding="UTF-8" %>
<html>
<head>
    <title>message</title>
</head>
<body>
<h2>資訊註冊成功!該使用者註冊資訊如下：</h2>
<%
    request.setCharacterEncoding("gb2312");
    String name=request.getParameter("name");
    String  password=request.getParameter("password1");
    String sex = request.getParameter("sex");
    String home = request.getParameter("home");
    out.println("賬號："+name);
    out.println("密碼："+password);
    out.println("性別："+sex);
    out.println("家鄉："+home);
    out.println("興趣愛好：");
    String[] fav = request.getParameterValues("fav");
    for (int i = 0; i < fav.length; i++) {
   
   
        out.print(fav[i]+" ");
    }

    try {
   
   
        Class.forName("com.mysql.jdbc.Driver");
    Connection con= null;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","password");
        PreparedStatement preparedStatement = con.prepareStatement("insert into usert values(?,?)");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,password);
        preparedStatement.executeUpdate();

        out.println("<a href=LoginServlet.jsp>資訊註冊成功，點選此處進行登入</a>");
    } catch (SQLException e) {
   
   
        e.printStackTrace();
    }
    catch (ClassNotFoundException e) {
   
   
        e.printStackTrace();
    }
%>
</body>
</html>
 --%>