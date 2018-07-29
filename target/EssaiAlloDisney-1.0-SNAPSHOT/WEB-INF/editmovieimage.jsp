<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 29/07/18
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <jsp:include page="/WEB-INF/head.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addamovie.css" />
    </head>

    <body>
        <jsp:include page="/WEB-INF/header.jsp" />

        <div class="content form">
            <c:forEach items="${requestScope.models}" var="movie">
                <h1>${movie.title}</h1>
                <form method="post" action="${pageContext.request.contextPath}/editmovieimage" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${movie.id}" />
                    <div class="form-control form-inline">
                        <input type="file" name="file" class="input-file form-control" />
                        <button type="submit" class="btn">Envoyer</button>
                    </div>
                </form>
                <c:if test="${!empty movie.image}">
                   <img src="${pageContext.request.contextPath}/img/${movie.image}" alt="${movie.title}" />
                </c:if>
            </c:forEach>
        </div>

        <jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>
