var song_nb = 0;

document.querySelector("#add-a-song").addEventListener('click', function (e) {
    var first = document.querySelector('.new-song:first-child');
    var clone = first.parentNode.appendChild(first.cloneNode(true));
    song_nb++;

    var input = clone.children[0];
    input.value = '';
    input.setAttribute("name", "song[" + song_nb +"]");
});
