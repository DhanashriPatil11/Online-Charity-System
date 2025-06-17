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
 * Servlet implementation class loginNgo
 */
public class loginNgo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginNgo() {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");

         System.out.println("id=" + id);
         System.out.println("email=" + email);
         System.out.println("password=" + password);
         
         Connection con=DbConnection.connect();
        

         try {
         	
             PreparedStatement pstmt = con.prepareStatement("insert into ngologin values(?,?,?)");
             pstmt.setInt(1, id);
             pstmt.setString(2, email);
             pstmt.setString(3, password);
            
             int i = pstmt.executeUpdate();
         	if(i>0)
			{
				response.sendRedirect("NgologinSuccess.html");
					System.out.println("Valid User");
			}else
			{
				response.sendRedirect("NgologinFail.html");
				System.out.println("Invalid User");
			}
			
         } catch (SQLException e) {
             e.printStackTrace();
         }   
		
		doGet(request, response);
	}

	}
