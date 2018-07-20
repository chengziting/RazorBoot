package com.chengziting.razor.model.persistent;

import com.chengziting.razor.model.system.GUIDGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by user on 2018-01-05.
 */
@Entity
@Table(name = "roles")
public class Role extends BaseModel {
    private String id;
    private String name;
    private int status;
//    private List<User> users;

    @Column(name = "Permission")
    private String permission;


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = GUIDGenerator.GENERATOR_NAME)
    @Column(name = "Id")
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
    @Column(name = "Name")
    @NotNull
    public String getName(){
        return name;
    }

    public void setStatus(int status){
        this.status = status;
    }
    @Column(name = "Status")
    public int getStatus(){
        return status;
    }

//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name = "userrole",
//            joinColumns = @JoinColumn(name = "roleid",referencedColumnName = "Id"),
//            inverseJoinColumns = @JoinColumn(name = "userid",referencedColumnName = "Id"))
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }


    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Role))
            return false;
        Role tmp = (Role)obj;
        return tmp.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

}
