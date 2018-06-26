package com.chengziting.razor.model.persistent;

import com.chengziting.razor.model.system.GUIDGenerator;
import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 2018-01-16.
 */
@Entity
@Table(name = "users")
public class User extends BaseModel {
    private String id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String qq;
    private int status;

    private Role role;

//    private List<Role> roles;

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

    @Column(name = "Name")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Password")
    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "Email")
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "QQ")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Column(name = "Status")
    @NotNull
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name = "userrole",
//            joinColumns = @JoinColumn(name = "userid",referencedColumnName = "Id",columnDefinition = "UserId"),
//            inverseJoinColumns = @JoinColumn(name = "roleid",referencedColumnName = "Id",columnDefinition = "RoleId"))
//    @JoinColumn(name = "roleid")
//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "roleid")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public void setCreateDate(Date date) {
        super.setCreateDate(date);
    }

    @Override
    public int hashCode() {
        return (name).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof User))
            return false;
        User tmp = (User)obj;
        return obj.equals(tmp);
    }
}
