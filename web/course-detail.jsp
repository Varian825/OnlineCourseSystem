<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Course Detail</title>
    </head>
    <body>

        <h2>Course Detail</h2>

        <c:if test="${COURSE == null}">
            <p style="color:red;">Course not found</p>
        </c:if>

        <c:if test="${COURSE != null}">

            <p><b>ID:</b> ${COURSE.id}</p>

            <p><b>Name:</b> ${COURSE.name}</p>

            <p><b>Description:</b> ${COURSE.description}</p>

            <p>
                <b>Fee:</b>
                <fmt:formatNumber value="${COURSE.fee}" type="number" maxFractionDigits="0"/>
            </p>

            <p><b>Schedule:</b> ${COURSE.schedule}</p>

            <p>
                <b>Start Date:</b>
                <c:if test="${COURSE.startDate != null}">
                    <fmt:formatDate value="${COURSE.startDate}" pattern="dd-MM-yyyy"/>
                </c:if>
            </p>

        </c:if>

        <br>
        
        <a href="main?action=viewCourses">⬅ Back to Course List</a>

        <br><br>

        <c:if test="${sessionScope.LOGIN_USER != null}">
            <p>
                Welcome, <b>${sessionScope.LOGIN_USER.fullname}</b> |
                <a href="main?action=logout">Logout</a>
            </p>
        </c:if>

        <c:if test="${sessionScope.LOGIN_USER == null}">
            <a href="login.jsp">Login</a>
        </c:if>

    </body>
</html>