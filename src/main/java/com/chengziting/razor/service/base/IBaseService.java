package com.chengziting.razor.service.base;

import com.chengziting.razor.model.persistent.BaseModel;
import com.chengziting.razor.model.system.PagingModel;
import org.hibernate.criterion.Criterion;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018-01-05.
 */
@Component
public interface IBaseService<TEntity,TId> {
    TEntity get(TId id);
    TEntity getFirst(TEntity entityExample);

    List<TEntity> getList();
    TEntity save(TEntity var1);

    List<TEntity> save(List<TEntity> var1);

    void delete(TId id);

    void delete(List<TEntity> var1);

    Page<TEntity> getList(int startIndex, int pagingSize, TEntity entityExample);
}
