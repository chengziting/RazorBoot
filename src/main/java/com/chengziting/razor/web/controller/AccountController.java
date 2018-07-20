package com.chengziting.razor.web.controller;


import com.chengziting.razor.core.IResponse;
import com.chengziting.razor.core.JsonResponse;
import com.chengziting.razor.core.annotations.WithoutAuthorize;

import com.chengziting.razor.core.exception.NoPermissionException;
import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.service.IRolesService;
import com.chengziting.razor.service.IUserService;
import com.chengziting.razor.utils.CacheModel;
import com.chengziting.razor.utils.CookieUtils;
import com.chengziting.razor.utils.redis.RedisCacheFactory;
import com.chengziting.razor.utils.SymmetricEncoder;
import com.chengziting.razor.web.model.vm.LoginModel;
import com.chengziting.razor.web.model.vm.RegisterModel;
import com.chengziting.razor.web.model.vm.ResultModel;
import com.google.gson.Gson;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 2018-01-16.
 */
@Controller
@WithoutAuthorize
@RequestMapping("account")
public class AccountController extends BaseController{

    @Autowired
    private IRolesService rolesService;
    @Autowired
    private IUserService usersService;
    @Autowired
    private RedisCacheFactory cacheFactory;


    @RequestMapping("login")
    @Transactional
    public String login() throws Exception {
        return "/account/login";
    }
    @RequestMapping("register")
    public String register(HttpServletRequest request) {
        return "/account/register";
    }

    @RequestMapping(value = "doRegister",method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String doRegister(@RequestBody RegisterModel model, HttpServletResponse response/*, HttpServletRequest request*/) throws ServiceException {
        ResultModel<String> rm = new ResultModel<String>();
        if(model.hasError()){
            rm.setMessage(model.getErrorMsg());
            rm.setRedirect("");
            return new Gson().toJson(rm);
        }
        Role role = rolesService.getDefault();
        if(role!=null){
            try {
//                Session session = SpringContextUtils.getCurrentSession();
                User users = new User();
                users.setName(model.getName());
                users.setEmail(model.getEmail());
                users.setRole(role);
                users.setPassword(SymmetricEncoder.AESEncode(model.getPassword()));
                User savedUser = usersService.save(users);
                CacheModel<String> cacheModel = new CacheModel<String>();

                cacheModel.setKey(savedUser.getId());
                cacheModel.setValue(users.getPassword());
                int expiry = 60 * 5;
                cacheModel.setExpiry(expiry);
                CookieUtils.writeCookie(response,SymmetricEncoder.AESEncode(cacheModel.getKey()),expiry);

                cacheFactory.saveToCache(cacheModel.getKey(),cacheModel.toString(),expiry * 1000);

                CacheModel temp = cacheFactory.get(cacheModel.getKey(),CacheModel.class);

                rm.setMessage("Register success");
                rm.setRedirect("/main/home?token="+cacheModel.getKey());
                rm.setSuccess(true);
            }catch (Exception ex){
                rm.setMessage("Register error");
                rm.setData(ex.getMessage());
                throw new ServiceException(ex.getMessage());
            }
        }

        return new Gson().toJson(rm);
    }

    private void saveToCache(User user,HttpServletResponse response){
        CacheModel<String> cacheModel = new CacheModel<String>();

        cacheModel.setKey(user.getId());
        cacheModel.setValue(user.getPassword());
        int expiry = 60 * 20;
        cacheModel.setExpiry(expiry);
        CookieUtils.writeCookie(response,SymmetricEncoder.AESEncode(cacheModel.getKey()),expiry);

        cacheFactory.saveToCache(cacheModel.getKey(),cacheModel.toString(),expiry * 1000);

        CacheModel temp = cacheFactory.get(cacheModel.getKey(),CacheModel.class);
    }

    @RequestMapping(value = "doLogin",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse doLogin(@RequestBody LoginModel model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(model == null || model.getName() == null || model.getPassword() == null)
            return new JsonResponse("", IResponse.PARAM_INCORRECT);

        User user = usersService.get(model.getName(),SymmetricEncoder.AESEncode(model.getPassword()));
        if(user == null)
            return new JsonResponse("user name or password error!",IResponse.LOGIN_FAILED);

        saveToCache(user,response);
        return new JsonResponse(SymmetricEncoder.AESEncode(user.getId()),"",IResponse.LOGIN_SUCCESS);
    }
}
