<%-- 
    Document   : index
    Created on : Mar 24, 2026, 6:09:03 AM
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
        <%
            response.sendRedirect("main?action=viewCourses");
        %>
    </body>
</html>
