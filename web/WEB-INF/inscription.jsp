<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 16/07/18
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <jsp:include page="/WEB-INF/head.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inscription.css" />
    </head>

    <body>
        <jsp:include page="/WEB-INF/header.jsp" />

        <div class="col-10 col-lg-4 mx-auto inscription content">
            <h1>Je m'inscris :</h1>
            <form method="post" action="${pageContext.request.contextPath}/inscription">
                <div class="form-group">
                    <input type="text" class="form-control" required name="pseudo_inscription" placeholder="Pseudo" />
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" required name="email_inscription" placeholder="Adresse email" />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" required name="password_inscription" placeholder="Mot de passe" />
                </div>
                <p id="inscrit">
                    <a href="${pageContext.request.contextPath}/index">Déjà inscrit ?</a>
                </p>
                <button type="submit" class="btn">Inscription</button>
                <c:if test="${!empty requestScope.error}">
                    <p class="msg-error">${requestScope.error}</p>
                </c:if>
            </form>
        </div>

        <jsp:include page="/WEB-INF/footer.jsp"/>
    </body>
</html>