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
 * Servlet implementation class donorLogin
 */
public class donorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public donorLogin() {
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
		
		
        String email = request.getParameter("email");
        String password =request.getParameter("password");
        
      
        System.out.println("email=" + email);
        System.out.println("password=" + password);
        Connection con = DbConnection.connect();

        try {
            PreparedStatement pstmt = con.prepareStatement("insert into donorlogin values(?,?)");
          
          
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            int i = pstmt.executeUpdate();
            if (i > 0) {
                response.sendRedirect("DonarLoginSuccess.html");
                System.out.println("Donar Added");
            } else {
                response.sendRedirect("DonorLoginFail.html");
                System.out.println("Failed to Add Donar!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        doGet(request, response);
    }
        
        
    
}
