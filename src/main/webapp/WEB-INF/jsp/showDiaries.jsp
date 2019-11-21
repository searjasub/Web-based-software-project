<%@ page import="pro150.intelligenius.diaryapp.model.Entry" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Entry> allEntries= (List<Entry>) request.getAttribute("list");%>
<html>
<head>
    <title>Your Diaries</title>
</head>
<body>
    <% for(int x = 0; x < allEntries.size(); x++){
        String title = allEntries.get(x).getTitle();
        String content = allEntries.get(x).getContent();
        String time = allEntries.get(x).getTimeInMilliSeconds();
        Long longTime = Long.parseLong(time);
        Date datetime = new Date(longTime);
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy 'at' HH:mm");
        String fullTime = formatter.format(datetime);
    %>
        <div>
            <div><%=title%></div>
            <div><%=fullTime%>%></div>
            <div><%=content%></div>
        </div>
    <div>------------</div>
    <% }%>
</body>
</html>
