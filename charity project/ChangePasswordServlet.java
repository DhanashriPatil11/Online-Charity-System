package charityPackage;

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
 * Servlet implementation class ChangePasswordServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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
		String email=request.getParameter("email");
		String Cpassword=request.getParameter("Cpassword");
		String Npassword=request.getParameter("Npassword");
		String Cnfpassword=request.getParameter("Cnfpassword");
		 
		Connection con=DbConnection.connect();
		
		PreparedStatement pstmt;
		PreparedStatement pst1;
		try{
			pstmt=con.prepareStatement("select * from ngo where password=?");
			pstmt.setString(1,Cpassword );
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				String password=rs.getString(3);
				if(Cpassword.equals(password)){
					if(Npassword.equals(Cnfpassword)){
						pst1=con.prepareStatement("update ngo set password=? where email=?");
						pst1.setString(1, Npassword);
						pst1.setString(2, email);
						response.sendRedirect("NGODashboard.html");
						pst1.executeUpdate();
						System.out.println("password updated");
					}
				}else
				{
					response.sendRedirect("changePassword.html");
					System.out.println("password not updated");
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
