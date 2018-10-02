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
    <head>
        <jsp:include page="/WEB-INF/head.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/listmovie.css" />
    </head>

    <body>
        <jsp:include page="/WEB-INF/header.jsp"/>

        <h1 class="titlelist">Liste des films
            <small>(${requestScope.nbMovies})</small>
        </h1>

        <ul class="content listmovie">
            <li><strong>2010 à aujourd'hui</strong></li>
            <c:forEach items="${requestScope.listMovie}" var="movie">
                <c:if test="${movie.year >= 2010}">
                    <li><a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}">${movie.title} (${movie.year})</a></li>
                </c:if>
            </c:forEach>
        </ul>
        <ul class="content listmovie">
            <li><strong>2000 à 2009</strong></li>
            <c:forEach items="${requestScope.listMovie}" var="movie">
                <c:if test="${movie.year >= 2000 && movie.year < 2010}">
                    <li><a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}">${movie.title} (${movie.year})</a></li>
                </c:if>
            </c:forEach>
        </ul>

        <ul class="content listmovie">
            <li><strong>1990 à 1999</strong></li>
            <c:forEach items="${requestScope.listMovie}" var="movie">
                <c:if test="${movie.year >= 1990 && movie.year < 2000}">
                    <li><a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}">${movie.title} (${movie.year})</a></li>
                </c:if>
            </c:forEach>
        </ul>

        <ul class="content listmovie">
            <li><strong>1980 à 1989</strong></li>
            <c:forEach items="${requestScope.listMovie}" var="movie">
                <c:if test="${movie.year >= 1980 && movie.year < 1990}">
                    <li><a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}">${movie.title} (${movie.year})</a></li>
                </c:if>
            </c:forEach>
        </ul>

        <ul class="content listmovie">
            <li><strong>1970 à 1979</strong></li>
            <c:forEach items="${requestScope.listMovie}" var="movie">
                <c:if test="${movie.year >= 1970 && movie.year < 1980}">
                    <li><a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}">${movie.title} (${movie.year})</a></li>
                </c:if>
            </c:forEach>
        </ul>

        <ul class="content listmovie">
            <li><strong>1960 à 1969</strong></li>
            <c:forEach items="${requestScope.listMovie}" var="movie">
                <c:if test="${movie.year >= 1960 && movie.year < 1970}">
                    <li><a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}">${movie.title} (${movie.year})</a></li>
                </c:if>
            </c:forEach>
        </ul>

        <ul class="content listmovie">
            <li><strong>1950 à 1959</strong></li>
            <c:forEach items="${requestScope.listMovie}" var="movie">
                <c:if test="${movie.year >= 1950 && movie.year < 1960}">
                    <li><a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}">${movie.title} (${movie.year})</a></li>
                </c:if>
            </c:forEach>
        </ul>

        <ul class="content listmovie">
            <li><strong>1940 à 1949</strong></li>
            <c:forEach items="${requestScope.listMovie}" var="movie">
                <c:if test="${movie.year >= 1940 && movie.year < 1950}">
                    <li><a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}">${movie.title} (${movie.year})</a></li>
                </c:if>
            </c:forEach>
        </ul>

        <ul class="content listmovie">
            <li><strong>1930 à 1939</strong></li>
            <c:forEach items="${requestScope.listMovie}" var="movie">
                <c:if test="${movie.year >= 1930 && movie.year < 1940}">
                    <li><a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}">${movie.title} (${movie.year})</a></li>
                </c:if>
            </c:forEach>
        </ul>

        <jsp:include page="/WEB-INF/footer.jsp"/>
    </body>
</html>
