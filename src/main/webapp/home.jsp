<%@ page import="pro150.intelligenius.diaryapp.model.Entry" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<% List<Entry> entries = (List<Entry>) session.getAttribute("entries");
    String name = (String) session.getAttribute("name");
    String city = (String) session.getAttribute("city");
    String birthday = (String) session.getAttribute(("birthday"));
    entries.sort(Comparator.comparing(Entry::getTimeInMilliSeconds).reversed());
    session.removeAttribute("entries");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='resources/calendar/packages/core/main.css' rel='stylesheet'/>
    <link href='resources/calendar/packages/daygrid/main.css' rel='stylesheet'/>
    <link href='resources/calendar/packages/timegrid/main.css' rel='stylesheet'/>
    <link href='resources/calendar/packages/list/main.css' rel='stylesheet'/>
    <link href='resources/calendar/packages/bootstrap/main.css' rel='stylesheet'/>
    <link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css' rel='stylesheet'>
    <%--    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' rel='stylesheet' />--%>
    <%--    <link href='https://bootswatch.com/4/litera/bootstrap.min.css' rel='stylesheet'/>--%>
    <link rel="stylesheet" type="text/css" href="resources/css/myCalendar.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>

    <script src='../resources/calendar/packages/core/main.js'></script>
    <script src='../resources/calendar/packages/interaction/main.js'></script>
    <script src='../resources/calendar/packages/daygrid/main.js'></script>
    <script src='../resources/calendar/packages/timegrid/main.js'></script>
    <script src='../resources/calendar/packages/list/main.js'></script>
    <script src='../resources/calendar/packages/bootstrap/main.js'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src='../resources/newsManager.js'></script>

    <title>Home</title>
    <script>
        $(document).ready(function () {
            var url = 'https://api.openweathermap.org/data/2.5/forecast?q=<%=city%>,us&units=imperial&appid=f7ea971141408669dea697373e8214ba';
            $.getJSON(url, function (data) {
                $('#icon0').attr('src', 'http://openweathermap.org/img/w/' + data.list[0].weather[0].icon + '.png');
                $('#weather0').html(data.list[0].weather[0].description);
                $('#temp0').html(data.list[0].main.temp + '&deg; F');
            });
        });

        var start, end;

        var entries = [<% for (Entry entry : entries) {
            Date timeStamp = new Date(Long.parseLong(entry.getTimeInMilliSeconds()));
            SimpleDateFormat comparisonFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String comparison = comparisonFormatter.format(timeStamp);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            String formatted = formatter.format(timeStamp);%>{
            timeINMilli: '<%=entry.getTimeInMilliSeconds()%>',
            comparisonDate: '<%=comparison%>',
            formattedDate: '<%=formatted%>',
            title: '<%=entry.getTitle()%>',
            content: '<%=entry.getContent()%>'
        },
            <% } %>
        ];

        function reverseEntries() {
            entries.reverse();
            if (!start || !end) {
                updateAllEntries();
            } else {
                updateEntries();
            }
        }

        function showAllEntries() {
            start = null;
            end = null;
            updateAllEntries();
        }

        function updateEntries() {
            var startDate = start.split('-');
            var endDate = end.split('-');
            var list = document.getElementById('entry-list');
            list.innerHTML = "";
            var hasEntry = false;

            var from = new Date(startDate[0], parseInt(startDate[1]) - 1, startDate[2]);
            var to = new Date(endDate[0], parseInt(endDate[1]) - 1, endDate[2] - 1);

            entries.forEach(function (entry) {
                var dateCheck = entry.comparisonDate.split('-');
                var check = new Date(dateCheck[0], parseInt(dateCheck[1]) - 1, dateCheck[2]);

                if (check >= from && check <= to) {
                    hasEntry = true;
                    list.innerHTML = list.innerHTML +
                        '<div class="container">\n' +
                        '<p><strong>' + entry.title + '</strong> - ' + entry.formattedDate + '</p>\n' +
                        '<p>' + entry.content + '</p>\n' +
                        '</div>'
                }
            });

            if (!hasEntry) {
                if (from.getTime() === to.getTime()) {
                    list.innerHTML = '<div class="container">\n' +
                        '<p><strong>No posts from ' + startDate[1] + '/' + startDate[2] + '/' + startDate[0] + '</strong></div>'
                } else {
                    list.innerHTML = '<div class="container">\n' +
                        '<p><strong>No posts from ' + startDate[1] + '/' + startDate[2] + '/' + startDate[0] + ' to ' + endDate[1] + '/' + (endDate[2] - 1) + '/' + endDate[0] + '</strong></div>'
                }
            }
        }

        function updateAllEntries() {
            var list = document.getElementById('entry-list');
            list.innerHTML = "";

            document.getElementById('displaying-dates').innerText = 'Displaying: All Posts';

            if (entries.length > 0) {
                entries.forEach(function (entry) {
                    list.innerHTML = list.innerHTML +
                        '<div class="container-diary">\n' +
                        '<div class="delete-btn-container"> <a href="/entries/delete/'+ entry.timeINMilli+'">X</a></div>' +
                        '<p><strong>' + entry.title + '</strong> - ' + entry.formattedDate + '</p>\n' +
                        '<p>' + entry.content + '</p>\n' +
                        '</div>'
                });
            } else {
                list.innerHTML = '<div class="container"><p><strong>You don\'t have any posts.</strong></p></div>';
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                plugins: ['bootstrap', 'interaction', 'dayGrid', 'timeGrid', 'list'],
                themeSystem: 'bootstrap',
                // header: {
                //     left: 'prev',
                //     center: 'title',
                //     right: 'next'
                // },
                selectable: true,
                select: function (selectInfo) {
                    start = selectInfo.startStr;
                    end = selectInfo.endStr;
                    var hasEntry = false;
                    var startDate = start.split('-');
                    var endDate = end.split('-');
                    var list = document.getElementById('entry-list');
                    list.innerHTML = "";
                    var from = new Date(startDate[0], parseInt(startDate[1]) - 1, startDate[2]);
                    var to = new Date(endDate[0], parseInt(endDate[1]) - 1, endDate[2] - 1);

                    var display = document.getElementById('displaying-dates');

                    if (from.getTime() === to.getTime()) {
                        display.innerText = 'Displaying: ' + startDate[1] + '/' + startDate[2] + '/' + startDate[0];
                    } else {
                        display.innerText = 'Displaying: ' + startDate[1] + '/' + startDate[2] + '/' + startDate[0] + ' - ' + endDate[1] + '/' + (endDate[2] - 1) + '/' + endDate[0];
                    }

                    entries.forEach(function (entry) {
                        var dateCheck = entry.comparisonDate.split('-');
                        var check = new Date(dateCheck[0], parseInt(dateCheck[1]) - 1, dateCheck[2]);

                        if (check >= from && check <= to) {
                            hasEntry = true;
                            list.innerHTML = list.innerHTML +
                                '<div class="container">\n' +
                                '<p><strong>' + entry.title + '</strong> - ' + entry.formattedDate + '</p>\n' +
                                '<p>' + entry.content + '</p>\n' +
                                '</div>'
                        }
                    });

                    if (!hasEntry) {
                        if (from.getTime() === to.getTime()) {
                            list.innerHTML = '<div class="container">\n' +
                                '<p><strong>No posts from ' + startDate[1] + '/' + startDate[2] + '/' + startDate[0] + '</strong></div>'
                        } else {
                            list.innerHTML = '<div class="container">\n' +
                                '<p><strong>No posts from ' + startDate[1] + '/' + startDate[2] + '/' + startDate[0] + ' to ' + endDate[1] + '/' + (endDate[2] - 1) + '/' + endDate[0] + '</strong></div>'
                        }
                    }
                },
                businessHours: true, // display business hours
                events: [
                    <% Map<String, Integer> entriesPerDate = new HashMap<>();
                    for(Entry entry: entries){
                        Date timeStamp = new Date(Long.parseLong(entry.getTimeInMilliSeconds()));
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String formatted = formatter.format(timeStamp);

                        if(entriesPerDate.containsKey(formatted)) {
                            entriesPerDate.put(formatted, entriesPerDate.get(formatted) + 1);
                        } else {
                            entriesPerDate.put(formatted, 1);
                        }
                    } %>
                    <% for(Map.Entry<String,Integer> element : entriesPerDate.entrySet()) {%>
                    {
                        title: '<%=element.getValue()%>' + ' Post' <%
                        if(element.getValue() > 1) { %>
                            + 's'
                        <%}
                        %>,
                        start: '<%=element.getKey()%>'
                    },
                    <%}%>
                    <% String[] birthdayParts = birthday.split("-");
                    for(int i = 0; i < 100; i++) {
                    String date = (Integer.parseInt(birthdayParts[0]) + i) + "-" + birthdayParts[1] + "-" + birthdayParts[2];        %>
                    {
                        title: 'Happy B-Day!',
                        start: '<%=date%>'
                    },
                    <%}%>
                ]
            });
            calendar.render();
            updateAllEntries();

            (function () {
                function checkTime(i) {
                    return (i < 10) ? "0" + i : i;
                }

                function startTime() {
                    var today = new Date(),
                        h = today.getHours() % 12 == 0 ? 12 : today.getHours() % 12,
                        m = checkTime(today.getMinutes()),
                        ampm = today.getHours() >= 12 ? 'PM' : 'AM';
                    timeText = document.getElementById('time');
                    time = timeText.innerText.split(':');
                    if (m + " " + ampm != time[1]) {
                        timeText.innerText = h + ":" + m + " " + ampm;
                        if (h != time[0]) {
                            var rawBirthday = '<%=birthday%>'.split('-');
                            var birthday = new Date(rawBirthday[0], parseInt(rawBirthday[1]) - 1, rawBirthday[2]);
                            var hour = today.getHours();
                            var greeting = document.getElementById("greeting");
                            if (today.getMonth() == birthday.getMonth() && today.getDate() == birthday.getDate()) {
                                greeting.innerText = "Happy Birthday, <%=name%>!";
                            } else {
                                if (hour >= 5 && hour <= 11) {
                                    greeting.innerText = "Good Morning, <%=name%>";
                                } else if (hour >= 12 && hour <= 16) {
                                    greeting.innerText = "Good Afternoon, <%=name%>";
                                } else if (hour >= 17 && hour <= 20) {
                                    greeting.innerText = "Good Evening, <%=name%>";
                                } else {
                                    greeting.innerText = "Good Night, <%=name%>";
                                }
                            }
                        }
                    }
                    t = setTimeout(function () {
                        startTime()
                    }, 500);
                }

                startTime();
            })();

        });

    </script>
    <style>

        body {
            margin: 40px 10px;
            padding: 0;
            font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
            font-size: 14px;
        }

        #calendar {
            max-width: 600px;
            margin: 0 auto;
            margin-bottom: 10px;
        }

    </style>
</head>
<body>
<div class="header top">
    <span>
    <h1 id="greeting"></h1>
    </span>
    <span><strong id="time"></strong>
    </span>
    <span class="current-weather-display">
        <span><img id="icon0"/></span>
        <span id='temp0'>           </span>
    </span>
    <span class="header-right">
        <a class="active" href="/home">Home</a>
        <a href="/entries/addEntry">Create Post</a>
        <a href="/profiles/edit">Edit Personal Settings</a>
        <a class="logout" href="/">Logout</a>
    </span>
</div>

<div class="left">
    <div id='calendar'></div>
    <div class="option-container">
        <div class="post-display">
            <p>Show Newest Post First</p>
            <label class="switch">
                <input id="postSort" type="checkbox" onclick="reverseEntries()" checked>
                <span class="slider round"></span>
            </label>
        </div>
    </div>
    <div class="post-display">
        <div class="button-container">
            <input id="allPosts" type="button" class="button" onclick="showAllEntries()" value="Show All Posts"/>
        </div>
    </div>
</div>

<div>
    <div class="showing">
        <p id="displaying-dates"></p>
    </div>
    <div id="entry-list" class="list-container right"></div>
</div>
<div class="news">
    <h3>News</h3>
    <a id="news0" target="_blank">
        <div class="news-container">
            <div class="article" id="news0tit"></div>
        </div>
    </a>
    <a id="news1" target="_blank">
        <div class="news-container">
            <div class="article" id="news1tit"></div>
        </div>
    </a>
    <a id="news2" target="_blank">
        <div class="news-container">
            <div class="article" id="news2tit"></div>
        </div>
    </a>
    <a id="news3" target="_blank">
        <div class="news-container">
            <div class="article" id="news3tit"></div>
        </div>
    </a>
    <a id="news4" target="_blank">
        <div class="news-container">
            <div class="article" id="news4tit"></div>
        </div>
    </a>
    <%--    <div class="news-container">--%>
    <%--        <div class="article" id="news5tit"></div>--%>
    <%--        <div class="article" id="news5url"></div>--%>
    <%--    </div>--%>
</div>
</body>
</html>
