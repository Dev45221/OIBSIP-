package classes;

import java.sql.*;

public class Conn {
	
	Connection c;
	Statement s;
	Conn() {
		try {
			c = DriverManager.getConnection("jdbc:mysql:///atm","root","123456");
			s = c.createStatement();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
