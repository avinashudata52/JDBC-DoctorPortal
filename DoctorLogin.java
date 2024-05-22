package DoctorPortal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import jdbc_pack.connection;

public class DoctorLogin {

	private static final int SUCCESS = 0;
	private static final int INVALID_CREDINTIALS = 0;
	private static final int DATABASE_ERROR = 0;
	String username = "Avinash";
	String password = "Avi@123";
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Username");
		String username=sc.nextLine();
		System.out.println("Enter Password");
		String password=sc.nextLine();
		
		Connection con=null;
		try
		{ 
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC","root","root");
		//System.out.println("Connection Established");
		{
			String Query = "select * from Doctor_Table where username =? and Password = ?";
			PreparedStatement ps = connection.prepareStatement(Query);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			switch(getLoginStatus(rs))
			{
			case 1 SUCCESS:
			System.out.println("Login Successfully");
			break;
			
			case 2 INVALID_CREDINTIALS:
				System.out.println("Invalid username and password");
				break;
				
			case DATABASE_ERROR:
			{
				System.out.println("Error Accessing in database");
				break;
			}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		private static LoginStatus getLoginStatus(ResultSet rs) throws SQLException
		{
			if(resultSet.next())
			{
				return loginStatus.SUCCESS;
			}
			else {
				return loginStatus.INVALID_CREDINTIALS;
			}
		}
		loginStatus{
			SUCCESS,
			INVALID_CREDINTIALS,
			DATABASE_ERROR;
		}
		}
	}
	private static int getLoginStatus(ResultSet rs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
