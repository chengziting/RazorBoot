package com.chengziting.razor.service.impl;


import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.repository.RoleRepository;
import com.chengziting.razor.service.IRolesService;
import com.chengziting.razor.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2018-01-05.
 */
@Service
public class RoleService extends BaseService<Role,String> implements IRolesService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected JpaRepository<Role, String> getRepo() {
        return roleRepository;
    }

    @Override
    public Role getDefault() {
        Role example = new Role();
        example.setName(IRolesService.ROLE_USER);
        return this.getFirst(example);
    }
}
