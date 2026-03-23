<%-- 
    Document   : login
    Created on : Mar 23, 2026, 4:26:49 PM
    Author     : anvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h2>Login</h2>

        <form action="main" method="post">
            <input type="hidden" name="action" value="login"/>
            <p>Username: <input type="text" name="username" required /><p/>
            <p>Password: <input type="password" name="password" required /><p/>
            <input type="submit" value="Login"/>
        </form>

        <p style="color:red;">
            ${ERROR}
        </p>
    </body>
</html>
