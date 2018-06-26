package com.chengziting.razor.web.model.vm;

/**
 * Created by user on 2018-03-15.
 */
public class LoginModel {
    private String name;
    private String password;
    private String verifyCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
