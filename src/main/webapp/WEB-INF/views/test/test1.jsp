<%@ page import="com.chengziting.razor.model.persistent.Roles" %>
<%@ page import="com.google.gson.Gson" %><%--
  Created by IntelliJ IDEA.
  User: czt
  Date: 2017/12/21
  Time: 7:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test1</title>
    <%
      Roles roles = (Roles)request.getAttribute("data");
    %>
</head>
<body>
    <p>
        <%=new Gson().toJson(roles)%>
    </p>

</body>
</html>
