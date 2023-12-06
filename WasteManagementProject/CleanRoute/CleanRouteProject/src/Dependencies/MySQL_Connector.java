package Dependies_Zeline;
import java.sql.Connection;
import java.sql.DriverManager;
public class MySQL_Connector {
	public static Connection getConnection() {
		Connection con=null;
		try {                                           
			con=DriverManager.getConnection("jdbc:mysql://localhost/DATA_BASE_NAME","root","");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String args[]) {
		Connection con=getConnection();
		if(con==null) {
			System.out.println("Not Connected!");
		}
		else
			System.out.println("***Connected!***");	
	}
}

//@Author: Zeline Project Services

