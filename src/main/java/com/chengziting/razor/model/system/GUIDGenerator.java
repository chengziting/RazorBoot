package com.chengziting.razor.model.system;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.io.Serializable;

/**
 * Created by user on 2018-02-24.
 */
public class GUIDGenerator extends org.hibernate.id.GUIDGenerator {
    public static final String GENERATOR_NAME = "com.chengziting.razor.model.system.GUIDGenerator";
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String result = (String)super.generate(session, obj);
        String id = hexToStr(result);
        System.out.println("GUIDGenerator:id="+id);
        return id;
    }

    private String hexToStr(String guid) {
        return guid;
        //if it is oracle database,should do bellow operation;
//        return guid.replaceAll("(.{8})(.{4})(.{4})(.{4})(.{12})", "$1-$2-$3-$4-$5").replaceAll("(.{2})(.{2})(.{2})(.{2}).(.{2})(.{2}).(.{2})(.{2})(.{18})", "$4$3$2$1-$6$5-$8$7$9");
    }
}
