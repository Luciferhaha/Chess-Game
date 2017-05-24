package Database;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class insert {
	String name;
	int passward;
	public insert(String name, int passward) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.passward=passward;
	}
	 ConnectExistedDatabase connectTest=new ConnectExistedDatabase();
	 public int insertMember() {
	    Connection connect = connectTest.getConn();
	    int i = 0;
	    String sql = "insert into REGISTRATION  (name,password) values(?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) connect.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setInt(2, passward);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        connect.close();
	        System.out.println("Insert successfully");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}

	public static void main(String[] args) {
	}
}
