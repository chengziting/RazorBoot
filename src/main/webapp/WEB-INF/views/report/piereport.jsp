<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/30
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pie Report</title>
    <jsp:include page="../patial/header.jsp"></jsp:include>
    <script src="/js/echarts.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $.ajax({
                url:"../report/getPieReportData",
                type:"GET",
                dataType:"json",
                success:function (data) {
                    var myChart = echarts.init(document.getElementById("reportContainer"));
                    myChart.setOption(data);
                },
                error:function (er) {
                    showMessageDialog(JSON.stringify(er));
                }
            });
        });
    </script>
</head>
<body>
<jsp:include page="../patial/naviMenu.jsp"/>
    <jsp:include page="../patial/dialog.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-md-2">

            </div>
            <div class="col-md-10">
                <div id="reportContainer" style="width: 500px;height: 500px;">

                </div>
            </div>
        </div>
    </div>

</body>
</html>
