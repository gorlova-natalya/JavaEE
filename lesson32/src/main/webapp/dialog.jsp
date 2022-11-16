<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dialog</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"/>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<div class="container">
    <c:set var="loggedUserId" value='<%= session.getAttribute("loggedInUserId") %>'/>
    <div class="row" style="margin-top: 25px">
        <div class="card">
            <div class="card-body">
                <div class="card-text">
                    <table class="table">
                        <tr>
                            <th>Message to <%= request.getAttribute("userName")%>
                            </th>
                            <th></th>
                        </tr>
                        <tbody>
                        <tr>
                            <td>
                                <form action="sendMessage" method="POST">
                                    <div class="input-group">
                                        <textarea class="form-control"
                                                  aria-label="With textarea"
                                                  name="message"
                                                  placeholder="Write message"></textarea>
                                    </div>
                                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                        <input type="hidden" name="messageTo" value="${messageTo}"/>
                                        <button type="submit" class="btn btn-primary mb-2 pull-right">Send</button>
                                    </div>
                                </form>
                            </td>
                            <td>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="card-text">
                    <table class='table table-borderless'>
                        <tr>
                            <th>Date</th>
                            <th>Message</th>
                            <th>Date</th>
                        </tr>
                        <tbody>
                        <c:forEach items="${messages}" var="message">
                            <c:choose>
                                <c:when test="${loggedUserId == message.messageFrom}">
                                    <tr>
                                        <td align="left" class="col">
                                            <fmt:parseDate value="${message.createdAt}" pattern="yyyy-MM-dd'T'HH:mm"
                                                           var="parsedDateTime" type="both"/>
                                            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
                                        </td>
                                        <td align="left" class="col-6">
                                            <c:out value="${message.messageText}"/>
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>

                                        </td>
                                        <td align="right" class="col-6">
                                            <c:out value="${message.messageText}"/>
                                        </td>
                                        <td align="right" class="col">
                                            <fmt:parseDate value="${message.createdAt}" pattern="yyyy-MM-dd'T'HH:mm"
                                                           var="parsedDateTime" type="both"/>
                                            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
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
