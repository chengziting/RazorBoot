<%@ page import="java.util.List" %>
<%@ page import="com.chengziting.razor.model.persistent.Role" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/6/7
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Role> roleList = (List<Role>) request.getAttribute("rolelist");
%>
<html>
<head>
    <title>Role List</title>
    <jsp:include page="../patial/header.jsp"/>
</head>
<body>
<jsp:include page="../patial/naviMenu.jsp"/>
<jsp:include page="../patial/dialog.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover table-bordered table-striped">
                <thead>
                <tr>
                    <th>
                        Name
                    </th>
                    <th>
                        Status
                    </th>
                    <th>

                    </th>
                </tr>
                </thead>
                <tbody>
                <%for (Role role : roleList) {%>
                <tr>
                    <td>
                        <a href="/roles/roleinfo?id=<%=role.getId()%>"><%=role.getName()%>
                        </a>
                    </td>
                    <td>
                        <%=role.getStatus()%>
                    </td>
                    <td>
                        <a href="/users/usersinrole?roleid=<%=role.getId()%>">View Users</a>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">

    </div>
</div>

</body>
</html>
