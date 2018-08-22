package com.chengziting.razor.web.controller;

import com.chengziting.razor.core.annotations.WithoutAuthorize;
import com.chengziting.razor.model.persistent.RBDepartment;
import com.chengziting.razor.service.IRBDepartmentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/7/23.
 */
@Controller
@RequestMapping("department")
@WithoutAuthorize
public class DepartmentController {
    @Autowired
    private IRBDepartmentService departmentService;

    @RequestMapping("list")
    @ResponseBody
    public String list(HttpServletRequest request){
//        ModelAndView mv = new ModelAndView("/department/list");
        List<RBDepartment> departmentList = departmentService.getList();
        DepartNode node = null;
        node = parseToDepartNode(departmentList,node);
//        mv.addObject("department",node);
//        return mv;
        return new Gson().toJson(node,DepartNode.class);
    }

    private DepartNode parseToDepartNode(List<RBDepartment> departmentList,DepartNode departNode){
        if(departmentList == null || departmentList.size() <= 0)
            return null;
        if(departNode == null){
            for(RBDepartment dept : departmentList){
                if(dept.getParentid() == null){
                    departNode = new DepartNode();
                    departNode.setId(dept.getId());
                    departNode.setName(dept.getName());
                    break;
                }
            }
             return parseToDepartNode(departmentList,departNode);
        }else{
            for(RBDepartment department : departmentList){
                if(departNode.getId().equals(department.getParentid())){
                    DepartNode node = new DepartNode();
                    node.setId(department.getId());
                    node.setName(department.getName());
                    departNode.getChildren().add(node);
                    parseToDepartNode(departmentList,node);
                }
            }
            return departNode;
        }
    }

    public static class DepartNode{
        private String id;
        private String name;
        private DepartNode parent;
        private List<DepartNode> children = new ArrayList<>();

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

        public DepartNode getParent() {
            return parent;
        }

        public void setParent(DepartNode parent) {
            this.parent = parent;
        }

        public List<DepartNode> getChildren() {
            return children;
        }

        public void setChildren(List<DepartNode> children) {
            this.children = children;
        }
    }
}
