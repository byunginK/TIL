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
	
	private static CustUserDao dao = new CustUserDao();		// 싱글톤 생성(데이터를 넘겨주기 위한)

	private CustUserDao() {		//생성자에 DB드라이브 로드 확인
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
	
	public List<CustUserDto> getCustUserList() {	//고객 리스트에 표시해줄 데이터를 DB에서 꺼내오기 위한 쿼리문
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
				
				list.add(new CustUserDto(id, name, address));	//가져온 데이터 리스트에 dto 형태로 넣기
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
	
	
	public Connection getConnection() throws SQLException {			//DB 연결
		String url = "jdbc:oracle:thin:@192.168.7.45:1521:xe";
		
		String user = "hr";
		String password = "hr";
		
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	
	public void close(Connection conn, PreparedStatement psmt, ResultSet rs) {	// DB 닫아주기
		
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
