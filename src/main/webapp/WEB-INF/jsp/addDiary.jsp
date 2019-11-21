<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Diary Entry</title>
</head>
<body>
<h1 id="createDiaryHeader">Create Diary Entry Here!</h1>
    <div>
        <%--@elvariable id="entry" type=""--%>
        <form:form action="/entries/create-entry" method="POST" modelAttribute="entry">
            <input id="entryTitle" type="text" name="title" placeholder="Title">
            <input id="entryBody" type="text" name="body" placeholder="What are you feeling today?">
            <input id="entrySubmit" type="submit" name="submit" value="Make Diary!">
        </form:form>
    </div>
</body>
</html>