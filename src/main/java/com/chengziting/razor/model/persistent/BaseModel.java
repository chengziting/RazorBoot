package com.chengziting.razor.model.persistent;

import com.chengziting.razor.model.system.GUIDGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by user on 2017-12-29.
 */
public class BaseModel {
//    protected String id;
    @Column(name = "CreateDate")
    protected Date m_createDate;
    @Column(name="UpdateDate")
    protected Date m_updateDate;


    public void setCreateDate(Date date){
        this.m_createDate = date;
    }

    public Date getCreateDate(){
        return m_createDate;
    }
    public void setUpdateDate(Date date){
        this.m_updateDate = date;
    }
    public Date getUpdateDate(){
        return m_updateDate;
    }
}
