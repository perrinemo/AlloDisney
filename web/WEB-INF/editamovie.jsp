<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 21/07/18
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <jsp:include page="/WEB-INF/head.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addamovie.css" />
    </head>

    <body>
        <jsp:include page="/WEB-INF/header.jsp" />

        <script type="text/javascript">
            <!--
            function maxlength_textarea(id, crid, max)
            {
                var txtarea = document.getElementById(id);
                document.getElementById(crid).innerHTML = max - txtarea.value.length;
                txtarea.onkeypress=function(){eval('v_maxlength("'+id+'","'+crid+'",'+max+');')};
                txtarea.onblur=function(){eval('v_maxlength("'+id+'","'+crid+'",'+max+');')};
                txtarea.onkeyup=function(){eval('v_maxlength("'+id+'","'+crid+'",'+max+');')};
                txtarea.onkeydown=function(){eval('v_maxlength("'+id+'","'+crid+'",'+max+');')};
            }
            function v_maxlength(id, crid, max)
            {
                var txtarea = document.getElementById(id);
                var crreste = document.getElementById(crid);
                var len = txtarea.value.length;
                if(len>max)
                {
                    txtarea.value=txtarea.value.substr(0,max);
                }
                len = txtarea.value.length;
                crreste.innerHTML = max - len;
            }
            -->
        </script>

        <div class="col-sm-10 col-lg-5 content form">

            <c:forEach items="${requestScope.models}" var="movie">
                <h1>${movie.title}</h1>
                <form method="post" action="${pageContext.request.contextPath}/editamovie">
                    <input type="hidden" name="id" value="${movie.id}" />
                    <div class="form-group">
                        <input type="text" class="form-control" required placeholder="ex : 1h27" value="${movie.duration}" name="duration" />
                    </div>
                    <div class="form-group">
                        <input type="number" min="1938" class="form-control" required placeholder="ex : 1991" value="${movie.year}" name="year" />
                    </div>
                    <div class="form-group">
                        <textarea type="text" class="form-control" required placeholder="Résumé" name="resume" id="resume" rows="4">${movie.resume}</textarea>
                        <p class="nb-char">Il vous reste <span id="reste_char"></span> caractères.</p>
                    </div>
                    <div class="form-group">
                        <input type="text" name="trailer" placeholder="Bande annonce (lien youtube optionnel)" value="${movie.trailer}" class="form-control" />
                    </div>
                    <c:if test="${empty movie.image}">
                        <div class="form-control">
                            <input type="file" name="file" required class="input-file form-control" />
                        </div>
                    </c:if>
                    <button type="submit" class="btn">Modifier</button>
                </form>
            </c:forEach>
        </div>

        <script type="text/javascript">
            <!--
            maxlength_textarea('resume','reste_char',250);
            -->
        </script>

        <jsp:include page="/WEB-INF/footer.jsp"/>
    </body>
</html>
