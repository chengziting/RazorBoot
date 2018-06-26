package com.chengziting.razor.core.interceptor;

import com.chengziting.razor.core.SpringContextUtil;
import com.chengziting.razor.core.annotations.Administrator;
import com.chengziting.razor.core.annotations.WithoutAuthorize;
import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.service.IRolesService;
import com.chengziting.razor.service.IUserService;
import com.chengziting.razor.utils.IGlobalKey;
import com.chengziting.razor.utils.StringUtils;
import com.chengziting.razor.utils.redis.RedisCacheFactory;
import com.chengziting.razor.utils.SymmetricEncoder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2018/5/3.
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(AuthorizationInterceptor.class);
    @Autowired
    private IRolesService roleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisCacheFactory redisCacheFactory ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Call AuthorizationInterceptor->preHandle");
        if(handler instanceof DefaultServletHttpRequestHandler)
            return true;

        if ((handler instanceof ResourceHttpRequestHandler) ||
                ((HandlerMethod) handler).getMethodAnnotation(WithoutAuthorize.class) != null ||
                (((HandlerMethod) handler).getMethod().getDeclaringClass().isAnnotationPresent(WithoutAuthorize.class))) {
            return true;
        }



        String authToken = findAuthToken(request);
        String key = null;
        if(authToken != null){
            key = SymmetricEncoder.AESDncode(authToken);
            if(redisCacheFactory.exists(key)){
                adminCheck(response,request,(HandlerMethod)handler,key);
                redisCacheFactory.refresh(key,5*30*1000);
                return true;
            }
            key = null;
        }
        if(key == null) {
            String currentUrl = request.getRequestURL().toString();
            currentUrl += "?"+getParametersWithoutToken(request);
            currentUrl = StringUtils.removeStr(currentUrl,"?",2);
            response.sendRedirect(request.getContextPath() + "/account/login?backto=" + currentUrl);
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    private String getParametersWithoutToken(HttpServletRequest request){
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuffer sb = new StringBuffer();
        while (parameterNames.hasMoreElements()){
            String name = StringUtils.removeStr(parameterNames.nextElement(),"?",1);

            if("token".equals(name)){
                continue;
            }
            sb.append(name);
            sb.append("=");
            sb.append(request.getParameter(name));
            sb.append("&");
        }
        String url = sb.toString();
        if(url.length() > 1)
            url = url.substring(0,url.length() - 1);
        return url;
    }

    //<editor-fold desc="find auth token">
    private String findAuthToken(HttpServletRequest request){
        String tokenParam = request.getParameter("token");
        String tokenCookie = findTokenInCookie(request);

        if(tokenCookie != null)
            return tokenCookie;
        return tokenParam;
    }

    private String findTokenInCookie(HttpServletRequest request){
        Cookie[]cookies = request.getCookies();

        String userCookieValue = null;
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals(IGlobalKey.COOKIE_USER_LOGIN_KEY)){
                userCookieValue = cookie.getValue();
                break;
            }
        }
        return userCookieValue;
    }

    //</editor-fold>

    private void adminCheck(HttpServletResponse response, HttpServletRequest request, HandlerMethod handler, String key) throws IOException {
        Administrator adminMethod = handler.getMethodAnnotation(Administrator.class);
        Administrator adminClass = handler.getMethod().getDeclaringClass().getAnnotation(Administrator.class);
        if(adminMethod == null && adminClass == null){
            return;
        }

        Set<String> roleSet = new HashSet<String>();
        if(adminClass != null) {
            for (String r : adminClass.roles()) {
                roleSet.add(r);
            }
        }
        if(adminMethod != null) {
            for (String r : adminMethod.roles()) {
                roleSet.add(r);
            }
        }


        String content = redisCacheFactory.get(key);
        User user = userService.get(key);
        Role tmpRole = new Role();
        tmpRole.setName("admin");
        if(!userService.getRoles(user.getId()).contains(tmpRole)){
            response.sendRedirect(request.getContextPath() + "/error/forbidden?token="+ SymmetricEncoder.AESEncode(key) + "&url="+request.getRequestURI());
        }
    }

}
