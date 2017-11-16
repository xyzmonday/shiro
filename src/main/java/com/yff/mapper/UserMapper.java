package com.yff.mapper;


import com.yff.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User queryByUserName(String username);

    User queryRolesByUserName(String username);
}
