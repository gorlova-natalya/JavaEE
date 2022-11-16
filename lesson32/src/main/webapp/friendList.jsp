<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Friends</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"/>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<div class="container">
    <div class="row" style="margin-top: 25px">
        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    <h3>Friends</h3>
                </div>
                <div class="card-text">
                    <table class="table">
                        <tr>

                            <th>Login</th>
                            <th>Password</th>
                        </tr>
                        <tbody>
                        <c:forEach items="${friends}" var="user">
                            <tr>
                                <td>
                                    <c:out value="${user.login}"/>
                                </td>
                                <td>
                                    <c:out value="${user.password}"/>
                                </td>
                                <td  class="col-1">
                                    <form action="sendMessage" method="get">
                                        <input type="hidden" name="messageTo" value="${user.id}"/>
                                        <button type="submit" class="btn btn-primary" name="button">Dialog</button>
                                    </form>
                                </td>
                                <td  class="col-1">
                                    <form action="friends" method="post">
                                        <input type="hidden" name="delete" value="${user.id}"/>
                                        <input type="hidden" name="redirect" value="friends"/>
                                        <button type="submit" class="btn btn-primary" name="button">Delete</button>
                                    </form>
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