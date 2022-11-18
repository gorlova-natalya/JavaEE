<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users Info</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"/>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<div class="container">
    <div class="row" style="margin-top: 25px">
        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    <h3>Users</h3>
                </div>
                <c:set var="loggedUserId" value='<%= session.getAttribute("loggedInUserId") %>'/>
                <div class="card-text">
                    <table class="table">
                        <tr>
                            <th>Login</th>
                            <th>Password</th>
                            <th>Add friend</th>
                        </tr>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>
                                    <c:out value="${user.login}"/>
                                </td>
                                <td>
                                    <c:out value="${user.password}"/>
                                </td>
                                <td>
                                    <c:if test="${loggedUserId != user.id}">
                                        <form action="addFriend" method="post">
                                            <input type="hidden" name="sendFr" value="${user.id}"/>
                                            <button type="submit" class="btn btn-primary" name="button">Add friend
                                            </button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
