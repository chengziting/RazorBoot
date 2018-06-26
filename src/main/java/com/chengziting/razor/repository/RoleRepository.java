package com.chengziting.razor.repository;

import com.chengziting.razor.model.persistent.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 2018/5/2.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    @Query("select ur.user from UserRole ur where ur.role.id = :roleId")
    List<User> getUsers(@Param("roleId") String roleId);
}
