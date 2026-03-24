<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin - Courses</title>
    </head>
    <body>

        <h2>Admin - Course Management</h2>

        <!-- ================= MESSAGE ================= -->
        <c:if test="${not empty MESSAGE}">
            <p style="color:green">${MESSAGE}</p>
        </c:if>

        <c:if test="${not empty ERROR}">
            <p style="color:red">${ERROR}</p>
        </c:if>

        <!-- ================= ADD COURSE ================= -->
        <h3>Add New Course</h3>

        <form action="main" method="post">
            <input type="hidden" name="action" value="adminCourses"/>
            <input type="hidden" name="subAction" value="add"/>

            Name:
            <input type="text" name="name" required/>

            Description:
            <input type="text" name="description" required/>

            Fee:
            <input type="number" name="fee" required/>

            Schedule:
            <input type="text" name="schedule" required/>

            Start Date:
            <input type="date" name="startDate" required/>

            <button type="submit">Add</button>
        </form>

        <br/>

        <!-- ================= EDIT COURSE ================= -->
        <c:if test="${EDIT_COURSE != null}">
            <h3>Edit Course</h3>

            <form action="main" method="post">
                <input type="hidden" name="action" value="adminCourses"/>
                <input type="hidden" name="subAction" value="update"/>
                <input type="hidden" name="id" value="${EDIT_COURSE.id}"/>

                <p><b>ID:</b> ${EDIT_COURSE.id}</p>

                Name:
                <input type="text" name="name" value="${EDIT_COURSE.name}" required/>

                Description:
                <input type="text" name="description" value="${EDIT_COURSE.description}" required/>

                Fee:
                <input type="number" name="fee" value="${EDIT_COURSE.fee}" required/>

                Schedule:
                <input type="text" name="schedule" value="${EDIT_COURSE.schedule}" required/>

                <button type="submit">Update</button>
            </form>

            <br/>
        </c:if>

        <!-- ================= EMPTY ================= -->
        <c:if test="${empty COURSE_LIST}">
            <p>No courses found</p>
        </c:if>

        <!-- ================= COURSE LIST ================= -->
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
                <fmt:formatNumber value="${c.fee}" maxFractionDigits="0"/>
            </td>

            <td>${c.schedule}</td>

            <td>
                <c:if test="${c.startDate != null}">
                <fmt:formatDate value="${c.startDate}" pattern="dd-MM-yyyy"/>
            </c:if>
        </td>

        <td>
            <!-- EDIT -->
            <a href="main?action=adminCourses&subAction=edit&id=${c.id}">
                Edit
            </a>

            |

            <!-- DELETE -->
            <a href="main?action=adminCourses&subAction=delete&id=${c.id}"
               onclick="return confirm('Are you sure to delete this course?');">
                Delete
            </a>
        </td>
    </tr>
</c:forEach>
</table>

<br/>

<!-- ================= LOGOUT ================= -->
<a href="main?action=logout">Logout</a>

<br/><br/>

<!-- ================= BACK ================= -->
<a href="admin.jsp">Back to Dashboard</a>

</body>
</html>