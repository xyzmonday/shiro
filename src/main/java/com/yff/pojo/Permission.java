package com.yff.pojo;

public class Permission {

    private Integer id;
    private String permissionName;
    // 一个权限对应一个角色
    private Role role;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}