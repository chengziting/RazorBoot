
<%@ page import="com.chengziting.razor.model.persistent.UserFile" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/8
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File List</title>
    <jsp:include page="../patial/header.jsp"/>

    <link href="/css/dialog.css" rel="stylesheet"/>

    <script type="text/javascript">
        function deleteFile(id) {
            var token = '<%=request.getParameter("token")%>';
            var targetDialog = $("#deleteFileDialog");
            targetDialog.load("<%=request.getContextPath()%>/dialog/deletefile?id=" + id + "&token=" + token,function (e) {
                console.log("load complete!")
                targetDialog.removeClass("hidden");
                targetDialog.dialog({
                    //resizable: false,
                    autoOpen: false,
                    show: {
                        effect: "blind",
                        duration: 500
                    },
                    hide: {
                        effect: "explode",
                        duration: 500
                    },
                    height: 400,
                    width: 500,
                    modal: true
                });
                targetDialog.dialog("open");
            });
        }
    </script>
</head>
<body>
<%
    Map<String,String> filesMap = (Map<String,String>) request.getAttribute("filelist");
%>

<div class="hidden container defaultDialog" id="deleteFileDialog" title="Delete File"></div>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <strong>File Name</strong>
        </div>
        <div class="col-md-4">
            <strong>File Size</strong>
        </div>
        <div class="col-md-2">
            <strong>Operation</strong>
        </div>
    </div>
    <%for (String key : filesMap.keySet()) {%>
    <div class="row">
        <div class="col-md-6">
            <%=filesMap.get(key)%>
        </div>
        <div class="col-md-4">
            Unknown
        </div>
        <div class="col-md-2">
            <a href="#" onclick="deleteFile('<%=key%>')"> <span class="glyphicon glyphicon-trash text-danger"
                                                                       aria-hidden="true"/></a>
        </div>
    </div>
    <%}%>
</div>

</body>
</html>
