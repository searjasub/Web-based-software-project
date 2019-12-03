<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css"/>
    <meta charset="UTF-8">
    <title>Edit ${name}</title>
</head>
<body>
<div class="container">
    <h1>Edit your user settings</h1>
    <form:form method="patch" action="/profiles/edit">
        <div class="form-group">
            <input type="text" id="name" autocomplete="off" name="name"/>
            <label for="name" class="control-label">Name</label><i class="bar"></i>
        </div>
        <div class="form-group">
            <input type="text" id="dateOfBirth" autocomplete="off" name="dateOfBirth"/>
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
