<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-01-16
  Time: 下午 4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <jsp:include page="../patial/header.jsp"/>

    <link href="/css/login.css" rel="stylesheet"/>

    <style type="text/css">


    </style>

    <script type="text/javascript">
        $(document).ready(function () {
            var txtUserName = $("#userName");
            var txtPassword = $("#userPassword");
            var txtVerifyCode = $("#patial_txt_kaptcha");

            $("#btnLogin").on("click", function () {
                if (txtUserName.val() == "" || txtPassword.val() == "" || txtVerifyCode.val() == "") {
                    return false;
                }

                var model = {
                    name: txtUserName.val(),
                    password: txtPassword.val(),
                    verifyCode: txtVerifyCode.val()
                };


                $.ajax({
                    url: "../account/doLogin" + new URL(document.URL).search,
                    type: "post",
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(model),
                    success: function (data) {
                        if (data.code == 2000) {
                            var redirectUrl = new URL(document.URL).searchParams.get("backto");
                            if (redirectUrl == undefined || redirectUrl == null || redirectUrl == "") {
                                window.location.href = "../main/home?token=" + data.data;
                                return;
                            }
                            var backUrl = new URL(redirectUrl);
                            if (backUrl.search == "")
                                window.location.href = redirectUrl + "?token=" + data.data;
                            else {
                                window.location.href = redirectUrl + "&token=" + data.data;
                            }
                        } else {
                            alert(data.message);
                        }
                    },
                    error: function (er) {
                        alert(JSON.stringify(er));
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="container razor-login-box">
    <div class="row">
        <div class="col-md-7">

        </div>
        <div class="col-md-5">
            <div class="container razor-login-panel">
                <div class="form-group text-center">
                    <p>
                    <h3>Welcome to Razor</h3>
                    </p>

                </div>

                <div class="form-group">
                    <label for="userName">UserName:</label>
                    <input type="text" pattern="^[A-z0-9]{6,32}$" class="form-control" name="userName" id="userName"
                           placeholder="UserName" value="admin" data-error="User name can not be empty!" required/>
                    <div class="help-block with-errors"/>
                </div>

                <div class="form-group">
                    <label for="userpassword">Password:</label>
                    <input type="password" class="form-control" value="123" name="userPassword" id="userPassword"
                           placeholder="Password"
                           data-error="Password can not be empty" required/>
                    <div class="help-block with-errors"/>
                </div>

                <div class="form-group">
                    <label>VerifyCode:</label>
                    <%--<jsp:include page="../patial/kaptcha.jsp"/>--%>
                    <div class="row">
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="userIdentifyCode" placeholder="Identify Code"/>
                        </div>
                        <div class="col-md-3">
                            <%--<jsp:include page="../patial/kaptcha.jsp"/>--%>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <input type="button" class="btn btn-success btn-block" value="Login" id="btnLogin"/>
                </div>

                <div class="form-group text-right">
                    <a href="../account/register">Sign In</a>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
