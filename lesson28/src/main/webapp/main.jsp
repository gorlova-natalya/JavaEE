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
                <div class="card-text">
                    <table class="table">
                        <tr>
                            <th>Login</th>
                            <th>Password</th>
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
                                    <div class="btn-group-toggle" data-toggle="buttons">
                                        <label class="btn btn-secondary active">
                                            <input type="checkbox" checked autocomplete="off"> Add friend
                                        </label>
                                    </div>
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
<c:if test="${queryError != null }">
    <div class="row" style="margin-top: 25px">
        <div class="alert alert-danger" role="alert">
                ${queryError}
        </div>
    </div>
</c:if>
</body>
</html>
