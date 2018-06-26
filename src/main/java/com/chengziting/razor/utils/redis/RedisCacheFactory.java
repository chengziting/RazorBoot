package com.chengziting.razor.utils.redis;

import com.google.gson.Gson;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2018/4/17.
 */
@Component
public class RedisCacheFactory {

    private StringRedisTemplate redisTemplate;

    public RedisCacheFactory(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        System.out.println("call RedisCacheFactory() constructor");
    }
    public void saveToCache(String key, String value, long millisSeconds) {
        redisTemplate.opsForValue().set(key,value,millisSeconds, TimeUnit.MILLISECONDS);
    }

    public <T> void saveToCache(String key, Class<T> obj, long millisSeconds) {
        String value = new Gson().toJson(obj,(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        redisTemplate.opsForValue().set(key,value,millisSeconds,TimeUnit.MILLISECONDS);
    }

    public String get(String key) {
        if(!redisTemplate.hasKey(key))
            return null;

        return redisTemplate.opsForValue().get(key);
    }

    public <T> T get(String key, Class<T> type) {
        String value = redisTemplate.opsForValue().get(key);
        T obj = new Gson().fromJson(value,type);
        return obj;
    }

    public boolean refresh(String key, long millisSecond) {
        if(!redisTemplate.hasKey(key))
            return false;
        redisTemplate.expireAt(key,new Date(System.currentTimeMillis()+millisSecond));
        return true;
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
}
