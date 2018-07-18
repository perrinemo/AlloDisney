<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 16/07/18
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="/WEB-INF/head.jsp"/>
    <body>
        <jsp:include page="/WEB-INF/header.jsp"/>

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

        <div class="col-5 content form">
            <h1>Ajouter un nouveau film</h1>
            <form method="post" action="${pageContext.request.contextPath}/addamovie">
                <div class="form-group">
                    <input type="text" class="form-control" required placeholder="ex : La Belle et la Bête" name="title" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" required placeholder="ex : 1h27" name="duration" />
                </div>
                <div class="form-group">
                    <input type="number" min="1950" class="form-control" required placeholder="ex : 1991" name="year" />
                </div>
                <div class="form-group">
                    <textarea type="text" class="form-control" required placeholder="Résumé" name="resume" id="resume" rows="4"></textarea>
                    <p class="nb-char">Il vous reste <span id="reste_char"></span> caractères.</p>
                </div>
                <button type="submit" class="btn">Ajouter</button>
            </form>
        </div>

        <script type="text/javascript">
        <!--
            maxlength_textarea('resume','reste_char',250);
        -->
        </script>
        <jsp:include page="/WEB-INF/footer.jsp"/>
    </body>
</html>
