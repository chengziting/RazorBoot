package com.chengziting.razor.service.impl;

import com.chengziting.razor.model.persistent.RBDepartment;
import com.chengziting.razor.repository.RBDepartmentRepository;
import com.chengziting.razor.service.IRBDepartmentService;
import com.chengziting.razor.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2018/7/23.
 */
@Service
public class RBDepartmentService extends BaseService<RBDepartment,String> implements IRBDepartmentService {
    @Autowired
    private RBDepartmentRepository repository;
    @Override
    protected JpaRepository<RBDepartment, String> getRepo() {
        return repository;
    }
}
