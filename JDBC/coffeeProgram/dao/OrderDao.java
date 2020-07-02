package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBClose;
import db.DBconnection;

public class OrderDao {

	private static OrderDao dao = null;
	
	private OrderDao() {
	}
	
	public static OrderDao getInstance() {
		if(dao == null) {
			dao = new OrderDao();
		}
		return dao;
	}
	
	public int insertOrder(String id, String coffee, String c_size, String syrup, String other) {
		String sql = " INSERT INTO ORDERCOFFEE(ID,COFFEE,C_SIZE,SYRUP,OTHER) "
				+ "	VALUES(?,?,?,?,?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, coffee);
			psmt.setString(3, c_size);
			psmt.setString(4, syrup);
			psmt.setString(5, other);
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("쿼리문이 잘못되었습니다.");
		} finally {
			DBClose.dbClose(psmt, conn, null);
		}
		return count;
	}
}
