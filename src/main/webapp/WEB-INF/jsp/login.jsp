<%--
  Created by IntelliJ IDEA.
  User: monday
  Date: 2017/11/15
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登陆</title>
</head>
<body>
    <form action="/login" method="post">
        <p>账号：<input type="text" name="username" /></p>
        <p>密码：<input type="text" name="password" /></p>
        <p><input type="checkbox" name="rememberMe" />记住我</p>
        <p><input type="submit" value="登录"/></p>
    </form>
</body>
</html>
