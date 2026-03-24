<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Course List</h2>

<c:if test="${sessionScope.LOGIN_USER != null}">
    <a href="main?action=logout">Logout</a>
</c:if>

<div>
    <c:if test="${sessionScope.LOGIN_USER == null}">
        <a href="login.jsp">Login</a> |
        <a href="register.jsp">Register</a>
    </c:if>

    <c:if test="${sessionScope.LOGIN_USER != null}">
        Welcome, <b>${sessionScope.LOGIN_USER.fullname}</b> |
        <a href="main?action=logout">Logout</a>
    </c:if>
</div>

<hr/>

<br>

<!-- ✅ MESSAGE -->
<c:if test="${not empty MESSAGE}">
    <p style="color:red">${MESSAGE}</p>
</c:if>

<!-- ✅ EMPTY -->
<c:if test="${empty COURSE_LIST}">
    <p>No courses found</p>
</c:if>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Fee</th>
        <th>Schedule</th>
        <th>Start Date</th>
        <th>Action</th>
    </tr>

    <c:forEach var="c" items="${COURSE_LIST}">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.description}</td>

            <td>
                <fmt:formatNumber value="${c.fee}" type="number" maxFractionDigits="0"/>
            </td>

            <td>${c.schedule}</td>

            <td>
                <c:if test="${c.startDate != null}">
                    <fmt:formatDate value="${c.startDate}" pattern="dd-MM-yyyy"/>
                </c:if>
            </td>

            <td>
                <a href="main?action=courseDetail&id=${c.id}">View</a>
            </td>
        </tr>
    </c:forEach>

</table>