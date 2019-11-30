<%--
  Created by IntelliJ IDEA.
  User: Daniil Baydak
  Date: 11/25/2019
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String errorMessage = (String) request.getAttribute("errorMessage");%>
<html>
<head>
    <title>Error!</title>
</head>
<body>
    <div>
        <%=errorMessage%>
    </div>
</body>
</html>
