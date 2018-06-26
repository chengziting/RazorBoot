package com.chengziting.razor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by user on 2018/5/7.
 */
@Controller
@RequestMapping("testjsp")
public class TestJspController {
    @RequestMapping("test")
    public String test(){
        return "testjsp/test";
    }

    @RequestMapping("test2")
    public ModelAndView test2(){
        ModelAndView mv = new ModelAndView("testjsp/test2");
        return mv;
    }

    @RequestMapping("test3")
    public ModelAndView test3(){
        ModelAndView mv = new ModelAndView("testjsp/test3");
        return mv;
    }
}
