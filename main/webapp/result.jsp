<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorHandler.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add the two numbers</title>
</head>
<body>
<%! int num1;
   int num2;
   int sum;
 %>
 <%
    num1 = Integer.parseInt(request.getParameter("num1"));
    num2 = Integer.parseInt(request.getParameter("num2"));
    sum = num1 + num2;
 %>
 <h1> The Sum of two number <%=num1%> and <%=num2%> is <%=sum %> </h1>
</body>
</html>