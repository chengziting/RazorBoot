package com.chengziting.razor.web.controller;

import com.chengziting.razor.core.IResponse;
import com.chengziting.razor.core.JsonResponse;
import com.chengziting.razor.core.annotations.Administrator;
import com.chengziting.razor.core.annotations.Authorization;
import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.service.IRolesService;
import com.chengziting.razor.service.IUserService;
import com.chengziting.razor.utils.StringUtils;
import com.chengziting.razor.utils.SymmetricEncoder;
import com.chengziting.razor.web.model.vm.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by user on 2018-03-02.
 */
@Controller
@Authorization
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;
    @Autowired
    private IRolesService rolesService;
    @Administrator
    @RequestMapping("list")
    public String listUsers(HttpServletRequest request) throws Exception {
        List<User> userList = userService.getList();
        request.setAttribute("user_list",userList);
        return "/users/list";
    }
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public JsonResponse delete(String id){

        return new JsonResponse("success", IResponse.DELETE_SUCCESS);
    }

    @RequestMapping("userinfo")
    public String userInfo(String id,HttpServletRequest request){
//        ModelAndView mv = new ModelAndView("/users/userinfo");
//        User savedUser = userService.get(id);
//        if(savedUser == null){
//            mv.setViewName("redirect:/error/notfound?msg="+id+"&token="+request.getParameter("token"));
//        }
//        mv.addObject("user",savedUser);
//        return mv;
        return "/users/userinfo";
    }

    @RequestMapping("getUserInfo")
    @ResponseBody
    public JsonResponse getUserInfo(String id){
        if(StringUtils.isNullOrEmpty(id))
            return new JsonResponse("data error!",IResponse.PARAM_INCORRECT);
        User savedUser = userService.get(id);
        UserModel.EditUserModel editUser = new UserModel.EditUserModel();
        editUser.setId(savedUser.getId());
        editUser.setName(savedUser.getName());
        editUser.setEmail(savedUser.getEmail());
        editUser.setPhone(savedUser.getPhone());
        editUser.setQq(savedUser.getQq());

        return new JsonResponse(editUser);
    }

    @RequestMapping(value = "getUserDetailInfo")
    @ResponseBody
    @Administrator
    public JsonResponse getUserDetailInfo(String id){
        if(StringUtils.isNullOrEmpty(id))
            return new JsonResponse("data error!",IResponse.PARAM_INCORRECT);
        User savedUser = userService.get(id);
        UserModel.EditUserDetailModel editUser = new UserModel.EditUserDetailModel();
        editUser.setId(savedUser.getId());
        editUser.setName(savedUser.getName());
        editUser.setEmail(savedUser.getEmail());
        editUser.setPhone(savedUser.getPhone());
        editUser.setQq(savedUser.getQq());
//        List<Role> roleList = userService.getRoles(savedUser.getId());
        editUser.setRole(savedUser.getRole());

        List<Role> allRoles = rolesService.getList();
        editUser.setAllRoles(allRoles);

        return new JsonResponse(editUser);
    }

    @RequestMapping(value = "edituser",method = RequestMethod.POST)
    @Administrator
    @ResponseBody
    public JsonResponse editUser(@RequestBody UserModel.EditUserModel model,HttpServletRequest request){
        if(model == null || !model.isValidate())
            return new JsonResponse("data error",IResponse.EDIT_FAILED);

        User savedUser = userService.get(model.getId());
        if(savedUser == null)
            return new JsonResponse("The edit data was not exists!",IResponse.EDIT_FAILED);
        savedUser.setEmail(model.getEmail());
        savedUser.setPhone(model.getPhone());
        savedUser.setQq(model.getQq());
        userService.save(savedUser);
        return new JsonResponse("Edit Success!",IResponse.EDIT_SUCCESS);
    }

    @RequestMapping("usersinrole")
    @Administrator
    public ModelAndView usersInRole(String roleid){
        List<User> userList = userService.getUsersWithRole(roleid);
        Role role = rolesService.get(roleid);
        ModelAndView mv = new ModelAndView("/users/userinrole");
        mv.addObject("userlist",userList);
        mv.addObject("roleinfo",role);
        return mv;
    }

    @RequestMapping(value = "edituserdetail",method = RequestMethod.POST)
    @Administrator
    @ResponseBody
    public JsonResponse editUserDetail(@RequestBody UserModel.EditUserDetailModel model){
        if(!model.isValidate())
            return new JsonResponse("data error!",IResponse.EDIT_FAILED);
        User savedUser = userService.get(model.getId());
        Role savedRole = rolesService.get(model.getRole().getId());
        savedUser.setRole(savedRole);
        savedUser.setEmail(model.getEmail());
        savedUser.setPhone(model.getPhone());
        savedUser.setQq(model.getQq());
        userService.save(savedUser);
        return new JsonResponse("Edit success!",IResponse.EDIT_SUCCESS);
    }

    @RequestMapping("adduser")
    @Administrator
    public ModelAndView addUser(){
        ModelAndView mv = new ModelAndView("/users/adduser");
        List<Role> roleList = rolesService.getList();
        mv.addObject("rolelist",roleList);
        return mv;
    }
    @RequestMapping(value = "addNewUser",method = RequestMethod.POST)
    @Administrator
    @ResponseBody
    public JsonResponse doAddNewUser(@RequestBody UserModel.AddUserModel model){
        if(!model.isValidate())
            return new JsonResponse("data error!",IResponse.PARAM_INCORRECT);
        Role tmpRole = rolesService.get(model.getRoleId());
        if(tmpRole == null)
            return new JsonResponse("Target role was not exists!",IResponse.PARAM_INCORRECT);
        User newUser = new User();
        newUser.setName(model.getName());
        newUser.setPhone(model.getPhone());
        newUser.setEmail(model.getEmail());
        newUser.setQq(model.getQq());
        newUser.setRole(tmpRole);
        newUser.setPassword(SymmetricEncoder.AESEncode("123456"));
        userService.saveWithCheck(newUser);
        return new JsonResponse("Add success!",IResponse.OPERATION_SUCCESS);
    }
}
