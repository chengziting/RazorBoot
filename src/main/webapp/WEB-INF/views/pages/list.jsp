<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/7/19
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String,List<Map<String,String>>> urlMap = (Map<String,List<Map<String,String>>>)request.getAttribute("urlmap");
%>
<html>
<head>
    <title>Page List</title>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <jsp:include page="../patial/header.jsp"/>
    <script type="text/javascript">
        function viewPage(url) {
            window.open(getContextPath() + url)
        }
    </script>
</head>
<body>
    <div class="container">
        <table class="table table-striped table-hover table-bordered">
            <thead>
            <tr>
                <th>
                    Controller Name
                </th>
                <th>
                    Action Name
                </th>
                <th>
                    Url
                </th>
                <th>
                    Request Type
                </th>
                <th>
                    Operation
                </th>
            </tr>
            </thead>
            <tbody>
                <%for(String key  : urlMap.keySet()){%>
                    <tr>
                        <td><%=key%></td>
                        <td colspan="4">
                            <table class="table table-bordered">
                                <%for(Map<String,String> map : urlMap.get(key)){%>
                                <tr>
                                    <td><%=map.get("action")%></td>
                                    <td><%=map.get("url")%></td>
                                    <td><%=map.get("method")%></td>
                                    <td><a href="#" onclick="viewPage('<%=map.get("url")%>')">View Page</a> </td>
                                </tr>
                                <%}%>
                            </table>
                        </td>
                    </tr>
                <%}%>
            </tbody>
        </table>
    </div>

</body>
</html>
