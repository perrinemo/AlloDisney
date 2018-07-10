<%--
  Created by IntelliJ IDEA.
  User: perrine
  Date: 10/07/18
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>AlloDisney</title>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="home.css" />
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">
            <img src="img/Mickey_Mouse_head_and_ears.png" width="50" height="40" class="d-inline-block" alt="logo" />
            AlloDisney
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Se connecter</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">S'inscrire</a>
                </li>
            </ul>
        </div>
    </nav>

    <div id="slider">
        <a href="1"><img src="./img/disney1.jpg" alt="disney" /></a>
        <a href="2"><img src="./img/disney2.jpg" alt="disney" /></a>
        <a href="3"><img src="./img/disney3.jpg" alt="disney" /></a>
    </div>

    <div class="container">
        <div class="row">
            <c:forEach items="${requestScope.models}" var="movie">
                <div class="vignette">
                    <img src="http://via.placeholder.com/200x280" alt="${movie.title}" />
                    <div>
                        <h3>${movie.title}</h3>
                        <p><span class="bold">Année : </span>${movie.year}
                            <br />
                            <span class="bold">Résumé :</span>
                            <br />
                            ${movie.resume}
                            <a href="${pageContext.request.contextPath}/test?id=${movie.id}" class="more">Plus d'infos</a>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <footer class="footer">
        <div class="container">
            <i class="far fa-copyright"></i>
            AlloDisney 2018
            <span class="reseaux">
                            <a href="https://fr-fr.facebook.com/disneyfrance/"><i class="fab fa-facebook-square fa-2x"></i></a>
                            <a href="https://www.instagram.com/disney/?hl=fr"><i class="fab fa-instagram fa-2x"></i></a>
                            <a href="https://twitter.com/disneyfr"><i class="fab fa-twitter-square fa-2x"></i></a>
                        </span>
        </div>
    </footer>

    <script type="text/javascript" src="js/slider.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
