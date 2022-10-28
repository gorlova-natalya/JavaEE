<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="list" value="${sessionScope[Keys.SUGGESTED_FRIEND_LIST]}" />
<c:forEach var="friend" items="${list}">
    <div class="friend-item">
        <span>
            <a href="#">${friend.lastName} ${friend.firstName}</a>
            <span style="display:block;">123 mutual friends </span>
            <button id="${friend.id}"><i class="fa fa-user-plus" aria-hidden="true"></i>
                Add friend
            </button>
        </span>
        <a href="#" class="ignore-friend"><i class="fa fa-times" aria-hidden="true"></i></a>
    </div>
</c:forEach>
</body>
</html>
