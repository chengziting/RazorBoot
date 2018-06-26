<%@ page import="com.chengziting.razor.model.persistent.User" %>
<%@ page import="com.chengziting.razor.utils.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/14
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User targetUser = (User)request.getAttribute("data");
%>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/16
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
    <jsp:include page="../patial/header.jsp"/>
    <script src="/webjars/angularjs/1.5.4/angular.min.js"></script>

</head>
<body>
    <jsp:include page="../patial/naviMenu.jsp"/>
    <jsp:include page="../patial/dialog.jsp"/>

    <div class="container" ng-app="myApp" ng-controller="editUserController">
        <div class="form-group">
            <label for="textTargetUserName">User Name:</label>
            <input type="text" id="textTargetUserName" readonly class="form-control" ng-model="user.name"/>
            <input type="hidden" id="textTargetUserId" readonly ng-model="user.id"/>
        </div>
        <div class="form-group">
            <label for="textTargetUserEmail">Email:</label>
            <input type="email" id="textTargetUserEmail" class="form-control" ng-model="user.email"/>
        </div>
        <div class="form-group">
            <label for="textTargetUserQQ">QQ:</label>
            <input type="text" id="textTargetUserQQ" class="form-control" ng-model="user.qq"/>
        </div>
        <div class="form-group">
            <label for="textTargetUserPhone">Phone:</label>
            <input type="text" id="textTargetUserPhone" class="form-control" ng-model="user.phone"/>
        </div>
        <div class="form-group">
            <input type="button" class="btn btn-danger" id="btnCancel" ng-click="cancelEdit()" value="Cancel"/>
            <input type="button" class="btn btn-success" id="btnEdit" ng-click="submitEdit()" value="Edit"/>
        </div>
    </div>

    <script>
        var userInfoUtl = "<%=request.getContextPath()%>/users/getUserInfo?id=<%=request.getParameter("id")%>";
        var app = angular.module("myApp",[]);
        app.controller('editUserController',function ($scope,$http) {
            $scope.user = {"id":"","name":"","phone":"","email":"","qq":""};
            angular.element(document).ready(function () {
                $http.get(userInfoUtl).then(function (response) {
                    if(response.status == 200 && response.data.code == 200){
                        $scope.user = response.data.data;
                    }
                });
            });//end init

            //events
            $scope.cancelEdit = function(){
                window.close();
            }
            $scope.submitEdit = function () {
                var postData = JSON.stringify($scope.user);
                var url = "/users/edituser";
                $http.post(url,postData).success(function (data,status) {
                    if(data.code == 5000) {
                        showMessageDialog("Edit Success!",function () {
                            if(window.opener != null) {
                                window.close();
                                window.opener.location.reload();
                            }else{
                                window.location.href = "../users/list?token="+getToken();
                            }
                        });
                    }
                }).error(function (er,status) {
                    console.log(JSON.stringify(er));
                });
            }

        });
    </script>
</body>
</html>





