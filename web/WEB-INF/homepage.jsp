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
<body>
    <jsp:include page="/WEB-INF/header.jsp" />

    <div id="slider">
        <a href="1"><img src="./img/disney1.jpg" alt="disney" /></a>
        <a href="2"><img src="./img/disney2.jpg" alt="disney" /></a>
        <a href="3"><img src="./img/disney3.jpg" alt="disney" /></a>
    </div>

    <div class="container">
        <div class="row">
            <c:forEach items="${requestScope.models}" var="movie">
                <div class="vignette">
                    <img src="http://via.placeholder.com/200x280" alt="${movie.title}" />
                    <div>
                        <h3>${movie.title}</h3>
                        <p>
                            <span class="bold">Résumé :</span>
                            <br />
                            ${movie.resume}
                        </p>

                        <a href="${pageContext.request.contextPath}/test?id=${movie.id}" class="more">Plus d'infos</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>
