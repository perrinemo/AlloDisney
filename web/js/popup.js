// POPUP CONNECTION
var modal = document.getElementById("myModal");
var connection = document.getElementById("connection");
var inscription = document.getElementById("inscription");
var popup = document.getElementById("title-popup");
var btn_connection = document.getElementById("btn-connection");

var span = document.getElementsByClassName("close")[0];
var footer = document.getElementsByClassName("modal-footer")[0];
var inscrit = document.getElementsByClassName("inscrit")[0];

connection.onclick = function () {
    modal.style.display = "block";
}

span.onclick = function () {
    modal.style.display = "none";
}

window.onclick = function (ev) {
    if (ev.target === modal) {
        modal.style.display = "none";
    }
}

inscription.onclick = function () {
    popup.innerHTML = "Inscription";
    btn_connection.innerHTML = "Valider";
    footer.style.display = "none";
    inscrit.style.display = "block";
}

inscrit.onclick = function () {
    popup.innerHTML = "Se connecter à AlloDisney";
    btn_connection.innerHTML = "Connexion";
    footer.style.display = "block";
    inscrit.style.display = "none";
}