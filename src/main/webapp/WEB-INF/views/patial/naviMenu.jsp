<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/3/29
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>

<script type="text/javascript">
    function selectNaviItem(id) {
        $("#" + id).addClass("active");
    }

    function navigation(id) {
        var url = document.URL;
        switch(id){
            case "main_menu_item_user_list":
                url = getContextPath() + "/users/list?token="+getToken();
                break;
            case "":

                break
            case "main_menu_item_resource_upload":
                url = getContextPath() + "/resource/index?token=" + getToken();
                break;
            case "main_menu_item_resource_list":
                url = getContextPath() + "/resource/filelist?token=" +getToken();
                break;
            default:

                break;
        }
        window.location.href = url;
    }
</script>

<nav class="navbar navbar-default fullwidth">
    <%--<div class="container-fluid">--%>
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <%--preference items--%>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">
                    Preference <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">System Setting</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">About Razor</a></li>
                </ul>
            </li>
            <%--Users & Roles--%>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"
                   aria-haspopup="true">
                    Users&Roles<span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li id="main_menu_item_user_list"><a href="../users/list?token=<%=request.getParameter("token")%>">Users Management</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="../roles/list?token=<%=request.getParameter("token")%>">Roles Management</a></li>
                </ul>
            </li>

            <%--Resource manager>--%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" aria-haspopup="true">
                        Resources Manager<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li id="main_menu_item_resource_upload"><a href="../resource/index?token=<%=request.getParameter("token")%>">Upload File</a> </li>
                        <li role="separator" class="divider"></li>
                        <li id="main_menu_item_resource_list"><a href="../resource/filelist?token=<%=request.getParameter("token")%>">File List</a> </li>
                    </ul>
                </li>
        </ul>
    </div>
    <%--</div>--%>
</nav>
