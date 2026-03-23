<%-- 
    Document   : register
    Created on : Mar 23, 2026, 4:27:00 PM
    Author     : anvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Register</h2>

        <form action="main" method="post">
            <input type="hidden" name="action" value="register"/>

            Username: <input type="text" name="username"/><br/>
            Password: <input type="password" name="password"/><br/>
            Full Name: <input type="text" name="fullname"/><br/>

            <button type="submit">Register</button>
        </form>

        <p style="color:red">${ERROR}</p>
        <p style="color:green">${SUCCESS}</p>

        <a href="login.jsp">Back to Login</a>
    </body>
</html>
