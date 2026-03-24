<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin - Users</title>
    </head>
    <body>

        <h2>Admin - User Management</h2>

        <!-- ================= MESSAGE ================= -->
        <c:if test="${not empty MESSAGE}">
            <p style="color:green">${MESSAGE}</p>
        </c:if>

        <c:if test="${not empty ERROR}">
            <p style="color:red">${ERROR}</p>
        </c:if>

        <!-- ================= ADD USER ================= -->
        <h3>Add New User</h3>

        <form action="main" method="post">
            <input type="hidden" name="action" value="adminUsers"/>
            <input type="hidden" name="subAction" value="add"/>

            Username:
            <input type="text" name="username" required/>

            Password:
            <input type="password" name="password" required/>

            Fullname:
            <input type="text" name="fullname" required/>

            Role:
            <select name="role">
                <option value="student">Student</option>
                <option value="instructor">Instructor</option>
            </select>

            <button type="submit">Add</button>
        </form>

        <br/>

        <!-- ================= EDIT USER ================= -->
        <c:if test="${EDIT_USER != null}">
            <h3>Edit User</h3>

            <form action="main" method="post">
                <input type="hidden" name="action" value="adminUsers"/>
                <input type="hidden" name="subAction" value="update"/>
                <input type="hidden" name="id" value="${EDIT_USER.id}"/>

                <p><b>ID:</b> ${EDIT_USER.id}</p>
                <p><b>Username:</b> ${EDIT_USER.username}</p>

                Fullname:
                <input type="text" name="fullname" value="${EDIT_USER.fullname}" required/>

                Role:
                <select name="role">
                    <option value="student"
                            <c:if test="${EDIT_USER.role == 'student'}">selected</c:if>>
                                Student
                            </option>

                            <option value="instructor"
                            <c:if test="${EDIT_USER.role == 'instructor'}">selected</c:if>>
                                Instructor
                            </option>
                    </select>

                    <button type="submit">Update</button>
                </form>

                <br/>
        </c:if>

        <!-- ================= EMPTY LIST ================= -->
        <c:if test="${empty USER_LIST}">
            <p>No users found</p>
        </c:if>

        <!-- ================= USER LIST ================= -->
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Fullname</th>
                <th>Role</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="u" items="${USER_LIST}">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.username}</td>
                    <td>${u.fullname}</td>
                    <td>${u.role}</td>

                    <!-- ✅ STATUS -->
                    <td>
                        <c:choose>
                            <c:when test="${u.status}">
                                Active
                            </c:when>
                            <c:otherwise>
                                Banned
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <!-- EDIT -->
                        <a href="main?action=adminUsers&subAction=edit&id=${u.id}">
                            Edit
                        </a>

                        |

                        <!-- DELETE (confirm) -->
                        <a href="main?action=adminUsers&subAction=delete&id=${u.id}"
                           onclick="return confirm('Are you sure to delete this user?');">
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