<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 13/07/18
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="img/disney.png" width="60" height="50" class="d-inline-block" alt="logo" />
        AlloDisney
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbar">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a href="#" class="nav-link">Liste des films</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" id="connection">Se connecter / S'inscrire</a>
            </li>
        </ul>
    </div>
</nav>

<div id="myModal" class="modal">
    <div class="modal-content col-5">
        <span class="close">&times;</span>
        <h2>Vous êtes connecté !</h2>
    </div>
</div>