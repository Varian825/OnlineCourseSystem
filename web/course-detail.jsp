<%-- 
    Document   : course-detail
    Created on : Mar 23, 2026, 4:27:25 PM
    Author     : anvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Course Detail</h2>

    <c:if test="${COURSE != null}">

        <p><b>ID:</b> ${COURSE.id}</p>
        <p><b>Name:</b> ${COURSE.name}</p>
        <p><b>Description:</b> ${COURSE.description}</p>
        <p><b>Fee:</b> ${COURSE.fee}</p>
        <p><b>Schedule:</b> ${COURSE.schedule}</p>
        <p>
            <b>Start Date:</b>
        <fmt:formatDate value="${COURSE.startDate}" pattern="dd-MM-yyyy"/>
    </p>

</c:if>

<br>

<a href="main?action=viewCourses">Back to Course List</a>
</body>
</html>
