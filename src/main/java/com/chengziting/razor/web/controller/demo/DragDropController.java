package com.chengziting.razor.web.controller.demo;

import com.chengziting.razor.core.annotations.WithoutAuthorize;
import com.chengziting.razor.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/7/11.
 */
@Controller
@WithoutAuthorize
@RequestMapping("dragdrop")
public class DragDropController extends BaseController {
    @RequestMapping("test")
    public ModelAndView test(){
        ModelAndView mv = new ModelAndView("/dragdrop/test");
        List<String> list = new ArrayList<>();
        list.add("User");
        list.add("Role");
        list.add("Organization");
        mv.addObject("moduleList",list);
        return mv;
    }


}
