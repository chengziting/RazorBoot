package com.chengziting.razor.web.controller;

import com.chengziting.razor.core.annotations.WithoutAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/5/7.
 */
@Controller
@WithoutAuthorize
public class MyErrorController extends BaseController implements ErrorController{

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public String error(HttpServletRequest request,WebRequest webRequest){
        Map<String,Object> errorMap = getErrorAttributes(request,webRequest,true);
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null){
            int statusCode = Integer.valueOf(status.toString());
            switch (statusCode){
                case 404:
                    return "notfound";
                case 500:
                    return "intererror";
                default:

                    break;
            }
        }
        return "";
    }

    private String getParamUrl(HttpServletRequest request){
        StringBuffer sb = new StringBuffer();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()){
            String param = enumeration.nextElement();
            String value = request.getParameter(param);
            sb.append(param);
            sb.append("=");
            sb.append(value);
            sb.append("&");
        }
        String url = "";
        if(sb.length() > 0) {
            sb.insert(0, "?");
            url = sb.toString();
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

    @RequestMapping("error/forbidden")
    public ModelAndView forbidden(String token, String url) {

        ModelAndView mv = new ModelAndView("/message/forbidden");
        mv.addObject("message", url);
        return mv;
    }
    @RequestMapping("/error/notfound")
    public ModelAndView notFound(String msg,String token){
        ModelAndView mv = new ModelAndView("/error/notfound");
        mv.addObject("message",msg);
        return mv;
    }

    @RequestMapping("/error/intererror")
    public ModelAndView internalError(String token,HttpServletRequest request,WebRequest webRequest){
        Map<String,Object> errorMap = getErrorAttributes(request,webRequest,true);
        ModelAndView mv = new ModelAndView("/error/intererror");
        return mv;
    }

    @Override
    public String getErrorPath() {
        return "/error/error";
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request,WebRequest webRequest,boolean includeStackTrace) {
        Map<String, Object> map = this.errorAttributes.getErrorAttributes(webRequest,true);
        String URL = request.getRequestURL().toString();
        map.put("URL", URL);
        return map;
    }
}
