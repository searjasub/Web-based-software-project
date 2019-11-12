<%--
  Created by IntelliJ IDEA.
  User: Searjasub Lopez
  Date: 11/11/2019
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="views/style.css"/>
    <style>
        <%@include file="views/style.css"%>
    </style>
    <title>Create Profile</title>
    <script type="application/javascript">
        function saveInfo() {
            var name = document.getElementById("name");
            var dob = document.getElementById("dob");
            var url = "/profiles/" + name.value + "/" + dob.value;
            var myForm = document.getElementById('form');
            myForm.action = url;
            myForm.submit();
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Welcome to your diary</h1>
    <form method="post" action="" id="form">
        <div class="form-group">
            <input type="text" required="required" id="name" autocomplete="off" name="name"/>
            <label for="name" class="control-label">Name</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="text" required="required" id="dob" autocomplete="off" name="dob"/>
            <label for="dob" class="control-label">Date of Birth</label><i class="bar"></i>
        </div>
        <div class="button-container" id="button-container">
            <div class="validation" id="buttonVal"></div>
            <input class="button" type="submit" value="Submit" id="button" onclick="saveInfo()"/>
        </div>
    </form>
</div>
</body>
</html>
