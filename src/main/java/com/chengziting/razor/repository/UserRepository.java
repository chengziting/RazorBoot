package com.chengziting.razor.repository;

import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.repository.base.BaseRepository;
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
}
