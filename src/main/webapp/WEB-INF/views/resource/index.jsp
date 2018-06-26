<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/7
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resource Manager</title>
    <jsp:include page="../patial/header.jsp"/>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnAjaxUpload").on("click",function () {
                uploadFile();
            });
        });

        function uploadFile() {
            var token = getToken();
            if(token == null){
                var contextPath = "<%=request.getContextPath()%>";
                window.location.href = contextPath +"/account/login?backto="+getContextPath()+"/resource/index";
            }
            $.ajax({
                url: "<%=request.getContextPath()%>/resource/upload",
                type: "POST",
                dataType:"json",
                data: new FormData($("#formUpload")[0]),
                enctype: 'multipart/form-data',
                headers:{"token":token},
                processData: false,
                contentType: false,
                cache: false,
                success: function (data) {
                    console.log(JSON.stringify(data));
                    // Handle upload success
                    // ...
                },
                error: function (er) {
                    console.log(JSON.stringify(er));
                    // Handle upload error
                    // ...
                }
            });
        }
    </script>
</head>
<body>


<form id="formUpload" action="/resource/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="button" value="UploadAjax" id="btnAjaxUpload"/>
</form>
</body>
</html>
