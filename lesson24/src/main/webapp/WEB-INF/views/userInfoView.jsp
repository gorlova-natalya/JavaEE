<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="users.jsp"></jsp:include>

<h3>Hello: ${loginedUser.name}</h3>

User Name: <b>${loginedUser.name}</b>

</body>
</html>
