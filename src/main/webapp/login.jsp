<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) session.getAttribute("error");
    session.removeAttribute("error");
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css"/>
    <title>Login</title>
</head>
<body>

<div class="container">
    <h1>ProDiary</h1>
    <%
        if(error != null) { %>
            <p class="error-message"><%=error%></p>
        <%}%>
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
