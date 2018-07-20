package com.chengziting.razor.service;

import com.chengziting.razor.model.persistent.RBPage;
import com.chengziting.razor.service.base.IBaseService;

/**
 * Created by user on 2018/7/19.
 */
public interface IRBPageService extends IBaseService<RBPage,String> {
    RBPage findByUrl(String url);
}
