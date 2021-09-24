package project_comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.comstudy21.db.JdbcUtill;

public class CommentDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int result = 0;
	
	public int getNext() {
		conn = JdbcUtill.getConnection();
		//현재 게시글을 내림차순으로 조회하여 가장 마지막 글의 번호를 구한다
		String sql = "select commid from comm2 order by commid desc";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; //첫 번째 게시물인 경우
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	
	public int write(int bbsID, String id, String commtext) {
		conn = JdbcUtill.getConnection();
		String SQL = "INSERT INTO comm2 VALUES(?, ?, ?, sysdate, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setInt(2, bbsID);
			pstmt.setString(3, id);
			pstmt.setString(4, commtext);
			pstmt.setInt(5, 1);
			result = pstmt.executeUpdate();
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtill.close(conn, pstmt, rs);
			
		}
		return result; //데이터베이스 오류
	}
	
	public String getUpdateComment(int commid) {
		conn = JdbcUtill.getConnection();
		String SQL = "SELECT COMMTEXT FROM comm2 WHERE COMMID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, commid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //오류
	}
	
	public List<CommentDTO> selectAll(int bbsID){
		conn = JdbcUtill.getConnection();
		String SQL = "SELECT * FROM comm2 where bbsid=? ORDER BY COMMID DESC"; 
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCommid(rs.getInt(1));
				dto.setBbsid(rs.getInt(2));
				dto.setId(rs.getString(3));
				dto.setCommdate(rs.getTimestamp(4));
				dto.setCommtext(rs.getString(5));
				dto.setComAvailable(rs.getInt(6));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		return list; //데이터베이스 오류
	}
	
	public int update(int commid, String commtext) {
		conn = JdbcUtill.getConnection();
		String SQL = "UPDATE comm2 SET commtext = ? WHERE commid LIKE ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, commtext);
			pstmt.setInt(2, commid);
			result = pstmt.executeUpdate();
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		return result; // 데이터베이스 오류
	}
	
	public CommentDTO getComment(int commid) {
		conn = JdbcUtill.getConnection();
		String SQL = "SELECT * FROM comm2 WHERE commid = ? ORDER BY commid DESC";
		CommentDTO dto = null;
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  commid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new CommentDTO();
				dto.setCommid(rs.getInt(1));
				dto.setBbsid(rs.getInt(2));
				dto.setId(rs.getString(3));
				dto.setCommdate(rs.getTimestamp(4));
				dto.setCommtext(rs.getString(5));
				dto.setComAvailable(rs.getInt(6));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		return dto;
	}
	
	public int delete(int commid) {
		conn = JdbcUtill.getConnection();
		String SQL = "DELETE FROM comm2 WHERE commid = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, commid);
			result = pstmt.executeUpdate();
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtill.close(conn, pstmt, rs);
		}
		return result; // 데이터베이스 오류
	}
	
	
}
