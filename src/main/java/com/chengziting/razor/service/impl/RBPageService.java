package com.chengziting.razor.service.impl;

import com.chengziting.razor.model.persistent.RBPage;
import com.chengziting.razor.repository.RBPageRepository;
import com.chengziting.razor.service.IRBPageService;
import com.chengziting.razor.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by user on 2018/7/19.
 */
@Service
public class RBPageService extends BaseService<RBPage,String> implements IRBPageService {
    @Autowired
    private RBPageRepository repository;
    @Override
    protected JpaRepository<RBPage, String> getRepo() {
        return repository;
    }

    @Override
    public RBPage findByUrl(String url) {
        return repository.findByUrl(url);
    }
}
