<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="main.css" />
    <title>Title</title>
</head>
<body>

<a href="/servlet/users">
    <%="Get list of users".toUpperCase()%>
</a>

<h3>Login Page</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/login">
    <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table border="0">
        <tr>
            <td>User Name</td>
            <td><input type="text" name="name" value= "${user.name}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value= "${user.password}" /> </td>
        </tr>

        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<a href="servlet/userInfo">
        <%="User Info".toUpperCase()%>


</body>

</html>



