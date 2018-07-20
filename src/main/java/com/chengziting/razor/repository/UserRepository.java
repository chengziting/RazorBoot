package com.chengziting.razor.repository;

import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 2018/5/2.
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {
//    @Query("select ur.role from UserRole ur where ur.user.id=:userId")
//    List<Role> getRoles(@Param("userId")String userId);

    @Query("select u.role from User u where u.id=:userId")
    List<Role> getRoles(@Param("userId")String userId);
    @Query("select u from User u where u.role.id=:roleId")
    List<User> getUsersWithRole(@Param("roleId")String roleId);
    @Query(value = "select u from User u inner join Role r on u.role.id = r.id where r.name = :roleName",
            countQuery = "select count(u) from User u inner join Role r on u.role.id = r.id where r.name = :roleName")
    Page<User> findByRoleName(@Param("roleName") String roleName, Pageable pageable);
}
