package com.chengziting.razor.core;


import org.springframework.http.HttpStatus;

/**
 * Created by user on 2018/4/20.
 */
public class JsonResponse implements IResponse {
    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean success(){
        return this.code == HttpStatus.OK.value();
    }

    public JsonResponse(Object data){
        this.data = data;
        this.code = HttpStatus.OK.value();
    }

    public JsonResponse(Object data,String message,int code){
        this.data = data;
        this.message = message;
        this.code =code;
    }

    public JsonResponse(String message,int code){
        this.data = null;
        this.message = message;
        this.code = code;
    }
}
