package com.chengziting.razor.repository;

import com.chengziting.razor.model.persistent.RBDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2018/7/23.
 */

@Repository
public interface RBDepartmentRepository extends JpaRepository<RBDepartment,String> {
}
