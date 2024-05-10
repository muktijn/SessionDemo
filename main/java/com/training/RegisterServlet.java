package com.training;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class registerServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		if (username.isBlank() || password.isBlank() || 
				 firstname.isBlank() || lastname.isBlank()) {
			session.setAttribute("regError",  true);
			session.setAttribute("ErrorMsg",  "user details missing");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Register.jsp");
			requestDispatcher.forward(request, response);
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root",
					"root");
			if (connection != null) {
				PreparedStatement statement = connection.prepareStatement(
						"select * from user where username=? and password=?");
				statement.setString(1, username);
				statement.setString(2, password);
						
				ResultSet rs = statement.executeQuery();
				if (rs.next()) {
					session.setAttribute("RegError",  true);
					session.setAttribute("ErrorMsg", "This user already registered!");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Register.jsp");
					requestDispatcher.forward(request, response);
				} else {
					PreparedStatement statement1 = connection.prepareStatement(
							"insert into user values(?,?,?,?)");
					statement1.setString(1, firstname);
					statement1.setString(2, lastname);
					statement1.setString(3, username);
					statement1.setString(4, password);
							
					int executeUpdate = statement1.executeUpdate();
					/*
					session.setAttribute("firstname", firstname);
					session.setAttribute("lastname",  lastname);
					*/
					if (executeUpdate > 0) {
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/RegisterSuccess.jsp");
					    requestDispatcher.forward(request, response);
					} else {
						session.setAttribute("RegError", true);
						session.setAttribute("ErrorMsg", "user could not be registered!");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Register.jsp");
					    requestDispatcher.forward(request, response);
					}
				
					
				}
				
			}
			
		} catch (SQLException e){
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
