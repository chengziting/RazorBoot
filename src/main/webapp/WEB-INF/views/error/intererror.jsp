<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/21
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = (String) request.getAttribute("errorMessage");
%>
<html>
<head>
    <title>Internal Server Error</title>
    <style>
        body{
            background-image: url('/image/error/error_500.jpg');
            background-repeat: no-repeat;
            background-position:center;
        }
    </style>
</head>
<body>
    <div style="display: none">
        <%=message%>
    </div>
</body>
</html>
