package com.chengziting.razor.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 2018-02-24.
 */
public class CookieUtils {


    public static void writeCookie(HttpServletResponse response, String cookieValue, int expiredSeconds){
        Cookie cookie = new Cookie(IGlobalKey.COOKIE_USER_LOGIN_KEY,cookieValue);
        cookie.setVersion(1);
        cookie.setMaxAge(expiredSeconds);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
