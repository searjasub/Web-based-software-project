$(document).ready(function(){
    var url = 'https://newsapi.org/v2/top-headlines?' +
        'country=us&' +
        'apiKey=3e02751187fc47139db53097a47ca54f';
    var req = new Request(url);
    fetch(req).then(function(response) {
        response.json().then(function(parsedJson){
            console.log(parsedJson);
            var numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19];
            var myNumbers = [];
            for (var i = 0; i < 5; i++) {
                var number = Math.floor(Math.random() * numbers.length);
                myNumbers.push(numbers[number]);
                numbers.splice(number, 1);
            }
            var news0tit = parsedJson.articles[myNumbers[0]].title;
            var news0url = parsedJson.articles[myNumbers[0]].url;
            document.getElementById('news0').href = news0url;
            document.getElementById('news0tit').innerHTML=news0tit;
            var news1tit = parsedJson.articles[myNumbers[1]].title;
            var news1url = parsedJson.articles[myNumbers[1]].url;
            document.getElementById('news1').href=news1url;
            document.getElementById('news1tit').innerHTML=news1tit;
            var news2tit = parsedJson.articles[myNumbers[2]].title;
            var news2url = parsedJson.articles[myNumbers[2]].url;
            document.getElementById('news2').href=news2url;
            document.getElementById('news2tit').innerHTML=news2tit;
            var news3tit = parsedJson.articles[myNumbers[3]].title;
            var news3url = parsedJson.articles[myNumbers[3]].url;
            document.getElementById('news3').href=news3url;
            document.getElementById('news3tit').innerHTML=news3tit;
            var news4tit = parsedJson.articles[myNumbers[4]].title;
            var news4url = parsedJson.articles[myNumbers[4]].url;
            document.getElementById('news4').href=news4url;
            document.getElementById('news4tit').innerHTML=news4tit;
            // var news5tit = parsedJson.articles[5].title;
            // var news5url = parsedJson.articles[5].url;
            // document.getElementById('news5tit').innerHTML=news5tit;
            // document.getElementById('news5url').innerHTML=news5url;
        })
    })
});