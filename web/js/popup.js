// POPUP CONNECTION
var modal = document.getElementById("myModal");
var connection = document.getElementById("connection");
var span = document.getElementsByClassName("close")[0];

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