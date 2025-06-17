package com.finalcharityproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddProject
 */
public class AddProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProject() {
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
		
		int projectid=0;
		projectid=Integer.parseInt(request.getParameter("projectid"));
		String ngoname=request.getParameter("ngoname");
		String projectname=request.getParameter("projectname");
		String donationtype=request.getParameter("donationtype");
		String projectaddress=request.getParameter("projectaddress");
		
		System.out.println("projectid="+projectid);
		System.out.println("ngoname="+ngoname);
		System.out.println("projectname="+projectname);
		System.out.println("donationtype="+donationtype);
		System.out.println("projectaddress="+projectaddress);
		
		Connection con=DbConnection.connect();
		
		  try {
	         	
	             PreparedStatement pstmt = con.prepareStatement("insert into addproject values(?,?,?,?,?)");
	             pstmt.setInt(1,projectid);
	             pstmt.setString(2,ngoname);
	             pstmt.setString(3, projectname);
	             pstmt.setString(4, donationtype);
	             pstmt.setString(5, projectaddress);
	            
	            int  i = pstmt.executeUpdate();
	         	if(i>0)
				{
					response.sendRedirect("projectAddSuccess.html");
						System.out.println("Project Added");
				}else
				{
					response.sendRedirect("projectAddFail.html");
					System.out.println("Failed to Add Project!!");
				}
				
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }   
		
		
		
		doGet(request, response);
	}

}
