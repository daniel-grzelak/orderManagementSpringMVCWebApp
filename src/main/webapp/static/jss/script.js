const tabs = ['Customer', 'Shop', 'Producer'];
const tabDefaultClass = 'tablinks';

let tabElements = tabs.map(t => document.getElementById(t + "Tab"));
let blockElements = tabs.map(t => document.getElementById(t));

tabElements.forEach(t => t.className.replace(" active", ""));
blockElements.forEach(t => t.style.display = 'none');

tabElements.forEach(t => {
    t.addEventListener('click', function () {
        tabElements.forEach(t => t.className = tabDefaultClass);
        blockElements.forEach(t => t.style.display = 'none');

        t.className += " active";
        blockElements[tabElements.indexOf(document.getElementById(t.id))].style.display = 'block';
    })
}, false);
