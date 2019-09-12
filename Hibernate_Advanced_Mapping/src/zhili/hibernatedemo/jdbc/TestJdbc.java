package zhili.hibernatedemo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main (String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/advanced_mapping?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		
		try {
			System.out.println("Connecting to databse: " + jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
