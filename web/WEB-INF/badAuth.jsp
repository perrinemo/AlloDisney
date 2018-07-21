<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 17/07/18
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="head.jsp"/>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="col-5 content">
            <div class="modal-body">
                <h2 id="title-popup">Se connecter à AlloDisney</h2>
                <form method="post" action="${pageContext.request.contextPath}/badauth">
                    <div class="form-group">
                        <input type="text" class="form-control" required name="pseudo_connexion" placeholder="Pseudo" />
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" required name="password_connexion" placeholder="Mot de passe" />
                    </div>
                    <button type="submit"id="btn-connection" class="btn">Connexion</button>
                </form>
                <p>Pseudo ou mot de passe inconnu</p>
            </div>
            <div class="modal-footer">
                <p>Pas encore inscrit ? <a href="${pageContext.request.contextPath}/inscription" id="inscription">S'inscrire maintenant</a></p>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
