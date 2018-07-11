package com.chengziting.razor.utils.http;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

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
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        try {
            HttpGet request = new HttpGet(url);
//            request.addHeader("User-Agent","Mozilla/5.0(compatible;X11;U;Linux i686;en-US) Gecko/20081202 Firefox(Debian-2.0.0.19-0etch1)");
//            request.addHeader("Content-Type", "application/text");
//            request.addHeader("Access-Control-Allow-Origin", "*");
//            request.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
            HttpResponse response = httpClient.execute(request);
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
