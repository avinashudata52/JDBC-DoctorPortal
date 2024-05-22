package DoctorPortal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Doctor3
{
	int id;
	String docname;
	String username;
	String password;
	String spec;
	int exps;
	
	
	public Doctor3(int id, String docname, String username, String password, String spec, int exps) {
		super();
		this.id = id;
		this.docname = docname;
		this.username = username;
		this.password = password;
		this.spec = spec;
		this.exps = exps;
	}

	
	public void setId(int id) {
		this.id = id;
	}


	public void setDocname(String docname) {
		this.docname = docname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public void setExps(int exps) {
		this.exps = exps;
	}

	public int getDocId() {
		return id;
	}

	public String getDocname() {
		return docname;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSpec() {
		return spec;
	}

	public int getExps() {
		return exps;
	}

	@Override
	public String toString() {
		int docId=0;
		// TODO Auto-generated method stub
		return "Doctor [docId="+ docId +", docname="+docname+", username="+username+", "
						+"password="+password+",spec="+spec+",exps="+exps+"]";	
	}
	
	class DoctorService
	{
		public void registorDoctor(Doctor d) throws SQLException
		{
			int id = d.getDocId();
			String name = d.getDocname();
			String un = d.getUsername();
			String pw = d.getPassword();
			String spel = d.getSpec();
			int exp = d.getExps();
			
			Connection con = DBUtil.getConnect();
			final String INSERTQUERY = "insert into Doctor values(?,?,?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(INSERTQUERY);
			ps.setInt(1,id);
			ps.setString(2,name);
			ps.setString(3, un);
			ps.setString(4, pw);
			ps.setString(5, spel);
			ps.setInt(6, exp);
			
			int insertStatus = ps.executeUpdate();
			if(insertStatus>0)
			{
			System.out.println("Registered successfully");
			
			}
			else {
				System.out.println("Failed to register");
			}
			
		}
	}
	
	
}



class demo{
	public static void main(String rg[]) throws SQLException {
		
		DoctorService d1 = new DoctorService();
		Doctor3 h1 = new Doctor3(101,"Avinash","doc@143","pass@213","Heart",7);
		d1.registerDoctor(h1);
		
	}
}
