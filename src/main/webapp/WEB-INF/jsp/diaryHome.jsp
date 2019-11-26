<%@ page import="java.security.Principal" %><%--
  Created by IntelliJ IDEA.
  User: Daniil Baydak
  Date: 11/13/2019
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<nav id="navBar">
    <span><button></button></span>
    <span><button>Add Diary Entry</button></span>
    <span><button></button></span>
  <!-- <span><button>News and Weather</button></span> -->
  <!-- <span>PROFILE PIC??? GOES HERE</span> -->
</nav>
<div id="homeScreen">
    <span id="entries">
        <!-- JAVA AND OTHER STUFF to display all of the diary entries goes here-->
    </span>
    <span id="calendarAndNews">
        <div id="calendar">Calendar Goes Here</div>
        <div id="news">News Goes  (Could have list type elements here?</div>
    </span>
</div>
</body>
</html>

