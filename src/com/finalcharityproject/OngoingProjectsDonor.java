package com.finalcharityproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OngoingProjectsDonor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public  OngoingProjectsDonor () {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        // Database connection
        Connection con = DbConnection.connect();

        try {
            if ("insert".equals(action)) {
                // Insert new project
            	
                String projectName = request.getParameter("projectName");
                String description = request.getParameter("description");
                String status = request.getParameter("status");

                PreparedStatement pstmt = con.prepareStatement("INSERT INTO ongoingprojectsdonor (projectName, description, status) VALUES (?, ?, ?)");
                pstmt.setString(1, projectName);
                pstmt.setString(2, description);
                pstmt.setString(3, status);
                pstmt.executeUpdate();
            } else if ("update".equals(action)) {
                // Update existing project
                int projectId = Integer.parseInt(request.getParameter("projectId"));
                String projectName = request.getParameter("projectName");
                String description = request.getParameter("description");
                String status = request.getParameter("status");

                PreparedStatement pstmt = con.prepareStatement("UPDATE ongoingprojectsdonor SET projectName = ?, description = ?, status = ? WHERE projectId = ?");
                pstmt.setString(1, projectName);
                pstmt.setString(2, description);
                pstmt.setString(3, status);
                pstmt.setInt(4, projectId);
                pstmt.executeUpdate();
            }

            // Fetch updated projects to display
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ongoingprojectsdonor");
            ResultSet rs = pstmt.executeQuery();

            // Start writing HTML response
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Manage Projects | Online Charity System</title>");
            out.println("<link href='css/app.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Manage Projects</h1>");
            out.println("<h2>Ongoing Projects</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Project ID</th><th>Project Name</th><th>Description</th><th>Status</th></tr>");
            
            while (rs.next()) {
                int projectId = rs.getInt("projectId");
                String projectName = rs.getString("projectName");
                String description = rs.getString("description");
                String status = rs.getString("status");

                out.println("<tr>");
                out.println("<td>" + projectId + "</td>");
                out.println("<td>" + projectName + "</td>");
                out.println("<td>" + description + "</td>");
                out.println("<td>" + status + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h2>Error managing projects.</h2></body></html>");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
