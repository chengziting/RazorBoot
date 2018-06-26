package com.chengziting.razor.service;

import com.chengziting.razor.model.persistent.UserFile;
import com.chengziting.razor.service.base.IBaseService;

import java.util.List;

/**
 * Created by user on 2018/5/8.
 */
public interface IUserFileService extends IBaseService<UserFile,String> {
    List<UserFile> getFilesByUserId(String uid);
}
