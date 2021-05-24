package test.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.cafe.dto.CafeDto;
import test.util.DbcpBean;

public class CafeDao {
	private static CafeDao dao;
	private CafeDao() {}
	public static CafeDao getInstance() {
		if(dao==null) {
			dao=new CafeDao();
		}
		return dao;
	}
	
	//글 목록을 리턴하는 메소드
	public List<CafeDto> getList(){
		List<CafeDto> list=new ArrayList<CafeDto>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=new DbcpBean().getConn();
			String sql="select num,writer,title,viewCount,regdate from board_cafe oreder by num desc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {//반복문 돌면서
				//select된 row의 정보를 CafeDto 객체에 담아서
				CafeDto dto=new CafeDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setViewCount(rs.getInt("viewCount"));
				dto.setRegdate(rs.getString("regdate"));
				//ArrayList객체에 누적 시킨다.
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {				
			}
		}
		return list;
	}
}
