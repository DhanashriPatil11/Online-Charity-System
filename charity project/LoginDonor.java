package charityPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginDonor
 */
public class LoginDonor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       int Id;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginDonor() {
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
		doGet(request, response);
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		int i=0;
		try{
		Connection con=DbConnection.connect();
		PreparedStatement ps=con.prepareStatement("select * from donor where email=? and password=?");
		
		ps.setString(1, email);
		ps.setString(2,password);
		
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Id=rs.getInt(1);
			SetGet.setNgoId(Id);
			{
			response.sendRedirect("donorDashboard.html");

	    	}
	    
		
		{
		response.sendRedirect("DonorLogin.html");
		
		}
		}}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}}


