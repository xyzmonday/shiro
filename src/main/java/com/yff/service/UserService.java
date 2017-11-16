package com.yff.service;

import com.yff.pojo.Role;
import com.yff.pojo.User;

import java.util.List;

public interface UserService {

    User queryByUserName(String username);

    User queryRolesByUserName(String username);

    void testShiroAnnotation();
}
