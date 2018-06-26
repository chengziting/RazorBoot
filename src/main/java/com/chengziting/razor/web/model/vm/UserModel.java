package com.chengziting.razor.web.model.vm;

import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.utils.StringUtils;
import com.chengziting.razor.web.model.Validatable;

import java.util.List;

/**
 * Created by user on 2018/5/23.
 */
public class UserModel {
    public static class EditUserModel implements Validatable{
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

        @Override
        public boolean isValidate() {
            return !StringUtils.isNullOrEmpty(id) && !StringUtils.isNullOrEmpty(name);
        }
    }

    public static class AddUserModel extends EditUserModel{
        private String roleId;

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        @Override
        public boolean isValidate() {
            return (!StringUtils.isNullOrEmpty(getName())) && (!StringUtils.isNullOrEmpty(roleId));
        }
    }

    public static class EditUserDetailModel extends EditUserModel{
        private Role role;
        private List<Role> allRoles;

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public List<Role> getAllRoles() {
            return allRoles;
        }

        public void setAllRoles(List<Role> allRoles) {
            this.allRoles = allRoles;
        }

        @Override
        public boolean isValidate() {
            boolean valid = super.isValidate();
            if(!valid)
                return valid;
            if(role == null || StringUtils.isNullOrEmpty(role.getId()))
                return false;
            return true;
        }
    }
}
