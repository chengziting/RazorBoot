<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/5/30
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bar Report</title>
    <jsp:include page="../patial/header.jsp"></jsp:include>
    <script src="/js/echarts.min.js"></script>
</head>
<body>
<jsp:include page="../patial/dialog.jsp"></jsp:include>
    <div class="container">
        <div class="row">
            <div class="col-md-2">

            </div>
            <div class="col-md-10">
                <div id="reportContainer" style="width: 500px;height: 500px;">

                </div>
                <script type="text/javascript">
                    var option = {};
                    var myChart = {};
                    $(document).ready(function () {
                        $.ajax({
                            url:"../report/getBarReportData",
                            type:"GET",
                            dataType:"json",
                            success:function (data) {
                                myChart = echarts.init(document.getElementById("reportContainer"));
                                option = data;
                                myChart.setOption(option);
                            },
                            error:function (er) {
                                showMessageDialog(JSON.stringify(er));
                            }
                        });
                    });

                </script>
            </div>
        </div>
    </div>

</body>
</html>
