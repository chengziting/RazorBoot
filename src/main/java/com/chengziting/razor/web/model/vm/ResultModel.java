package com.chengziting.razor.web.model.vm;

/**
 * Created by user on 2018-02-24.
 */
public class ResultModel<T> {
    private boolean success;
    private T data;
    private String message;
    private String redirect;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
