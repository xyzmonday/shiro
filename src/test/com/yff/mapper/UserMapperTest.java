package com.yff.mapper;

import com.yff.pojo.User;
import com.yff.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class UserMapperTest {

    @Autowired
    private UserService userService;

    @Test
    public void testQueryRoles() {
        User user = userService.queryByUserName("tom");
        System.out.println(user);
    }

    @Test
    public void testAOP() {
        User user = userService.queryByUserName("yff");
        System.out.println(user);
    }
}
