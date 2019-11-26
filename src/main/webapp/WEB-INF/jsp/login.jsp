<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../views/style.css"/>
    <title>Login to your Diary</title>
    <script type="application/javascript">
        var username = null, password = null;

        function doLogin() {
            username = document.getElementById('username').value;
            password = document.getElementById('password').value;
            var url = "/home"
            var xmlHttp = new XMLHttpRequest();

            xmlHttp.open("POST", "/home", true);
            xmlHttp.setRequestHeader('Authorization', createAuthHeaderValue())

        }

        function createAuthHeaderValue() {
            var rawAuth = username + ":" + password;
            var encAuth = btoa(rawAuth);
            return "Basic " + encAuth;
        }

    </script>
</head>
<body>

<div class="container">
    <h1>Welcome to your diary</h1>
    <%--@elvariable id="profile" type=""--%>
    <form:form method="post" onsubmit="return false;" modelAttribute="profile">
        <div class="form-group">
            <input type="text" required="required" id="username" autocomplete="off" name="username"/>
            <label for="username" class="control-label">Username</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="password" required="required" id="password" autocomplete="off" name="password"/>
            <label for="password" class="control-label">Password</label><i class="bar"></i>
        </div>
        <div class="button-container" id="button-container">
            <input class="button" type="submit" value="Login" id="button" onclick="doLogin()"/>
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
