package com.finalcharityproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class donorDonation
 */

public class donorDonation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public donorDonation() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String donorName = request.getParameter("donorName");
         String donorEmail = request.getParameter("donorEmail");
         String donationType = request.getParameter("donationtype");
         String projectName = request.getParameter("projectname");
         String donationAmountStr = request.getParameter("donationAmount");
         String donorMessage = request.getParameter("donorMessage");
         
      // Validate input
         
         if (donorName == null || donorName.isEmpty() ||
             donorEmail == null || donorEmail.isEmpty() ||
             donationType == null || donationType.isEmpty() ||
             projectName == null || projectName.isEmpty() ||
             (donationType.equals("money") && (donationAmountStr == null || donationAmountStr.isEmpty()))) {

             response.sendRedirect("error.html");
             return;
         }

         int donationAmount = donationType.equals("money") ? Integer.parseInt(donationAmountStr) : 0;
         
        // Database connection
        Connection con = DbConnection.connect();

        try {
            PreparedStatement pstmt;
            if ("money".equals(donationType)) {
                pstmt = con.prepareStatement("INSERT INTO donardonation (donorName, donorEmail, donationType, projectName, donationAmount, donorMessage) VALUES (?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, donorName);
                pstmt.setString(2, donorEmail);
                pstmt.setString(3, donationType);
                pstmt.setString(4, projectName);
                pstmt.setInt(5, donationAmount);
                pstmt.setString(6, donorMessage);
            } else {
                pstmt = con.prepareStatement("INSERT INTO donardonation (donorName, donorEmail, donationType, projectName, donorMessage) VALUES (?, ?, ?, ?, ?)");
                pstmt.setString(1, donorName);
                pstmt.setString(2, donorEmail);
                pstmt.setString(3, donationType);
                pstmt.setString(4, projectName);
                pstmt.setString(5, donorMessage);
            }


            int result = pstmt.executeUpdate();

            if (result > 0) {
                response.sendRedirect("success.html");
            } else {
                response.sendRedirect("error.html");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
