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
 * Servlet implementation class DonarRegistration
 */
public class DonarRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DonarRegistration() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String name = request.getParameter("name");
        String contactStr = request.getParameter("contact");  // Contact as String for validation
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        // Print the name to check if it's received correctly
        System.out.println("name=" + name);

        // Validate inputs
        if (name == null || contactStr == null || email == null || password == null || address == null || 
            name.isEmpty() || contactStr.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty()) {
            response.sendRedirect("error.html"); // Redirect to an error page if any input is invalid
            return;
        }

        long contact = 0;  // Initialize contact variable
        try {
            contact = Long.parseLong(contactStr);  // Convert contact to long
        } catch (NumberFormatException e) {
            response.sendRedirect("error.html");  // Redirect to an error page for invalid number
            return;
        }

        // Simple validation (You can add more validation as needed)
        Connection con = DbConnection.connect();
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO donarregistration (name, contact, email, password, address) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, name);
            pstmt.setLong(2, contact);  // Set contact as long
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, address);

            int i = pstmt.executeUpdate();
            if (i > 0) {
                response.sendRedirect("DonarRegistrationSuccess.html");
                System.out.println("Donor Registered");
            } else {
                response.sendRedirect("index.html");
                System.out.println("Failed to Register Donor!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        doGet(request, response);
    }
}
