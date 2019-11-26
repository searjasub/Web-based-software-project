<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8' />
    <link href='../calendar/packages/core/main.css' rel='stylesheet' />
    <link href='../calendar/packages/daygrid/main.css' rel='stylesheet' />
    <link href='../calendar/packages/timegrid/main.css' rel='stylesheet' />
    <link href='../calendar/packages/list/main.css' rel='stylesheet' />
    <link href='../calendar/packages/bootstrap/main.css' rel='stylesheet' />
    <link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css' rel='stylesheet'>
<%--    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' rel='stylesheet' />--%>
    <link href='https://bootswatch.com/4/litera/bootstrap.min.css' rel='stylesheet' />
    <script src='../calendar/packages/core/main.js'></script>
    <script src='../calendar/packages/interaction/main.js'></script>
    <script src='../calendar/packages/daygrid/main.js'></script>
    <script src='../calendar/packages/timegrid/main.js'></script>
    <script src='../calendar/packages/list/main.js'></script>
    <script src='../calendar/packages/bootstrap/main.js'></script>

    <script>

        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                plugins: [ 'bootstrap', 'interaction', 'dayGrid', 'timeGrid', 'list' ],
                themeSystem: 'bootstrap',
                // header: {
                //     left: 'prev',
                //     center: 'title',
                //     right: 'next'
                // },
                navLinks: false, // can click day/week names to navigate views
                businessHours: true, // display business hours
                editable: true,
                events: [
                    {
                        title: 'Business Lunch',
                        start: '2019-08-03T13:00:00',
                        constraint: 'businessHours'
                    },
                    {
                        title: 'Meeting',
                        start: '2019-08-13T11:00:00',
                        constraint: 'availableForMeeting', // defined below
                        color: '#257e4a'
                    },
                    {
                        title: 'Conference',
                        start: '2019-08-18',
                        end: '2019-08-20'
                    },
                    {
                        title: 'Party',
                        start: '2019-08-29T20:00:00'
                    },

                    // areas where "Meeting" must be dropped
                    {
                        groupId: 'availableForMeeting',
                        start: '2019-08-11T10:00:00',
                        end: '2019-08-11T16:00:00',
                        rendering: 'background'
                    },
                    {
                        groupId: 'availableForMeeting',
                        start: '2019-08-13T10:00:00',
                        end: '2019-08-13T16:00:00',
                        rendering: 'background'
                    },

                    // red areas where no events can be dropped
                    {
                        start: '2019-08-24',
                        end: '2019-08-28',
                        overlap: false,
                        rendering: 'background',
                        color: '#ff9f89'
                    },
                    {
                        start: '2019-08-06',
                        end: '2019-08-08',
                        overlap: false,
                        rendering: 'background',
                        color: '#ff9f89'
                    }
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

<div id='calendar' style="float: left"></div>

</body>
</html>
