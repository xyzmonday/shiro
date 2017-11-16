package com.yff.controller;

import com.yff.pojo.User;
import com.yff.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShiroController {

    @Autowired
    private UserService userService;

    @RequestMapping("/testShiroAnnotation")
    @RequiresRoles({"admin"})
    public String testShiroAnnotation() {
        userService.testShiroAnnotation();
        return "redirect:/success";
    }

    @RequestMapping("/rememberMe")
    public String rememberMe() {
        return "rememberMe";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String toLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam(value = "rememberMe",required = false,defaultValue = "false") boolean rememberMe) {

        System.out.println("username = " + username + "; password = " + password);
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // rememberMe
            token.setRememberMe(rememberMe);
            try {
                // 执行登录.
                currentUser.login(token);
            }
            // catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类.
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                System.out.println("登录失败: " + ae.getMessage());
            }
        }
        return "redirect:/success";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }

    @RequestMapping("/user/add")
    public String addUser() {
        return "user-add";
    }


    @RequestMapping("/queryUser")
    @ResponseBody
    public User queryUser(@RequestParam(value = "username",required = true) String username) {
        User user = userService.queryByUserName(username);
        return user;
    }

    @RequestMapping("/queryRoles")
    @ResponseBody
    public User queryRoles(@RequestParam("username") String username) {
        User user = userService.queryRolesByUserName(username);
        return user;
    }
}
