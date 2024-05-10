<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<H1>
<% 
session = request.getSession();
if (session.getAttribute("loginError") != null)
    loginerror = (Boolean)session.getAttribute("loginError");
    if(loginerror == true) {
    	session.setAttribute("loginError", false);
	    out.append("Invalid Login");
}
if (session.getAttribute("firstname") != null) {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Success.jsp");
	requestDispatcher.forward(request, response);
}
%>
</H1>
<body>
<form action="loginServlet" method="POST">
UserName : <input type="text" name=username>
Password : <input type="text" name=password>
<input type="submit">
</form>
<%!
HttpSession session;
Boolean loginerror = false;
%>



</body>
</html>