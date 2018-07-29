<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 13/07/18
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
            <img src="img/disney2.png" width="auto" height="50" class="d-inline-block" alt="logo" />
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav ml-auto">
                <c:if test="${!empty sessionScope.user}">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/addamovie" class="nav-link">Ajouter un film</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/listmovie" class="nav-link">Liste des films</a>
                </li>
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <li class="nav-item">
                            <a class="nav-link" href="#" id="connection">Se connecter / S'inscrire</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/profil?id=${sessionScope.user_id}">
                                <c:forEach items="${requestScope.userid}" var="avatar">
                                    <c:choose>
                                        <c:when test="${empty avatar.avatar}">
                                            <img src="/img/avatar.png">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="img/${avatar.avatar}" />
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </div>
</nav>

<div id="myModal" class="modal">
    <div class="modal-content col-10 col-lg-5">
        <span class="close">&times;</span>
        <div class="modal-body">
            <h1 id="title-popup">Se connecter à AlloDisney</h1>
            <form method="post" action="${pageContext.request.contextPath}/index">
                <div class="form-group">
                    <input type="text" class="form-control"required  name="pseudo_connexion" placeholder="Pseudo" />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control"required  name="password_connexion" placeholder="Mot de passe" />
                </div>
                <button type="submit" id="btn-connection" class="btn mx-auto">Connexion</button>
            </form>
        </div>
        <div class="modal-footer">
            <p>Pas encore inscrit ? <a href="${pageContext.request.contextPath}/inscription" id="inscription">S'inscrire maintenant</a></p>
        </div>
    </div>
</div>