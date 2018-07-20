package com.chengziting.razor.model.persistent;

import com.chengziting.razor.model.system.GUIDGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by user on 2018/7/19.
 */
@Entity
@Table(name = "pages")
public class RBPage extends BaseModel{
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = GUIDGenerator.GENERATOR_NAME)
    private String id;

    @Column(name = "controller",nullable = false)
    private String controller;
    @Column(name = "action",nullable = false)
    private String action;
    @Column(name = "url",nullable = false)
    private String url;
    @Column(name = "module",nullable = false)
    private String module;
    @Column(name = "status",nullable = false,columnDefinition = "0")
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
