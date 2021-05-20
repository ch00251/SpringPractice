package test.users.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test.users.dto.UsersDto;
import test.util.DbcpBean;

public class UsersDao {
	private static UsersDao dao;
	private UsersDao() {}
	public static UsersDao getInstance() {
		if(dao==null) {
			dao=new UsersDao();
		}
		return dao;
	}
	//인자로 전달되는 아이디가 존재하는지 여부를 리턴하는 메소드
	public boolean isExist(String inputId) {
		boolean isExist=false; //아이디가 이미 존재 하는지 여부 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = "SELECT id FROM users"
					+ " WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩 
			pstmt.setString(1, inputId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				isExist=true; //이미 존재하는 아이디 임으로...
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				//connection pool 에 반납하기 
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return isExist; //아이디가 이미 존재하는지 여부를 리턴해준다.
	}

	//인자로 전달되는 UsersDto에 담긴 정보가 유효한 정보인지 여부를 리턴해주는 메소드
	public boolean isValid(UsersDto dto) {
		boolean isValid=false;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DbcpBean().getConn();
			String sql="select * from users where id=? and pwd=?";
			pstmt=conn.prepareStatement(sql);
			//?에 값 바인딩하기
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			rs=pstmt.executeQuery();
			while(rs.next()) {//select된 row가 있으면
				//아이디, 비밀번호가 일치함으로 isValid=true로 바꿔준다
				isValid=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {				
			}
		}
		return isValid;
	}
	
	//회원 가입 정보를 DB에 저장하는 메소드
	public boolean insert(UsersDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DbcpBean().getConn();
			String sql="insert into users (id,pwd,email,regdate) values(?,?,?,sysdate)";
			pstmt=conn.prepareStatement(sql);
			//?에 값 바인딩 하기
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getEmail());
			flag=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(flag>0) {
			return true;
		}else {
			return false;
		}
	}
}
