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
    <jsp:include page="/WEB-INF/head.jsp" />

    <body>
        <jsp:include page="/WEB-INF/header.jsp" />

        <div class="col-4 mx-auto inscription content">
            <h2>Je m'inscris :</h2>
            <form method="post" action="${pageContext.request.contextPath}/inscription">
                <div class="form-group">
                    <input type="text" class="form-control" name="pseudo_inscription" placeholder="Pseudo" />
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" name="email_inscription" placeholder="Adresse email" />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password_inscription" placeholder="Mot de passe" />
                </div>
                <p id="inscrit">
                    <a href="${pageContext.request.contextPath}/index">Déjà inscrit ?</a>
                </p>
                <button type="submit" class="btn">Inscription</button>
            </form>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>