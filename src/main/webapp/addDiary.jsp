<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css"/>
    <title>Add Diary Entry</title>
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
        <a href="/home">Home</a>
        <a class="active" href="/entries/addEntry">Create Post</a>
        <a href="/profiles/edit">Edit Personal Settings</a>
        <a class="logout" href="/">Logout</a>
    </span>
</div>
<div class="container form-container">
    <h1>Create Post!</h1>
    <%--@elvariable id="entry" type=""--%>
    <form:form action="/entries/addEntry" method="POST" modelAttribute="entry">
        <div class="form-group">
            <input id="title" type="text" required  autocomplete="off" name="title">
            <label for="title" class="control-label">Title</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input id="entryBody" required type="text" name="content" autocomplete="off">
            <label for="entryBody" class="control-label">What are you feeling today?</label><i class="bar"></i>
        </div>
        <div class="button-container" id="button-container">
            <input class="button" id="entrySubmit" type="submit" name="submit" value="Make Post!">
        </div>
    </form:form>
</div>
</body>
</html>