<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Searjasub Lopez
  Date: 11/25/2019
  Time: 6:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" type="text/css" href="views/style.css"/>
    <style>
        <%@include file="views/style.css"%>
    </style>
    <title>Login to your Diary</title>
</head>
<body>

<div class="container">
    <h1>Welcome to your diary</h1>
    <%--@elvariable id="profile" type=""--%>
    <form:form method="post" action="/login" modelAttribute="profile">
        <div class="form-group">
            <input type="text" required="required" id="username" autocomplete="off" name="username"/>
            <label for="username" class="control-label">Username</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="password" required="required" id="password" autocomplete="off" name="password"/>
            <label for="password" class="control-label">Password</label><i class="bar"></i>
        </div>
        <div class="button-container" id="button-container">
            <input class="button" type="submit" value="Login" id="button"/>
        </div>
        <div class="button-container" id="button-container">
            <a href="/profiles/">
                <input class="button" value="Sign up" id="signupBtn"/>
            </a>
        </div>
    </form:form>
</div>

</body>
</html>
