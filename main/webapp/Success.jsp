<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome page after login </title>
</head>
<body>
<%!
	String firstname;
	String lastname;
	HttpSession session;
%>
<%
	session = request.getSession();
	firstname = (String)session.getAttribute("firstname");
	lastname = (String)session.getAttribute("lastname");
	if (session.getAttribute("firstname") == null) {
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");
		requestDispatcher.forward(request, response);
	}
%>
Welcome <%=firstname%>
  <%=lastname%>
<%
RequestDispatcher requestDispatcher = request.getRequestDispatcher("/finalServlet");
requestDispatcher.forward(request, response);
%>
</body>
</html>