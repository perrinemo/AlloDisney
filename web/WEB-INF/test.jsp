<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 10/07/18
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${requestScope.models}" var="movie">
        <li>${movie.title} : ${movie.resume}</li>
    </c:forEach>
</body>
</html>
