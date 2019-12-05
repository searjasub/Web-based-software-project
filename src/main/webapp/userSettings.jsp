<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css"/>
    <meta charset="UTF-8">
    <title>Edit ${name}</title>
</head>
<body>
<div class="header top">
    <span>
    <h1 id="greeting"></h1>
    </span>
    <span><strong id="time"></strong>
    </span>
    <span class="current-weather-display">
        <span><img id="icon0"/></span>
        <span id='temp0'>           </span>
    </span>
    <span class="header-right">
        <a href="/home">Home</a>
        <a href="/entries/addEntry">Create Post</a>
        <a class="active" href="/profiles/edit">Edit Personal Settings</a>
        <a class="logout" href="/">Logout</a>
    </span>
</div>
<div class="container form-container">
    <h1>Edit Your Settings</h1>
    <form:form method="patch" action="/profiles/edit">
        <div class="form-group">
            <input type="text" id="name" autocomplete="off" name="name"/>
            <label for="name" class="control-label">Name</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="date" id="dateOfBirth" autocomplete="off" name="dateOfBirth"/>
            <label for="dateOfBirth" class="control-label">Date of Birth</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="text" id="city" autocomplete="off" name="city"/>
            <label for="city" class="control-label">City</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="text" id="state" autocomplete="off" name="state"/>
            <label for="state" class="control-label">State</label><i class="bar"></i>
        </div>
        <div class="button-container" id="button-container">
            <input class="button" type="submit" value="Update" id="button"/>
        </div>
    </form:form>
</div>
</body>
</html>
