package com.chengziting.razor.utils;

import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * Created by user on 2018/4/16.
 */
public class CacheModel<T> {
    private String key;
    private T value;
    private Date startTime;
    private long expiry;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setExpiry(long seconds) {
        expiry = seconds;
        startTime = new Date(System.currentTimeMillis() + seconds * 1000);
    }

    public long getExpiry(){
        return expiry;
    }

    public boolean isExpired(){
        return new Date().after(startTime);
    }

    @Override
    public String toString() {
        String json = new GsonBuilder().create().toJson(this,this.getClass());
        System.out.println(json);
        return json;
    }
}
