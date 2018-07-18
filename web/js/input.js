document.querySelector("#add-a-song").addEventListener('click', function (e) {
    var first = document.querySelector('.new-song:first-child');
    var clone = first.parentNode.appendChild(first.cloneNode(true));
    var input = clone.children[0];
    input.value = '';
});
