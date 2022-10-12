<%@ page import="com.teachmeskills.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" />
</head>
<body>

<%--<form method="POST" action="${pageContext.request.contextPath}/users">--%>
<%--        <input type="hidden" name="redirectId" value="${param.redirectId}" />--%>
<%--    <table border="0">--%>
<%--        <tr>--%>
<%--            <td>Login</td>--%>
<%--            <td><input type="text" name="login" value="${user.login}"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Password</td>--%>
<%--            <td><input type="password" name="password" value="${user.password}"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td colspan="2">--%>
<%--                <input type="submit" value="Submit"/>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form>--%>

<div class="container">
    <div class="row" style="margin-top: 25px">
        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    <h3>Login Page</h3>
                </div>
                <div class="card-text">
                    <form action="users" method="POST">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label" for="login">Login</label>
                            <div class="col-sm-7">
                                <input class="col-form-label form-control" id="login" name="login" placeholder="Login">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label" for="password">Password</label>
                            <div class="col-sm-7">
                                <input class="col-form-label form-control" type="password"
                                       id="password" name="password"
                                       placeholder="password">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<c:if test="${param.error != null }">--%>
<%--    <div class="row" style="margin-top: 25px">--%>
<%--        <div class="alert alert-danger" role="alert">--%>
<%--                ${param.error}--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</c:if>--%>

<div><a href="main.jsp">
    <%="User Info".toUpperCase()%>
</div>

</body>
</html>



