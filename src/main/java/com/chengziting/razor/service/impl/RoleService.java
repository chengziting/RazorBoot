package com.chengziting.razor.service.impl;


import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.repository.RoleRepository;
import com.chengziting.razor.service.IRolesService;
import com.chengziting.razor.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Role> search(String name, String status) {
        if(name == null && status == null){
            return roleRepository.findAll();
        }

        if(name != null && status == null){
            return roleRepository.search(name);
        }
        try {
            if (name == null && status != null) {
                return roleRepository.search(Integer.parseInt(status));
            }

            return roleRepository.search(name, Integer.parseInt(status));
        }catch (NumberFormatException nfe){
            return roleRepository.search(name);
        }
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role findRole(String name) {
        return roleRepository.checkRole(name);
    }
}
