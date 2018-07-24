<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 21/07/18
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <jsp:include page="head.jsp"/>
        <link rel="stylesheet" type="text/css" href="css/profil.css" />
    </head>

    <body>
        <jsp:include page="header.jsp"/>

        <div class="content col-10 col-lg-6 profil">
            <h1>Mon profil</h1>
            <p class="deconnexion"><small>
                <a href="#" id="edit">Modifier</a> /
                <a href="${pageContext.request.contextPath}/deconnexion">Se déconnecter</a>
            </small></p>

            <div class="profil-content mx-auto" id="profil">
                <c:forEach items="${requestScope.users}" var="user">
                    <div class="avatar">
                        <c:choose>
                            <c:when test="${empty user.avatar}">
                                <img src="img/avatar.png" width="150px" height="auto" />
                            </c:when>
                            <c:otherwise>
                                <img src="img/${user.avatar}" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="info-user">
                        <p>Pseudo : <strong>${user.pseudo}</strong></p>
                        <p>Nombre de films ajoutés : <strong>${requestScope.nbMovie}</strong></p>
                        <c:forEach items="${requestScope.lastmovie}" var="lastmovie">
                            <p>Dernier film ajouté : <strong>${lastmovie.title} (${lastmovie.year})</strong></p>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>

            <div class="profil-edit" id="edit-profil">
                <c:forEach items="${requestScope.users}" var="user">
                    <form method="post" action="${pageContext.request.contextPath}/profil" enctype="multipart/form-data">
                        <div class="avatar">
                            <c:choose>
                                <c:when test="${empty user.avatar}">
                                    <img src="img/avatar.png" width="150px" height="auto" />
                                </c:when>
                                <c:otherwise>
                                    <img src="img/${user.avatar}" />
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="form-edit">
                            <input type="hidden" name="id-edit" value="${user.id}" />
                            <div class="info-ser form-group row">
                                <label class="col-4 col-form-label">Pseudo : </label>
                                <input type="text" name="pseudo-edit" value="${user.pseudo}" placeholder="Pseudo" />
                            </div>
                            <div class="info-ser form-group row">
                                <label class="col-4 col-form-label">Nouveau mot de passe : </label>
                                <input type="password" name="password-edit" value="${user.password}" placeholder="Mot de passe" />
                            </div>
                            <div class="info-ser form-group row">
                                <label class="col-4 col-form-label">Nouvel avatar : </label>
                                <input type="file" name="avatar-edit" class="input-file" value="${user.avatar}" />
                            </div>
                        </div>
                        <button type="button" class="btn" id="cancel">Annuler</button>
                        <button type="submit" class="btn">Modifier</button>
                    </form>
                </c:forEach>
            </div>
        </div>



        <jsp:include page="footer.jsp" />
    </body>
</html>
