package co.kwangho.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.kwangho.board.vo.MemberVO;


public class MemberDao extends DAO {
	private PreparedStatement psmt; //sql명령어 작성시에 사용
	private ResultSet rs; //select 후 결과셋 받기
	private MemberVO vo;
	
	private final String SELECT_ALL = "SELECT * FROM MEMBER"; //변경 못하게 상수처리한다
	private final String SELECT = "SELECT * FROM MEMBER WHERE ID = ? AND PASSWORD = ? ";
	private final String INSERT = "INSERT INTO MEMBER(ID,NAME,PASSWORD,ADDRESS,TEL,ENTERDATE) VALUES(?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE MEMBER SET NAME = ?,PASSWORD = ?,ADDRESS = ?,TEL = ? WHERE ID = ? ";
	private final String DELETE = "DELETE FROM MEMBER WHERE ID = ? ";
	
	public List<MemberVO> selectAll(){//멤버리스트 전체를 가져온다
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			psmt = conn.prepareStatement(SELECT_ALL);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setAuthor(rs.getString("author"));
				
				list.add(vo);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return list;
	}
	public MemberVO select(MemberVO vo) {//한행을 찾을때
		try {
			psmt = conn.prepareStatement(SELECT);
			psmt.setString(1, vo.getId()); //물음표 처리
			psmt.setString(2, vo.getPassword());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setAuthor(rs.getString("author"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		return vo;
	}
	
	public int insert(MemberVO vo) {
		int n=0;
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1,vo.getId());
			psmt.setString(2,vo.getName());
			psmt.setString(3,vo.getPassword());
			psmt.setString(4,vo.getAddress());
			psmt.setString(5,vo.getTel());
			psmt.setDate(6,vo.getEnterdate());
			n= psmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return n;
	}
	
	public int delete(MemberVO vo) {
		int n=0;
		try {
			psmt = conn.prepareStatement(DELETE);
			psmt.setString(1,vo.getId());
			n= psmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return n;
	}
	public int update(MemberVO vo) {
		int n=0;
		
		try {
			psmt = conn.prepareStatement(UPDATE);
			psmt.setString(1,vo.getName());
			psmt.setString(2,vo.getPassword());
			psmt.setString(3,vo.getAddress());
			psmt.setString(4,vo.getTel());
			psmt.setString(5,vo.getId());
			
			n=psmt.executeUpdate();
			
		}catch(SQLException e) {
			
		}finally {
			close();
		}
		
		
		
		
		
		
		
		return n;
	}
	
	private void close() {
		try {
			if(rs != null) {
				rs.close();
				System.out.println("rs 종료");
			}
			if(psmt != null) {
				psmt.close();
				System.out.println("psmt 종료");
			}
			if(conn != null) {
				conn.close();
				System.out.println("conn 종료");
				System.out.println("--------------");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	
}
