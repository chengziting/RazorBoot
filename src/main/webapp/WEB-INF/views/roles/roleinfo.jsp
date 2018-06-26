<%@ page import="com.chengziting.razor.model.persistent.Role" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/31
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Role role = (Role)request.getAttribute("roleinfo");
%>
<html>
<head>
    <title>Role Information</title>
    <jsp:include page="../patial/header.jsp"/>
    <jsp:include page="../patial/dialog.jsp"/>

    <script type="text/javascript">

        $(document).ready(function () {
            $("#roleNameGroupShow").show();
            $("#roleNameGroupEdit").hide();
            $("#selectRoleStatus").val(<%=role.getStatus()%>);
        });
        function editRole() {
            $("#roleNameGroupShow").hide();
            $("#roleNameGroupEdit").show();
        }
        function Cancel() {
            $("#roleNameGroupShow").show();
            $("#roleNameGroupEdit").hide();
        }
        function submitEdit() {
            var originalName = "<%=role.getName()%>";
            var originalStatus = <%=role.getStatus()%>;
            var currentName = $("#textRoleName").val();
            var currentStatus = $("#selectRoleStatus").val();
            if(currentName == originalName && currentStatus == originalStatus)
            {
                showMessageDialog("Nothing was changed!");
                return;
            }
            var url = "../roles/doEditRole";
            var model = {
                id:"<%=role.getId()%>",
                name:currentName,
                status:currentStatus
            };
            $.ajax({
                url:url,
                type:"POST",
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                data:JSON.stringify(model),
                success:function (data) {
                    showMessageDialog(data.message,function () {
                        if(data.code == 5000){
                            window.location.reload();
                        }
                    });
                },
                error:function (er) {
                    showMessageDialog(JSON.stringify(er));
                }
            });
        }
    </script>
</head>
<body>
    <jsp:include page="../patial/naviMenu.jsp"/>
    <jsp:include page="../patial/dialog.jsp"/>

    <div class="container">
        <div class="form-group" id="roleNameGroupShow">
            <label for="divRoleName">Name:</label>
            <div class="form-control" id="divRoleName"><%=role.getName()%></div>
            <a href="#" onclick="editRole()"><span class="glyphicon glyphicon-pencil">Edit</span> </a>
        </div>
        <div class="form-group" id="roleNameGroupEdit">
            <div class="form-group">
                <label for="textRoleName">Name:</label>
                <input type="text" class="form-control" required id="textRoleName" value="<%=role.getName()%>"/>
            </div>
            <div class="form-group">

                <select class="form-control" id="selectRoleStatus">
                    <option value="0">Enable</option>
                    <option value="1">Disable</option>
                </select>
            </div>
            <div class="form-group">
                <input type="button" class="btn btn-danger" onclick="Cancel()" value="Cancel"/>
                <input type="button" class="btn btn-success" onclick="submitEdit()" value="Edit"/>
            </div>
        </div>
    </div>
</body>
</html>
