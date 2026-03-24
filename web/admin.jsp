<%-- 
    Document   : admin
    Created on : Mar 24, 2026, 4:26:14 AM
    Author     : anvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>
    </head>
    <body>

        <c:if test="${sessionScope.LOGIN_USER == null 
                      || sessionScope.LOGIN_USER.role ne 'admin'}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h2>Admin Dashboard</h2>

        <a href="main?action=adminUsers">Manage Users</a><br/>
        <a href="main?action=adminCourses">Manage Courses</a><br/>

        <br/>

        <a href="main?action=logout">Logout</a>

    </body>
</html>
