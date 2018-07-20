package com.chengziting.razor.repository;

import com.chengziting.razor.model.persistent.RBPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2018/7/19.
 */
@Repository
public interface RBPageRepository extends JpaRepository<RBPage,String> {
    RBPage findByUrl(String url);
}
