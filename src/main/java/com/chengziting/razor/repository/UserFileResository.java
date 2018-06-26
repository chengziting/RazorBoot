package com.chengziting.razor.repository;

import com.chengziting.razor.model.persistent.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 2018/5/8.
 */
@Repository
public interface UserFileResository extends JpaRepository<UserFile,String> {
    @Query("select uf from UserFile uf where uf.user.id=:uid")
    List<UserFile> getFilesByUserId(@Param("uid") String uid);
}
