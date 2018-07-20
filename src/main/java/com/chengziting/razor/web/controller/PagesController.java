package com.chengziting.razor.web.controller;

import com.chengziting.razor.core.annotations.WithoutAuthorize;
import com.chengziting.razor.model.persistent.RBPage;
import com.chengziting.razor.service.IRBPageService;
import com.chengziting.razor.utils.redis.RedisCacheFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.*;

/**
 * Created by user on 2018/7/19.
 */
@Controller
@RequestMapping("pages")
@WithoutAuthorize
public class PagesController {
    @Autowired
    private IRBPageService pageService;
    @Autowired
    private RedisCacheFactory redisCacheFactory;

    @Autowired
    @SuppressWarnings("unchecked")
    private RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

    @RequestMapping("list")
    @Transactional(rollbackFor = Exception.class)
    public ModelAndView listPage() throws Exception {
        ModelAndView mv = new ModelAndView("/pages/list");
        mv.addObject("urlmap",getFromCache());
        return mv;
    }

    private Map<String,List<Map<String,String>>> getPageList() throws Exception {
        Map<String,List<Map<String,String> >> urlMap = new HashMap<>();

        Map<RequestMappingInfo,HandlerMethod> handlerMethodMap = requestMappingInfoHandlerMapping.getHandlerMethods();
        for(Map.Entry<RequestMappingInfo,HandlerMethod> m : handlerMethodMap.entrySet()){
            Map<String,String> hashMap = new HashMap<>();
            RequestMappingInfo mappingInfo = m.getKey();
            HandlerMethod method = m.getValue();
            if(method.getBeanType().getAnnotation(Controller.class) != null &&
                    method.getMethod().getAnnotation(RequestMapping.class) != null){

                String controllerName = (String)method.getBean();
                String actionName = method.getMethod().getName();
                if(urlMap.get(controllerName) == null){
                    urlMap.put(controllerName,new ArrayList<>());
                }
                List<Map<String,String>> urlList = urlMap.get(controllerName);
                String url = (String)mappingInfo.getPatternsCondition().getPatterns().toArray()[0];
                hashMap.put("controller",controllerName);
                hashMap.put("action",actionName);
                hashMap.put("url",url);
                String requestMethod = "GET";
                if(method.getMethod().getAnnotation(RequestMapping.class).method().length > 0){
                    requestMethod = method.getMethod().getAnnotation(RequestMapping.class).method()[0].name();
                }
                hashMap.put("method",requestMethod);
                urlList.add(hashMap);
                syncToDB(controllerName,actionName,url);
            }
        }
        return urlMap;
    }

    private Map<String,List<Map<String,String>>> getFromCache() throws Exception {
        String redisKey = "RazorPageList";
        if(redisCacheFactory.exists(redisKey)){
            return  (Map<String,List<Map<String,String>>>)redisCacheFactory.get(redisKey,Map.class);
        }

        Map<String,List<Map<String,String>>> urlMap = getPageList();

        redisCacheFactory.saveToCache(redisKey,new Gson().toJson(urlMap,Map.class),24*60*60*1000);
        return urlMap;
    }

    private void syncToDB(String controller,String action,String url) throws Exception {
        if(pageService.findByUrl(url) == null){
            RBPage newObj = new RBPage();
            newObj.setController(controller);
            newObj.setAction(action);
            newObj.setUrl(url);
            newObj.setModule("");
            pageService.save(newObj);
        }
    }
}
