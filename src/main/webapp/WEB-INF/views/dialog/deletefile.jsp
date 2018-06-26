<%@ page import="com.chengziting.razor.model.persistent.UserFile" %>
<%@ page import="org.apache.commons.io.FilenameUtils" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/11
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserFile userFile = (UserFile)request.getAttribute("data");
%>


<table class="table">
    <thead>
    <tr>
        <td width="80%">File Path</td>
        <td>
            <input type="checkbox" id="checkAll"/>
        </td>
    </tr>
    </thead>
    <tr>
        <td>
            <strong>
                <%=FilenameUtils.getName(userFile.getFilePath())%>
            </strong>
        </td>
        <td>
            <input type="checkbox" data-id="<%=userFile.getId()%>"/>
        </td>
    </tr>
</table>

