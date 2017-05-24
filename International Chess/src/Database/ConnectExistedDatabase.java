package Database;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectExistedDatabase {
	public static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/JustForTest";
		String username = "root";
		String password = "new";
		Connection conn = null;
		try {
			Class.forName(driver); //classLoader
			conn = (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return conn;
	}
	public static void main(String[] args) {
	 getConn();
	}
	
}
