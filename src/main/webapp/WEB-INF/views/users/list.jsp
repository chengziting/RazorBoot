<%@ page import="java.util.List" %>
<%@ page import="com.chengziting.razor.model.persistent.User" %>
<%@ page import="com.chengziting.razor.utils.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/3/30
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../patial/header.jsp"/>

    <title>User List</title>

    <script type="text/javascript">
        $(document).ready(function () {
            selectNaviItem("main_menu_item_user_list");
        });

        function deleteUser(id) {
            showConfirmDialog("Warning","Are you sure to delete the User?",function (code) {
                if(code == 1){
                    $.ajax({
                        url:"<%=request.getContextPath()%>/admin/deleteuser",
                        type:"POST",
                        contentType:"application/json;charset=utf-8",
                        dataType:"json",
                        data:id,
                        success:function (data) {
                            showMessageDialog(data.message,function () {
                                if(data.code == 3000){
                                    window.location.reload();
                                }
                            });
                        },
                        error:function (er) {
                            showMessageDialog(JSON.stringify(er));
                        }
                    });
                }
            });
        }

        function editUser(id) {
            var token = "<%=request.getParameter("token")%>";
            var url = "<%=request.getContextPath()%>/admin/edituser?id="+id + "&token="+token;
            window.open(url);
        }
        function addUser() {
            window.open("../users/adduser?token="+getToken());
        }
    </script>
</head>
<body>
    <jsp:include page="../patial/naviMenu.jsp"/>
<%
    List<User> usersList = (List<User>) request.getAttribute("user_list");
%>

    <jsp:include page="../patial/dialog.jsp"/>

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
                                Role
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
                                Operation
                            </td>
                        </tr>
                    </thead>
                    <tbody>
                    <%for (User user : usersList){%>
                        <tr>
                            <td>
                                <input type="checkbox" id="ck_<%=user.getId()%>"/>
                            </td>
                            <td>
                                <a href="../users/userinfo?id=<%=user.getId()%>&token=<%=request.getParameter("token")%>">
                                    <span>
                                    <%=user.getName()%>
                                    </span>
                                </a>
                            </td>
                            <td>
                                <a href="../roles/roleinfo?id=<%=user.getRole().getId()%>&token=<%=request.getParameter("token")%>"><%=user.getRole().getName()%></a>
                            </td>
                            <td>
                                <%=StringUtils.replaceIfNullOrEmpty(user.getEmail(),"none")%>
                            </td>
                            <td>
                                <%=StringUtils.replaceIfNullOrEmpty(user.getPhone(),"none")%>
                            </td>
                            <td>
                                <%=StringUtils.replaceIfNullOrEmpty(user.getQq(),"none")%>
                            </td>
                            <td>
                                <a href="#" onclick="deleteUser('<%=user.getId()%>')"> <span class="glyphicon glyphicon-trash" aria-hidden="true"/></a>
                                &nbsp;
                                <a href="#" onclick="editUser('<%=user.getId()%>')"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> </a>
                            </td>
                        </tr>
                    <%}%>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-10"></div>
            <div class="col-md-2 text-right">
                <a href="#" onclick="addUser()" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> </a>
            </div>
        </div>
    </div>


</body>
</html>
