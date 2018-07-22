<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 16/07/18
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <jsp:include page="head.jsp" />
    <body>
        <jsp:include page="header.jsp"/>

        <h1 class="titlelist">Liste des films
            <small>(${requestScope.nbMovies})</small>
        </h1>

        <ul class="content listmovie">
        <c:forEach items="${requestScope.listMovie}" var="movie">
            <li><a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}">${movie.title} (${movie.year})</a></li>
        </c:forEach>
        </ul>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
