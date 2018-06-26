<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-03-15
  Time: 下午 3:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
$(document).ready(function () {
    $("#patial_kaptchaImage,#patial_link_changeCode").on("click",function () {
        $('#patial_kaptchaImage').hide().attr('src', '../captcha/captcha-image.htm?' + Math.floor(Math.random()*100) ).fadeIn();
        event.cancelBubble=true;
    });
});
</script>

<div class="row">
    <div class="col-md-9">
        <input type="text" name="kaptcha" id="patial_txt_kaptcha" class="form-control"/>
    </div>
    <div class="col-md-3">
        <img src="../captcha/captcha-image.htm" id="patial_kaptchaImage"  style="margin-bottom: -3px"/>
        <a href="#" onclick="return false;" id="patial_link_changeCode">换一张</a>
    </div>
</div>
