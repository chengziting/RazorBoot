package com.chengziting.razor.web.controller;

import com.chengziting.razor.model.persistent.UserFile;
import com.chengziting.razor.service.IUserFileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2018/5/11.
 */
@Controller
@RequestMapping("dialog")
public class DialogController extends BaseController{
    @Autowired
    private IUserFileService userFileService;

    @RequestMapping("deletefile")
    public ModelAndView deleteFile(String id, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/dialog/deletefile");
        UserFile userFile = userFileService.get(id);
        mv.addObject("data",userFile);
        return mv;
    }
}
