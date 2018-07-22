<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 10/07/18
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <jsp:include page="/WEB-INF/head.jsp" />
    <link rel="stylesheet" type="text/css" href="css/slider.css" />
    <link rel="stylesheet" type="text/css" href="css/homepage.css" />

    <body>
    <jsp:include page="/WEB-INF/header.jsp" />
    <div class="content">
        <div id="slider">
            <a href="#"><img src="./img/disney1.jpg" alt="disney" /></a>
            <a href="#"><img src="./img/disney4.jpg" alt="disney" /></a>
            <a href="#"><img src="./img/disney3.jpg" alt="disney" /></a>
            <a href="#"><img src="./img/disney5.jpg" alt="disney" /></a>
            <a href="#"><img src="./img/disney2.jpg" alt="disney" /></a>
        </div>

        <div class="container">
            <div class="row">
                <c:forEach items="${requestScope.models}" var="movie">
                    <div class="vignette">
                        <img src="${pageContext.request.contextPath}/img/${movie.image}" alt="${movie.title}" />
                        <div>
                            <h3>${movie.title}</h3>
                            <p>
                                <span class="bold">Résumé :</span>
                                <br />
                                ${movie.resume}
                            </p>

                            <a href="${pageContext.request.contextPath}/moviepage?id=${movie.id}" class="more">Plus d'infos</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>
