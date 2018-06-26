package com.chengziting.razor.web.model;

/**
 * Created by user on 2018-01-16.
 */
public class ViewModel<T> {
    private String title;
    private T data;
    private String redirect;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
