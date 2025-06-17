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


public class ngoRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ngoRegistration() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        System.out.println("name=" + name);
        System.out.println("email=" + email);

        // Simple validation (You can add more validation as needed)
        Connection con = DbConnection.connect();

        try {
            PreparedStatement pstmt = con.prepareStatement("insert into ngoregistration values(?,?,?,?,?,?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, contact);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setString(6, address);

            int i = pstmt.executeUpdate();
            if (i > 0) {
                response.sendRedirect("ngoRegistrationSuccess.html");
                System.out.println("Project Added");
            } else {
                response.sendRedirect("ngoRegistrationFail.html");
                System.out.println("Failed to Add Project!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        doGet(request, response);
    }
}
