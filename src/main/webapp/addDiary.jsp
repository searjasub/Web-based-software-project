<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css"/>
    <title>Add Diary Entry</title>
</head>
<body>
<div class="container">
    <h1>Create Diary Entry Here!</h1>
    <%--@elvariable id="entry" type=""--%>
    <form:form action="/entries/addEntry" method="POST" modelAttribute="entry">
        <div class="form-group">
            <input id="entryTitle" type="text" required  autocomplete="off" name="entryTitle">
            <label for="entryTitle" class="control-label">Title</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input id="entryBody" required type="text" name="content" autocomplete="off">
            <label for="entryBody" class="control-label">What are you feeling today?</label><i class="bar"></i>
        </div>
        <div class="button-container" id="button-container">
            <input class="button" id="entrySubmit" type="submit" name="submit" value="Make Diary!">
        </div>
    </form:form>
</div>
</body>
</html>