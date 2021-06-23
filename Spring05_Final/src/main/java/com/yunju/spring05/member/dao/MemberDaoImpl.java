package com.yunju.spring05.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunju.spring05.member.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{

	//�ٽ� ���� ��ü�� spring���� ���� ���Թޱ�(Dependency Injection)
	@Autowired
	private SqlSession session;
	
	@Override
	public List<MemberDto> getList(){
		List<MemberDto> list=session.selectList("member.getList");
		return list;
	}
	@Override
	public void delete(int num) {
		session.delete("member.delete",num);
	}
	@Override
	public void insert(MemberDto dto) {
		session.insert("member.insert",dto);
	}
}
