<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 16/07/18
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="head.jsp"/>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="col-5">
            <form method="post" action="${pageContext.request.contextPath}/addamovie">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Titre du film" name="title" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Durée du film" name="duration" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Année de sortie" name="year" />
                </div>
                <div class="form-group">
                    <textarea type="text" class="form-control" placeholder="Résumé" name="resume"></textarea>
                </div>
                <button type="submit" class="btn">Ajouter</button>
            </form>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
