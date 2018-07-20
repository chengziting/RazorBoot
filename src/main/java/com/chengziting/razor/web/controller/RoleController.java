package com.chengziting.razor.web.controller;

import com.chengziting.razor.core.IResponse;
import com.chengziting.razor.core.JsonResponse;
import com.chengziting.razor.core.annotations.Authorization;
import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.service.impl.RoleService;
import com.chengziting.razor.service.impl.UserService;
import com.chengziting.razor.utils.StringUtils;
import com.chengziting.razor.web.model.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by user on 2018/5/31.
 */
@RequestMapping("roles")
@Controller
@Authorization
public class RoleController extends BaseController{
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @RequestMapping("roleinfo")
    public ModelAndView roleInfo(String id){
        ModelAndView mv = new ModelAndView("/roles/roleinfo");
        Role role = roleService.get(id);
        mv.addObject("roleinfo",role);
        return mv;
    }

    @RequestMapping("list")
    public ModelAndView roleList(HttpServletRequest request){
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        List<Role> roleList = roleService.search(name,status);
        Page<User> page = userService.findByRoleName("user", PageRequest.of(0,1));
        System.out.println(String.format("There are %s users in role %s.",page.getTotalElements(),"user"));
        ModelAndView mv = new ModelAndView("/roles/list");
        mv.addObject("rolelist",roleList);
        return mv;
    }

    @RequestMapping(value = "doEditRole",method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public JsonResponse doEditRole(@RequestBody EditRoleModel model){
        if(!model.isValidate()){
            return new JsonResponse("data error!", IResponse.PARAM_INCORRECT);
        }

        Role savedRole = roleService.get(model.getId());
        if(savedRole == null){
            return new JsonResponse("Can not find target object!",IResponse.EDIT_FAILED);
        }

        savedRole.setName(model.getName());
        savedRole.setStatus(model.getStatus());
        Role tmp = roleService.save(savedRole);
        return new JsonResponse("Edit success!",IResponse.EDIT_SUCCESS);
    }

    static class EditRoleModel implements Validatable{
        private String id;
        private String name;
        private int status;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public boolean isValidate() {
            return !StringUtils.isNullOrEmpty(id) && !StringUtils.isNullOrEmpty(name);
        }

    }
}
