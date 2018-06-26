package com.chengziting.razor.core;

/**
 * Created by user on 2018/4/20.
 */
public interface IResponse {
    static final int PARAM_INCORRECT    = 1000;
    static final int KAPTCHA_INCORRECT  = 1001;
    
    
    static final int LOGIN_SUCCESS      = 2000;
    static final int LOGIN_FAILED       = 2001;

    static final int DELETE_SUCCESS     = 3000;
    static final int DELETE_FAILED      = 3001;
    static final int DELETE_FORBIDDEN   = 3002;

    static final int UPLOAD_SUCCESS     = 4000;
    static final int UPLOAD_FAILED      = 4001;

    static final int EDIT_SUCCESS       = 5000;
    static final int EDIT_FAILED        = 5001;

    static final int ADD_SUCCESS        = 6000;
    static final int ADD_FAILED         = 6001;


    static final int OPERATION_SUCCESS  = 20000;
    static final int OPERATION_FAILED   = 20001;

    int getCode();
    void setCode(int code);
    Object getData();
    void setData(Object data);
}
