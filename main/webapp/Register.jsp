<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration</title>
</head>
<H1>
<% 
session = request.getSession();
if (session.getAttribute("RegError") != null) {
	    regError = (Boolean)session.getAttribute("RegError");
	    if (regError == true) {
	        out.append((String)session.getAttribute("ErrorMsg"));
	        session.setAttribute("RegError", null);
	        session.setAttribute("ErrorMsg", null);
	    }
}

%>
</H1>
<body>
<form action="registerServlet" method="POST">
FirstName : <input type="text" name=firstname>
LastName : <input type="text" name=lastname>
UserName : <input type="text" name=username>
Password : <input type="text" name=password>
<input type="submit">
</form>
<%!
HttpSession session;
Boolean regError = false;
%>



</body>
</html>