$(document).ready(function(){
    var url='https://api.openweathermap.org/data/2.5/forecast?q=Salt+Lake+City,us&units=imperial&appid=f7ea971141408669dea697373e8214ba';
    $.getJSON(url, function(data){
        console.log(data);
        $('#icon0').attr('src','http://openweathermap.org/img/w/' + data.list[0].weather[0].icon + '.png');
        $('#weather0').html(data.list[0].weather[0].description);
        $('#temp0').html(data.list[0].main.temp + '&deg; F');
    });
});