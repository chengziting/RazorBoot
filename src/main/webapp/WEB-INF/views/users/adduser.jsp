<%@ page import="java.util.List" %>
<%@ page import="com.chengziting.razor.model.persistent.Role" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/31
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Role> roleList = (List<Role>)request.getAttribute("rolelist");
%>
<html>
<head>
    <title>Add New User</title>
    <jsp:include page="../patial/header.jsp"/>
    <script src="/webjars/angularjs/1.5.4/angular.min.js"></script>
</head>
<body>
    <jsp:include page="../patial/naviMenu.jsp"/>
    <jsp:include page="../patial/dialog.jsp"/>
    <div class="container" ng-app="myApp" ng-controller="myController">
        <div class="form-group">
            <label for="textUserName">Name:</label>
            <input type="text" class="form-control" id="textUserName" ng-model="user.name"/>
        </div>
        <div class="form-group">
            <label for="textUserEmail">Email:</label>
            <input type="text" class="form-control" id="textUserEmail" ng-model="user.email"/>
        </div>
        <div class="form-group">
            <label for="textUserPhone">Phone:</label>
            <input type="text" class="form-control" id="textUserPhone" ng-model="user.phone"/>
        </div>
        <div class="form-group">
            <label for="textUserQQ">QQ:</label>
            <input type="text" class="form-control" id="textUserQQ" ng-model="user.qq"/>
        </div>
        <div class="form-group">
            <label for="selectUserRole">Role:</label>
            <select class="form-control" id="selectUserRole" ng-model="user.roleId">
                <%for(Role r : roleList){%>
                    <option value="<%=r.getId()%>"><%=r.getName()%></option>
                <%}%>
            </select>
        </div>
        <div class="form-group">
            <input type="button" class="btn btn-danger" value="Cancel" ng-click="cancelAdd()"/>
            <input type="button" class="btn btn-success" value="Submit" ng-click="submitAdd()"/>
        </div>
    </div>
<script>
    var app = angular.module("myApp",[]);
    var emptyUser = {name:"",email:"",phone:"",qq:"",roleId:"000000"};
    app.controller("myController",function ($scope,$http) {
        $scope.user = emptyUser;

        $scope.cancelAdd = function () {

        }

        $scope.submitAdd = function () {
            var url = "../users/addNewUser?token="+getToken();
            $http.post(url,$scope.user).then(function (response) {
                showMessageDialog(response.data.message,function () {
                    if(response.data.code == 20000){
                        window.close();
                        window.opener.location.reload();
                    }
                });
            }).catch(function (er) {
                showMessageDialog(JSON.stringify(er));
            });
        }
    });

</script>

</body>
</html>
