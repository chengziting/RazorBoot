package com.chengziting.razor.core;

import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.utils.redis.RedisCacheFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2018/4/16.
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

}
