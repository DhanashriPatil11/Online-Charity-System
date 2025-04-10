package charityPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterDonorServlet
 */
public class RegisterDonorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterDonorServlet() {
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
		try{
		int id=0;
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String pincode=request.getParameter("pincode");
		String contact=request.getParameter("contact");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		Connection con=DbConnection.connect();
		PreparedStatement pstmt = con.prepareStatement("insert  into donor values(?,?,?,?,?,?,?,?)");
		    pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, city);
			pstmt.setString(5, pincode);
			pstmt.setString(6, contact);
			pstmt.setString(7, email);
			pstmt.setString(8, password);
			int i=pstmt.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("DonorLogin.html");
			}
			else
			{
				response.sendRedirect("registerDonor.html");
			}
		}catch(Exception e){
			
		
		e.printStackTrace();
		}
		doGet(request, response);
	}

}
