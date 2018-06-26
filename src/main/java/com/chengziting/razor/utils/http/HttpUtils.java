package com.chengziting.razor.utils.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Dictionary;

/**
 * Created by user on 2018/6/19.
 */
public class HttpUtils {
    private static HttpUtils _instance;

    public static HttpUtils instance(){
        synchronized (HttpUtils.class){
            if(_instance == null)
                _instance = new HttpUtils();
            return _instance;
        }
    }

    public String get(String url){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        String result = null;
        try {
            HttpResponse response = httpClient.execute(request);
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
