package com.chengziting.razor.repository;

import com.chengziting.razor.model.persistent.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by user on 2018/7/18.
 */
public class UserRepositoryImpl {
    public UserRepositoryImpl(){

    }
    @PersistenceContext
    private EntityManager em;

}
