package DoctorPortal;
	
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	public static Connection getConnect() {
	Connection con=null;
	try
	{ 
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC","root","root");
	System.out.println("Connection Established");
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	return con;
	}

	}

