package com.chengziting.razor.service.impl;

import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.repository.UserRepository;
import com.chengziting.razor.repository.base.BaseRepository;
import com.chengziting.razor.service.IUserService;
import com.chengziting.razor.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2018-01-16.
 */
@Service
public class UserService extends BaseService<User,String> implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected JpaRepository<User, String> getRepo() {
        return userRepository;
    }
    @Override
    public User get(String name, String password) {
        User example = new User();
        example.setName(name);
        example.setPassword(password);
        return this.getFirst(example);
    }

    @Override
    public String saveWithCheck(User user) {
        User saved = this.save(user);
        return saved == null ? "" : saved.getId();
    }

    @Override
    public List<Role> getRoles(String userId) {
        return userRepository.getRoles(userId);
    }

    @Override
    public List<User> getUsersWithRole(String roleId) {
        return userRepository.getUsersWithRole(roleId);
    }

    @Override
    public Page<User> findByRoleName(String roleName, Pageable pageable) {
        return userRepository.findByRoleName(roleName,pageable);
    }

}
