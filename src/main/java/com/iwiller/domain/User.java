package com.iwiller.domain;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String pwd;
    private Date createtime;

    public User() {
    }

    public User(Integer id, String username, String pwd, Date createtime) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
