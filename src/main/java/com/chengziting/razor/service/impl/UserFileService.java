package com.chengziting.razor.service.impl;

import com.chengziting.razor.model.persistent.UserFile;
import com.chengziting.razor.repository.UserFileResository;
import com.chengziting.razor.service.IUserFileService;
import com.chengziting.razor.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2018/5/8.
 */
@Service
public class UserFileService extends BaseService<UserFile,String> implements IUserFileService {
    @Autowired
    private UserFileResository userFileResository;

    @Override
    protected JpaRepository<UserFile, String> getRepo() {
        return userFileResository;
    }

    @Override
    public List<UserFile> getFilesByUserId(String uid) {
        return userFileResository.getFilesByUserId(uid);
    }
}
