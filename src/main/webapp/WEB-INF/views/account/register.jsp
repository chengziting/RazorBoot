<%@ page import="com.chengziting.razor.web.model.ViewModel" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-01-16
  Time: 下午 5:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
    <jsp:include page="../patial/header.jsp"/>
    <style type="text/css">
        .razor-masthead {
            background-color: #6f5499;
            -webkit-box-shadow: inset 0 -2px 5px rgba(0, 0, 0, .1);
            box-shadow: inset 0 -2px 5px rgba(0, 0, 0, .1);
            border-radius: 20px;
            width: 550px;
            height: 450px;
            margin-top:5%;
            padding-top:15px;
        }

        body{
            background-color:#00b3ee;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function(){
            var txtUserName = $("#userName");
            var txtUserEmail = $("#userEmail");
            var txtUserPassword=$("#userPassword");
            var txtPwdConfirm = $("#userPwdConfirm");
            txtUserName.val("czt");
            txtUserEmail.val("czt@kodak.com");
            txtUserPassword.val("123");
            txtPwdConfirm.val("123");
            $("#btnCancel").on("click",function(){
                txtUserName.val("");
                txtUserPassword.val("");
                txtPwdConfirm.val("");
                txtUserEmail.val("");
            });
            $("#btnRegist").on("click",function(){
                if(txtUserName.val() == "" ||
                        txtUserEmail.val() == "" ||
                        txtUserPassword.val() == "" ||
                        txtPwdConfirm.val() == ""){
                    txtUserName.parent().addClass("has-error");
                    txtUserEmail.parent().addClass("has-error");
                    txtPwdConfirm.parent().addClass("has-error");
                    txtUserPassword.parent().addClass("has-error");
                    return false;
                }
                if(txtUserPassword.val() != txtPwdConfirm.val()){
                    txtPwdConfirm.parent().addClass("has-error");
                    txtUserPassword.parent().addClass("has-error");
                    return false;
                }
                var vm = {
                    name:txtUserName.val(),
                    email:txtUserEmail.val(),
                    password:txtUserPassword.val(),
                    confirmPwd:txtPwdConfirm.val()
                };
                $.ajax({
                    url:"<%=request.getContextPath()%>/account/doRegister",
                    type:"POST",
                    dataType:"json",
                    contentType:"application/json;charset=UTF-8",
                    data:JSON.stringify(vm),
                    success:function(data){
                        if(data.success){
                            if(data.redirect != "")
                                window.location.href=data.redirect;
                        }
                        else {
                            alert(data.message);
                        }
                    },
                    error:function(er){
                        alert(JSON.stringify(er));
                    }
                });

            });


        });
    </script>
</head>
<body>
    <div class="container razor-masthead">
        <div class="row">
            <div class="col-md-12">
                <div class="form-group">
                    <label for="userName">UserName:</label>
                    <input type="text" pattern="^[A-z0-9]{6,32}$" class="form-control" name="userName" id="userName" placeholder="UserName" data-error="User name can not be empty!" required/>
                    <div class="help-block with-errors"/>
                </div>
                <div class="form-group">
                    <label for="useremail">Email:</label>
                    <input type="email" class="form-control" name="userEmail" id="userEmail" placeholder="Email" required/>
                    <div class="help-block with-errors"/>
                </div>

                <div class="form-group">
                    <label for="userpassword">Password:</label>
                    <input type="password" class="form-control" name="userPassword" id="userPassword" placeholder="Password" data-error="Password can not be empty" required/>
                    <div class="help-block with-errors"/>
                </div>

                <div class="form-group">
                    <label for="userPwdConfirm">Confirm Password:</label>
                    <input type="password" class="form-control" name="userPwdConfirm" id="userPwdConfirm" placeholder="Confirm password" required/>
                    <div class="help-block with-errors"/>
                </div>

                <div class="form-group">
                    <input type="button" class="btn btn-success" value="Regist" id="btnRegist"/>
                    <input type="button" class="btn btn-danger" value="Cancel" id="btnCancel"/>
                </div>
            </div>
        </div>

    </div>
</body>
</html>
