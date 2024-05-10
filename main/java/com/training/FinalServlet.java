package com.training;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Servlet implementation class FinalServlet
 */
public class FinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null; 
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			ServletContext servletContext = config.getServletContext();
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(servletContext.getInitParameter("connectionURL"),
					servletContext.getInitParameter("userName"), servletContext.getInitParameter("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		String Attribute = (String) session.getAttribute("userName");
		response.setContentType("text/html");
		response.getWriter().append("Welcome  " + Attribute);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
	
			Statement statement = connection.createStatement();
			String queryString = "select * from Orders;";
			System.out.println(queryString);
			ResultSet rs = statement.executeQuery(queryString);
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");
			 //Extract data from result set
			writer.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=100%>"
		              +"<tr><th>Order id</th><th>customerID</th><th>date</th><th>price</th></tr>");
			while (rs.next()) {
			     // Retrieve by column name
				writer.println("<tr><td><center>"+rs.getInt("OrderID")+"</center></td>"+
			               "<td><center>"+rs.getInt("CustomerID")+
			               "<td><center>"+rs.getInt("EmployeeID")+
			               "<td><center>"+rs.getDate("OrderDate")+
						"</center></td></tr>");
			 }
			writer.println("</table>");
			 rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
