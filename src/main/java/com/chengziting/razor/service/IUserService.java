package com.chengziting.razor.service;

import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.service.base.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by user on 2018-01-16.
 */
public interface IUserService extends IBaseService<User,String> {
    User get(String name, String password);
    String saveWithCheck(User users);
    List<Role> getRoles(String userId);
    List<User> getUsersWithRole(String roleId);
    Page<User> findByRoleName(String roleName, Pageable pageable);
}
