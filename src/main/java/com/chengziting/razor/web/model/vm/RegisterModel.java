package com.chengziting.razor.web.model.vm;

/**
 * Created by user on 2018-02-24.
 */
public class RegisterModel {
    private String name;
    private String email;
    private String password;
    private String confirmPwd;
    private boolean existError;
    private String errorMsg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public boolean isExistError() {
        return existError;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean hasError(){
        StringBuffer sb = new StringBuffer();
        if(name == null || name.trim().equals("")){
            sb.append("[Name] was required\r\n");
        }

        if(email == null || email.trim().equals("")){
            sb.append("[Email] was required\r\n");
        }

        if(password == null || password.trim().equals("") ||
                confirmPwd == null || confirmPwd.trim().equals("") ||
                !password.equals(confirmPwd)){
            sb.append("[Password] valid failed");
        }
        if(sb.toString().length()>0){
            this.existError = true;
            this.errorMsg = sb.toString();
        }
        return this.existError;
    }
}
