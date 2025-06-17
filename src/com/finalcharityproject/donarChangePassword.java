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
 * Servlet implementation class donarChangePassword
 */
public class donarChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public donarChangePassword() {
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
		  String donorname = request.getParameter("donorname");
	        String dcurrentpassword = request.getParameter("dcurrentpassword");
	        String dnewpassword=request.getParameter("dnewpassword");
	        String dconfirmpassword= request.getParameter("dconfirmpassword");

	    
			Connection con=DbConnection.connect();
			
			  try {
		         	
		             PreparedStatement pstmt = con.prepareStatement("insert into donorchangepassword values(?,?,?,?)");
		             pstmt.setString(1,donorname);
		             pstmt.setString(2,dcurrentpassword);
		             pstmt.setString(3, dnewpassword);
		             pstmt.setString(4, dconfirmpassword);
		           
		            
		            int  i = pstmt.executeUpdate();
		         	if(i>0)
					{
						response.sendRedirect("donorpasssuccess.html");
							System.out.println("Password Changed Successfully!");
					}else
					{
						response.sendRedirect("donorpasserror.html");
						System.out.println("Failed to Change Password!!");
					}
					
		         } catch (SQLException e) {
		             e.printStackTrace();
		         }   
			
			doGet(request, response);
		}

	}