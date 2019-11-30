<%@ page import="pro150.intelligenius.diaryapp.model.Profile" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Profile profile = (Profile) request.getAttribute("profile"); %>
<html>
<head>
    <title>Hello </title>
</head>
<body>
    <h1>Name: <%=profile.getName()%></h1>
    <h1>DOB: <%=profile.getDateOfBirth()%></h1>
</body>
</html>
