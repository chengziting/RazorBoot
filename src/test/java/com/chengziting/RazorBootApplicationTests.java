package com.chengziting;

import com.chengziting.razor.model.persistent.Role;
import com.chengziting.razor.model.persistent.User;
import com.chengziting.razor.repository.RoleRepository;
import com.chengziting.razor.repository.UserRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RazorBootApplicationTests {

    private Logger logger = Logger.getLogger(RazorBootApplicationTests.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void contextLoads() {
        List<User> userList = userRepository.findAll();

        for (User u : userList){
            logger.info(u.getId()+"_"+u.getName());
            List<User> users = roleRepository.getUsers(u.getId());

            System.out.println(u.getName());
        }

        logger.error(new Exception("a error occurs"));
    }

}
