package com.finalcharityproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test
 */
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Get form parameters
	        String name = request.getParameter("name");
	        int prn = Integer.parseInt(request.getParameter("prn"));
	        int rollno = Integer.parseInt(request.getParameter("rollno"));
	        int marks = Integer.parseInt(request.getParameter("marks"));

	        // Response content type
	        response.setContentType("text/html");
	        
	        try (Connection con = DbConnection.connect()) {
	            // SQL query to insert data
	        	PreparedStatement pstmt = con.prepareStatement("insert into test values(?,?,?,?)");
	            pstmt.setString(1, name);
	            pstmt.setInt(2, prn);
	            pstmt.setInt(3, rollno);
	            pstmt.setInt(4, marks);

	            // Execute update and check the result
	            int rows = pstmt.executeUpdate();
	            if (rows > 0) {
	                response.getWriter().println("<h3>Data inserted successfully!</h3>");
	            } else {
	                response.getWriter().println("<h3>Failed to insert data.</h3>");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
	        }
	    }
	}