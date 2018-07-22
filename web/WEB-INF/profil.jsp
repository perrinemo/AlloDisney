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
    <jsp:include page="head.jsp"/>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="content col-10 col-lg-6 profil">
            <a href="#" id="edit"><i class="far fa-edit"></i></a>
            <h1>Mon profil</h1>
            <p class="deconnexion"><small><a href="${pageContext.request.contextPath}/deconnexion">Se déconnecter</a></small></p>

            <div class="profil-content" id="profil">
                <c:forEach items="${requestScope.users}" var="user">
                    <div class="avatar">
                        <c:choose>
                            <c:when test="${empty requestScope.avatar}">
                                <img src="img/avatar.png" width="150px" height="auto" />
                            </c:when>
                            <c:otherwise>
                                <img src="${requestScope.avatar}" width="150px" height="auto" />
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div class="info-user">
                        <p>Pseudo : <strong>${user.pseudo}</strong></p>
                        <p>Nombre de films ajoutés : <strong>${requestScope.nbMovie}</strong></p>
                    </div>
                </c:forEach>
            </div>
            <div class="profil-edit" id="edit-profil">
                <c:forEach items="${requestScope.users}" var="user">
                    <form>
                        <div class="avatar">
                            <c:choose>
                                <c:when test="${empty requestScope.avatar}">
                                    <img src="img/avatar.png" width="150px" height="auto" />
                                </c:when>
                                <c:otherwise>
                                    <img src="${requestScope.avatar}" width="150px" height="auto" />
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="form-edit">
                            <div class="info-ser form-group row">
                                <label class="col-4 col-form-label">Pseudo : </label>
                                <input type="text" value="${user.pseudo}" placeholder="Pseudo" />
                            </div>
                            <div class="info-ser form-group row">
                                <label class="col-4 col-form-label">Nouveau mot de passe : </label>
                                <input type="password" value="${user.password}" placeholder="Mot de passe" />
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
