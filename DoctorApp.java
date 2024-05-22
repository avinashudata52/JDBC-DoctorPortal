package DoctorPortal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JList;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import java.sql.ResultSet;

class Doctor
{
	private int docId;
	private String docName;
	private String username;
	private String password;
	private String sepcility;
	private int exp;
	public Doctor(int docId, String docName, String username, String password, String sepcility, int exp) {
		
		super();
		this.docId = docId;
		this.docName = docName;
		this.username = username;
		this.password = password;
		this.sepcility = sepcility;
		this.exp = exp;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSepcility(String sepcility) {
		this.sepcility = sepcility;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public  Doctor()
	{
		
	}
	
	public int getDocId() {
		return docId;
	}
	public String getDocName() {
		return docName;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getSepcility() {
		return sepcility;
	}
	public int getExp() {
		return exp;
	}
	@Override
	public String toString() {
		return "Doctor [docId=" + docId + ", docName=" + docName + ", username=" + username + ", password=" + password + ", sepcility="
				+ sepcility + ", exp=" + exp + "]";
	}
	
	
	
}
class DoctorService
{
		private static final String ListOfDoctors = null;
		public void registerDoctor(Doctor3 h1) throws SQLException
		{	int id= h1.getDocId();
			String name = h1.getDocName();
			String docUser = h1.getUsername();
			String pw = h1.getPassword();
			String spec = h1.getSepcility();
			int exp=h1.getExp();
			
			Connection con = DBUtil.getConnect();
			final String INSERT_QUERY 
			="insert into Doctor_Table values(?,?,?,?,?,?)";
		
			// creating prepared statement object
			PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
			ps.setInt(1,id);
			ps.setString(2, name);
			ps.setString(3, docUser);
			ps.setString(4, pw);
			ps.setString(5, spec);
			ps.setInt(6, exp);
//			int insertStatus = ps.executeUpdate();
//			if(insertStatus>0)
//			{
//				System.out.println("Doctor"+" "+name+" "+"registered....");
//			}
//			else
//			{
//				System.out.println("we can not able to registor you");
//			}
//			//System.out.println(id+ " "+name+" "+Docuser+" "+pw+" "+spec+" "+exp);
		}
		
		public int UpdatesDoctor(int docId, Doctor newDoctor)throws SQLException
		{
			String username = newDoctor.getUsername();
			String pw = newDoctor.getPassword();
			String spec = newDoctor.getSepcility();
			int exp = newDoctor.getExp();
			//System.out.println("username"+" "+"pw"+" "+"spec"+" "+"exp");
			//return exp;
			
		
	Connection con = DBUtil.getConnect();
	final String Update_query
	="update doctor_table set user_name=?,user_pass=?,doc_spac=?,doc_exp=? where doc_Id=?";
	PreparedStatement ps = con.prepareStatement(Update_query);
	ps.setString(1,username);
	ps.setString(2, pw);
	ps.setString(3, spec);
	ps.setInt(4, exp);
	ps.setInt(5, docId);
	int UpdateStatus = ps.executeUpdate();
	return UpdateStatus;
}

		public List<Doctor> fectchAllDoctorFromDB()
		{ 
		List<Doctor> listOfDoctors=new ArrayList();
		try
		{
			Connection con = DBUtil.getConnect();
			final String FETCH_QUERY="select * from Doctor_table";
			PreparedStatement ps = con.prepareStatement(FETCH_QUERY);
			ResultSet rs =ps.executeQuery();
			while(rs.next())
			{
				int doc_Id= rs.getInt(1);
				String name=rs.getString(2);
				String un=rs.getString(3);
				String pw=rs.getString(4);
				String spac=rs.getString(5);
				int exp=rs.getInt(6);
				
				int docId;
				Doctor docObj = new Doctor(doc_Id,name,un,pw,spac,exp);
				
				listOfDoctors.add(docObj);
			} 
			
		}
			catch(Exception e)
			{
				System.out.println("Not fetch the data"+e);
			}
			return listOfDoctors;
		}
		
		public Doctor getDoctorBYID(int docId)
		{
			try
			{
				// linking with BD
				Connection con = DBUtil.getConnect();
				String fetchQuery = "select * from Doctor_Table where doc_id=?";
				//String FETCH_QUERY = null;
				PreparedStatement ps = con.prepareStatement(fetchQuery);
				ps.setInt(1, docId);
				// result set is the cursor point
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					int doc_Id= rs.getInt(1);
					String name=rs.getString(2);
					String un=rs.getString(3);
					String pw=rs.getString(4);
					String spac=rs.getString(5);
					int exp=rs.getInt(6);
					
					Doctor d = new Doctor(doc_Id,name,un,pw,spac,exp);
					
					if(d!=null)
					{
						return d;
					}
					else
					{
						System.out.println("No records found");
					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			return null;
		}
		
		public int removeDoctor(int doc_id)
		{
			try
			{
				Connection con = DBUtil.getConnect();
				String DelQuery = "delete from Doctor_Table where doc_Id=?";
				PreparedStatement ps = con.prepareStatement(DelQuery);
				ps.setInt(1, doc_id);
				int delstatus = ps.executeUpdate();
				return delstatus;
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			return doc_id;
		}
		
		public void login(String un,String pw)
		{
			try
			{
				Connection con = DBUtil.getConnect();
				String Query = "select * from Doctor_Table where user_name=? and user_pass=?";
				PreparedStatement ps = con.prepareStatement(Query);
				ps.setString(1, un);
				ps.setString(2, pw);
				ResultSet rs = ps.executeQuery();
				//return Query;
				if(rs.next())
				{
					
					int doc_Id= rs.getInt(1);
					String name=rs.getString(2);
					String un1=rs.getString(3);
					String pw1=rs.getString(4);
					String spac=rs.getString(5);
					int exp=rs.getInt(6);
					
					//int docId;
					Doctor docObj = new Doctor(doc_Id,name,un1,pw1,spac,exp);
					System.out.println(docObj);
					System.out.println("Login successfully");
					
				}
				else
				{
					System.out.println("invalid credintial");
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
}

public class DoctorApp {

	public static void main(String[] args) throws SQLException {
		
		DoctorService service = new DoctorService();
		// im creating object of DoctorService
		//Doctor docObj = new Doctor(101,"Avinash","doc@143","pass@213","Heart",7);
		
		// now i am calling registerDoctor(Doctor Doc)
		//service.registerDoctor(docObj);
		
		
		//Update
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Doctor ID ot Update");
//		int doc_id = sc.nextInt();
//		System.out.println("Enter Doctor User Name");
//		String un=sc.next();
//		System.out.println("Enter Doctor Password");
//		String pw=sc.next();
//		System.out.println("Enter Doctor Specialization");
//		String spec=sc.next();
//		System.out.println("Enter Doctor Experience");
//		int newExp=sc.nextInt();
//
//		Doctor updatedocObj = new Doctor();
//		updatedocObj.setUsername(un);
//		updatedocObj.setPassword(pw);
//		updatedocObj.setSepcility(spec);
//		updatedocObj.setExp(newExp);
////	
//		try {
//		int status= service.UpdatesDoctor(doc_id, updatedocObj);
//		if (status>0)
//		{
//			System.out.println("Update Success.........");
//		}
//		else
//		{
//			System.out.println("Not updated...!");
//		}
//		}
//		catch(Exception e)
//		{
//			System.out.println(e);
//		}
//		
		
//		List<Doctor> list = service.fectchAllDoctorFromDB();
//		for(Doctor d:list)
//		{
//			System.out.println(d);
//		}
		
		//Remove 
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Please enter the Id");
//		int remove = sc.nextInt();
//		remove =service.removeDoctor(remove);
//		System.out.println("Id "+ remove + " are removed successfully");
		
		// Fetch
//		System.out.println("******fetching the details based on ID*******");
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter doctor is to search");
//		int id = sc.nextInt();
//		
//		Doctor doc =service.getDoctorBYID(id);
//		//service.getDoctorBYID(id);
//		System.out.println(doc);
		
		service.login("avi@13","pass@123");
	}

}

