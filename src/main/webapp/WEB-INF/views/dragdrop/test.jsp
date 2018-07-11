<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/7/11
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<String> moduleList = (List<String>)request.getAttribute("moduleList");
%>
<html>
<head>
    <title>Drag&Drop Test</title>
    <jsp:include page="../patial/header.jsp"/>
    <link href="/webjars/jquery-ui/1.12.0/jquery-ui.css" rel="stylesheet"/>
    <style>
        .draggable { width: 100px; height: 50px; padding: 0.5em; float: left; margin: 10px 10px 10px 0;
            border:solid;
            }
        #droppable { width: 150px; height: 150px; padding: 0.5em; float: left; margin: 10px; }
        .dropped{
            width: 16px;
            height:16px;
            border-radius: 4px;
            position: absolute;
            top: -6px;
            right:-6px;
            background-image: url("/image/icon/close_red_cross.png");
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $( "div.draggable" ).draggable({
                revert : "invalid"//set not auto revert
            }).css("cursor","move").find("div.dropped").hide();
            $("#droppable").droppable({
                drop:function (event,ui) {
                    var draggedTarget = ui.draggable;
                    $(draggedTarget)/*.draggable("disable")*/.css("cursor","pointer").find("div.dropped").show().on("click",function () {
                        showConfirmDialog("Remove","are you sure remove this module?",function (result) {
                            if(result == 1){
//                              //revert to original position
                                draggedTarget.animate({"left":0, "top":0}).css("cursor","move").find("div.dropped").hide();
                                //do something
//                                showMessageDialog("this module was removed!");
                            }
                        });
                    });

//                    showMessageDialog($(draggedTarget).data("id"));
                }
            });
        });
    </script>
</head>
<body>
    <jsp:include page="../patial/dialog.jsp"/>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <%for(String module : moduleList){%>
                    <div class="draggable" id="draggable<%=module%>" data-id="<%=module%>">
                        <strong><%=module%></strong>
                        <div class="dropped"></div>
                    </div>
                <%}%>
            </div>
        </div>
    </div>


    <div id="droppable" class="ui-widget-content">

    </div>

</body>
</html>
