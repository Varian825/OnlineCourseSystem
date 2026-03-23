<%-- 
    Document   : course-list
    Created on : Mar 23, 2026, 4:27:14 PM
    Author     : anvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Course List</title>
    </head>
    <body>

        <h2>Course List</h2>

        <a href="main?action=logout">Logout</a>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Fee</th>
                <th>Schedule</th>
                <th>Start Date</th>
            </tr>

            <c:forEach var="c" items="${COURSE_LIST}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td>${c.description}</td>
                    <td>${c.fee}</td>
                    <td>${c.schedule}</td>
                    <td>${c.startDate}</td>
                </tr>
            </c:forEach>

        </table>

    </body>
</html>
