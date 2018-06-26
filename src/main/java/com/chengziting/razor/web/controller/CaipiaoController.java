package com.chengziting.razor.web.controller;

import com.chengziting.razor.core.annotations.WithoutAuthorize;
import com.chengziting.razor.utils.http.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by user on 2018/6/19.
 */
@Controller
@WithoutAuthorize
@RequestMapping("caipiao")
public class CaipiaoController extends BaseController {
    @RequestMapping("ssq")
    public ModelAndView sjq(){
        ModelAndView mv = new ModelAndView("/caipiao/ssq");
        String result = HttpUtils.instance().get("http://f.apiplus.net/ssq-20.json");
        mv.addObject("data",result);
        return mv;
    }
}
