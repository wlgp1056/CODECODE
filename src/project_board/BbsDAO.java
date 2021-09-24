package project_board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.comstudy21.db.JdbcUtill;

public class BbsDAO {
	private static BbsDAO bbsDao = new BbsDAO();
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;;
	
	private int result = 0;
	
	private BbsDAO() {
		super();
	}
	
	public static BbsDAO getInstance() {
		return bbsDao;		
	}
	
	public int getNext() {
		conn = JdbcUtill.getConnection();
		//현재 게시글을 내림차순으로 조회하여 가장 마지막 글의 번호를 구한다
		String sql = "select bbsid from bbs2 order by bbsid desc";
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
	
	// 글쓰기
		public int write(String bbscategory, String bbstitle, String bbscontent, String id, String b_fname) {
			String sql = "INSERT INTO bbs2 values (?, ?, ?, sysdate, 0, ?, ?, ?, ?)"; 
			conn = JdbcUtill.getConnection();
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext());
				pstmt.setString(2, bbstitle);
				pstmt.setString(3, bbscontent);
				pstmt.setString(4, bbscategory);
				pstmt.setString(5, id);
				pstmt.setInt(6, 1); //글의유효번호
				pstmt.setString(7, b_fname);
				result = pstmt.executeUpdate();
				if(result>0) {
					System.out.println("성공");
					conn.commit();
				}else {
					System.out.println("실패");
					conn.rollback();
				}
			} catch (SQLException e) {
				System.out.println("sql실패");
				e.printStackTrace();
			}finally {
				JdbcUtill.close(conn, pstmt, rs);
			}
			return result;
		}
		
		public List<BbsDTO> selectAll2(String keyField, String keyWord){
			conn = JdbcUtill.getConnection();
			List<BbsDTO> list = new ArrayList<BbsDTO>();
			
			try {
				if(keyField == null || keyField.equals("")) {
					String sql = "SELECT * FROM bbs2 ORDER BY bbsid DESC";
					pstmt = conn.prepareStatement(sql);
				}else {
					String sql = "select * from bbs2 where "+keyField+" like '%'||?||'%' order by bbsid desc";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, keyWord);
				}
				
				rs=pstmt.executeQuery();
				while(rs.next()) {
					BbsDTO dto = new BbsDTO();
					dto.setBbsid(rs.getInt(1));
					dto.setBbstitle(rs.getString(2));
					dto.setBbscontent(rs.getString(3));
					dto.setBbsdate(rs.getTimestamp(4));
					dto.setBbshit(rs.getInt(5));
					dto.setBbscategory(rs.getString(6));
					dto.setId(rs.getNString("id"));
					dto.setBbsAvailable(rs.getInt(8));
					dto.setB_fname(rs.getString(9));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtill.close(conn, pstmt, rs);
			}
					return list;
		}
		
		public int hitUpdate(int bbsId) {
			conn = JdbcUtill.getConnection();
			String sql = "update bbs2 set bbshit=bbshit+1 where bbsid=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bbsId);
				result = pstmt.executeUpdate();
				if(result>0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtill.close(conn, pstmt, rs);
			}
			return result;
		}
		
		public BbsDTO selectId(int bbsId) {
			BbsDTO dto = null;
			String sql = "select * from bbs2 where bbsid=?";
			conn = JdbcUtill.getConnection();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bbsId);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto = new BbsDTO();
					dto.setBbsid(rs.getInt(1));
					dto.setBbstitle(rs.getString(2));
					dto.setBbscontent(rs.getString(3));
					dto.setBbsdate(rs.getTimestamp(4));
					dto.setBbshit(rs.getInt(5));
					dto.setBbscategory(rs.getString(6));
					dto.setId(rs.getNString("id"));
					dto.setBbsAvailable(rs.getInt(8));
					dto.setB_fname(rs.getString(9));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtill.close(conn, pstmt, rs);
			}
			return dto;
		}
		
		// 삭제
		public int delete(int bbsId) {
			conn = JdbcUtill.getConnection();
			String sql = "delete from bbs2 where bbsid=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bbsId);
				result = pstmt.executeUpdate();
				if(result>0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtill.close(conn, pstmt, rs);
			}
			
			return result;
		}
		
		public int update(BbsDTO bbsDto) {
			conn = JdbcUtill.getConnection();
			String sql = "UPDATE bbs2 SET bbstitle=?,bbscontent=?,bbscategory=? WHERE bbsid=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bbsDto.getBbstitle());
				pstmt.setString(2, bbsDto.getBbscontent());
				pstmt.setString(3, bbsDto.getBbscategory());
				pstmt.setInt(4, bbsDto.getBbsid());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcUtill.close(conn, pstmt, rs);
			}
			return result;
		}
		
		public int update2(BbsDTO bbsDto) {
			conn = JdbcUtill.getConnection();
			String sql = "UPDATE bbs2 SET bbstitle=?,bbscontent=?,bbscategory=?, b_fname=? WHERE bbsid=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bbsDto.getBbstitle());
				pstmt.setString(2, bbsDto.getBbscontent());
				pstmt.setString(3, bbsDto.getBbscategory());
				pstmt.setString(4, bbsDto.getB_fname());
				pstmt.setInt(5, bbsDto.getBbsid());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcUtill.close(conn, pstmt, rs);
			}
			return result;
		}

}
