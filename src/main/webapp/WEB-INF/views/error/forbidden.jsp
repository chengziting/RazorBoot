<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/4/26
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forbidden</title>
</head>
<body>
    <p>
        You do not have authority to access this page: <strong><%=request.getAttribute("message")%></strong>
    </p>
</body>
</html>
