package com.chengziting.razor.repository;

import com.chengziting.razor.model.persistent.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by user on 2018/7/18.
 */
public class RoleRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings(value = {"unchecked"})
    public Role findByName(String name){

        String sql = "select r from Role r where r.name = ?1";
        Query query = em.createQuery(sql);
        query.setParameter(1,name);
        return (Role)query.getSingleResult();
    }

    public Role checkRole(String name){
        String sql = "select r from Role r where r.name = ?1";
        Query query = em.createQuery(sql);
        query.setParameter(1,name);
        return (Role)query.getSingleResult();
    }

}
