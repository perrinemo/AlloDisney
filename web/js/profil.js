var edit = document.getElementById("edit-profil");
var profil = document.getElementById("profil");
var btnEdit = document.getElementById("edit");
var cancel = document.getElementById("cancel");

btnEdit.onclick = function () {
    edit.style.display = "block";
    profil.style.display = "none";
}

cancel.onclick = function () {
    edit.style.display = "none";
    profil.style.display = "flex";
}