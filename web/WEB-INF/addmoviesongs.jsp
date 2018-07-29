<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 29/07/18
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="/WEB-INF/head.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addamovie.css" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/header.jsp"/>

        <div class="content form col-lg-5">
            <c:forEach items="${requestScope.models}" var="movie">
                <h1>${movie.title}</h1>
                <form method="post" action="${pageContext.request.contextPath}/addmoviesongs">
                    <input type="hidden" name="id" value="${movie.id}" />
                    <div class="form-group form-inline">
                        <div class="input-group col-10 new-song">
                            <input type="text" class="form-control col-6 song" placeholder="Ajouter une chanson (optionnel)"
                                   name="song[0]"/>
                            <input type="text" class="form-control col-6 song" placeholder="Lien youtube (optionnel)"
                                   name="url[0]"/>
                        </div>
                        <div class="input-group-prepend col-2">
                            <div class="input-group-text">
                                <button type="button" class="btn" id="add-a-song">+</button>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn">Ajouter</button>
                </form>
            </c:forEach>
        </div>

        <jsp:include page="/WEB-INF/footer.jsp"/>
    </body>
</html>
