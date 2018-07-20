package com.chengziting.razor.service;

import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.service.base.IBaseService;

import java.util.List;

/**
 * Created by user on 2018-01-05.
 */
public interface IRolesService extends IBaseService<Role,String> {
    public static final String ROLE_USER = "user";
    public static final String ROLE_ADMIN="admin";
    public static final String ROLE_GUEST = "guest";

    Role getDefault();

    List<Role> search(String name, String status);
    Role findByName(String name);
    Role findRole(String name);
}
