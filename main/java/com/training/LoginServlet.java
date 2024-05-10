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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String firstname="";
		String lastname="";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		if (null == username || null == password) {
			session.setAttribute("username",  username);
			session.setAttribute("firstname", firstname);
			session.setAttribute("lastname",  lastname);
			session.setAttribute("loginError",  true);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");
			requestDispatcher.include(request, response);
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
				if (!rs.next()) {
					session.setAttribute("loginError",  true);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");
					requestDispatcher.include(request, response);
				} else {
					if (rs.getString(1) != null) {
						// success
						firstname = rs.getString(1);
						lastname = rs.getString(2);
						session.setAttribute("firstname", firstname);
						session.setAttribute("lastname",  lastname);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Success.jsp");
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
