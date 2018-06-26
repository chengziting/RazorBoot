package com.chengziting.razor.web.controller;

import com.chengziting.razor.core.IResponse;
import com.chengziting.razor.core.JsonResponse;
import com.chengziting.razor.core.RazorContext;
import com.chengziting.razor.core.SpringContextUtil;
import com.chengziting.razor.core.annotations.Authorization;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.model.persistent.UserFile;
import com.chengziting.razor.service.impl.UserFileService;
import com.chengziting.razor.utils.SymmetricEncoder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by user on 2018/5/7.
 */
@Controller
@RequestMapping("resource")
@Authorization
public class ResourceController extends BaseController{
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private UserFileService userFileService;

    @RequestMapping("index")
    public String index() {
        return "/resource/index";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public JsonResponse upload(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("fileName：" + file.getOriginalFilename());
        String path = Paths.get("upload").toFile().getAbsolutePath()+"/("+System.currentTimeMillis()+")" + file.getOriginalFilename();

        File newFile = new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);

        String token = request.getHeader("token");
        UserFile userFile = new UserFile();
        userFile.setFilePath(newFile.getAbsolutePath());
        userFile.setFileType(FilenameUtils.getExtension(newFile.getName()));
        userFile.setUser(RazorContext.getCurrentUser(token));
        userFileService.save(userFile);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return new JsonResponse("success",IResponse.UPLOAD_SUCCESS);
    }


    @RequestMapping("filelist")
    public ModelAndView fileList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/resource/filelist");
        User currentUser = RazorContext.getCurrentUser(request.getParameter("token"));
        List<UserFile> userFileList = userFileService.getFilesByUserId(currentUser.getId());
        Map<String,String> filesMap = new HashMap<>();
        for (UserFile file : userFileList) {
            String fileName = FilenameUtils.getName(file.getFilePath());
            filesMap.put(file.getId(),fileName);
        }
        mv.addObject("filelist", filesMap);
        return mv;
    }
}
