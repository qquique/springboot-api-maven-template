package com.qquique.jsbm.application.dto;

import java.util.Date;

public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private Date lastDateCreated;
    private Date lastDateModified;

    public Date getLastDateCreated() {
        return lastDateCreated;
    }

    public void setLastDateCreated(Date lastDateCreated) {
        this.lastDateCreated = lastDateCreated;
    }

    public Date getLastDateModified() {
        return lastDateModified;
    }

    public void setLastDateModified(Date lastDateModified) {
        this.lastDateModified = lastDateModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

