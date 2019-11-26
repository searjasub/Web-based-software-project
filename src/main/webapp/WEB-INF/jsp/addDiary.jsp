<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="views/style.css"/>
    <style>
        <%@include file="views/style.css"%>
    </style>
    <title>Add Diary Entry</title>
</head>
<body>
<div class="container">
<h1 id="createDiaryHeader">Create Diary Entry Here!</h1>
    <div>
        <%--@elvariable id="entry" type=""--%>
        <form:form action="/entries/addEntry" method="POST" modelAttribute="entry">
            <div id="entryDiv"><input id="entryTitle" type="text" name="title" placeholder="Title" autocomplete="false"></div>
            <div id="entryDiv"><input id="entryBody" type="text" name="body" placeholder="What are you feeling today?" autocomplete="false"></div>
            <div><input id="entrySubmit" type="submit" name="submit" value="Make Diary!"></div>
        </form:form>
    </div>
</div>
</body>
</html>