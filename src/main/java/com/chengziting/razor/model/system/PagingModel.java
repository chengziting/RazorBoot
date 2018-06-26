package com.chengziting.razor.model.system;

import com.chengziting.razor.model.persistent.BaseModel;

import java.util.List;

/**
 * Created by user on 2018-01-05.
 */
public class PagingModel<T extends BaseModel> {
    private long currentPage;
    private long pagingSize;
    private long totalCount;
    private List<T> data;

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPagingSize() {
        return pagingSize;
    }

    public void setPagingSize(long pagingSize) {
        this.pagingSize = pagingSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
