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
    <jsp:include page="/WEB-INF/head.jsp" />

    <body>
        <jsp:include page="/WEB-INF/header.jsp" />
        <div class="movie-page col-6 content">
            <c:forEach items="${requestScope.models}" var="movie">
                <!--<i class="far fa-edit"></i>-->
                <h1>${movie.title}</h1>
                <div class="container">
                    <img src="http://via.placeholder.com/300x400" alt="${movie.title}" />
                    <div>
                        <p><span>Durée : </span>${movie.duration}</p>
                        <p><span>Année de sortie : </span>${movie.year}</p>
                        <p>
                            <span>Résumé :</span>
                            <br />
                            ${movie.resume}
                        </p>
                        <p style="margin-bottom: 0;"><span>Chansons :</span></p>
                        <ul>
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
                        </ul>
                    </div>
                </div>

            </c:forEach>
        </div>

        <jsp:include page="/WEB-INF/footer.jsp" />
    </body>
</html>