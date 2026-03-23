<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Course List</h2>

<c:if test="${sessionScope.LOGIN_USER != null}">
    <a href="main?action=logout">Logout</a>
</c:if>

<c:if test="${sessionScope.LOGIN_USER == null}">
    <a href="login.jsp">Login</a>
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
            <td>${c.fee}</td>
            <td>${c.schedule}</td>
            <td>
                <fmt:formatDate value="${c.startDate}" pattern="dd-MM-yyyy"/>
            </td>
            <td>
                <a href="main?action=courseDetail&id=${c.id}">View</a>
            </td>
        </tr>
    </c:forEach>

</table>