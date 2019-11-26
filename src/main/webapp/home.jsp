<%@ page import="java.util.List" %>
<%@ page import="pro150.intelligenius.diaryapp.model.Entry" %>
<%@ page import="pro150.intelligenius.diaryapp.model.Profile" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<% List<Entry> entries = (List<Entry>) session.getAttribute("entries");
    session.removeAttribute("entries"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='../resources/calendar/packages/core/main.css' rel='stylesheet'/>
    <link href='../resources/calendar/packages/daygrid/main.css' rel='stylesheet'/>
    <link href='../resources/calendar/packages/timegrid/main.css' rel='stylesheet'/>
    <link href='../resources/calendar/packages/list/main.css' rel='stylesheet'/>
    <link href='../resources/calendar/packages/bootstrap/main.css' rel='stylesheet'/>
    <link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css' rel='stylesheet'>
    <%--    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' rel='stylesheet' />--%>
    <link href='https://bootswatch.com/4/litera/bootstrap.min.css' rel='stylesheet'/>
    <title>Homepage</title>

    <script src='../resources/calendar/packages/core/main.js'></script>
    <script src='../resources/calendar/packages/interaction/main.js'></script>
    <script src='../resources/calendar/packages/daygrid/main.js'></script>
    <script src='../resources/calendar/packages/timegrid/main.js'></script>
    <script src='../resources/calendar/packages/list/main.js'></script>
    <script src='../resources/calendar/packages/bootstrap/main.js'></script>

    <script>

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
                navLinks: true, // can click day/week names to navigate views
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
                        title: '<%=element.getValue()%>' + ' Posts',
                        start: '<%=element.getKey()%>'
                    },
                    <%}%>
                ]

        });
            calendar.render();

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
        }

    </style>
</head>
<body>
<div class="header">
    <div class="header-right">
        <a href="/entries">Show Diary Entries</a>
        <a href="/entries/addEntry">Add Diary Entry</a>
        <a href="/profiles/edit">Edit Personal Settings</a>
    </div>
</div>

<div id='calendar' style="float: left"></div>
<div>
    <%
        for(Entry entry : entries) { %>
            <div>
                <p><%=entry.getTitle()%></p>
                <p><%=entry.getContent()%></p>
            </div>
        <% } %>
</div>


</body>
</html>
