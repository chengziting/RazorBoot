package com.chengziting.razor.web.controller;


import com.chengziting.razor.core.annotations.WithoutAuthorize;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by user on 2018/5/2.
 */
@Controller
@RequestMapping("test")
@WithoutAuthorize
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("test")
    public String test(String p, HttpServletRequest request){
        List<User> userList = userRepository.findAll();
        request.setAttribute("title","test"+p);
        request.setAttribute("userlist",userList);
        return "/test/test";
    }
}
