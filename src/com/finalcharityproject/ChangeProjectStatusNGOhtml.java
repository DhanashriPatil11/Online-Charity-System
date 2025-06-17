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


public class ChangeProjectStatusNGOhtml extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChangeProjectStatusNGOhtml() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Database connection
        Connection con = DbConnection.connect();

        try {
            // Handle insertion
            String insertProjectName = request.getParameter("insertProjectName");
            String insertDescription = request.getParameter("insertDescription");
            if (insertProjectName != null && insertDescription != null) {
                PreparedStatement pstmtInsert = con.prepareStatement("INSERT INTO projectstatusngo (projectNamengo, projectdescription, projectstatus) VALUES (?, ?, 'Ongoing')");
                pstmtInsert.setString(1, insertProjectName);
                pstmtInsert.setString(2, insertDescription);
                pstmtInsert.executeUpdate();
            }

            // Handle status update
            String updateProjectIdParam = request.getParameter("projectId");
            if (updateProjectIdParam != null) {
                int projectId = Integer.parseInt(updateProjectIdParam);
                PreparedStatement pstmtUpdate = con.prepareStatement("UPDATE projectstatusngo SET projectstatus = 'Completed' WHERE projectIdngo = ?");
                pstmtUpdate.setInt(1, projectId);
                pstmtUpdate.executeUpdate();
            }

            // Fetch ongoing projects from the database
            PreparedStatement pstmtOngoing = con.prepareStatement("SELECT * FROM projectstatusngo WHERE projectstatus = 'Ongoing'");
            ResultSet rsOngoing = pstmtOngoing.executeQuery();

            // Fetch completed projects from the database
            PreparedStatement pstmtCompleted = con.prepareStatement("SELECT * FROM projectstatusngo WHERE projectstatus = 'Completed'");
            ResultSet rsCompleted = pstmtCompleted.executeQuery();

            // Start writing HTML response
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Projects Overview | Online Charity System</title>");
            out.println("<link href='css/app.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header><h1>Projects Overview</h1></header>");
            out.println("<main>");

            // Insertion Form
            out.println("<section>");
            out.println("<h2>Add New Project</h2>");
            out.println("<form action='ChangeProjectStatusNGOhtml' method='post'>");
            out.println("<label for='insertProjectName'>Project Name:</label>");
            out.println("<input type='text' id='insertProjectName' name='insertProjectName' required><br><br>");
            out.println("<label for='insertDescription'>Description:</label>");
            out.println("<textarea id='insertDescription' name='insertDescription' required></textarea><br><br>");
            out.println("<button type='submit'>Add Project</button>");
            out.println("</form>");
            out.println("</section>");

            // Ongoing Projects Table
            out.println("<section>");
            out.println("<h2>Ongoing Projects</h2>");
            out.println("<form action='ChangeProjectStatusNGOhtml' method='post'>");
            out.println("<table border='1'>");
            out.println("<thead><tr><th>Project ID</th><th>Project Name</th><th>Description</th><th>Status</th><th>Update Status</th></tr></thead>");
            out.println("<tbody>");
            
            while (rsOngoing.next()) {
                int projectId = rsOngoing.getInt("projectIdngo");
                String projectName = rsOngoing.getString("projectNamengo");
                String description = rsOngoing.getString("projectdescription");
                String status = rsOngoing.getString("projectstatus");

                out.println("<tr>");
                out.println("<td>" + projectId + "</td>");
                out.println("<td>" + projectName + "</td>");
                out.println("<td>" + description + "</td>");
                out.println("<td>" + status + "</td>");
                out.println("<td><button type='submit' name='projectId' value='" + projectId + "'>Complete</button></td>");
                out.println("</tr>");
            }
            
            out.println("</tbody>");
            out.println("</table>");
            out.println("</form>");
            out.println("</section>");

            // Completed Projects Table
            out.println("<section>");
            out.println("<h2>Completed Projects</h2>");
            out.println("<table border='1'>");
            out.println("<thead><tr><th>Project ID</th><th>Project Name</th><th>Description</th><th>Status</th></tr></thead>");
            out.println("<tbody>");

            while (rsCompleted.next()) {
                int projectId = rsCompleted.getInt("projectIdngo");
                String projectName = rsCompleted.getString("projectNamengo");
                String description = rsCompleted.getString("projectdescription");
                String status = rsCompleted.getString("projectstatus");

                out.println("<tr>");
                out.println("<td>" + projectId + "</td>");
                out.println("<td>" + projectName + "</td>");
                out.println("<td>" + description + "</td>");
                out.println("<td>" + status + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</section>");

            out.println("</main>");
            out.println("<footer><p>&copy; 2024 Online Charity System</p></footer>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h2>Error handling projects.</h2></body></html>");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
