<%--
  Created by IntelliJ IDEA.
  User: Daniil Baydak
  Date: 11/13/2019
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Diary Entry</title>
</head>
<body>
    <div>
        <form action="where we send diary data to goes here" method="POST"> <!-- PROBABLY -->
            <input id="entryTitle" type="text" name="title" placeholder="Title">
            <input id="entryBody" type="text" name="body" placeholder="What are you feeling today?">
            <!-- attach image to diary entry here??? -->
            <input id="entrySubmit" type="submit" name="submit" value="Make Diary!">
        </form>
    </div>
</body>
</html>