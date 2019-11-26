<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
</head>
<body>
<div class="container">
    <h1>Edit your user settings</h1>
    <form:form method="post" action="/profiles/edit/${name}" modelAttribute="update">
        <div class="form-group">
            <input type="text" required="required" id="name" autocomplete="off" name="name"/>
            <label for="name" class="control-label">Name</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="text" required="required" id="dateOfBirth" autocomplete="off" name="dateOfBirth"/>
            <label for="dateOfBirth" class="control-label">Date of Birth</label><i class="bar"></i>
        </div>
        <div class="button-container" id="button-container">
            <input class="button" type="submit" value="Update" id="button"/>
        </div>
    </form:form>
</div>
</body>
</html>
