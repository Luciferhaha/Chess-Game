package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class select {
	 protected static ConnectExistedDatabase connectTest=new ConnectExistedDatabase();
	static String sql = "select * from cars where reg_No='111'";
	 static String sql2="select *from cars inner join rent_command as r using (reg_no) "
	 		+ "where reg_no!=All(select reg_no from sell_command) and r.rent_date!='2015-11-23';;";
	private static void getAll(String sql) {
	    Connection conn = connectTest.getConn();
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int col = rs.getMetaData().getColumnCount();
	        System.out.println("============================");
//	        System.out.print("Reg_No"+"\t"+"seatnumbers"+"\t"+"made_date\t"+"\t"+"Name"+"\t"
//	        +"Model"+"\t"+"RentPrice"+"\t"+"SellPrice"+"\t"+"Time_limit"+"\t"+"Rent_date"+"\t"
//	        		+"Rent_Price"+"\t"+"Mem_No"+"\t"+"Distance");
//	        System.out.println("");
	        while (rs.next()) {//traverse row line
	            for (int i = 1; i <= col; i++) {
	                System.out.print(rs.getString(i) + "\t");
	             }
	            System.out.println("");
	        }
	            System.out.println("============================");
	           if (rs!=null) {
				rs.close();
			}
	           if (conn!=null) {
				conn.close();
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	 
	}
	public static void main(String[] args) {
		getAll(sql);
		getAll(sql2);
	}
}
