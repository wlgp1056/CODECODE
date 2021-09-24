package project_user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.comstudy21.db.JdbcUtill;

public class UserDAO {
	private static UserDAO uDao;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int result;
	
	private UserDAO() {
		
	}
	
	public static synchronized UserDAO getInstance() {
		if(uDao == null) {
			uDao = new UserDAO();
		}
		return uDao;
	}
	
	public int login(String userID, String password) {
		String sql = "select password from mem where id=?";
		conn = JdbcUtill.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("password").equals(password)) {
					return 1;
				} else
					return 0;
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		return -2;
	}
	
	public int join(UserDTO dto) {
		String sql = "insert into mem values(?, ?, ?, ?, ?)";
		conn = JdbcUtill.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getEmail());
			result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("입력성공");
				conn.commit();
			}
			else {
				System.out.println("입력실패");
				conn.rollback();
			}
		} catch (SQLException e) {
		}finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public String idfind(String name,String email) {
		String id=null;
		String sql = "select id from mem where name=? and email=?";
		conn = JdbcUtill.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getString("id"); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		return id; 
	}
	
	public String pwfind(String name,String id) {
		String password=null;
		String sql = "select password from mem where name=? and id=?";
		conn = JdbcUtill.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				password = rs.getString("password"); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		return password; 
	}
}
