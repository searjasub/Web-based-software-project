<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="views/style.css"/>
    <style>
        <%@include file="views/style.css"%>
    </style>
    <title>Create Profile</title>
</head>
<body>
<div class="container">
    <h1>Welcome to your diary</h1>
    <%--@elvariable id="profile" type=""--%>
    <form:form method="post" action="/profiles/create-profile" modelAttribute="profile">
        <div class="form-group">
            <input type="text" required="required" id="username" autocomplete="off" name="username"/>
            <label for="username" class="control-label">Username</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="password" required="required" id="password" autocomplete="off" name="password"/>
            <label for="password" class="control-label">Password</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="text" required="required" id="name" autocomplete="off" name="name"/>
            <label for="name" class="control-label">Name</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="text" required="required" id="dateOfBirth" autocomplete="off" name="dateOfBirth"/>
            <label for="dateOfBirth" class="control-label">Date of Birth</label><i class="bar"></i>
        </div>
        <div class="button-container" id="button-container">
            <div class="validation" id="buttonVal"></div>
            <input class="button" type="submit" value="Submit" id="button"/>
        </div>
    </form:form>
</div>
</body>
</html>
