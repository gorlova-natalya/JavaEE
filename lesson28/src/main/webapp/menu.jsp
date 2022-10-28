<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" />
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Menu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="/servlet/users">Users<span class="sr-only">(current)</span></a>
            <a class="nav-item nav-link" href="#">Friends</a>
            <a class="nav-item nav-link" href="#">Friends Requests</a>
            <a class="nav-item nav-link" href="#">Outgoing Friends Requests</a>
            <a class="nav-item nav-link disabled" href="/servlet/login">Disabled</a>
        </div>
    </div>
</nav>
</body>
