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

    /**
     * search with name and status
     * @param status
     * @return
     */
    @Query("select r from Role r where r.status=:status")
    List<Role> search(@Param("status") int status);

    /**
     * search with name(like)
     * @param name
     * @return
     */
    @Query("select r from Role r where r.name like %:name%")
    List<Role> search(@Param("name") String name);

    /**
     * search with status
     * @param name
     * @param status
     * @return
     */
    @Query("select r from Role r where r.status=:status and r.name like %:name%")
    List<Role> search(@Param("name") String name,@Param("status") int status);

    Role findByName(String name);
    Role checkRole(String name);
}
