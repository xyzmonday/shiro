package com.yff.service.impl;

import com.yff.mapper.UserMapper;
import com.yff.anno.TargetDataSource;
import com.yff.pojo.User;
import com.yff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public void testShiroAnnotation() {
        System.out.println("testShiroAnnotation ; time :" + new Date());
    }

    @TargetDataSource(name="dataSource2")
    public User queryByUserName(String username) {
        return userMapper.queryByUserName(username);
    }

    public User queryRolesByUserName(String username) {
        return userMapper.queryRolesByUserName(username);
    }
}
