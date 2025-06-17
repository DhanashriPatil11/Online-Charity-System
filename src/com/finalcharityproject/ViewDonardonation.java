package com.finalcharityproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewDonardonation
 */
public class ViewDonardonation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDonardonation() {
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
		
		
		 String donorEmail = request.getParameter("donorEmail");

	        Connection con = DbConnection.connect();
	        PrintWriter out = response.getWriter();
	        response.setContentType("text/html");
	        out.println("<!DOCTYPE html>");
	        out.println("<html lang=\"en\">");
	        out.println("<head>");
	        out.println("<meta charset=\"UTF-8\">");
	        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
	        out.println("<title>View Donations</title>");
	        out.println("<style>");
	        out.println("body { background-image: url('img/avatars/back.jpg'); text-align: center; }");
	        out.println(".container { margin: 0 auto; padding: 50px; max-width: 600px; background: rgba(255, 255, 255, 0.8); border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
	        out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
	        out.println("table, th, td { border: 1px solid #ddd; }");
	        out.println("th, td { padding: 8px; text-align: left; }");
	        out.println("th { background-color: #f2f2f2; }");
	        out.println("</style>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<div class=\"container\">");
	        out.println("<h1>Your Donations</h1>");

	        try {
	            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM donardonation WHERE donorEmail = ?");
	            pstmt.setString(1, donorEmail);
	            ResultSet rs = pstmt.executeQuery();

	            out.println("<table>");
	            out.println("<th>Donor Name</th><th>Donor Email</th><th>Project Name</th><th>Donation Type</th><th>Donation Amount</th><th>Message</th></tr>");
	            while (rs.next()) {
	                out.println("<tr>");
	               
	                out.println("<td>" + rs.getString("donorName") + "</td>");
	                out.println("<td>" + rs.getString("donorEmail") + "</td>");
	                out.println("<td>" + rs.getString("projectname") + "</td>");
	                out.println("<td>" + rs.getString("donationtype") + "</td>");
	                out.println("<td>" + rs.getInt("donationAmount") + "</td>");
	                out.println("<td>" + rs.getString("donorMessage") + "</td>");
	                out.println("</tr>");
	            }
	            out.println("</table>");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            out.println("<p>Error retrieving donations. Please try again later.</p>");
	        } finally {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        out.println("</div>");
	        out.println("</body>");
	        out.println("</html>");
	    }
	}
