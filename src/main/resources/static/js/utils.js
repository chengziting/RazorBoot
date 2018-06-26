/**
 * Created by user on 2018/5/9.
 */
function getToken() {
    //get from url
    var tokenFromUrl = new URL(document.URL).searchParams.get("token");
    //get from cookie
    var tokenFromCookie = $.cookie("LOGIN_USER");
    if(tokenFromUrl === tokenFromCookie){
        return tokenFromCookie;
    }
    return null;
}

function checkLoginExpired() {
    var cookie = $.cookie("LOGIN_USER");
    if(cookie == undefined || cookie == null || cookie == "")
        return true;
    return false;
}

function getParameter(name) {
    var param = new URL(document.URL).searchParams.get(name);
    return param;
}

function getContextPath() {
    return "http://"+document.location.host;
}

function checkEmail(email) {
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    return reg.test(email);
}

function checkMobilePhone(phoneNum) {
    var reg = /^1(3|4|5|7|8)\d{9}$/;
    return reg.test(phoneNum);
}

function closeAndBackToParent(reload) {
    
}


