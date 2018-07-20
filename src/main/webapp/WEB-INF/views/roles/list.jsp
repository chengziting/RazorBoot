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

    <script type="text/javascript">
        $(document).ready(function () {
            bindDefaultValue();

            $("#btnSearchRole").on("click",function () {
                var token = getToken();
                var searchName = $("#textSearchRoleName").val();
                var searchStatus = $("#selectSearchRoleStatus").val();
                var url = "/roles/list?name="+searchName+"&status="+searchStatus+"&token="+token;
                window.location.href = url;
            });
        });

        function bindDefaultValue() {
            var name = getParameter("name");
            var status = getParameter("status");
            if(name != null && name != undefined){
                $("#textSearchRoleName").val(name);
            }
            if(status != null && status != "" && status != undefined){
                $("#selectSearchRoleStatus").val(status);
            }
        }
    </script>
</head>
<body>
<jsp:include page="../patial/naviMenu.jsp"/>
<jsp:include page="../patial/dialog.jsp"/>

<div class="container">
    <div class="container area-10">
        <div class="row vertical-center">
            <div class="col-md-1">
                <span>
                    角色名称:
                </span>
            </div>
            <div class="col-md-4">
                <input type="text" class="form-control" id="textSearchRoleName" placeholder="role name"/>
            </div>
            <div class="col-md-1">
                角色状态:
            </div>
            <div class="col-md-4">
                <select class="form-control" id="selectSearchRoleStatus">
                    <option value="1">启用</option>
                    <option value="0">禁用</option>
                </select>
            </div>
            <div class="col-md-2">
                <input type="button" class="btn btn-default" id="btnSearchRole" value="查找"/>
                <input type="button" class="btn btn-success" id="btnNewRole" value="新增"/>
            </div>
        </div>
    </div>

    <div class="container area-10">
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
</div>

</body>
</html>
