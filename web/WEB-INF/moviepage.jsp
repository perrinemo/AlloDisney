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
        <jsp:include page="/WEB-INF/head.jsp" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/moviepage.css" />
    </head>

    <body>
        <jsp:include page="/WEB-INF/header.jsp" />
        <div class="movie-page col-10 col-lg-6 content">
            <c:forEach items="${requestScope.models}" var="movie">
                <h1>${movie.title}</h1>
                <c:if test="${!empty sessionScope.user}">
                    <p class="editMovie">
                        <small>
                            <a href="${pageContext.request.contextPath}/editamovie?id=${movie.id}">
                                Modifier les infos
                            </a>
                             /
                            <a href="${pageContext.request.contextPath}/editmovieimage?id=${movie.id}">
                                Changer l'affiche
                            </a>
                            <!--
                             /
                            <a href="${pageContext.request.contextPath}/addmoviesongs?id=${movie.id}">
                                Ajouter des chansons
                            </a>
                            -->
                        </small>
                    </p>
                </c:if>

                <div class="container movie-content row">
                    <div class="image col-md-6 col-12">
                        <img src="${pageContext.request.contextPath}/img/${movie.image}" alt="${movie.title}" />
                    </div>
                    <div class="infos col-md-6 col-12">
                        <p><span>Durée : </span>${movie.duration}</p>
                        <p><span>Année de sortie : </span>${movie.year}</p>
                        <p>
                            <span>Résumé :</span>
                            <br />
                            ${movie.resume}
                        </p>
                        <p style="margin-bottom: 0;"><span>Chansons :</span></p>
                        <ul>
                            <c:choose>
                                <c:when test="${!empty requestScope.no_song}">
                                    <li>${requestScope.no_song}</li>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${requestScope.songs}" var="song">
                                        <c:choose>
                                            <c:when test="${empty song.video}">
                                                <li>${song.title}</li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="${song.video}">${song.title}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </div>
                <c:if test="${!empty movie.trailer}">
                    <div class="trailer">
                        <iframe width="560" height="315" src="${movie.trailer}"
                                frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
                    </div>
                </c:if>
            </c:forEach>
        </div>

        <jsp:include page="/WEB-INF/footer.jsp" />
    </body>
</html>