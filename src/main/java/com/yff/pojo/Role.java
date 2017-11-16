package com.yff.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Role {

    private Integer id;
    private String roleName;
    private List<Permission> permissionList;// 一个角色对应多个权限

    private List<User> userList;// 一个角色对应多个用户

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Set<String> getPermissionsName() {
        Set<String> set = new HashSet<>();
        List<Permission> permissionList = getPermissionList();
        if(permissionList == null) {
            return set;
        }
        for (Permission per : permissionList) {
            set.add(per.getPermissionName());
        }
        return set;
    }
}