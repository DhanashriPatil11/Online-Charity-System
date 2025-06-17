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
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
		
		String ngoname=request.getParameter("ngoname");
		String currentpassword=request.getParameter("currentpassword");
		
		String newpassword=request.getParameter("newpassword");
		String confirmpassword=request.getParameter("confirmpassword");
		
		System.out.println("ngoname="+ngoname);
		System.out.println("currentpassword="+currentpassword);
		System.out.println("newpassword="+newpassword);
		System.out.println("confirmpassword="+confirmpassword);
		
		Connection con=DbConnection.connect();
		
		  try {
	         	
	             PreparedStatement pstmt = con.prepareStatement("insert into changepassword values(?,?,?,?)");
	             pstmt.setString(1,ngoname);
	             pstmt.setString(2,currentpassword);
	             pstmt.setString(3, newpassword);
	             pstmt.setString(4, confirmpassword);
	           
	            
	            int  i = pstmt.executeUpdate();
	         	if(i>0)
				{
					response.sendRedirect("passwordChangedSuccess.html");
						System.out.println("Password Changed Successfully!");
				}else
				{
					response.sendRedirect("passwordChangeFailed.html");
					System.out.println("Failed to Change Password!!");
				}
				
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }   
		
		doGet(request, response);
	}

}
