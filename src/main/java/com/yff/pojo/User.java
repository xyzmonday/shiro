package com.yff.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private Integer id;
    private String username;
    private String password;
    // 一个用户具有多个角色
    private List<Role> roleList;

    public User() {
        super();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}