$(document).ready(function(){
    var url = 'https://newsapi.org/v2/top-headlines?' +
        'country=us&' +
        'apiKey=3e02751187fc47139db53097a47ca54f';
    var req = new Request(url);
    fetch(req).then(function(response) {
        console.log(response.json());

    })
});