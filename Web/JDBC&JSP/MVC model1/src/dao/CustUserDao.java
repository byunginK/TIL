package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CustUserDto;

// Dao
public class CustUserDao {
	
	private static CustUserDao dao = new CustUserDao();

	private CustUserDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Oracle Driver loading Success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static CustUserDao getInstance() {
		return dao;
	}
	
	public List<CustUserDto> getCustUserList() {
		String sql = " SELECT ID, NAME, ADDRESS "
					+ " FROM CUSTUSER "
					+ " ORDER BY ID DESC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<CustUserDto> list = new ArrayList<CustUserDto>();
		
		try {
			conn = this.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while(rs.next()) {	// list 값을 전부 가지고 오기 위해 while
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				
				list.add(new CustUserDto(id, name, address));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, psmt, rs);
		}
		
		return list;
	}
	
	public boolean addCustUser(CustUserDto dto) {
		String sql = " INSERT INTO CUSTUSER(ID, NAME, ADDRESS) "
					+" VALUES(?,?,?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getAddress());
			
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	
	
	
	
	
	
	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@192.168.7.45:1521:xe";
		
		String user = "hr";
		String password = "hr";
		
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	public void close(Connection conn, PreparedStatement psmt, ResultSet rs) {
		
			try {
				if(conn != null) {
					conn.close();
				}
				if(psmt != null) {
					psmt.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
