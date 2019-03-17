<!DOCTYPE html>
<html lang="en">
<head>
    <title>认证服务</title>
</head>
<body>
<form method="POST" action="/login?redirect=${RequestParameters.redirect!}">
    <h2>Log in</h2>
    <input name="username" type="text" placeholder="用户名称"
           autofocus="true"/>
    <input name="password" type="password" placeholder="密码"/>
    <div>( 用户名：hellokoding， 密码：hellokoding)</div>
    <div style="color: red">${error!}</div>
    <br/>
    <button type="submit">登录</button>
</form>
</body>
</html>