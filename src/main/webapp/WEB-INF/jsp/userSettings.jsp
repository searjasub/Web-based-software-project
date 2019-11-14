<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
    <script type="application/javascript">
        function saveInfo() {
            var name = document.getElementById("name");
            var url = "/profiles/edit/${name}";
            var myForm = document.getElementById('form');
            myForm.action = url;
            myForm.submit();
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Edit your user settings</h1>
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
            <input class="button" type="submit" value="Update" id="button" onclick="saveInfo()"/>
        </div>
    </form>
</div>
</body>
</html>
