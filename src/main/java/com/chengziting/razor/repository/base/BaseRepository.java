package com.chengziting.razor.repository.base;

import com.chengziting.razor.model.persistent.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2018/5/2.
 */
public interface BaseRepository<TEntity extends BaseModel,TId> {
}
