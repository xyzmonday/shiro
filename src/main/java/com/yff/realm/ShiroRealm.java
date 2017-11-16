package com.yff.realm;

import com.yff.pojo.Role;
import com.yff.pojo.User;
import com.yff.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro认证和授权Realm
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("first realm");
        //1. 把AuthenticationToken装换为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //2. 从UsernamePasswordToken获取username
        String username = usernamePasswordToken.getUsername();
        //3. 调用数据，查询username对应的用户信息
        User user = userService.queryByUserName(username);
        if (user == null) {
            //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
            throw new UnknownAccountException("用户不存在!");
        }
        //5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
        if ("monster".equals(username)) {
            throw new LockedAccountException("用户被锁定");
        }
        //6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        //以下信息是从数据库中获取的.
        //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        Object principal = username;
        //2). credentials: 密码. shiro将username-password保存了，如果前台输入的密码不正确，那么shiro
        // 通过credentialsMatcher进行密码比对
        Object credentials = md5(username,user.getPassword());

        //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();
        //4). 盐值. 使用盐值之后，即便是两个用户的密码一致他们加密后也不一样
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(principal, credentials,credentialsSalt, realmName);
        return info;
    }

    /**
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->ShiroRealm.doGetAuthorizationInfo()");
        //1.从principalCollection获取用户登录的信息
        //1.1这里需要注意principalCollection可能由于多一个realm，会存在多个,shiro使用LinkedHashMap维护了一个有顺序的Map
        String username= (String) principalCollection.getPrimaryPrincipal();
        //2.利用用户登录信息获取用户角色和权限信息
        User user = userService.queryRolesByUserName(username);
        if(user == null || user.getRoleList() == null)
            return null;
        //3.创建AuthorizationInfo，设置它的role和permission
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> roleList = user.getRoleList();
        Set<String> roleNameSet = new HashSet<>();
        for (Role role : roleList) {
            roleNameSet.add(role.getRoleName());
            info.setStringPermissions(role.getPermissionsName());
        }
        info.setRoles(roleNameSet);
        return info;
    }



    /**
     * 对密码进行md5加密，使用username做盐值
     * @param username
     * @param password
     * @return
     */
    private String md5(String username,String password) {
        String hashAlgorithmName = "MD5";
        Object credentials = password;
        Object salt = ByteSource.Util.bytes(username);
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return result.toString();
    }

}
