<%@ page import="com.chengziting.razor.model.persistent.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/17
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Detail Message</title>
    <jsp:include page="../patial/header.jsp"/>
    <script src="/webjars/angularjs/1.5.4/angular.min.js"></script>
</head>
<body>
    <jsp:include page="../patial/naviMenu.jsp"/>

    <jsp:include page="../patial/dialog.jsp"/>

    <div class="container" ng-app="userInfoApp" ng-controller="userInfoController">
        <div class="form-group">
            <label for="textUserName">User Name:</label>
            <input type="text" class="form-control" id="textUserName" readonly value="{{user.name}}"/>
        </div>
        <div class="form-group">
            <label for="textUserEmail">Email:</label>
            <input type="email" class="form-control md-contact-email" id="textUserEmail" ng-model="user.email" ng-change="changed()"/>
        </div>
        <div class="form-group">
            <label for="textUserPhone">Phone:</label>
            <input type="tel" class="form-control glyphicon-phone" id="textUserPhone" ng-model="user.phone" ng-change="changed()"/>
        </div>
        <div class="form-group">
            <label for="textUserQQ">QQ:</label>
            <input type="text" class="form-control" id="textUserQQ" ng-model="user.qq" ng-change="changed()"/>
        </div>
        <div class="form-group">
            <label for="textUserRole">Role:</label>
            <div class="row">
                <div class="col-md-11">
                    <input type="text" class="form-control" id="textUserRole" ng-model="selectedRole.name"  ng-change="changed()"/>
                </div>
                <div class="col-md-1 layout-align-center text-center">
                    <a href="#" ng-click="changeRole()">
                        <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                        <span class="glyphicon-cloass">change</span>
                    </a>
                </div>
            </div>
        </div>
        <div class="form-group">
            <button class="btn btn-danger" ng-click="cancelEdit()">Cancel</button>
            <button class="btn btn-success" ng-click="submitEdit()">Edit</button>
        </div>

        <%--dialog--%>
        <div class="container" id="dialogSelectRole" hidden title="">
            <table class="table table-striped table-bordered table-hover">
                <tr ng-repeat="r in user.allRoles" ng-dblclick="roleSelected(r.id)">
                    <td data-roleid="{{r.id}}">{{r.name}}</td>
                </tr>
            </table>
        </div>
        <script>
            var app = angular.module("userInfoApp",[]);
            app.controller('userInfoController',function ($scope,$http) {
                angular.element(document).ready(function () {
                    var url = "/users/getUserDetailInfo?id="+getParameter("id")+"&token="+getToken();
                    $http.get(url).then(function (response) {
                        if(response.status == 200 && response.data.code == 200){
                            $scope.user = response.data.data;
                            if($scope.user.role != null || $scope.user.role != undefined){
                                $scope.selectedRole = $scope.user.role;
                            }else{
                                $scope.selectedRole = {'id':'000000','name':''};
                            }
                        }
                    });

                    angular.element("#dialogSelectRole table tr");
                });//document ready

                $scope.isChanged = false;

                $scope.changed = function () {
                    $scope.isChanged = true;
                }

                $scope.changeRole = function () {
                    showTargetDialog("dialogSelectRole","select a role(Double click to select)",function (data) {
                        
                    });
                }//end changeRole

                $scope.roleSelected = function (id) {
                    for(var i=0;i<$scope.user.allRoles.length;i++){
                        var r = $scope.user.allRoles[i];
                        if(r.id == id){
                            $scope.selectedRole = r;
                            break;
                        }
                    }
                    if($scope.selectedRole.id != "000000" && $scope.selectedRole.id != $scope.user.role.id){
                        $scope.isChanged = true;
                    }
                    closeDialog("dialogSelectRole");
                    showMessageDialog($scope.selectedRole.name);
                }//end roleSelected

                $scope.cancelEdit = function(){
                    var listUrl = "../users/list?token="+getParameter("token");
                    window.location.href= listUrl;
                }
                $scope.submitEdit = function () {
                    //check login
                    if(checkLoginExpired()){
                        showMessageDialog("Please Log in!",function () {
                            window.location.reload();
                        });
                        return;
                    }
                    if(!$scope.isChanged){
                        return;
                    }
                    if($scope.selectedRole.id != "000000" && $scope.selectedRole.id != $scope.user.role.id){
                        $scope.user.role = $scope.selectedRole;
                    }
                    var postUrl = "../users/edituserdetail";
                    $http.post(postUrl,$scope.user).then(function (response) {
                        showMessageDialog(response.data.message,function () {
                            if(response.data.code == 5000){
                                window.location.href = "../users/list?token="+getToken();
                            }
                        });
                    }).catch(function (er) {
                        showMessageDialog(JSON.stringify(er));
                    });
                }
            });
        </script>
    </div>

</body>
</html>
