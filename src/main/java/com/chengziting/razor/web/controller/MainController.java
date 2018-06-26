package com.chengziting.razor.web.controller;


import com.chengziting.razor.core.annotations.Authorization;
import com.chengziting.razor.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2018/5/7.
 */
@Controller
@Authorization
@RequestMapping("main")
public class MainController extends BaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping("home")
    public String home(HttpServletRequest request){

        return "/main/home";
    }
}
