package com.chengziting.razor.core;

import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.service.IUserService;
import com.chengziting.razor.utils.CacheModel;
import com.chengziting.razor.utils.SymmetricEncoder;
import com.chengziting.razor.utils.redis.RedisCacheFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2018/5/8.
 */
public class RazorContext {
    public synchronized static User getCurrentUser(String token) {
        if(token == null || "".equals(token.trim()))
            return null;
        String key = SymmetricEncoder.AESDncode(token);
        RedisCacheFactory cacheFactory = (RedisCacheFactory) SpringContextUtil.getApplicationContext().getBean(RedisCacheFactory.class);
        if (!cacheFactory.exists(key))
            return null;
        CacheModel<String> cacheModel = cacheFactory.get(key, CacheModel.class);
        if(cacheModel == null) return null;
        IUserService userService = (IUserService)SpringContextUtil.getApplicationContext().getBean(IUserService.class);
        User currentUser = userService.get(cacheModel.getKey());
        if(currentUser == null || !(currentUser.getPassword().equals(cacheModel.getValue()))) return null;

        return currentUser;
    }

    public synchronized static String getToken(){
        HttpServletRequest request = (HttpServletRequest)SpringContextUtil.getApplicationContext().getBean(HttpServletRequest.class);
        String token = null;
        if((token = request.getParameter("token")) == null && (token = request.getHeader("token"))==null){
            return null;
        }
        return token;
    }

    public synchronized static String getToken(HttpServletRequest request){
        String token = null;
        if((token = request.getParameter("token")) == null && (token = request.getHeader("token"))==null){
            return null;
        }
        return token;
    }

}
