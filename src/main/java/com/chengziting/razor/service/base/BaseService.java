package com.chengziting.razor.service.base;


import com.chengziting.razor.model.persistent.BaseModel;
import com.chengziting.razor.model.system.PagingModel;
import com.chengziting.razor.repository.base.BaseRepository;
import org.hibernate.criterion.Criterion;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.PageAdapter;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by user on 2018-01-05.
 */
public abstract class BaseService<TEntity extends BaseModel,TId> implements IBaseService<TEntity,TId> {
    protected abstract JpaRepository<TEntity,TId> getRepo();

    @Override
    public TEntity get(TId tId) {
        return getRepo().getOne(tId);
    }

    @Override
    public List<TEntity> getList() {
        return getRepo().findAll();
    }

    @Override
    public TEntity save(TEntity var1) {
        return getRepo().save(var1);
    }

    @Override
    public List<TEntity> save(List<TEntity> var1) {
        Iterable<TEntity> entities = var1;
        List<TEntity> saved = getRepo().saveAll(entities);
        return saved;
    }


    @Override
    public void delete(List<TEntity> var1) {
        Iterable<TEntity> entities = var1;
        getRepo().deleteAll(entities);
    }


    @Override
    public void delete(TId tId) {
        getRepo().deleteById(tId);
    }

    @Override
    public Page<TEntity> getList(int startIndex, int pagingSize,TEntity entityExample) {
        Pageable pageable = PageRequest.of(startIndex,pagingSize);
        Page<TEntity> entityPage = getRepo().findAll(Example.of(entityExample),pageable);
        return entityPage;
    }

    @Override
    public TEntity getFirst(TEntity entityExample) {
        Example<TEntity>  example = Example.of(entityExample);
        List<TEntity> result = getRepo().findAll(example);
        if(result.size() > 0)
            return result.get(0);
        return null;
    }
}
