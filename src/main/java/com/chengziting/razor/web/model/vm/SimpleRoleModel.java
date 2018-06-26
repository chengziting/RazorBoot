package com.chengziting.razor.web.model.vm;

import java.util.List;

/**
 * Created by user on 2018-03-08.
 */
public class SimpleRoleModel {
    private String id;
    private String name;
    private int status;
    private List<SimpleUserInfoModel> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SimpleUserInfoModel> getUsers() {
        return users;
    }

    public void setUsers(List<SimpleUserInfoModel> users) {
        this.users = users;
    }
}
