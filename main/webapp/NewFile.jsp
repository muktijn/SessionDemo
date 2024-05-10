 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello from JSP</title>

</head>
<body>
<%!
    int a = 10;
//delaration directive
%>
<%= a %>
<% 
    out.append("this is coming from scriplet");

%>

</body>
</html>