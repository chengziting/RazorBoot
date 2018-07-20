<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/7/20
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Treeview Demo</title>
    <jsp:include page="../patial/header.jsp"></jsp:include>
    <link rel="stylesheet" href="/js/jquery-treeview/jquery.treeview.css"/>
    <link rel="stylesheet" href="/js/jquery-treeview/screen.css"/>
    <script src="/js/jquery-treeview/jquery.treeview.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $("#browser").treeview({
                toggle: function() {
                    console.log("%s was toggled.", $(this).find(">span").text());
                }
            });

            $("#add").click(function() {
                var branches = $("<li><span class='folder'>New Sublist</span><ul>" +
                        "<li><span class='file'>Item1</span></li>" +
                        "<li><span class='file'>Item2</span></li></ul></li>").appendTo("#browser");
                $("#browser").treeview({
                    add: branches
                });
            });
        });
    </script>
</head>
<body>
<div id="main">



    <ul id="browser" class="filetree treeview-famfamfam">
        <li><span class="folder">Folder 1</span>
            <ul>
                <li><span class="folder">Item 1.1</span>
                    <ul>
                        <li><span class="file">Item 1.1.1</span></li>
                    </ul>
                </li>
                <li><span class="folder">Folder 2</span>
                    <ul>
                        <li><span class="file">File 2.1</span></li>
                    </ul>
                    <ul>
                        <li><span class="folder">Subfolder 2.1</span>
                            <ul id="folder21">
                                <li><span class="file">File 2.1.1</span></li>
                                <li><span class="file">File 2.1.2</span></li>
                            </ul>
                        </li>
                        <li><span class="folder">Subfolder 2.2</span>
                            <ul>
                                <li><span class="file">File 2.2.1</span></li>
                                <li><span class="file">File 2.2.2</span></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="closed"><span class="folder">Folder 3 (closed at start)</span>
                    <ul>
                        <li><span class="file">File 3.1</span></li>
                    </ul>
                </li>
                <li><span class="file">File 4</span></li>
            </ul>
        </li>
    </ul>

    <button id="add">Add!</button>



</div>

</body>
</html>
