package charityPackage;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddProjectServlet
 */
public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProjectServlet() {
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
		int id= 0;
		int ngoId=Integer.parseInt(request.getParameter("ngoId"));
		String projectName=request.getParameter("projectName");
		String projectDetails=request.getParameter("projectDetails");
		String projectAddress=request.getParameter("projectAddress");
		String city=request.getParameter("city");
		String pincode=request.getParameter("pincode");
		String state=request.getParameter("state");
	    int latitude=Integer.parseInt(request.getParameter("latitude"));
	    int longitude=Integer.parseInt(request.getParameter("longitude"));
	    String projectCost=request.getParameter("projectCost");
	    String status="onGoing";
	    Connection con=DbConnection.connect();
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccinedb","root","");
	    	
			PreparedStatement pstmt = con.prepareStatement("insert into project values(?,?,?,?,?,?,?,?,?,?,?,?)");
			
			
			pstmt.setInt(1, id);
			pstmt.setInt(2, ngoId);
			pstmt.setString(3, projectName);
			pstmt.setString(4, projectDetails);
			pstmt.setString(5, projectAddress);
			pstmt.setString(6, city);
			pstmt.setString(7, pincode);
			pstmt.setString(8, state);
			pstmt.setInt(9, latitude);
			pstmt.setInt(10, longitude);
			pstmt.setString(11,projectCost);
			pstmt.setString(12,status);
            
			int i=pstmt.executeUpdate();
			if (i>0)
					{
				        response.sendRedirect("NgoDashboard.html");
					}else{
						response.sendRedirect("addProject.html");
					}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
