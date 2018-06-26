package com.chengziting.razor.web.controller;

import com.chengziting.razor.core.IResponse;
import com.chengziting.razor.core.JsonResponse;
import com.chengziting.razor.core.annotations.Administrator;
import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.service.IRolesService;
import com.chengziting.razor.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/5/14.
 */
@Controller
@RequestMapping("admin")
@Administrator
public class AdminController extends BaseController{
    @Autowired
    private IRolesService rolesService;
    @Autowired
    private IUserService userService;

    @RequestMapping("allroles")
    @ResponseBody
    public Map<String,String> getRoles(){
        List<Role> roleList = rolesService.getList();
        Map<String,String> rolesMap = new HashMap<>();
        for(Role r : roleList){
            rolesMap.put(r.getId(),r.getName());
        }
        return rolesMap;
    }

    @RequestMapping("edituser")
    @Administrator
    public ModelAndView editUser(String id){
        ModelAndView mv = new ModelAndView("/admin/edituser");
        User targetUser = userService.get(id);
        mv.addObject("data",targetUser);
        return mv;
    }

    @RequestMapping(value = "doEditUser",method = RequestMethod.POST)
    @Administrator
    @ResponseBody
    public JsonResponse doEditUser(@RequestBody EditUser user){
        if(user == null || !user.isValid()){
            return new JsonResponse("data error",IResponse.EDIT_FAILED);
        }
        User savedUser = userService.get(user.getId());
        if(savedUser == null){
            return new JsonResponse("can not find target data",IResponse.EDIT_FAILED);
        }
        savedUser.setPhone(user.getPhone());
        savedUser.setQq(user.getQq());
        savedUser.setEmail(user.getEmail());
        userService.save(savedUser);
        return new JsonResponse("edit success", IResponse.EDIT_SUCCESS);
    }

    @RequestMapping(value = "deleteuser",method = RequestMethod.POST)
    @Administrator
    @ResponseBody
    public JsonResponse doDeleteUser(@RequestBody String id){
        if(id == null || "".equals(id)){
            return new JsonResponse("data error",IResponse.DELETE_FAILED);
        }
        User savedUser = userService.get(id);
        if(savedUser  == null){
            return new JsonResponse("Can not find target data.",IResponse.EDIT_FAILED);
        }
        userService.delete(id);
        return new JsonResponse("delete success", IResponse.DELETE_SUCCESS);
    }

    public static class EditUser{
        private String id;
        private String name;
        private String email;
        private String phone;
        private String qq;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public boolean isValid(){
            return id != null && !("".equals(id)) &&
                    name != null && !("".equals(name));
        }
    }
}
