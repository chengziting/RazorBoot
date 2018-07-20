package com.chengziting.razor.model.persistent;

import com.chengziting.razor.model.system.GUIDGenerator;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by user on 2018/5/8.
 */
@Entity
@Table(name = "userfile")
public class UserFile extends BaseModel {
    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = GUIDGenerator.GENERATOR_NAME)
    private String id;
    @Column(name = "filepath")
    @NotNull
    private String filePath;
    @Column(name = "filetype")
    @ColumnDefault(value = "0")
    private String fileType;
    @Column(name = "status")
    @ColumnDefault(value = "0")
    private int status;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userid")
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
