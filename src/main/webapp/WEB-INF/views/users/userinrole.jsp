<%@ page import="java.util.List" %>
<%@ page import="com.chengziting.razor.model.persistent.User" %>
<%@ page import="com.chengziting.razor.model.persistent.Role" %>
<%@ page import="com.chengziting.razor.utils.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/6/7
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> userList = (List<User>) request.getAttribute("userlist");
    Role role = (Role) request.getAttribute("roleinfo");
%>
<html>
<head>
    <title>Users in <%=role.getName()%>
    </title>
    <jsp:include page="../patial/header.jsp"/>
</head>
<body>
<jsp:include page="../patial/naviMenu.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered table-hover table-striped">
                <thead>
                <tr>
                    <td>

                    </td>
                    <td>
                        Name
                    </td>
                    <td>
                        Email
                    </td>
                    <td>
                        Phone
                    </td>
                    <td>
                        QQ
                    </td>
                    <td>

                    </td>
                </tr>
                </thead>
                <tbody>
                <% for (User user : userList) { %>
                <tr>
                    <td>

                    </td>
                    <td>
                        <a href="/users/userinfo?id=<%=user.getId()%>">
                            <span><%=user.getName()%></span>
                        </a>

                    </td>
                    <td>
                        <%=StringUtils.replaceIfNullOrEmpty(user.getEmail(), "")%>
                    </td>
                    <td>
                        <%=StringUtils.replaceIfNullOrEmpty(user.getPhone(), "")%>
                    </td>
                    <td>
                        <%=StringUtils.replaceIfNullOrEmpty(user.getQq(), "")%>
                    </td>
                    <td>
                        <a href="#" > <span class="glyphicon glyphicon-trash" aria-hidden="true"/></a>
                        &nbsp;
                        <a href="#" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> </a>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
</html>
